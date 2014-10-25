package Screens;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenuScreen extends GenericScreen
{
	
	Stage stage;
	BitmapFont font;
	Skin skin;
	TextureAtlas buttonAtlas;
	TextButtonStyle textButtonStyle;
	TextButton levelSelectButton;

	public MainMenuScreen(MyGame myGame)
	{
		super(myGame);
	}
	
	
	public void render(float delta) 
	{	
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act();
        
		batch.begin();
			stage.draw();
		batch.end();
	}


	public void show()
	{
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("MenuButtonAtlas.atlas"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("Button");
		textButtonStyle.down = skin.getDrawable("Button-Pressed");
		levelSelectButton = new TextButton("Level Select", textButtonStyle);
		stage.addActor(levelSelectButton);
		
		
		
		
		levelSelectButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	            System.out.println("Button Pressed");
	        }});
	}

	public void hide() {}
	public void pause() {}
	public void resume() {}
	public void resize(int width, int height) {}


  public void dispose() 
  {
    /*
     * It is very important that everything created is disposed of properly when it is no longer needed. I find it best
     * to explicitly set everything equal to null so the garbage collector knows it can remove the stuff from memory.
     * 
     * Calling super.dispose() will get rid of the built in variables, but it is important that anything that is
     * uniquely created in this class be disposed.
     */

    super.dispose();
  }
  
}
