package Screens;

import Managers.ScreenManager;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GenericScreen implements Screen 
{

	public MyGame myGame;
	public ScreenManager screenManager;
	public int screenWidth = Gdx.graphics.getWidth();
	public int screenHeight = Gdx.graphics.getHeight();
	public OrthographicCamera camera;
	public SpriteBatch batch;
	
	public GenericScreen(MyGame myGame, ScreenManager screenManager)
	{
		this.myGame = myGame;
		this.screenManager = screenManager;
		camera = new OrthographicCamera();
		camera.setToOrtho(true, screenWidth, screenHeight);
		batch = new SpriteBatch();
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
		batch.dispose();
		batch = null;
	}

}
