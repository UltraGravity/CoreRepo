package Screens;

import FileIO.LevelFile;
import Objects.GridImage;
import Objects.ThePlane;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class LevelEditorScreen extends GenericScreen
{
	
	int selectedBlock = 0;
	BitmapFont font;
	
	Stage stage;
	
	Skin buttonSkin;
	
	Table mapTable;
	Table toolTable;
	Table levelGrid;
	
	ScrollPane scrollPane;
	TextButtonStyle textButtonStyle;
	
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
	ThePlane thePlane;

	
	
	public LevelEditorScreen(MyGame myGame)
	{
		super(myGame);
	}
	
	
	public void render(float delta) 
	{	
        Gdx.gl.glClearColor(0, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act();
        
        
		batch.begin();
		
			//mapTable.debug();
			//mapTable.debugTable();
			//toolTable.debug();
			//toolTable.debugTable();
			//levelGrid.debug();
			//levelGrid.debugTable();
			stage.draw();
			
		batch.end();
	}


	public void show()
	{
		// Show
		Gdx.input.setCatchBackKey(true);
    
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		mapTable = new Table(); // Holds the ScrollPane
		levelGrid = new Table(); // Holds all the items you place
		toolTable = new Table(myGame.assetLoader.menuButtonSkin); // Holds all the tools
		scrollPane = new ScrollPane(levelGrid);
				
		mapTable.setHeight(screenHeight);
		mapTable.setWidth(screenWidth);		
		
		//toolTable.setFillParent(true);
		mapTable.add(scrollPane).height(screenHeight - screenHeight/7);
		mapTable.top();
		//toolTable.center().bottom();
		
		//toolTable.setX(screenWidth/2 - toolTable.getWidth()/2);
		

		// Styles and stuff
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
		
		// Buttons
		groundButton = new ImageButton(groundBlockStyle);
		boxButton = new ImageButton(boxBlockStyle);
		safeZoneButton = new ImageButton(safeZoneBlockStyle);
		blankButton = new ImageButton(blankBlockStyle);
		saveButton = new ImageButton(saveButtonStyle);
		loadButton = new ImageButton(loadButtonStyle);
		backButton = new ImageButton(backButtonStyle);
		playButton = new ImageButton(playButtonStyle);
		
		selectedStyle = blankBlockStyle;
		
		toolTable.add(backButton).size(screenHeight/12).padLeft(screenWidth/100).padRight(screenWidth/100);
		toolTable.add(groundButton).size(screenHeight/12).padLeft(screenWidth/100).padRight(screenWidth/100);
		toolTable.add(boxButton).size(screenHeight/12).padLeft(screenWidth/100).padRight(screenWidth/100);
		toolTable.add(safeZoneButton).size(screenHeight/12).padLeft(screenWidth/100).padRight(screenWidth/100);
		toolTable.add(blankButton).size(screenHeight/12).padLeft(screenWidth/100).padRight(screenWidth/100);
		toolTable.add(saveButton).size(screenHeight/12).padLeft(screenWidth/100).padRight(screenWidth/100);
		toolTable.add(loadButton).size(screenHeight/12).padLeft(screenWidth/100).padRight(screenWidth/100);
		toolTable.add(playButton).size(screenHeight/12).padLeft(screenWidth/100).padRight(screenWidth/100);
	
		
		toolTable.setBackground("Button-Pressed");
		//toolTable.pack();
		toolTable.center().bottom();
		
		int index = 0;
		thePlane = new ThePlane(20,10);
		// world = new World(new Vector2(0,-10), true);
		cell = new GridImage[thePlane.getXSize() * thePlane.getYSize()];
		createGrid(cell);
		
		mapTable.row();
		mapTable.add(toolTable).bottom();
		
		addListeners(levelGrid);
		
		stage.addActor(mapTable);
		
		// This might not be working because the action listener is set to the stage.
		InputProcessor backProcessor = new InputAdapter() 
		{
			public boolean keyDown(int keycode) 
			{
				if((keycode == Keys.BACK) || (keycode == Keys.BACKSPACE)) {
					myGame.changeToMainMenuScreen();
				}
				return false;
			}};
    
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
	          load("CL_0.txt");
	        	// open a pop up window or something to select specific level to edit.
	        	//load(that_file);
	        }});
		
		playButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {	
	        	myGame.changeToGameScreen();
	        }});
		
		
		
		
		
