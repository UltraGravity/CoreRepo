package Screens;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class OptionsScreen extends GenericScreen
{
	
	Stage stage;
	BitmapFont font;
	Skin skin;
	Table table;
	TextureAtlas buttonAtlas;
	TextButtonStyle textButtonStyle;
	TextButton soundEffectsButton;
	TextButton musicButton;
	TextButton backButton;
	Label ultraGravity;
	LabelStyle ultraGravityFont;

	public OptionsScreen(MyGame myGame) 
	{
		super(myGame);
	}

	public void render(float delta) 
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        
	    stage.act();
	        
	    batch.begin();
			
	    	//table.debug();
			//table.debugTable();
			stage.draw();
				
		batch.end();
	}

	public void show()
	{
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		table.setFillParent(true);
		
		
		font = myGame.assetLoader.font;
		skin = new Skin();
		buttonAtlas = myGame.assetLoader.mainMenuButtonAtlas;
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("Button");
		textButtonStyle.down = skin.getDrawable("Button-Pressed");

		
		if (myGame.music)
		{
			musicButton = new TextButton("Music On", textButtonStyle);
		}
		else
		{
			musicButton = new TextButton("Music Off", textButtonStyle);
		}
		
		if (myGame.sfx)
		{
			soundEffectsButton = new TextButton("SFX On", textButtonStyle);
		}
		else
		{
			soundEffectsButton = new TextButton("SFX Off", textButtonStyle);
		}
	

		backButton = new TextButton("Back", textButtonStyle);
		
		table.add(musicButton).width(screenWidth - screenWidth/8);
		table.row();
		table.add(soundEffectsButton).fillX();
		table.row();
		table.add(backButton).fillX();
		
		stage.addActor(table);

		
		// Button actions
		musicButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	            System.out.println("Music Button");
	            
	            if (myGame.music)
	            {
	            	myGame.music = false;
	            	musicButton.setText("Music Off");
	            }
	            else
	            {
	            	myGame.music = true;
	            	musicButton.setText("Music On");
	            }
	            
	        }});
		
		soundEffectsButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	            System.out.println("Sound Effects Button");
	            
	            if (myGame.sfx)
	            {
	            	myGame.sfx = false;
	            	soundEffectsButton.setText("SFX Off");
	            }
	            else
	            {
	            	myGame.sfx = true;
	            	soundEffectsButton.setText("SFX On");
	            }
	            
	        }});
		
		backButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	            System.out.println("Back Button");
	            myGame.changeToMainMenuScreen();
	        }});
	}

	public void hide() 
	{
		System.out.println("Disposing Options Screen");
		this.dispose();
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

	public void resize(int width, int height) {}
	public void pause() {}
	public void resume() {}


}
