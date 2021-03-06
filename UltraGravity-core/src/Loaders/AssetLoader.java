///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 AssetLoader.java
//
//							Notes:
//This class is in charge of loading any graphical and audio files used 
//within the game.
///////////////////////////////////////////////////////////////////////////

package Loaders;

import FileIO.LevelFile;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class AssetLoader 
{
	MyGame myGame;
	LevelFile levelFile;
	
	public String buttonFont;
	public TextButtonStyle textButtonStyle;
	
	// Skins
	public Skin menuButtonSkin;
	public Skin gameButtonSkin;
	public Skin uiSkin;
	
	// Sound Effects
	public Music music;
	public Sound click;
	
	public AssetManager assetManager;
	public BitmapFont font;
	public BitmapFont titleFont;
	public TextureAtlas gameScreenAtlas;
	public TextureAtlas mainMenuButtonAtlas;
	public TextureAtlas uiSkinAtlas;
	
	// Textures for actual gameplay
	public TextureRegion box;
	public TextureRegion ground;
	public TextureRegion safeZone;
	public TextureRegion blank;
	public TextureRegion play;
	public TextureRegion save;
	public TextureRegion load;
	public TextureRegion back;
	public TextureRegion character;
	public TextureRegion easterEgg;
	public TextureRegion buzzSaw;
	public TextureRegion tire;
	public TextureRegion spikedBlock;
	public TextureRegion metalBox;
	public TextureRegion ice;
	
	/* 
	 * Button Styles
	 * Used for the actual grid, not the tool box
	*/ 
	public ImageButtonStyle groundBlockStyle;
	public ImageButtonStyle boxBlockStyle;
	public ImageButtonStyle safeZoneBlockStyle;
	public ImageButtonStyle blankBlockStyle;
	public ImageButtonStyle saveButtonStyle;
	public ImageButtonStyle loadButtonStyle;
	public ImageButtonStyle playButtonStyle;
	public ImageButtonStyle backButtonStyle;
	public ImageButtonStyle characterBlockStyle;
	public ImageButtonStyle tireBlockStyle;
	public ImageButtonStyle metalBoxBlockStyle;
	public ImageButtonStyle buzzsawBlockStyle;
	public ImageButtonStyle spikedBlockStyle;
	public ImageButtonStyle iceBlockStyle;

	// Tool Button Styles used for the tool box
	public ImageButtonStyle boxStyle;
	public ImageButtonStyle groundStyle;
	public ImageButtonStyle safeStyle;
	public ImageButtonStyle blankStyle;
	public ImageButtonStyle settingsStyle;
	public ImageButtonStyle pauseButtonStyle;
	public ImageButtonStyle plusStyle;
	public ImageButtonStyle minusStyle;
	public ImageButtonStyle characterButtonStyle;
	public ImageButtonStyle buzzsawStyle;
	public ImageButtonStyle tireStyle;
	public ImageButtonStyle metalBoxStyle;
	public ImageButtonStyle spikedBoxStyle;
	public ImageButtonStyle iceBoxStyle;
	
	 public AssetLoader(MyGame myGame)
	 {
		assetManager = new AssetManager();	
		this.myGame = myGame;
	 }
	
	 public void loadMenuAssets()
	 {
		 assetManager.load("GameAssets.atlas", TextureAtlas.class);
		 assetManager.load("MenuButtonAtlas.atlas", TextureAtlas.class);
		 assetManager.load("title.fnt", BitmapFont.class);
		 assetManager.load("uiskin.atlas", TextureAtlas.class);
		 assetManager.load("click.mp3", Sound.class);
		 assetManager.load("music.mp3", Music.class);
		 chooseFont();	 
	 }
	 
	 public void setupMenu()
	 {
		music = assetManager.get("music.mp3", Music.class);
		music.setLooping(true);
		click = assetManager.get("click.mp3", Sound.class);
		  
		gameScreenAtlas = assetManager.get("GameAssets.atlas", TextureAtlas.class);
		mainMenuButtonAtlas = new TextureAtlas(Gdx.files.internal("MenuButtonAtlas.atlas"));
		uiSkinAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		gameButtonSkin = new Skin(gameScreenAtlas);
		uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		menuButtonSkin = new Skin();
		menuButtonSkin.addRegions(mainMenuButtonAtlas);
		font = assetManager.get(buttonFont, BitmapFont.class);
		titleFont = assetManager.get("title.fnt", BitmapFont.class);

		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = myGame.assetLoader.font;
		textButtonStyle.up = menuButtonSkin.getDrawable("grey");
		textButtonStyle.down = menuButtonSkin.getDrawable("grey-pressed");
		
		box = gameScreenAtlas.findRegion("crate");
		ground = gameScreenAtlas.findRegion("metal");
		safeZone = gameScreenAtlas.findRegion("safezone");
		blank = gameScreenAtlas.findRegion("blank");
		save = gameScreenAtlas.findRegion("save");
		load = gameScreenAtlas.findRegion("load");
		back = gameScreenAtlas.findRegion("back");
		play = gameScreenAtlas.findRegion("play");
		character = gameScreenAtlas.findRegion("character");
		buzzSaw = gameScreenAtlas.findRegion("buzzsaw");
		spikedBlock = gameScreenAtlas.findRegion("spikedBlock");
		tire = gameScreenAtlas.findRegion("tire");
		metalBox = gameScreenAtlas.findRegion("metalBox");
		ice = gameScreenAtlas.findRegion("iceblock");
		easterEgg = gameScreenAtlas.findRegion("easterEgg");

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
		
		buzzsawBlockStyle = new ImageButtonStyle();
		buzzsawBlockStyle.up = gameButtonSkin.getDrawable("buzzsaw");
		spikedBlockStyle = new ImageButtonStyle();
		spikedBlockStyle.up = gameButtonSkin.getDrawable("spikedBlock");
		tireBlockStyle = new ImageButtonStyle();
		tireBlockStyle.up = gameButtonSkin.getDrawable("tire");
		metalBoxBlockStyle = new ImageButtonStyle();
		metalBoxBlockStyle.up = gameButtonSkin.getDrawable("metalBox");
		iceBlockStyle = new ImageButtonStyle();
		iceBlockStyle.up = gameButtonSkin.getDrawable("iceblock");
		
		
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
		
		characterButtonStyle = new ImageButtonStyle();
		characterButtonStyle.up = gameButtonSkin.getDrawable("main");
		characterButtonStyle.down = gameButtonSkin.getDrawable("main-pressed");
		characterButtonStyle.checked = gameButtonSkin.getDrawable("main-pressed");
		
		characterBlockStyle = new ImageButtonStyle();
		characterBlockStyle.up = gameButtonSkin.getDrawable("main-level");
		
		pauseButtonStyle = new ImageButtonStyle();
		pauseButtonStyle.up = gameButtonSkin.getDrawable("pauseButton");
		pauseButtonStyle.down = gameButtonSkin.getDrawable("pauseButton-pressed");
		
		tireStyle = new ImageButtonStyle();
		tireStyle.up = gameButtonSkin.getDrawable("tireButton");
		tireStyle.down = gameButtonSkin.getDrawable("tireButton-pressed");
		tireStyle.checked = gameButtonSkin.getDrawable("tireButton-pressed");
		
		metalBoxStyle = new ImageButtonStyle();
		metalBoxStyle.up = gameButtonSkin.getDrawable("metalBoxButton");
		metalBoxStyle.down = gameButtonSkin.getDrawable("metalBoxButton-pressed");
		metalBoxStyle.checked = gameButtonSkin.getDrawable("metalBoxButton-pressed");
		
		buzzsawStyle = new ImageButtonStyle();
		buzzsawStyle.up = gameButtonSkin.getDrawable("buzzsawButton");
		buzzsawStyle.down = gameButtonSkin.getDrawable("buzzsawButton-pressed");
		buzzsawStyle.checked = gameButtonSkin.getDrawable("buzzsawButton-pressed");
		
		spikedBoxStyle = new ImageButtonStyle();
		spikedBoxStyle.up = gameButtonSkin.getDrawable("spikedBlock-notPressed");
		spikedBoxStyle.down = gameButtonSkin.getDrawable("spikedBlock-pressed");
		spikedBoxStyle.checked = gameButtonSkin.getDrawable("spikedBlock-pressed");	
		
		iceBoxStyle = new ImageButtonStyle();
		iceBoxStyle.up = gameButtonSkin.getDrawable("icebutton");
		iceBoxStyle.down = gameButtonSkin.getDrawable("icebutton-pressed");
		iceBoxStyle.checked = gameButtonSkin.getDrawable("icebutton-pressed");
	 }	
	 

	 public void chooseFont()
	 {
		 buttonFont = "font_small.fnt";
		 assetManager.load(buttonFont, BitmapFont.class);
	 }
	
	 public void dispose()
	 {			
		menuButtonSkin.dispose();
		gameButtonSkin.dispose();
		uiSkin.dispose();

		music.dispose();
		click.dispose();

		assetManager.dispose();
		font.dispose();
		titleFont.dispose();
		gameScreenAtlas.dispose();
		mainMenuButtonAtlas.dispose();
		uiSkinAtlas.dispose();
	 }
	 
}
