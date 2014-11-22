package Screens;

import Objects.LevelButton;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelScreen extends GenericScreen
{
	
	Stage stage;
	Table builtInTable;
	Table customTable;
	Table window;
	ScrollPane builtInScroll;
	ScrollPane customScroll;
	TextureAtlas buttonAtlas;
	TextButtonStyle textButtonStyle;
	Label builtInLabel;
	Label customLabel;

	FileHandle[] builtInLevels;
	FileHandle[] customLevels;
	
	public LevelScreen(MyGame myGame)
	{
		super(myGame);
	}
	
	public void render(float delta) 
	{	
        Gdx.gl.glClearColor(.2f, .2f, .2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		camera.update();
		batch.setProjectionMatrix(camera.combined);

        stage.act();
        
		batch.begin();
			stage.draw();		
		batch.end();
	}


	public void show()
	{
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		textButtonStyle = myGame.assetLoader.textButtonStyle;
		builtInTable = new Table();
		customTable = new Table();
		
		window = new Table();
		window.setFillParent(true);
		window.defaults().expand().top();
		
		
		
		
		builtInLevels = Gdx.files.local("BuiltIn").list();
		int nBuiltInLevels = builtInLevels.length;
		
		for (int i = 0; i < nBuiltInLevels; i++)
		{
			String name = builtInLevels[i].name();
			name = name.replace(".txt", "");
			LevelButton button = new LevelButton(name, textButtonStyle, builtInLevels[i].name(), "BuiltIn");
			builtInTable.add(button).height(myGame.screenWidth/5);
		}
		
		customLevels = Gdx.files.local("Levels").list();
		int nCustomLevels = customLevels.length;
		
		for (int i = 0; i < nCustomLevels; i++)
		{
			String name = customLevels[i].name();
			name = name.replace(".txt", "");
			LevelButton button = new LevelButton(name, textButtonStyle, customLevels[i].name(), "Levels");
			customTable.add(button).height(myGame.screenWidth/5);
		}	
		
		
		builtInLabel = new Label("TEST", myGame.assetLoader.uiSkin);
		customLabel = new Label("TEST", myGame.assetLoader.uiSkin);
		
		window.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	            LevelButton button = (LevelButton) actor;
	            System.out.println(button.getLevelName());
	            myGame.changeToGameScreen(button.getLevelName(), button.getFolder());
	        }});
		
		
		builtInScroll = new ScrollPane(builtInTable);
		customScroll = new ScrollPane(customTable);
		
		
		window.add(builtInLabel);
		window.row();
		window.add(builtInScroll);
		window.row();
		window.add(customLabel);
		window.row();
		window.add(customScroll);	
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