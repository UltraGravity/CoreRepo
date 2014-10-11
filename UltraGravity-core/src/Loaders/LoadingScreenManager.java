package Loaders;

import Managers.ScreenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LoadingScreenManager implements Loader
{
	public ScreenManager screenManager;
	public TextureAtlas loadingScreenAtlas;
	public TextureRegion UltraGravityLogo;
	public Animation loadingAnimation;
	public TextureRegion[] loadingFrames;
	
	public LoadingScreenManager(ScreenManager screenManager)
	{
		this.screenManager = screenManager;
		loadingScreenAtlas = new TextureAtlas(Gdx.files.internal("LoadingScreenAtlas.atlas"));
	}

	public void load()
	{
		UltraGravityLogo = loadingScreenAtlas.findRegion("UltraGravityLogo");
		loadingFrames = new TextureRegion[4];
		for (int iImage = 0; iImage <= 3; iImage++)
		{
			String loadingFrameFileName = "LoadingFrame" + (iImage + 1);
			loadingFrames[iImage] = new TextureRegion();
			loadingFrames[iImage] = loadingScreenAtlas.findRegion(loadingFrameFileName);
		}
		
		loadingAnimation = new Animation(0.2f, loadingFrames);
		
		screenManager.makeLoadingScreen();
		System.out.println(loadingFrames[0]);
	}

	public void unload() 
	{
		loadingScreenAtlas.dispose();
		screenManager = null;
		loadingScreenAtlas = null;
		UltraGravityLogo = null;
		loadingAnimation = null;
		loadingFrames = null;
	}
	
	
	public void drawLogo(SpriteBatch batch)
	{
		int xSize = screenWidth / 2;
		int ySize = (490 * xSize) / 721;
		int xPosition = (screenWidth / 2) - (xSize / 2);
		int yPosition = (screenHeight / 2) - (ySize / 2) - screenHeight/20;
		batch.draw(UltraGravityLogo, xPosition, yPosition, xSize, ySize);
	}
	
	public void drawLoadAnimation(SpriteBatch batch, float stateTime)
	{
		int xSize = screenWidth / 8;
		int ySize = (31 * xSize) / 238;
		int xPosition = (screenWidth / 2) - (xSize / 2);
		int yPosition = screenHeight - (screenHeight / 6);
		batch.draw(loadingAnimation.getKeyFrame(stateTime, true), xPosition, yPosition, xSize, ySize);
	}
	
}
