package Loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LoadingScreenFiles implements Loader
{
	public TextureAtlas loadingScreenAtlas;
	public TextureRegion UltraGravityLogo;
	public Animation loadingAnimation;
	public TextureRegion[] loadingFrames;
	
	public LoadingScreenFiles()
	{
		loadingScreenAtlas = new TextureAtlas(Gdx.files.internal("LoadingScreenAtlas.atlas"));
		this.load();
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
		
	public void unload() 
	{
		loadingScreenAtlas.dispose();
		loadingScreenAtlas = null;
		UltraGravityLogo = null;
		loadingAnimation = null;
		loadingFrames = null;
	}
}
