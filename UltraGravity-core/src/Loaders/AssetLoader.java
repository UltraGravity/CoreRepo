package Loaders;

import Dialog.LoadLevelDialog;
import FileIO.LevelFile;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class AssetLoader 
{
	MyGame myGame;
	LevelFile levelFile;
	
	public AssetManager assetManager;
	public BitmapFont font;
	public TextureAtlas gameScreenAtlas;
	public TextureAtlas mainMenuButtonAtlas;
	public TextureAtlas uiSkinAtlas;
	public TextureRegion box;
	public TextureRegion ground;
	public TextureRegion safeZone;
	public TextureRegion blank;
	public TextureRegion play;
	public TextureRegion save;
	public TextureRegion load;
	public TextureRegion back;
	
	// Button Styles
	public ImageButtonStyle groundBlockStyle;
	public ImageButtonStyle boxBlockStyle;
	public ImageButtonStyle safeZoneBlockStyle;
	public ImageButtonStyle blankBlockStyle;
	public ImageButtonStyle saveButtonStyle;
	public ImageButtonStyle loadButtonStyle;
	public ImageButtonStyle playButtonStyle;
	public ImageButtonStyle backButtonStyle;
	
	// Tool Button Styles
	public ImageButtonStyle boxStyle;
	public ImageButtonStyle groundStyle;
	public ImageButtonStyle safeStyle;
	public ImageButtonStyle blankStyle;
	public ImageButtonStyle settingsStyle;
	
	public ImageButtonStyle plusStyle;
	public ImageButtonStyle minusStyle;
	
	
	// Skins
	public Skin menuButtonSkin;
	public Skin gameButtonSkin;
	public Skin uiSkin;

	
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
		gameButtonSkin = new Skin(gameScreenAtlas);
		uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		menuButtonSkin = new Skin();
		menuButtonSkin.addRegions(mainMenuButtonAtlas);
		font = assetManager.get("Font.fnt", BitmapFont.class);

		box = gameScreenAtlas.findRegion("crate");
		ground = gameScreenAtlas.findRegion("metal");
		safeZone = gameScreenAtlas.findRegion("safezone");
		blank = gameScreenAtlas.findRegion("blank");
		save = gameScreenAtlas.findRegion("save");
		load = gameScreenAtlas.findRegion("load");
		back = gameScreenAtlas.findRegion("back");
		play = gameScreenAtlas.findRegion("play");

		groundBlockStyle = new ImageButtonStyle();
		groundBlockStyle.up = gameButtonSkin.getDrawable("metal");
		boxBlockStyle = new ImageButtonStyle();
		boxBlockStyle.up = gameButtonSkin.getDrawable("crate");
		safeZoneBlockStyle = new ImageButtonStyle();
		safeZoneBlockStyle.up = gameButtonSkin.getDrawable("safezone");
		blankBlockStyle = new ImageButtonStyle();
		blankBlockStyle.up = gameButtonSkin.getDrawable("blank");
		saveButtonStyle = new ImageButtonStyle();
		saveButtonStyle.up = gameButtonSkin.getDrawable("save");
		saveButtonStyle.down = gameButtonSkin.getDrawable("save-pressed");
		loadButtonStyle = new ImageButtonStyle();
		loadButtonStyle.up = gameButtonSkin.getDrawable("load");
		loadButtonStyle.down = gameButtonSkin.getDrawable("load-pressed");
		backButtonStyle = new ImageButtonStyle();
		backButtonStyle.up = gameButtonSkin.getDrawable("back");
		backButtonStyle.down = gameButtonSkin.getDrawable("back-pressed");
		
		boxStyle = new ImageButtonStyle();
		boxStyle.up = gameButtonSkin.getDrawable("boxbutton");
		boxStyle.down = gameButtonSkin.getDrawable("boxbutton-pressed");
		boxStyle.checked = gameButtonSkin.getDrawable("boxbutton-pressed");
		
		groundStyle = new ImageButtonStyle();
		groundStyle.up = gameButtonSkin.getDrawable("groundbutton");
		groundStyle.down = gameButtonSkin.getDrawable("groundbutton-pressed");
		groundStyle.checked = gameButtonSkin.getDrawable("groundbutton-pressed");
		
		safeStyle = new ImageButtonStyle();
		safeStyle.up = gameButtonSkin.getDrawable("safebutton");
		safeStyle.down = gameButtonSkin.getDrawable("safebutton-pressed");	
		safeStyle.checked = gameButtonSkin.getDrawable("safebutton-pressed");	
		
		blankStyle = new ImageButtonStyle();
		blankStyle.up = gameButtonSkin.getDrawable("blankbutton");
		blankStyle.down = gameButtonSkin.getDrawable("blankbutton-pressed");
		blankStyle.checked = gameButtonSkin.getDrawable("blankbutton-pressed");
		
		playButtonStyle = new ImageButtonStyle();
		playButtonStyle.up = gameButtonSkin.getDrawable("play");
		playButtonStyle.down = gameButtonSkin.getDrawable("play-pressed");
		
		settingsStyle = new ImageButtonStyle();
		settingsStyle.up = gameButtonSkin.getDrawable("settingsbutton");
		settingsStyle.down = gameButtonSkin.getDrawable("settingsbutton-pressed");
		
		plusStyle = new ImageButtonStyle();
		plusStyle.up = gameButtonSkin.getDrawable("plus");
		plusStyle.down = gameButtonSkin.getDrawable("plus-pressed");
		
		minusStyle = new ImageButtonStyle();
		minusStyle.up = gameButtonSkin.getDrawable("minus");
		minusStyle.down = gameButtonSkin.getDrawable("minus-pressed");
	 }	
	 
	 public void loadGameAssets()
	 {
		 
	 }
	 
	 public void setupGame()
	 {
		 
	 }
	 
	
	 
}
