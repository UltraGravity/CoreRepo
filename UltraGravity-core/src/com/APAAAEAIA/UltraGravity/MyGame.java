package com.APAAAEAIA.UltraGravity;

import FileIO.LevelFile;
import Loaders.AssetLoader;
import Screens.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game 
{	
	public LoadingScreen loadingScreen;
	public MainMenuScreen mainMenuScreen;
	public LevelScreen levelScreen;
	public LevelEditorScreen levelEditorScreen;
	public GameScreen gameScreen;
	public OptionsScreen optionsScreen;
	public PauseScreen pauseScreen;
	
	public Screen currentScreen;
	public Screen nextScreen;
	
	public AssetLoader assetLoader;
	
	public OrthographicCamera camera;
	public SpriteBatch batch;
	
	public int screenWidth;
	public int screenHeight;
	
	public boolean music = true;
	public boolean sfx = true;
	
	public void create()
	{
		/* This is the very first thing to run when the game is started.
		 * We create the global variables screenWidth and screenHeight.
		 * We create the SpriteBatch used to draw images to the screen.
		 * We create and set up the camera. The camera is the size of the screen.
		 * The AssetLoader is what handles all the loading and unloading of all
		 * the images in the entire game. We use this to get the textures and stuff.
		 */
		
		LevelFile levelFile = new LevelFile(this);
		levelFile.checkSettings();
		
		levelFile.SaveLevel("Level 1", "10,4:0000000003040000000300000000030000000003", "BuiltIn");
		levelFile.SaveLevel("Level 2", "10,4:1111001111111100111140002200030000220003", "BuiltIn");
		levelFile.SaveLevel("Level 3", "13,5:40100220000000010022000000001001100111100022110011110002211331111", "BuiltIn");
		levelFile.SaveLevel("Level 4", "10,5:00001111330000111100000002222211110111114000211111", "BuiltIn");
		levelFile.SaveLevel("Level 5", "14,7:11111111110000111111113122101111111101221011111111012210000000000022101111111111111040000000000000", "BuiltIn");
		levelFile.SaveLevel("Level 6", "10,5:40002200040011221100112200221122000000223311331133", "BuiltIn");
		
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, screenWidth, screenHeight);
		assetLoader = new AssetLoader(this);	
		
		mainMenuScreen = new MainMenuScreen(this);
		loadScreen(mainMenuScreen);
	}
	
	
	public void loadScreen(Screen screen)
	{
		/* When you need to load the assets for the main menu or
		 * the game screen, use this method.
		 */
		loadingScreen = new LoadingScreen(this);
		nextScreen = screen;
		
		if (screen == mainMenuScreen)
		{
			assetLoader.loadMenuAssets();
		}
		
		if (screen == gameScreen)
		{

		}
		
		this.setScreen(loadingScreen);
	}
	
	public void switchScreen()
	{
		camera.setToOrtho(false);
		if (nextScreen == mainMenuScreen)
		{
			// This sets up the textures so we can reference them.
			assetLoader.setupMenu();
		}
		
		this.setScreen(nextScreen);
	}
	
	public void changeToOptionsScreen()
	{
		optionsScreen = new OptionsScreen(this);
		this.setScreen(optionsScreen);
	}
	
	public void changeToMainMenuScreen()
	{
		mainMenuScreen = new MainMenuScreen(this);
		this.setScreen(mainMenuScreen);
	}
	
	public void changeToLevelEditorScreen()
	{
		levelEditorScreen = new LevelEditorScreen(this);
		this.setScreen(levelEditorScreen);
	}
	
	public void changeToLevelScreen()
	{
		levelScreen = new LevelScreen(this);
		this.setScreen(levelScreen);
	}

	public void changeToGameScreen(String levelString, String folder)
	{
		gameScreen = new GameScreen(this, levelString, folder);
		this.setScreen(gameScreen);
	}

	public void playMusic()
	{
		if (music)
		{
			assetLoader.music.play();
		}
	}
	
	public void playClick()
	{
		if (sfx)
		{
			assetLoader.click.play();
		}
	}
	
	public void nextLevel()
	{
		// TODO
		// make this select the next level
		changeToGameScreen("Level 1.txt", "BuiltIn");
	}
	
	public void dispose()
	{
		// Called when the game is closed for good.
		batch.dispose();
	}
}
