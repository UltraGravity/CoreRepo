package Screens;

import java.util.ArrayList;

import FileIO.LevelFile;
import Objects.Box;
import Objects.Item;
import Objects.ThePlane;
import Physics.Constants;
import Physics.Direction;
import Physics.WorldUtils;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SharedLibraryLoader;

public class GameScreen extends GenericScreen
{
	String levelString;

	World world;
	LevelEditorScreen levelEditor;
	ThePlane thePlane;

	Array<Body> worldArray = new Array<Body>();
	// WorldUtils worldUtils;

	private OrthographicCamera boxCam;
	private Box2DDebugRenderer renderer;

	private float accumulator = 0;

	private int zoomFactor = 16;

	public GameScreen(MyGame myGame, String levelString)
	{
		super(myGame);
		this.levelString = levelString;

	}

	private enum GameState {
		PLAY, PAUSE, GAMEOVER
	};

	public GameState gameState = GameState.PLAY;

	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		doPhysics(delta);
		renderer.render(world, boxCam.combined);
	}

	public void setupBoxCam()
	{
		boxCam = new OrthographicCamera(screenWidth, screenHeight);
		boxCam.setToOrtho(false, screenWidth / zoomFactor, screenHeight
				/ zoomFactor);
		boxCam.update();

	}

	private void doPhysics(float deltaTime)
	{
		world.getBodies(worldArray);
		for (Body b : worldArray)
		{
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
		if (body.getUserData() instanceof Sprite)
		{
			Sprite sprite = (Sprite) body.getUserData();

			sprite.setOriginCenter();
			sprite.setPosition((body.getPosition().x - Constants.OBJECT_SCALE)
					* (screenWidth / boxCam.viewportWidth),
					(body.getPosition().y - Constants.OBJECT_SCALE)
							* (screenHeight / boxCam.viewportHeight));
			sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
			sprite.setSize((2 * Constants.OBJECT_SCALE)
					* (screenWidth / boxCam.viewportWidth),
					(2 * Constants.OBJECT_SCALE)
							* (screenHeight / boxCam.viewportHeight));

			sprite.draw(batch);
		}
		batch.end();
	}

	public void resize(int width, int height)
	{

	}

	public void show()
	{
		System.out.println("beginning");
		System.out.println("Creating a new world!");
		WorldUtils worldUtils = new WorldUtils(myGame);
		world = worldUtils.createWorld();

		fillThePlane(LevelFile.LoadLevel(LevelFile.getLastLevelName()));
		System.out.println("Plane Filled");
		thePlane.fillWorld(world);
		renderer = new Box2DDebugRenderer();
		setupBoxCam();

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

	private void fillThePlane(String gridString)
	{
		// System.out.println("string: " + gridString);
		this.levelString = gridString;
		int i = 0;
		String xSizeString = "";
		String ySizeString = "";
		while (!String.valueOf(levelString.charAt(i)).equals(","))
		{
			xSizeString = xSizeString + String.valueOf(levelString.charAt(i));
			i++;
		}
		int x = Integer.parseInt(xSizeString);
		System.out.println(x);

		i = xSizeString.length() + 1;
		while (!String.valueOf(levelString.charAt(i)).equals(":"))
		{
			ySizeString = ySizeString + String.valueOf(levelString.charAt(i));
			i++;
		}
		int y = Integer.parseInt(ySizeString);
		System.out.println(y);
		thePlane = new ThePlane(myGame, x * Constants.GRID_TO_WORLD, y
				* Constants.GRID_TO_WORLD);
		// thePlane.setSize(x * 100, y * 100);
		System.out.println(thePlane.getXSize());
		System.out.println(thePlane.getYSize());

		i = xSizeString.length() + ySizeString.length() + 2;

		int xIndex = x;

		while (y > 0)
		{
			while (x > 0)
			{
				int nextInt = levelString.charAt(i);
				if (nextInt == '1')
				{
					thePlane.addItem(1, x * (Constants.GRID_TO_WORLD), y
							* (Constants.GRID_TO_WORLD));
				}
				if (nextInt == '2')
				{
					thePlane.addItem(2, x * (Constants.GRID_TO_WORLD), y
							* Constants.GRID_TO_WORLD);
				}
				if (nextInt == '3')
				{
					thePlane.addItem(3, x * (Constants.GRID_TO_WORLD), y
							* (Constants.GRID_TO_WORLD));
				}
				i++;
				x--;
			}
			x = xIndex;
			y--;
		}
	}

	// TODO use below to set the level based on the string from either a file or
	// the level editor

}
