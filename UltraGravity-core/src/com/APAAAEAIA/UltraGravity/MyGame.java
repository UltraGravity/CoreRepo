///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 MyGame.java
//
//							Notes:
//Control Class used to change screens while storing needed data during
//play. Keeps the assets alive and controls current screens
///////////////////////////////////////////////////////////////////////////

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
		levelFile.SaveLevel("Level 5", "14,7:11111111190000111111113922901111111109229099999999092290000000000022909999999999999040000000000000", "BuiltIn");
		levelFile.SaveLevel("Level 6", "10,5:40002200040011221100112200221122000000223311331133", "BuiltIn");
		levelFile.SaveLevel("Level 7", "12,7:001000100004075000101111805000101111111000101111000000101111000099901111000093901111", "BuiltIn");
		levelFile.SaveLevel("Level 8","14,10:11100111111111040008000000811111111111110018000000008100101111111101010013110011010100100000000101101111111111011008000000000111111110011111", "BuiltIn");
		levelFile.SaveLevel("Level 9","10,7:4055200009999999990966111119093999999909000000000900999999996611111111", "BuiltIn");
//		levelFile.SaveLevel("Level 10", 
		
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
		this.changeToLevelScreen();
	}
	
	public void dispose()
	{
		// Called when the game is closed for good.
		batch.dispose();
		camera = null;
		assetLoader.dispose();
	}
}
