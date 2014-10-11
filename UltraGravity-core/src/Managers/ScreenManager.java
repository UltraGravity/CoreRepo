package Managers;

import com.APAAAEAIA.UltraGravity.MyGame;

import Loaders.LoadingScreenManager;
import Screens.GameScreen;
import Screens.LevelEditorScreen;
import Screens.LevelScreen;
import Screens.LoadingScreen;
import Screens.MainMenuScreen;
import Screens.OptionsScreen;
import Screens.PauseScreen;

public class ScreenManager 
{

	MyGame myGame;
	LoadingScreen loadingScreen;
	MainMenuScreen mainMenuScreen;
	LevelScreen levelScreen;
	LevelEditorScreen levelEditorScreen;
	OptionsScreen optionsScreen;
	GameScreen gameScreen;
	PauseScreen pauseScreen;
	
	public LoadingScreenManager loadingScreenManager;
	
	public void createMainMenu(MyGame myGame)
	{
		// Create loading screen
		// Set current screen equal to loading screen
		// Have loading screen load main menu assets and display animation
		// Have loading screen set up main menu while displaying animation (with threading)
		// Fade from loading screen into main menu screen
		// Delete loading screen
		// Activate main menu screen
		
		this.myGame = myGame;
		
		loadingScreenManager = new LoadingScreenManager(this);
		loadingScreenManager.load();
		myGame.setScreen(loadingScreen);
		
		
		
		
	}
	
	
	public void makeLoadingScreen()
	{
		loadingScreen = new LoadingScreen(myGame, this);
	}
	
}
