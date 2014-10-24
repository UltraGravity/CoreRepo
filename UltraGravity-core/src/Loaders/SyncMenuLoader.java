package Loaders;

import com.APAAAEAIA.UltraGravity.MyGame;

public class SyncMenuLoader implements Runnable
{

	MyGame myGame;
	
	public SyncMenuLoader(MyGame myGame)
	{
		this.myGame = myGame;
	}
	
	public void run() 
	{
		// Run something in the mygame class that loads all the necessary files.
		myGame.loadMainMenuAssets();
	}
	
}
