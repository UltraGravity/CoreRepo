package Screens;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelScreen extends GenericScreen
{
	
	Stage stage;
	BitmapFont font;
	Skin skin;
	Table table;
	TextureAtlas buttonAtlas;
	TextButtonStyle textButtonStyle;
	TextButton levelSelectButton;
	TextButton levelEditorButton;
	TextButton optionsButton;
	Label ultraGravity;
	LabelStyle ultraGravityFont;

	public LevelScreen(MyGame myGame)
	{
		super(myGame);
	}
	
	
	public void render(float delta) 
	{	
        Gdx.gl.glClearColor(0, 0, 0, 1);
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

		ultraGravityFont = new LabelStyle();
		ultraGravityFont.font = myGame.assetLoader.font;
		ultraGravity = new Label("The Level Screen", ultraGravityFont);
		
		table.add(ultraGravity).fillX();
		stage.addActor(table);
	}

	public void hide() 
	{
		System.out.println("Disposing Main Menu Screen");
		this.dispose();
	}
	
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
		stage.dispose();
	}
 
	public void pause() {}
	public void resume() {}
	public void resize(int width, int height) {}
	
}