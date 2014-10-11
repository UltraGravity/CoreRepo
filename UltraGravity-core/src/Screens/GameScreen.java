package Screens;

import Managers.ScreenManager;

import com.APAAAEAIA.UltraGravity.MyGame;

public class GameScreen extends GenericScreen
{

	public GameScreen(MyGame myGame, ScreenManager screenManager) 
	{
		super(myGame, screenManager);
	}


	private enum GameState {PLAY, PAUSE, GAMEOVER};
	public GameState gameState = GameState.PLAY;
	
	public void render(float delta) 
	{
		
	}

	public void resize(int width, int height) 
	{
		
	}

	public void show()
	{
		
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
		 * It is very important that everything created is disposed of properly when it is no longer needed.
		 * I find it best to explicitly set everything equal to null so the garbage collector knows it can
		 * remove the stuff from memory. 
		 * 
		 * Calling super.dispose() will get rid of the built in variables, but it is important that anything
		 * that is uniquely created in this class be disposed.
		 */
	
		super.dispose();
	}

}