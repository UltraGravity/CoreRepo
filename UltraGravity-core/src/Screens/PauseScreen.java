///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 Pause.java
//
//							Notes:
//Screen that appears when the game is paused.
///////////////////////////////////////////////////////////////////////////

package Screens;

import com.APAAAEAIA.UltraGravity.MyGame;

public class PauseScreen extends GenericScreen
{

	public PauseScreen(MyGame myGame) 
	{
		super(myGame);
	}

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
