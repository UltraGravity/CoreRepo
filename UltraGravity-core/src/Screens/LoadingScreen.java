package Screens;

import Loaders.*;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class LoadingScreen extends GenericScreen
{
	LoadingScreenFiles loadingScreenFiles;
	float stateTime = 0;
	float color = 0;
	double colorD = 0;
	int screenToLoad = -1;
	Screen newScreen;
	
	Runnable fileLoader;
	Thread fileThread;
	
	public LoadingScreen(MyGame myGame, int screenToLoad) 
	{
		super(myGame);
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		loadingScreenFiles = new LoadingScreenFiles();
		this.screenToLoad = screenToLoad;
		
		switch (screenToLoad)
		{
			case 0:
			{
				// Main Menu
				fileLoader = new SyncMenuLoader(myGame);
				newScreen = myGame.mainMenuScreen;
			}
			case 1:
			{
				
			}
			case 2:
			{
				
			}
			case 3:
			{
				
			}
			case 4: 
			{
				
			}
			case 5:
			{
				
			}
		}
		
		fileThread = new Thread(fileLoader);
		fileThread.setPriority(Thread.MIN_PRIORITY);
		fileThread.start();
	}

	public void render(float delta) 
	{	
		if (fileThread.isAlive())
		{
			System.out.println("LOADING");
			color = (float) Math.sin(colorD/1.5);
			colorD += delta;
			stateTime += delta;	
			//Gdx.gl.glClearColor(.3f, .5f, .9f, 0);
			//Gdx.gl.glClearColor(.3f, .6f, .8f, 0);
			Gdx.gl.glClearColor(color, -color, .8f, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);			
			camera.update();									
			batch.setProjectionMatrix(camera.combined);
			
			batch.begin();
				loadingScreenFiles.drawLogo(batch);
				loadingScreenFiles.drawLoadAnimation(batch, stateTime);
			batch.end();
		}
		else
		{
			System.out.println("Done Loading");
			myGame.setScreen(newScreen);
		}
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
	
	// We don't really have to do anything with these.
	public void resize(int width, int height) {}
	public void show() {}
	public void hide() {}
	public void pause() {}
	public void resume() {}
}
