package Screens;

import Objects.World;

import com.APAAAEAIA.UltraGravity.MyGame;

public class LevelEditorScreen extends GenericScreen
{
  World world;

	public LevelEditorScreen(MyGame myGame) 
	{
		super(myGame);
	}

	public void render(float delta)
	{
	  System.out.println("Setting Array");
	  world.setWorld();
	  System.out.println("Array Set");
	  batch.begin();
	  
	  //batch.draw(texture,x,y)
	  batch.end();
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
