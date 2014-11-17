package Screens;

import FileIO.LevelFile;
import Loaders.GameAssets;
import Objects.GridImage;
import Objects.Item;
import Objects.ThePlane;
import Physics.Direction;
import Physics.WorldUtils;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends GenericScreen
{
	String levelString;

	World world;
	LevelEditorScreen levelEditor;
	ThePlane thePlane;
	// WorldUtils worldUtils;

	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;

	private static final int VIEWPORT_WIDTH = 20;
	private static final int VIEWPORT_HEIGHT = 13;

	private final float TIME_STEP = 1 / 300f;
	private float accumulator = 0f;

	public GameScreen(MyGame myGame, String levelString)
	{
		super(myGame);
		// this.levelString = levelString;

	}

	private void fillWorld()
	{

	}

	private void setupCamera()
	{
		camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		camera.position.set(camera.viewportWidth / 2,
				camera.viewportHeight / 2, 0f);
		camera.update();
	}

	private enum GameState {
		PLAY, PAUSE, GAMEOVER
	};

	public GameState gameState = GameState.PLAY;

	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, .25f, .25f, 1);
		Gdx.gl.glClearColor(.65f, .65f, .65f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		accumulator += delta;

		while (accumulator >= delta)
		{
			world.step(TIME_STEP, 6, 2);
			accumulator -= TIME_STEP;
		}

		renderer.render(world, camera.combined);
	}

	public void resize(int width, int height)
	{

	}

	public void show()
	{
		System.out.println("beginning");
		fillThePlane(LevelFile.LoadLevel(LevelFile.getLastLevelName()));
		// fillWorld(levelString);
		System.out.println("Plane Filled");
		// world = worldUtils.createWorld();
		// thePlane.fillWorld(world);
		System.out.println("Creating a new world!");
		// world = new World(Direction.DOWN, true);
		WorldUtils worldUtils = new WorldUtils();
		world = worldUtils.createWorld(); // Why is this shit null

		thePlane.fillWorld(world);
		renderer = new Box2DDebugRenderer();
		setupCamera();

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
		thePlane = new ThePlane(x * 100, y * 100);
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
					thePlane.addItem(1, x * 100, y * 100);
				}
				if (nextInt == '2')
				{
					thePlane.addItem(2, x * 100, y * 100);
				}
				if (nextInt == '3')
				{
					thePlane.addItem(3, x * 100, y * 100);
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
