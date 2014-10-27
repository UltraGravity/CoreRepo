package Screens;

import java.io.File;
import java.nio.file.Files;

import FileIO.LevelFile;
import Objects.GridImage;
import Objects.World;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
	ImageButtonStyle saveButtonStyle;
	ImageButtonStyle loadButtonStyle;
	ImageButtonStyle playButtonStyle;
	ImageButtonStyle backButtonStyle;
	
	GridImage cell[];
	ImageButtonStyle selectedStyle;
	
	ImageButton groundButton;
	ImageButton boxButton;
	ImageButton safeZoneButton;
	ImageButton blankButton;
	ImageButton saveButton;
	ImageButton loadButton;
	ImageButton playButton;
	ImageButton backButton;
	
	Label ultraGravity;
	LabelStyle ultraGravityFont;
	
	LevelFile levelFile;
	World world;

	
	
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
		saveButtonStyle = new ImageButtonStyle();
		saveButtonStyle.up = buttonSkin.getDrawable("save");
		loadButtonStyle = new ImageButtonStyle();
		loadButtonStyle.up = buttonSkin.getDrawable("load");
		backButtonStyle = new ImageButtonStyle();
		backButtonStyle.up = buttonSkin.getDrawable("back");
		playButtonStyle = new ImageButtonStyle();
		playButtonStyle.up = buttonSkin.getDrawable("play");
		
		selectedStyle = blankBlockStyle;
		
		groundButton = new ImageButton(groundBlockStyle);
		boxButton = new ImageButton(boxBlockStyle);
		safeZoneButton = new ImageButton(safeZoneBlockStyle);
		blankButton = new ImageButton(blankBlockStyle);
		saveButton = new ImageButton(saveButtonStyle);
		loadButton = new ImageButton(loadButtonStyle);
		backButton = new ImageButton(backButtonStyle);
		playButton = new ImageButton(playButtonStyle);

		toolTable.add(backButton).pad(screenWidth/100).size(screenHeight/12);
		toolTable.add(groundButton).pad(screenWidth/100).size(screenHeight/12);
		toolTable.add(boxButton).pad(screenWidth/100).size(screenHeight/12);
		toolTable.add(safeZoneButton).pad(screenWidth/100).size(screenHeight/12);
		toolTable.add(blankButton).pad(screenWidth/100).size(screenHeight/12);
		toolTable.add(saveButton).pad(screenWidth/100).size(screenHeight/12);
		toolTable.add(loadButton).pad(screenWidth/100).size(screenHeight/12);
		toolTable.add(playButton).pad(screenWidth/100).size(screenHeight/12);
		
		toolTable.bottom().left();
		levelGrid.setSize(screenWidth, screenHeight);;
		
		cell = new GridImage[72];

		int index = 0;
		
		world = new World(12, 6);
		for (int y = 0; y < world.getYSize(); y++)
		{
			for (int x = 0; x < world.getXSize(); x++)
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
	        	
	        	if (selectedStyle == blankBlockStyle)
	        	{
	        		image.cellValue = 0;
	        	}
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
	        }});
		
		
		backButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	        	myGame.changeToMainMenuScreen();
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
		
		saveButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	        	save();
	        }});
		
		loadButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	        	// open a pop up window or something to select specific level to edit.
	        	//load(that_file);
	        }});
		
		playButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	        	//save();
	        	//play();
	        }});
		
		stage.addActor(toolTable);
		stage.addActor(levelGrid);
	}

	
	public void save()
	{
	  boolean nameFound = false;
	  String fileName = "";
	  int fName = 0;
	  while(!nameFound) 
	  {
	    fileName = "CL_" + Integer.toString(fName) + ".txt";
	    System.out.println("Checking if " + fileName + " is an available name");
	    FileHandle file = Gdx.files.local("Levels/" + fileName);
	    if(file.exists()) {
	      System.out.println("File existed");
	      fName++;
	    }
	    else {
	      System.out.println(fileName + " is being created");
	      nameFound = true;
	    }
	  }
		String world = "";
		for (Actor A : levelGrid.getChildren())
		{
			GridImage item = (GridImage) A;
			System.out.println(item.cellValue);
			world = world + item.cellValue;
			
		}
		System.out.println(world);
		System.out.println(fileName);
		levelFile = new LevelFile(myGame);
		levelFile.SaveLevel(fileName, world);
		
	}
	
	public void load(String file) 
	{
	  String level = levelFile.LoadLevel(file);
	    int x = world.getXSize();
	    int y = world.getYSize();
	    int i = 0;
	    while(x >= 0) {
	      while(y >= 0) {
	        int nextInt = level.charAt(i);
	        if(i == 0) {
	          System.out.print(" " + 0 + " ");
	          //add blank space
	        }
	        if(i == 1) {
	          System.out.print(" " + 1 + " ");
	          //add ground block
	        }
	        if(i == 2) {
	          System.out.print(" " + 2 + " ");
	          //add crate
	        }
	        if(i == 3) {
	          System.out.print(" " + 3 + " ");
	          //add character
	        }
	        System.out.println();
	        i++;
	        y++;
	      }
	      y = world.getYSize();
	      x++;
	    }
	    
	    //TODO enter actors into the grid based of of the ints recieved in the file
//	  }
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
