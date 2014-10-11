package Loaders;

import com.badlogic.gdx.Gdx;

public interface Loader 
{

	public int screenWidth = Gdx.graphics.getWidth();
	public int screenHeight = Gdx.graphics.getHeight();
	public void load();
	public void unload();
	
}
