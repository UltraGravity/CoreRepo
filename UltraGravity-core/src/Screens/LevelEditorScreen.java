package Screens;

import Objects.GridImage;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelEditorScreen extends GenericScreen
{
	
	int selectedBlock = 0;
	
	Stage stage;
	BitmapFont font;
	Skin skin;
	Table toolTable;
	Table levelGrid;
	TextButtonStyle textButtonStyle;
	
	Skin buttonSkin;
	TextureAtlas buttonAtlas;
	
	ImageButtonStyle groundBlockStyle;
	ImageButtonStyle boxBlockStyle;
	ImageButtonStyle safeZoneBlockStyle;
	ImageButtonStyle blankBlockStyle;
	
	GridImage cell[];
	ImageButtonStyle selectedStyle;
	
	ImageButton groundButton;
	ImageButton boxButton;
	ImageButton safeZoneButton;
	ImageButton blankButton;
	Label ultraGravity;
	LabelStyle ultraGravityFont;

	
	
	public LevelEditorScreen(MyGame myGame)
	{
		super(myGame);
	}
	
	
	public void render(float delta) 
	{	
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act();
        
		batch.begin();
		
			toolTable.debug();
			toolTable.debugTable();
			levelGrid.debug();
			levelGrid.debugTable();
		
			stage.draw();
			
		batch.end();
	}


	public void show()
	{
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		toolTable = new Table();
		toolTable.setFillParent(true);
		
		levelGrid = new Table();
		levelGrid.setFillParent(true);
		levelGrid.setHeight(screenHeight - screenHeight/10);
		
		font = myGame.assetLoader.font;
		
		buttonSkin = new Skin();
		buttonAtlas = myGame.assetLoader.gameScreenAtlas;
		buttonSkin.addRegions(buttonAtlas);
		
		groundBlockStyle = new ImageButtonStyle();
		groundBlockStyle.up = buttonSkin.getDrawable("metal");
		boxBlockStyle = new ImageButtonStyle();
		boxBlockStyle.up = buttonSkin.getDrawable("crate");
		safeZoneBlockStyle = new ImageButtonStyle();
		safeZoneBlockStyle.up = buttonSkin.getDrawable("safezone");
		blankBlockStyle = new ImageButtonStyle();
		blankBlockStyle.up = buttonSkin.getDrawable("blank");
		
		
		groundButton = new ImageButton(groundBlockStyle);
		boxButton = new ImageButton(boxBlockStyle);
		safeZoneButton = new ImageButton(safeZoneBlockStyle);
		blankButton = new ImageButton(blankBlockStyle);

		toolTable.add(groundButton).pad(screenWidth/100).size(screenHeight/12);
		toolTable.add(boxButton).pad(screenWidth/100).size(screenHeight/12);
		toolTable.add(safeZoneButton).pad(screenWidth/100).size(screenHeight/12);
		toolTable.add(blankButton).pad(screenWidth/100).size(screenHeight/12);
		
		toolTable.bottom().left();
		levelGrid.setSize(screenWidth, screenHeight);;
		
		cell = new GridImage[72];

		int index = 0;
		
		
		for (int y = 0; y < 6; y++)
		{
			for (int x = 0; x < 12; x++)
			{
				cell[index] = new GridImage(blankBlockStyle);
				levelGrid.add(cell[index]).size(screenHeight/8);//.size(screenHeight/7);				
				index++;
			}
			levelGrid.row();
		}
		
		
		
		levelGrid.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	        	GridImage image = (GridImage) actor;
	        	image.setStyle(selectedStyle);
	        	
	        	if (selectedStyle == groundBlockStyle)
	        	{
	        		image.cellValue = 1;
	        	}
	        	if (selectedStyle == boxBlockStyle)
	        	{
	        		image.cellValue = 2;
	        	}
	        	if (selectedStyle == safeZoneBlockStyle)
	        	{
	        		image.cellValue = 3;
	        	}
	        	if (selectedStyle == groundBlockStyle)
	        	{
	        		image.cellValue = 4;
	        	}
	        	
	        }});
		
		groundButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	        	selectedStyle = groundBlockStyle;
	        }});
		
		boxButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	        	selectedStyle = boxBlockStyle;
	        }});
		
		safeZoneButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	        	selectedStyle = safeZoneBlockStyle;
	        }});
		
		blankButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	        	selectedStyle = blankBlockStyle;
	        }});
		
		stage.addActor(toolTable);
		stage.addActor(levelGrid);


	
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