//		scrollPane.addListener(new ActorGestureListener() {
//			
//			public void pinch(InputEvent event, Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) 
//			{
//				scrollPane.stageToLocalCoordinates(initialPointer1.set(initialPointer1));
//				scrollPane.stageToLocalCoordinates(initialPointer2.set(initialPointer2));
//				scrollPane.stageToLocalCoordinates(pointer1.set(pointer1));
//				scrollPane.stageToLocalCoordinates(pointer2.set(pointer2));
//				pinch(event, initialPointer1, initialPointer2, pointer1, pointer2);
//			}
//		});
	}
	
	
	
	
	private void addListeners(Table levelGrid2) 
	{
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
    
  }


	private void createGrid(GridImage[] cell2)
	{
		int index = 0;
		for (int y = 0; y < thePlane.getYSize(); y++)
		{
			for (int x = 0; x < thePlane.getXSize(); x++)
			{
				cell[index] = new GridImage(blankBlockStyle);
				levelGrid.add(cell[index]).size(screenHeight / 8);// .size(screenHeight/7);
				index++;
			}
			levelGrid.row();
		}

	}

	public String getLastLevel()
	{
		String level = "";
		level = getLevelString(getLastLevelName());
		return level;
	}

	public String getLastLevelName()
	{
		boolean nameFound = false;
		String fileName = "";
		int fName = 0;
		while (!nameFound)
		{
			fileName = "CL_" + Integer.toString(fName) + ".txt";
			System.out.println("Checking if " + fileName
					+ " is an available name");
			FileHandle file = Gdx.files.local("Levels/" + fileName);
			if (file.exists())
			{
				System.out.println("File existed");
				fName++;
			}
			else
			{
				System.out.println(fileName + " is being created");
				nameFound = true;
			}
		}
		return fileName;
	}

	public String getLevelString()
	{

		String level = Integer.toString(thePlane.getXSize()) + ","
				+ Integer.toString(thePlane.getYSize()) + ":";
		for (Actor A : levelGrid.getChildren())
		{
			GridImage item = (GridImage) A;
			System.out.println(item.cellValue);
			level = level + item.cellValue;

		}
		return level;
	}

	public void save()
	{
		String fileName = "";
		fileName = getLastLevelName();
		String level = getLevelString();
		System.out.println(level);
		System.out.println(fileName);
		levelFile = new LevelFile(myGame);
		levelFile.SaveLevel(fileName, level);
	}

	public String getLevelString(String file)
	{
		System.out.println("loading " + file);
		levelFile = new LevelFile(myGame);
		String level = levelFile.LoadLevel(file); // needs to have file selected
													// with grid
		return level;
	}

	public void load(String file)
	{
		levelGrid.clearChildren();
		String level = getLevelString(file);
		int i = 0;
		String xSizeString = "";
		String ySizeString = "";
		int index = 0;

		while (!String.valueOf(level.charAt(i)).equals(","))
		{
			xSizeString = xSizeString + String.valueOf(level.charAt(i));
			i++;
		}
		int x = Integer.parseInt(xSizeString);
		System.out.println(x);

		i = xSizeString.length() + 1;
		while (!String.valueOf(level.charAt(i)).equals(":"))
		{
			ySizeString = ySizeString + String.valueOf(level.charAt(i));
			i++;
		}
		int y = Integer.parseInt(ySizeString);
		System.out.println(y);

		thePlane.setSize(x, y);
		System.out.println(thePlane.getXSize());
		System.out.println(thePlane.getYSize());
		cell = new GridImage[x * y];
		System.out.println("New grid created " + (x * y));
		createGrid(cell);

		i = xSizeString.length() + ySizeString.length() + 2;
		System.out.println(index);

		levelGrid.reset();
		while (y > 0)
		{
			while (x > 0)
			{ // The x and y loops are here to help place in a grid
				int nextInt = level.charAt(i);
				if (nextInt == '0')
				{
					System.out.print(" " + 0 + " ");
					cell[index] = new GridImage(blankBlockStyle);
					levelGrid.add(cell[index]).size(screenHeight / 8);// .size(screenHeight/7);
					// add blank space
				}
				if (nextInt == '1')
				{
					System.out.print(" " + 1 + " ");
					cell[index] = new GridImage(groundBlockStyle);
					levelGrid.add(cell[index]).size(screenHeight / 8);
					// add ground block
				}
				if (nextInt == '2')
				{
					System.out.print(" " + 2 + " ");
					cell[index] = new GridImage(boxBlockStyle);
					levelGrid.add(cell[index]).size(screenHeight / 8);
					// add crate
				}
				if (nextInt == '3')
				{
					System.out.print(" " + 3 + " ");
					cell[index] = new GridImage(safeZoneBlockStyle);
					levelGrid.add(cell[index]).size(screenHeight / 8);
					// add character
				}
				index++;
				i++;
				x--;
			}
			System.out.println();
			levelGrid.row();
			x = thePlane.getXSize();
			y--;
		}
		addListeners(levelGrid);
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
