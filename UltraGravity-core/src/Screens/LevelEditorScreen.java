package Screens;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelEditorScreen extends GenericScreen
{
	
	Stage stage;
	BitmapFont font;
	Skin skin;
	Table toolTable;
	Table world;
	TextButtonStyle textButtonStyle;
	
	Skin buttonSkin;
	TextureAtlas buttonAtlas;
	
	ImageButtonStyle groundBlockStyle;
	ImageButtonStyle boxBlockStyle;
	ImageButtonStyle safeZoneBlockStyle;
	
	ImageButton groundButton;
	ImageButton boxButton;
	ImageButton safeZoneButton;
	Label ultraGravity;
	LabelStyle ultraGravityFont;

	public LevelEditorScreen(MyGame myGame)
	{
		super(myGame);
	}
	
	
	public void render(float delta) 
	{	
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act();
        
		batch.begin();
		
			toolTable.debug();
			toolTable.debugTable();
			
		
			stage.draw();
			
		batch.end();
	}


	public void show()
	{
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		toolTable = new Table();
		toolTable.setFillParent(true);
		
		world = new Table();
		
		font = myGame.assetLoader.font;
		
		buttonSkin = new Skin();
		buttonAtlas = myGame.assetLoader.gameScreenAtlas;
		buttonSkin.addRegions(buttonAtlas);
		
		groundBlockStyle = new ImageButtonStyle();
		groundBlockStyle.up = buttonSkin.getDrawable("crate");
		boxBlockStyle = new ImageButtonStyle();
		boxBlockStyle.up = buttonSkin.getDrawable("metal");
		safeZoneBlockStyle = new ImageButtonStyle();
		safeZoneBlockStyle.up = buttonSkin.getDrawable("safezone");
		
		
		groundButton = new ImageButton(groundBlockStyle);
		boxButton = new ImageButton(boxBlockStyle);
		safeZoneButton = new ImageButton(safeZoneBlockStyle);

		toolTable.add(groundButton).pad(screenWidth/100).size(screenHeight/10);
		toolTable.add(boxButton).pad(screenWidth/100).size(screenHeight/10);
		toolTable.add(safeZoneButton).pad(screenWidth/100).size(screenHeight/10);
		
		toolTable.bottom().left();
		
		stage.addActor(toolTable);

	
	}

	public void hide() 
	{
		System.out.println("Disposing Level Editor Screen");
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
