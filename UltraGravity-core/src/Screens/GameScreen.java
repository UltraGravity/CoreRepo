package Screens;

import java.util.ArrayList;

import FileIO.LevelFile;
import Gesture.GameGestureDetector;
import Objects.Box;
import Objects.GridImage;
import Objects.Item;
import Objects.LevelButton;
import Objects.MainCharacter;
import Objects.ThePlane;
import Physics.Constants;
import Physics.Direction;
import Physics.WorldUtils;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends GenericScreen
{
	String levelName;
	Stage stage;
	ImageButton pauseButton;
	World world;
	LevelEditorScreen levelEditor;
	ThePlane thePlane;
	GridImage[] cell;
	LevelFile levelFile;
	Table table;

	ActorGestureListener listener;

	Array<Body> worldArray = new Array<Body>();

	private OrthographicCamera boxCam;
	private Box2DDebugRenderer renderer;

	private float accumulator = 0;

	private int zoomFactor = 16;

	private int levelWidth;
	private int levelHeight;

	private enum GameState {
		PLAY, PAUSE, GAMEOVER
	};

	public GameState gameState = GameState.PLAY;

	public GameScreen(MyGame myGame, String levelName)
	{
		super(myGame);
		this.levelName = levelName;
		levelFile = new LevelFile(myGame);
	}

	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		doPhysics(delta);
		renderer.render(world, boxCam.combined);

		stage.act();
		stage.draw();
	}

	public void setupBoxCam()
	{
		setGameDimensions();
		boxCam = new OrthographicCamera(screenWidth, screenHeight);
		boxCam.setToOrtho(false, screenWidth / zoomFactor, screenHeight
				/ zoomFactor);
		boxCam.position.set(levelWidth / 2f, levelHeight / 2f, 0);
		boxCam.update();
	}

	private void setGameDimensions()
	{
		levelHeight = thePlane.getYSize() * Constants.GRID_TO_WORLD;
		levelWidth = thePlane.getXSize() * Constants.GRID_TO_WORLD;

	}

	private void doPhysics(float deltaTime)
	{
		float acelx = Gdx.input.getAccelerometerX();
		float acely = Gdx.input.getAccelerometerY(); // Accelerometer

		world.getBodies(worldArray);
		for (Body b : worldArray)
		{
			Item i = (Item) b.getUserData();
			if (i instanceof MainCharacter)
			{
				System.out.println("Character Physics Active");	//Pushing
				b.setGravityScale(0);
				Vector2 force = new Vector2((acely * Constants.GRAVITY)
						+ b.getLinearVelocity().x, (-acelx * Constants.GRAVITY)
						+ b.getLinearVelocity().y);
				System.out.println(force.x + " , " + force.y);
				b.applyForceToCenter(force, true);
			}
			draw(b);
		}
		float frameTime = Math.min(deltaTime, 0.25f);
		accumulator += frameTime;
		while (accumulator >= Constants.TIME_STEP)
		{
			world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS,
					Constants.POSITION_ITERATIONS);
			accumulator -= Constants.TIME_STEP;
		}

	}

	private void draw(Body body)
	{
		batch.begin();
		if (body.getUserData() instanceof Item)
		{
			Sprite sprite = ((Item) body.getUserData()).getSprite();

			sprite.setOriginCenter();
			// sprite.setPosition((body.getPosition().x * (screenWidth /
			// boxCam.viewportWidth)) + (levelWidth), body.getPosition().y);

			// sprite.setPosition((body.getPosition().x -
			// Constants.OBJECT_SCALE)
			// * (screenWidth / boxCam.viewportWidth),
			// (body.getPosition().y - Constants.OBJECT_SCALE)
			// * (screenHeight / boxCam.viewportHeight));
			sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
			sprite.setSize((2 * Constants.OBJECT_SCALE)
					* (screenWidth / boxCam.viewportWidth),
					(2 * Constants.OBJECT_SCALE)
							* (screenHeight / boxCam.viewportHeight));

			sprite.draw(batch);
		}
		batch.end();
	}

	public void show()
	{
		Viewport view = new ScreenViewport();

		stage = new Stage(view, batch);
		pauseButton = new ImageButton(myGame.assetLoader.pauseButtonStyle);
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		// table.setFillParent(true);
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

				System.out.println(velocityX + ", " + velocityY);

				wakeAllItems();

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

		};

		stage.addListener(listener);

		stage.addActor(table);

		pauseButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				// ADD STUFF TO THIS
				myGame.changeToMainMenuScreen();
			}
		});

		System.out.println("beginning");
		System.out.println("Creating a new world!");
		WorldUtils worldUtils = new WorldUtils(myGame);
		world = worldUtils.createWorld();

		thePlane = new ThePlane(myGame, 0, 0);
		fillThePlane(levelName);

		System.out.println("Plane Filled");
		thePlane.fillWorld(world);
		renderer = new Box2DDebugRenderer();
		setupBoxCam();
	}

	protected void wakeAllItems()
	{
		System.out.println("Waking items");
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

		cell = levelFile.getLevelGrid(levelName, thePlane);
		cell = levelFile.addGroundBoarder(cell, thePlane);

		int y = 0;
		int x = 0;
		int xIndex = x;
		int i = 0;

		while (y < thePlane.getYSize())
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
				i++;
				x++;
			}
			x = xIndex;
			y++;
		}
	}

	// TODO use below to set the level based on the string from either a file or
	// the level editor

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
