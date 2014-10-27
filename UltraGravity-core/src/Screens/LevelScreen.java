package Screens;

import Objects.LevelButton;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelScreen extends GenericScreen
{
	
	Stage stage;
	BitmapFont font;
	Skin skin;
	Table table;
	Table window;
	ScrollPane scroll;
	TextureAtlas buttonAtlas;
	TextButtonStyle textButtonStyle;

	public LevelScreen(MyGame myGame)
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
			//table.debugAll();
			stage.draw();
			
		batch.end();
	}


	public void show()
	{
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		window = new Table();
		window.setFillParent(true);
		
		window.defaults().expand().top();
		
		font = myGame.assetLoader.font;
		skin = new Skin();
		buttonAtlas = myGame.assetLoader.mainMenuButtonAtlas;
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("Button");
		textButtonStyle.down = skin.getDrawable("Button-Pressed");

		for (int i = 0; i < 50; i++)
		{
			LevelButton button = new LevelButton(String.valueOf(i), textButtonStyle);
			table.add(button).width(screenWidth/5).height(screenWidth/5);
			
			if (i % 3 == 2)
			{
				table.row();
			}
		}
		
		
		scroll = new ScrollPane(table);
		
		window.add(scroll);
		
		stage.addActor(window);
	}

	public void hide() 
	{
		System.out.println("Disposing Level Select Screen");
		this.dispose();
	}
	
	public void dispose() 
	{
		super.dispose();
		stage.dispose();
	}
 
	public void pause() {}
	public void resume() {}
	public void resize(int width, int height) {}
	
}