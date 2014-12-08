package Screens;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class LoadingScreen extends GenericScreen
{
	Screen newScreen;
	Texture loading;
	
	Runnable fileLoader;
	Thread fileThread;
	
	public LoadingScreen(MyGame myGame) 
	{
		super(myGame);
		loading = new Texture(Gdx.files.internal("loading.png"));
	}

	public void render(float delta) 
	{	
		if (!myGame.assetLoader.assetManager.update())
		{
			System.out.println("LOADING");
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
			
			batch.begin();
				batch.draw(
						loading, screenWidth/2 - loading.getWidth()/2, 
						screenHeight/2 - loading.getHeight()/2, 
						loading.getWidth(), 
						loading.getHeight());
			batch.end();
		}
		else
		{
			System.out.println("Done Loading");
			myGame.assetLoader.setupMenu();
			myGame.switchScreen();
			myGame.playMusic();
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
