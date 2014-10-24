package Loaders;

import com.APAAAEAIA.UltraGravity.MyGame;

public class SyncGameLoader implements Runnable
{

	MyGame myGame;
	
	public SyncGameLoader(MyGame myGame)
	{
		this.myGame = myGame;
	}
	
	public void run() 
	{
		// Run something in the mygame class that loads all the necessary files.
		myGame.loadGameAssets();
	}
	
}
