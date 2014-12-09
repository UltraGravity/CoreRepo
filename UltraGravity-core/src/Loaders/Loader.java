///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 Loader.java
//
//							Notes:
//Interface sets the screen Height and Width within the game.
///////////////////////////////////////////////////////////////////////////

package Loaders;

import com.badlogic.gdx.Gdx;

public interface Loader
{
	public int screenWidth = Gdx.graphics.getWidth();
	public int screenHeight = Gdx.graphics.getHeight();
	public void load();
	public void unload();	
}
