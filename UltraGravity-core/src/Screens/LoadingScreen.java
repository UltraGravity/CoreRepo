package Screens;

import Managers.ScreenManager;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class LoadingScreen extends GenericScreen
{

	float stateTime = 0;
	
	public LoadingScreen(MyGame myGame, ScreenManager screenManager) 
	{
		super(myGame, screenManager);
	}

	public void render(float delta) 
	{
		stateTime += delta;	
		//Gdx.gl.glClearColor(.3f, .5f, .9f, 0);
		Gdx.gl.glClearColor(.3f, .6f, .8f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);			
		camera.update();									
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
			screenManager.loadingScreenManager.drawLogo(batch);
			screenManager.loadingScreenManager.drawLoadAnimation(batch, stateTime);
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
