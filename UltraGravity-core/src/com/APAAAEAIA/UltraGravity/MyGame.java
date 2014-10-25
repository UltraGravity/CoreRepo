package com.APAAAEAIA.UltraGravity;

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
	
	public void create()
	{
		/* This is the very first thing to run when the game is started.
		 * We create the global variables screenWidth and screenHeight.
		 * We create the SpriteBatch used to draw images to the screen.
		 * We create and set up the camera. The camera is the size of the screen.
		 * The AssetLoader is what handles all the loading and unloading of all
		 * the images in the entire game. We use this to get the textures and stuff.
		 */
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
			assetLoader.loadGameAssets();
		}
		
		this.setScreen(loadingScreen);
	}
	
	public void switchScreen()
	{
		if (nextScreen == mainMenuScreen)
		{
			// This sets up the textures so we can reference them.
			assetLoader.setupMenu();
		}
		
		this.setScreen(nextScreen);
	}
}
