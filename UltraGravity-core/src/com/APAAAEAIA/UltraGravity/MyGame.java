package com.APAAAEAIA.UltraGravity;

import Managers.ScreenManager;
import com.badlogic.gdx.Game;

public class MyGame extends Game 
{
	
	ScreenManager screenManager;
	
	public void create() 
	{
		screenManager = new ScreenManager();
		screenManager.createMainMenu(this);
	}
	
}
