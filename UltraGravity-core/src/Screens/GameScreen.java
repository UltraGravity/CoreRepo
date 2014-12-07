package Screens;

import Dialog.PauseDialog;
import Dialog.WinDialog;
import FileIO.LevelFile;
import Objects.Buzzsaw;
import Objects.GridImage;
import Objects.Item;
import Objects.MainCharacter;
import Objects.SafeZone;
import Objects.SpikedBox;
import Objects.ThePlane;
import Physics.Constants;
import Physics.Direction;
import Physics.WorldUtils;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends GenericScreen
{
	public String levelName;
	Stage stage;
	ImageButton pauseButton;
	World world;
	LevelEditorScreen levelEditor;
	ThePlane thePlane;
	GridImage[] cell;
	LevelFile levelFile;
	Table table;
	public String folder;
	ActorGestureListener listener;

	ScrollPane levelScrollPane;

	float lastX = -1;
	float lastY = -1;

	int oldFingerX;
	int oldFingerY;
	float oldFingerDist;
	boolean twoFingersActive = false;
	int twoFingerActiveCounter;

	float oldCamX;
	float oldCamY;
	float oldCamZoom;

	Array<Body> worldArray = new Array<Body>();

	private OrthographicCamera boxCam;
	private Box2DDebugRenderer renderer;

	private float accumulator = 0;

	private int zoomFactor = 16;

	private int levelWidth;
	private int levelHeight;

	private enum GameState {
		PLAY, PAUSE, WIN, LOSE
	};

	public GameState gameState = GameState.PLAY;

	public GameScreen(MyGame myGame, String levelName, String folder)
	{
		super(myGame);
		this.levelName = levelName;
		this.folder = folder;
		levelFile = new LevelFile(myGame);
	}

	public void render(float delta)
	{
		Gdx.gl.glClearColor(.2f, .2f, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (gameState != GameState.WIN && gameState != GameState.LOSE)
		{
			doPhysics(delta);
			renderer.render(world, boxCam.combined);

			// Handles 2 finger touches for scrolling the camera
			if (Gdx.input.isTouched(1))
			{
				int X1 = Gdx.input.getX(0);
				int Y1 = Gdx.input.getY(0);
				int X2 = Gdx.input.getX(1);
				int Y2 = Gdx.input.getY(1);
				panCamera(X1, Y1, X2, Y2);
				twoFingersActive = true;
				twoFingerActiveCounter = 15;
			}
			else
			{
				oldFingerX = 0;
				oldFingerY = 0;
				oldFingerDist = 0;

				twoFingerActiveCounter--;
				if (twoFingerActiveCounter <= 0)
				{
					twoFingersActive = false;
				}
			}
		}

		stage.act();
		stage.draw();

	}

	public void setupBoxCam()
	{
		setGameDimensions();
		// boxCam = new OrthographicCamera(screenWidth, screenHeight);
		boxCam = new OrthographicCamera(levelWidth, levelHeight);
		// boxCam.setToOrtho(false, screenWidth / zoomFactor, screenHeight /
		// zoomFactor);
		boxCam.setToOrtho(false, stage.getViewport().getWorldWidth()
				/ zoomFactor, stage.getViewport().getWorldHeight() / zoomFactor);
		boxCam.position.set(levelWidth / 2, levelHeight / 2, 0);
		oldCamX = boxCam.position.x;
		oldCamY = boxCam.position.y;
		oldCamZoom = boxCam.zoom;
		boxCam.update();
	}

	private void setGameDimensions()
	{
		levelHeight = thePlane.getYSize() * Constants.GRID_TO_WORLD;
		levelWidth = thePlane.getXSize() * Constants.GRID_TO_WORLD;

	}

	private void doPhysics(float deltaTime)
	{
		int numCharacters = 0;
		float acelx;
		float acely;
		if (gameState == GameState.PLAY)
		{
			acelx = Gdx.input.getAccelerometerX();
			acely = Gdx.input.getAccelerometerY(); // Accelerometer
		}
		else
		{
			acelx = 0;
			acely = 0;
		}

		world.getBodies(worldArray);
		for (Body b : worldArray)
		{

			Item i = (Item) b.getUserData();
			if (i instanceof MainCharacter)
			{
				// System.out.println("Character Physics Active");
				b.setGravityScale(0);
				Vector2 force = new Vector2((acely * Constants.GRAVITY)
						+ b.getLinearVelocity().x, (-acelx * Constants.GRAVITY)
						+ b.getLinearVelocity().y);
				// System.out.println(force.x + " , " + force.y);
				b.applyForceToCenter(force, true);
				numCharacters++;
			}

			draw(b);
		}
		int safeCount = 0;
		int numContacts = world.getContactCount();
		if (numContacts > 0)
		{
			for (int i = 0; i < world.getContactList().size; i++)
			{
				Contact contact = world.getContactList().get(i);
				Fixture fixA = contact.getFixtureA();
				Fixture fixB = contact.getFixtureB();
				if (fixA.getBody().getUserData() instanceof MainCharacter
						&& fixB.getBody().getUserData() instanceof SafeZone
						|| fixA.getBody().getUserData() instanceof SafeZone
						&& fixB.getBody().getUserData() instanceof MainCharacter)
				{
					safeCount++;
				}

				if (fixA.getBody().getUserData() instanceof MainCharacter
						&& fixB.getBody().getUserData() instanceof Buzzsaw
						|| fixA.getBody().getUserData() instanceof Buzzsaw
						&& fixB.getBody().getUserData() instanceof MainCharacter
						|| fixA.getBody().getUserData() instanceof MainCharacter
						&& fixB.getBody().getUserData() instanceof SpikedBox
						|| fixA.getBody().getUserData() instanceof SpikedBox
						&& fixB.getBody().getUserData() instanceof MainCharacter)
				{
					kill();
				}
			}
			if (safeCount == numCharacters)
			{
				System.out.println("Winner!!!!");
				gameState = GameState.WIN;
				winLevel();
			}
		}
		if (gameState == GameState.PLAY)
		{
			float frameTime = Math.min(deltaTime, 0.25f);
			accumulator += frameTime;
			while (accumulator >= Constants.TIME_STEP)
			{
				world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS,
						Constants.POSITION_ITERATIONS);
				accumulator -= Constants.TIME_STEP;
			}
		}

	}

	private void kill()
	{
		try
		{
			Thread.sleep(3000);
			myGame.changeToGameScreen(myGame.gameScreen.levelName,
					myGame.gameScreen.folder);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void winLevel()
	{
		WinDialog winDialog = new WinDialog(myGame, "",
				myGame.assetLoader.uiSkin);
		winDialog.show(stage);
	}

	private void draw(Body body)
	{

		// System.out.println(boxCam.zoom);

		batch.begin();
		if (body.getUserData() instanceof Item)
		{
			Sprite sprite = ((Item) body.getUserData()).getSprite();

			sprite.setOriginCenter();

			float scaleX = screenWidth / boxCam.viewportWidth;
			float scaleY = screenHeight / boxCam.viewportHeight;

			// sprite.setPosition(
			// ((boxCam.position.x) + ((levelWidth / 2) *
			// Constants.GRID_TO_WORLD) + (body.getPosition().x +
			// Constants.OBJECT_SCALE) * scaleX),
			// ((boxCam.position.y) + ((levelHeight / 2) *
			// Constants.GRID_TO_WORLD) + (body.getPosition().y) * scaleY));

			sprite.setSize((2 * Constants.OBJECT_SCALE)
					* (screenWidth / boxCam.viewportWidth) / boxCam.zoom,
					(2 * Constants.OBJECT_SCALE)
							* (screenHeight / boxCam.viewportHeight)
							/ boxCam.zoom);

			// This works more or less
			// sprite.setPosition(
			// screenWidth/2 + body.getPosition().x * scaleX +
			// boxCam.view.getScaleX()/2*scaleX -
			// boxCam.position.x/boxCam.viewportWidth*screenWidth,
			// screenHeight/2 + body.getPosition().y * scaleY +
			// boxCam.view.getScaleY()/2*scaleY -
			// boxCam.position.y/boxCam.viewportHeight*screenHeight);

			sprite.setPosition(screenWidth / 2 + body.getPosition().x * scaleX
					+ boxCam.view.getScaleX() / 2 * scaleX - boxCam.position.x
					/ boxCam.viewportWidth * screenWidth, screenHeight / 2
					+ body.getPosition().y * scaleY + boxCam.view.getScaleY()
					/ 2 * scaleY - boxCam.position.y / boxCam.viewportHeight
					* screenHeight);

			float viewX = boxCam.position.x;
			float viewY = boxCam.position.y;

			sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);

			// sprite.setSize((2 * Constants.OBJECT_SCALE)
			// * (screenWidth / boxCam.viewportWidth),
			// (2 * Constants.OBJECT_SCALE)
			// * (screenHeight / boxCam.viewportHeight));

			sprite.draw(batch);
		}
		batch.end();
	}

	public void show()
	{
		Viewport view = new ScreenViewport();
		stage = new Stage(view, batch);
		table = new Table();

		pauseButton = new ImageButton(myGame.assetLoader.pauseButtonStyle);
		Gdx.input.setInputProcessor(stage);

		table.setSize(screenWidth / 25, screenWidth / 25);
		table.setX(screenWidth - screenWidth / 25);
		table.setY(screenHeight - screenWidth / 25 - 5);
		table.add(pauseButton).top().right().size((int) screenWidth / 25);

		listener = new ActorGestureListener()
		{
			@Override
			public void fling(InputEvent event, float velocityX,
					float velocityY, int button)
			{
				wakeAllItems();

				if (!twoFingersActive && gameState == GameState.PLAY)
				{
					if (Math.abs(velocityX) > Math.abs(velocityY))
					{

						if (velocityX > 0)
						{

							world.setGravity(Direction.RIGHT);
							System.out.println("Gravity Right");
						}
						else
						{
							world.setGravity(Direction.LEFT);
							System.out.println("Gravity LEFT");
						}
					}
					else
					{
						if (velocityY > 0)
						{
							world.setGravity(Direction.UP);
							System.out.println("Gravity UP");
						}
						else
						{
							world.setGravity(Direction.DOWN);
							System.out.println("Gravity DOWN");
						}
					}
				}
			}

		};

		stage.addListener(listener);
		stage.addActor(table);

		pauseButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				pauseGame();
				PauseDialog pauseDialog = new PauseDialog(myGame, "",
						myGame.assetLoader.uiSkin);
				pauseDialog.show(stage);
			}
		});

		System.out.println("beginning");
		System.out.println("Creating a new world!");
		WorldUtils worldUtils = new WorldUtils(myGame);
		world = worldUtils.createWorld();

		// createCollisionListener();

		thePlane = new ThePlane(myGame, 0, 0);
		fillThePlane(levelName);

		System.out.println("Plane Filled");
		thePlane.fillWorld(world);
		renderer = new Box2DDebugRenderer();
		setupBoxCam();
	}

	protected void wakeAllItems()
	{
		// System.out.println("Waking items");
		for (Body b : worldArray)
		{
			b.setAwake(true);
		}
	}

	private void fillThePlane(String levelName)
	{
		/*
		 * Loads a level from a file using LevelFile. That returns a
		 * GridImage[]. Then it adds a boarder of ground blocks around the
		 * level.
		 */

		cell = levelFile.getLevelGrid(levelName, thePlane, folder);
		cell = levelFile.addGroundBoarder(cell, thePlane);

		int y = thePlane.getYSize();
		int x = 0;
		int xIndex = x;
		int i = 0;

		while (y > 0)
		{
			while (x < thePlane.getXSize())
			{
				int nextInt = cell[i].cellValue;
				if (nextInt == 1) // Ground
				{
					thePlane.addItem(1, x * (Constants.GRID_TO_WORLD), y
							* (Constants.GRID_TO_WORLD));
				}
				else if (nextInt == 2) // Crate
				{
					thePlane.addItem(2, x * (Constants.GRID_TO_WORLD), y
							* Constants.GRID_TO_WORLD);
				}
				else if (nextInt == 3) // Safe Zone
				{
					thePlane.addItem(3, x * (Constants.GRID_TO_WORLD), y
							* (Constants.GRID_TO_WORLD));
				}
				else if (nextInt == 4) // Character
				{
					thePlane.addItem(4, x * (Constants.GRID_TO_WORLD), y
							* (Constants.GRID_TO_WORLD));
				}
				else if (nextInt == 5) // MetalBox
				{
					thePlane.addItem(5, x * (Constants.GRID_TO_WORLD), y
							* (Constants.GRID_TO_WORLD));
				}
				else if (nextInt == 6) // SpikedBox
				{
					thePlane.addItem(6, x * (Constants.GRID_TO_WORLD), y
							* (Constants.GRID_TO_WORLD));
				}
				else if (nextInt == 7) // Buzzsaw
				{
					thePlane.addItem(7, x * (Constants.GRID_TO_WORLD), y
							* (Constants.GRID_TO_WORLD));
				}
				else if (nextInt == 8) // Tire
				{
					thePlane.addItem(8, x * (Constants.GRID_TO_WORLD), y
							* (Constants.GRID_TO_WORLD));
				}
				else if (nextInt == 9) // IceBlock
				{
					thePlane.addItem(9, x * (Constants.GRID_TO_WORLD), y
							* (Constants.GRID_TO_WORLD));
				}
				i++;
				x++;
			}
			x = xIndex;
			y--;
		}
	}

	// TODO use below to set the level based on the string from either a file or
	// the level editor

	public void panCamera(int x1, int y1, int x2, int y2)
	{
		int centerX = (x1 + x2) / 2; // center between fingers
		int centerY = (y1 + y2) / 2;

		int distX = Math.abs(x1 - x2);
		int distY = Math.abs(y1 - y2);
		float fingerDist = (float) Math.sqrt(distX * distX + distY * distY);
		float changeDist = Math.abs(fingerDist - oldFingerDist);

		if (oldFingerX == 0 || oldFingerY == 0 || oldFingerDist == 0)
		{
			oldFingerX = centerX;
			oldFingerY = centerY;
			oldFingerDist = fingerDist;
		}
		else
		{
			if (changeDist > 10)
			{
				if (oldFingerDist > fingerDist)
				{
					boxCam.zoom += (oldFingerDist / fingerDist) * 0.05f;
				}
				else if (oldFingerDist < fingerDist)
				{
					boxCam.zoom -= (oldFingerDist / fingerDist) * 0.05f;
				}
			}

			if (boxCam.zoom > 6f)
			{
				boxCam.zoom = 6f;
			}
			if (boxCam.zoom < 0.3f)
			{
				boxCam.zoom = 0.3f;
			}

			int changeX = (oldFingerX - centerX) * 2;
			int changeY = (oldFingerY - centerY) * 2;

			boxCam.position.x = oldCamX + (changeX / boxCam.viewportWidth);
			boxCam.position.y = oldCamY - (changeY / boxCam.viewportHeight);

			oldFingerX = centerX;
			oldFingerY = centerY;
			oldFingerDist = fingerDist;
			oldCamX = boxCam.position.x;
			oldCamY = boxCam.position.y;
			oldCamZoom = boxCam.zoom;
		}
		boxCam.update();
	}

	public void pauseGame()
	{
		this.gameState = GameState.PAUSE;
	}

	public void resumeGame()
	{
		this.gameState = GameState.PLAY;
	}

	public void hide()
	{
	}

	public void pause()
	{
	}

	public void resume()
	{
	}

	public void resize(int width, int height)
	{
	}

	public void dispose()
	{
		/*
		 * It is very important that everything created is disposed of properly
		 * when it is no longer needed. I find it best to explicitly set
		 * everything equal to null so the garbage collector knows it can remove
		 * the stuff from memory.
		 * 
		 * Calling super.dispose() will get rid of the built in variables, but
		 * it is important that anything that is uniquely created in this class
		 * be disposed.
		 */

		super.dispose();
	}
}
