package Loaders;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader 
{
	MyGame myGame;
	public AssetManager assetManager;
	
	public TextureAtlas gameScreenAtlas;
	public TextureRegion crate;
	public TextureRegion ground;
	public TextureRegion safeZone;
	
	 public AssetLoader(MyGame myGame)
	 {
		assetManager = new AssetManager();	
		this.myGame = myGame;
	 }
	
	 public void loadMenuAssets()
	 {
		 assetManager.load("GameAssets.atlas", TextureAtlas.class);
	 }
	 
	 public void setupMenu()
	 {
		 gameScreenAtlas = assetManager.get("GameAssets.atlas", TextureAtlas.class);
		 crate = gameScreenAtlas.findRegion("crate");
		 ground = gameScreenAtlas.findRegion("metal");
		 safeZone = gameScreenAtlas.findRegion("safezone");
	 }
	 
	 public void loadGameAssets()
	 {
		 
	 }
	 
}
