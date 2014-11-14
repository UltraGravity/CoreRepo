package Screens;

import Dialog.LoadLevelDialog;
import Dialog.SaveDialog;
import Dialog.SettingsDialog;
import FileIO.LevelFile;
import Objects.GridImage;
import Objects.ThePlane;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class LevelEditorScreen extends GenericScreen
{

	public int selectedBlock = 0;
	public BitmapFont font;
	public Stage stage;
	public Skin buttonSkin;
	public Table windowTable;
	public Table toolTable;
	public Table levelGrid;
	public ScrollPane scrollPane;
	public TextButtonStyle textButtonStyle;
	public TextureAtlas buttonAtlas;
	
	
	public ImageButton groundButton;
	public ImageButton boxButton;
	public ImageButton safeZoneButton;
	public ImageButton blankButton;
	public ImageButton saveButton;
	public ImageButton loadButton;
	public ImageButton playButton;
	public ImageButton backButton;
	public ImageButton settingsButton;
	public ImageButton boxTool;
	public ImageButton groundTool;
	public ImageButton safeTool;
	public ImageButton blankTool;

	
	

	public GridImage cell[];
	public ImageButtonStyle selectedStyle;

	public Label ultraGravity;
	public LabelStyle ultraGravityFont;

	public LevelFile levelFile;
	public ThePlane thePlane;

	float currentScaleX = screenWidth;
	float currentScaleY = screenHeight;

	public LevelEditorScreen(MyGame myGame)
	{
		super(myGame);
	}

	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, .25f, .25f, 1);
		Gdx.gl.glClearColor(.65f, .65f, .65f, 1);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		stage.act();

		batch.begin();
			// scrollPane.debug();
			// scrollPane.debugAll();
			// windowTable.debug();
			// windowTable.debugTable();
			// toolTable.debug();
			// toolTable.debugTable();
			// levelGrid.debug();
			// levelGrid.debugTable();
			stage.draw();
		batch.end();
	}

	public void show()
	{
		stage = new Stage();		
		windowTable = new Table();
		levelGrid = new Table();
		toolTable = new Table();
		scrollPane = new ScrollPane(levelGrid);
		windowTable.setHeight(screenHeight);
		windowTable.setWidth(screenWidth);
		windowTable.add(scrollPane).height(screenHeight - screenHeight / 8);
		
		addTools();
		toolTable.center().bottom();
	
		thePlane = new ThePlane(10, 5);
		// world = new World(new Vector2(0,-10), true);
		cell = new GridImage[thePlane.getXSize() * thePlane.getYSize()];
		createGrid(cell);

		windowTable.row();
		windowTable.add(toolTable).bottom();

		addListeners(levelGrid);

		stage.addActor(windowTable);
		
		
		InputProcessor backProcessor = new InputAdapter()
		{
			public boolean keyDown(int keycode)
			{
				if ((keycode == Keys.BACK) || (keycode == Keys.BACKSPACE))
				{
					myGame.changeToMainMenuScreen();
				}
				return false;
			}
		};

		stage.addListener(new ActorGestureListener()
		{
			public void pinch(InputEvent event, Vector2 initialPointer1,
					Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2)
			{
				// System.out.println(initialPointer1);
				//
				//
				// float origDist = (float)(Math.sqrt(Math.pow(initialPointer1.x
				// - initialPointer2.x, 2) +
				// Math.pow(initialPointer1.y - initialPointer2.y, 2)));
				// float newDist = (float)(Math.sqrt(Math.pow(pointer1.x -
				// pointer2.x, 2) + Math.pow(pointer1.y - pointer2.y,
				// 2)));
				//
				//
				// if (newDist > origDist) // Add a little bit of error in there
				// {
				// currentScaleX += newDist;
				// currentScaleY += newDist;
				// windowTable.setScale(currentScaleX);
				// }
				// if (newDist < origDist)
				// {
				// currentScaleX -= newDist;
				// currentScaleY -= newDist;
				// windowTable.setScale(currentScaleX);
				// }
				//
				// //System.out.println(origDist + ",  " + (origDist + (origDist
				// * .20)) + ",  " + newDist);
				//
				//
				// windowTable.pack();
			}
		});
	
		selectedStyle = myGame.assetLoader.blankBlockStyle;
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
	}

	private void addTools()
	{
		// Buttons
		groundButton = new ImageButton(myGame.assetLoader.groundBlockStyle);
		boxButton = new ImageButton(myGame.assetLoader.boxBlockStyle);
		safeZoneButton = new ImageButton(myGame.assetLoader.safeZoneBlockStyle);
		blankButton = new ImageButton(myGame.assetLoader.blankBlockStyle);
		saveButton = new ImageButton(myGame.assetLoader.saveButtonStyle);
		loadButton = new ImageButton(myGame.assetLoader.loadButtonStyle);
		backButton = new ImageButton(myGame.assetLoader.backButtonStyle);
		playButton = new ImageButton(myGame.assetLoader.playButtonStyle);
		
		boxTool = new ImageButton(myGame.assetLoader.boxStyle);
		groundTool = new ImageButton(myGame.assetLoader.groundStyle);
		safeTool = new ImageButton(myGame.assetLoader.safeStyle);
		blankTool = new ImageButton(myGame.assetLoader.blankStyle);
		settingsButton = new ImageButton(myGame.assetLoader.settingsStyle);
		
		toolTable.add(backButton).size(screenHeight / 8);
		toolTable.add(groundTool).size(screenHeight / 8);
		toolTable.add(boxTool).size(screenHeight / 8);
		toolTable.add(safeTool).size(screenHeight / 8);
		toolTable.add(blankTool).size(screenHeight / 8);
		toolTable.add(settingsButton).size(screenHeight / 8);
		toolTable.add(saveButton).size(screenHeight / 8);
		toolTable.add(loadButton).size(screenHeight / 8);
		toolTable.add(playButton).size(screenHeight / 8);
		

		settingsButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				SettingsDialog dialog = new SettingsDialog(myGame, "", myGame.assetLoader.uiSkin);
				dialog.show(stage);
			}
		});
		
		
		backButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				SaveDialog dialog = new SaveDialog(myGame, "", myGame.assetLoader.uiSkin);
				dialog.show(stage);
			}
		});

		groundTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				selectedStyle = myGame.assetLoader.groundBlockStyle;
				groundTool.setChecked(true);
			}
		});

		boxTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				selectedStyle = myGame.assetLoader.boxBlockStyle;
			}
		});

		safeTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				selectedStyle = myGame.assetLoader.safeZoneBlockStyle;
			}
		});

		blankTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				selectedStyle = myGame.assetLoader.blankBlockStyle;
			}
		});

		saveButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				save();
			}
		});

		loadButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				LoadLevelDialog dialog = new LoadLevelDialog(myGame, "", myGame.assetLoader.uiSkin);
				dialog.show(stage);
				
				
				if (dialog.getHeight() < screenHeight)
				{
					dialog.pack();
				}
				else
				{
					dialog.setHeight(screenHeight);
				}
			}
		});

		playButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
			  levelFile = new LevelFile(myGame);
			  System.out.println(levelFile.getLastLevelName());
				myGame.changeToGameScreen(levelFile.LoadLevel(levelFile.getLastLevelName()));
			}
		});
	}
	
	public void addListeners(Table levelGrid2)
	{
		levelGrid.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				GridImage image = (GridImage) actor;
				image.setStyle(selectedStyle);

				if (selectedStyle == myGame.assetLoader.blankBlockStyle)
				{
					image.cellValue = 0;
				}
				if (selectedStyle == myGame.assetLoader.groundBlockStyle)
				{
					image.cellValue = 1;
				}
				if (selectedStyle == myGame.assetLoader.boxBlockStyle)
				{
					image.cellValue = 2;
				}
				if (selectedStyle == myGame.assetLoader.safeZoneBlockStyle)
				{
					image.cellValue = 3;
				}
			}
		});
	}

	public void createGrid(GridImage[] cell2)
	{
		int index = 0;
		for (int y = 0; y < thePlane.getYSize(); y++)
		{
			for (int x = 0; x < thePlane.getXSize(); x++)
			{
				cell[index] = new GridImage(myGame.assetLoader.blankBlockStyle);
				levelGrid.add(cell[index]).size(screenHeight / 8);
				index++;
			}
			levelGrid.row();
		}

	}

	public String getLastLevel()
	{
		String level = "";
		level = levelFile.LoadLevel(levelFile.getLastLevelName());
		return level;
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
		levelFile = new LevelFile(myGame);
		fileName = levelFile.getNextLevelName();
		String level = getLevelString();
		System.out.println(level);
		System.out.println(fileName);
		levelFile.SaveLevel(fileName, level);
	}

	public void load(String file)
	{
		levelGrid.clearChildren();
		levelFile = new LevelFile(myGame);
		String level = levelFile.LoadLevel(file);

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
					cell[index] = new GridImage(
							myGame.assetLoader.blankBlockStyle);
					levelGrid.add(cell[index]).size(screenHeight / 8);// .size(screenHeight/7);
					// add blank space
				}
				if (nextInt == '1')
				{
					System.out.print(" " + 1 + " ");
					cell[index] = new GridImage(
							myGame.assetLoader.groundBlockStyle);
					levelGrid.add(cell[index]).size(screenHeight / 8);
					// add ground block
				}
				if (nextInt == '2')
				{
					System.out.print(" " + 2 + " ");
					cell[index] = new GridImage(
							myGame.assetLoader.boxBlockStyle);
					levelGrid.add(cell[index]).size(screenHeight / 8);
					// add crate
				}
				if (nextInt == '3')
				{
					System.out.print(" " + 3 + " ");
					cell[index] = new GridImage(
							myGame.assetLoader.safeZoneBlockStyle);
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
		save();
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
