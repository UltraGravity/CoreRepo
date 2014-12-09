///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 GenericScreen.java
//
//							Notes:
//Creates a generic screen used to carry constants between common screens.
///////////////////////////////////////////////////////////////////////////

package Screens;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GenericScreen implements Screen 
{

	public MyGame myGame;
	public int screenWidth;
	public int screenHeight;
	public SpriteBatch batch;
	public OrthographicCamera camera;
	
	public GenericScreen(MyGame myGame)
	{
		this.myGame = myGame;
		camera = myGame.camera;
		batch = myGame.batch;
		screenWidth = myGame.screenWidth;
		screenHeight = myGame.screenHeight;
	}
	
	public void render(float delta) {}

	public void resize(int width, int height) {}

	public void show() {}

	public void hide() {}

	public void pause() {}

	public void resume() {}

	public void dispose() 
	{
		myGame = null;
		screenWidth = -1;
		screenHeight = -1;
		camera = null;
		batch = null;
	}

}
