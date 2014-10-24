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
	public final static int MAIN_MENU	 = 0;
	public final static int LEVEL_SELECT = 1;
	public final static int LEVEL_EDITOR = 2;
	public final static int OPTIONS 	 = 3;
	public final static int GAME_SCREEN  = 4;
	public final static int PAUSE_SCREEN = 5;
	
	public LoadingScreen loadingScreen;
	public MainMenuScreen mainMenuScreen;
	public LevelScreen levelScreen;
	public LevelEditorScreen levelEditorScreen;
	public GameScreen gameScreen;
	public OptionsScreen optionsScreen;
	public PauseScreen pauseScreen;
	
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
		 * Finally, we load the MAIN_MENU.
		 */
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, screenWidth, screenHeight);
		assetLoader = new AssetLoader(this);
		mainMenuScreen = new MainMenuScreen(this);
		
		
		// This is just for testing. Remove
		levelEditorScreen = new LevelEditorScreen(this);
		// This is just for testing. Remove
		
		loadScreen(MAIN_MENU);
	}
	
	
	public void loadScreen(int screen)
	{
		/* When you need to switch screens, use this method. It creates a
		 * new LoadingScreen screen and uses multithreading to load the assets
		 * in the background. This way, we can animate the screen while its loading.
		 */
		loadingScreen = new LoadingScreen(this);
		
		switch (screen)
		{
			case MAIN_MENU:
			{
				nextScreen = this.mainMenuScreen;
				assetLoader.loadMenuAssets();
			}
			case LEVEL_SELECT:
			{
				
			}
			case LEVEL_EDITOR:
			{
				
			}
			case OPTIONS:
			{
				
			}
			case GAME_SCREEN:
			{
				nextScreen = this.gameScreen;
				assetLoader.loadGameAssets();
			}
			case PAUSE_SCREEN:
			{
				
			}
		}
		this.setScreen(loadingScreen);
	}
	
	public void switchScreen()
	{
		this.setScreen(nextScreen);
	}
}
