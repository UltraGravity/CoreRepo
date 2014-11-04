package Loaders;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader 
{
	MyGame myGame;
	public AssetManager assetManager;
	public BitmapFont font;
	public TextureAtlas gameScreenAtlas;
	public TextureAtlas mainMenuButtonAtlas;
	public TextureAtlas uiSkinAtlas;
	public TextureRegion crate;
	public TextureRegion ground;
	public TextureRegion safeZone;
	public TextureRegion blank;
	
	public Skin menuButtonSkin;
	public Skin uiSkin;
//	public TextureRegion character;
	
	 public AssetLoader(MyGame myGame)
	 {
		assetManager = new AssetManager();	
		this.myGame = myGame;
	 }
	
	 public void loadMenuAssets()
	 {
		 assetManager.load("GameAssets.atlas", TextureAtlas.class);
		 assetManager.load("MenuButtonAtlas.atlas", TextureAtlas.class);
		 assetManager.load("Font.fnt", BitmapFont.class);
		 assetManager.load("uiskin.atlas", TextureAtlas.class);
	 }
	 
	 public void setupMenu()
	 {
		 gameScreenAtlas = assetManager.get("GameAssets.atlas", TextureAtlas.class);
		 mainMenuButtonAtlas = new TextureAtlas(Gdx.files.internal("MenuButtonAtlas.atlas"));
		 uiSkinAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
	 
		 font = assetManager.get("Font.fnt", BitmapFont.class);
		 crate = gameScreenAtlas.findRegion("crate");
		 ground = gameScreenAtlas.findRegion("metal");
		 safeZone = gameScreenAtlas.findRegion("safezone");
		 blank = gameScreenAtlas.findRegion("blank");
		 
		 uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		 menuButtonSkin = new Skin();
		 menuButtonSkin.addRegions(mainMenuButtonAtlas);
	 }	
	 
	 public void loadGameAssets()
	 {
		 
	 }
	 
	 public void setupGame()
	 {
		 
	 }
	 
}
