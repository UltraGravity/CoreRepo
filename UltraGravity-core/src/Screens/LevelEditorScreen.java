///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 LEvelEditorScreen.java
//
//							Notes:
//Creates the screen that appears while the level editor is opened.
///////////////////////////////////////////////////////////////////////////

package Screens;

import Dialog.LoadLevelDialog;
import Dialog.PlayDialog;
import Dialog.SaveDialog;
import Dialog.SaveLevelDialog;
import Dialog.SettingsDialog;
import FileIO.GridResizer;
import FileIO.LevelFile;
import Objects.GridImage;
import Objects.ThePlane;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
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
	public String levelName = "";
	public int selectedBlock = 0;
	public BitmapFont font;
	public Stage stage;
	public Table windowTable;
	public Table toolTable;
	public Table levelGrid;
	public Table toolButtons;
	public ScrollPane toolScrollPane;
	public ScrollPane scrollPane;
	public TextButtonStyle textButtonStyle;
	public ImageButtonStyle selectedStyle;
	public TextureAtlas buttonAtlas;

	public Label ultraGravity;
	public LabelStyle ultraGravityFont;
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
	public ImageButton plusButton;
	public ImageButton minusButton;
	public ImageButton characterButton;
	public ImageButton tireTool;
	public ImageButton spikedBoxTool;
	public ImageButton metalBoxTool;
	public ImageButton buzzsawTool;
	public ImageButton iceTool;
	
	public GridImage cell[];
	GridResizer gridResizer;

	public LevelFile levelFile;
	public ThePlane thePlane;

	float currentScaleX = screenWidth;
	float currentScaleY = screenHeight;

	int zoomFactor = 8;

	public LevelEditorScreen(MyGame myGame)
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

		stage.draw();
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
		toolButtons = new Table();
		toolScrollPane = new ScrollPane(toolButtons);

		addTools();
		toolTable.center().bottom();

		thePlane = new ThePlane(myGame, 10, 5);
		cell = new GridImage[thePlane.getXSize() * thePlane.getYSize()];

		gridResizer = new GridResizer(myGame, thePlane);

		createGrid(cell);

		addListeners();

		windowTable.row();
		windowTable.add(toolTable).bottom();

		stage.addActor(windowTable);

		stage.addListener(new ActorGestureListener()
		{
			// public void pinch(InputEvent event, Vector2 initialPointer1,
			// Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2)
			// {
			//
			// float origDist = (float) (Math.sqrt(Math.pow(initialPointer1.x
			// - initialPointer2.x, 2)
			// + Math.pow(initialPointer1.y - initialPointer2.y, 2)));
			// float newDist = (float) (Math.sqrt(Math.pow(pointer1.x
			// - pointer2.x, 2)
			// + Math.pow(pointer1.y - pointer2.y, 2)));
			//
			// if (newDist > origDist)
			// {
			// if (zoomFactor >= 5)
			// {
			// zoomFactor -= 1;
			// for (Actor A : levelGrid.getChildren())
			// {
			// System.out.println("ZOOMING IN");
			// GridImage item = (GridImage) A;
			// item.setSize(screenHeight/zoomFactor, screenHeight/zoomFactor);
			// }
			// }
			// }
			//
			//
			// if (newDist < origDist)
			// {
			// if (zoomFactor <= 14)
			// {
			// zoomFactor += 1;
			// for (Actor A : levelGrid.getChildren())
			// {
			// System.out.println("ZOOMING OUT");
			// GridImage item = (GridImage) A;
			// item.setSize(screenHeight/zoomFactor, screenHeight/zoomFactor);
			// }
			// }
			// }
			//
			// levelGrid.pack();
			//
			// }
		});

		selectedStyle = myGame.assetLoader.characterBlockStyle;
		Gdx.input.setInputProcessor(stage);
		
		Gdx.input.setCatchBackKey(true);
		InputProcessor backProcessor = new InputAdapter(){
			@Override
			public boolean keyDown(int keycode) {

                if ((keycode == Keys.ESCAPE) || (keycode == Keys.BACK) )

                // Maybe perform other operations before exiting
                myGame.changeToMainMenuScreen();
                			
                return false;
            }
        };
        
        InputMultiplexer multiplexer = new InputMultiplexer(stage,backProcessor);
        Gdx.input.setInputProcessor(multiplexer);
        
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
		metalBoxTool = new ImageButton(myGame.assetLoader.metalBoxStyle);
		iceTool = new ImageButton(myGame.assetLoader.iceBoxStyle);
		spikedBoxTool = new ImageButton(myGame.assetLoader.spikedBoxStyle);
		buzzsawTool = new ImageButton(myGame.assetLoader.buzzsawStyle);
		tireTool = new ImageButton(myGame.assetLoader.tireStyle);
		characterButton = new ImageButton(
				myGame.assetLoader.characterButtonStyle);

		boxTool = new ImageButton(myGame.assetLoader.boxStyle);
		groundTool = new ImageButton(myGame.assetLoader.groundStyle);
		safeTool = new ImageButton(myGame.assetLoader.safeStyle);
		blankTool = new ImageButton(myGame.assetLoader.blankStyle);
		settingsButton = new ImageButton(myGame.assetLoader.settingsStyle);

		plusButton = new ImageButton(myGame.assetLoader.plusStyle);
		minusButton = new ImageButton(myGame.assetLoader.minusStyle);

		toolTable.add(backButton).size(screenHeight / 8);

		toolButtons.add(characterButton).size(screenHeight / 8);
		toolButtons.add(groundTool).size(screenHeight / 8);
		toolButtons.add(iceTool).size(screenHeight / 8);
		toolButtons.add(boxTool).size(screenHeight / 8);
		toolButtons.add(metalBoxTool).size(screenHeight / 8);
		toolButtons.add(spikedBoxTool).size(screenHeight / 8);
		toolButtons.add(buzzsawTool).size(screenHeight / 8);
		toolButtons.add(tireTool).size(screenHeight / 8);
		toolButtons.add(safeTool).size(screenHeight / 8);
		toolButtons.add(blankTool).size(screenHeight / 8);

		toolTable.add(toolScrollPane).width(screenWidth / 4);
		toolTable.add(settingsButton).size(screenHeight / 8);
		toolTable.add(minusButton).size(screenHeight / 8);
		toolTable.add(plusButton).size(screenHeight / 8);
		toolTable.add(saveButton).size(screenHeight / 8);
		toolTable.add(loadButton).size(screenHeight / 8);
		toolTable.add(playButton).size(screenHeight / 8);

		// So we have radio button effect
		ButtonGroup group = new ButtonGroup();
		group.add(boxTool);
		group.add(groundTool);
		group.add(safeTool);
		group.add(blankTool);
		group.add(characterButton);
		group.add(buzzsawTool);
		group.add(tireTool);
		group.add(spikedBoxTool);
		group.add(metalBoxTool);
		group.add(iceTool);

		characterButton.setChecked(true);

		settingsButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				SettingsDialog dialog = new SettingsDialog(myGame, "",
						myGame.assetLoader.uiSkin);
				dialog.show(stage);
			}
		});

		backButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				SaveDialog dialog = new SaveDialog(myGame, "",
						myGame.assetLoader.uiSkin);
				dialog.show(stage);
				dialog.setPosition(dialog.getX(), screenHeight - screenHeight
						/ 2);
			}
		});

		groundTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				if (groundTool.isChecked())
				{
					myGame.playClick();
				}
				selectedStyle = myGame.assetLoader.groundBlockStyle;
			}
		});

		buzzsawTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				if (buzzsawTool.isChecked())
				{
					myGame.playClick();
				}
				selectedStyle = myGame.assetLoader.buzzsawBlockStyle;
			}
		});

		tireTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				if (tireTool.isChecked())
				{
					myGame.playClick();
				}
				selectedStyle = myGame.assetLoader.tireBlockStyle;
			}
		});

		metalBoxTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				if (metalBoxTool.isChecked())
				{
					myGame.playClick();
				}
				selectedStyle = myGame.assetLoader.metalBoxBlockStyle;
			}
		});

		iceTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				if (iceTool.isChecked())
				{
					myGame.playClick();
				}
				selectedStyle = myGame.assetLoader.iceBlockStyle;
			}
		});
		spikedBoxTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				if (spikedBoxTool.isChecked())
				{
					myGame.playClick();
				}
				selectedStyle = myGame.assetLoader.spikedBlockStyle;
			}
		});

		boxTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				if (boxTool.isChecked())
				{
					myGame.playClick();
				}
				selectedStyle = myGame.assetLoader.boxBlockStyle;
			}
		});

		safeTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				if (safeTool.isChecked())
				{
					myGame.playClick();
				}
				selectedStyle = myGame.assetLoader.safeZoneBlockStyle;
			}
		});

		blankTool.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				if (blankTool.isChecked())
				{
					myGame.playClick();
				}
				selectedStyle = myGame.assetLoader.blankBlockStyle;
			}
		});

		characterButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				if (characterButton.isChecked())
				{
					myGame.playClick();
				}
				selectedStyle = myGame.assetLoader.characterBlockStyle;
			}
		});

		saveButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				saveDialog(false);
			}
		});

		minusButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				if (zoomFactor <= 14)
				{
					zoomFactor += 1;
				}
				createGrid(cell);
				addListeners();
				System.out.println(zoomFactor);
			}
		});

		plusButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				if (zoomFactor >= 5)
				{
					zoomFactor -= 1;
				}
				createGrid(cell);
				addListeners();
				System.out.println(zoomFactor);
			}
		});

		loadButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				LoadLevelDialog dialog = new LoadLevelDialog(myGame, "",
						myGame.assetLoader.uiSkin);
				dialog.show(stage);

				if (dialog.getWidth() < screenWidth)
				{
					dialog.pack();
				}
				else
				{
					dialog.setWidth(screenWidth);
				}
			}
		});

		playButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				if (levelName.equals(""))
				{
					PlayDialog dialog = new PlayDialog(myGame, "",
							myGame.assetLoader.uiSkin);
					dialog.show(stage);
				}
				else
				{
					levelFile = new LevelFile(myGame);
					saveDialog(false);
					myGame.changeToGameScreen(levelName, "Levels");
				}
			}
		});
	}

	public void saveDialog(boolean closeAfterSave)
	{
		SaveLevelDialog dialog = new SaveLevelDialog(myGame,
				myGame.assetLoader.uiSkin, levelName, closeAfterSave);
		dialog.show(stage);
		dialog.setPosition(dialog.getX(), screenHeight - screenHeight / 3);
	}

	public void addListeners()
	{
		ChangeListener listener = new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				GridImage image = (GridImage) actor;
				image.setStyle(selectedStyle);
				myGame.playClick();

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
				if (selectedStyle == myGame.assetLoader.characterBlockStyle)
				{
					image.cellValue = 4;
				}
				if (selectedStyle == myGame.assetLoader.metalBoxBlockStyle)
				{
					image.cellValue = 5;
				}
				if (selectedStyle == myGame.assetLoader.spikedBlockStyle)
				{
					image.cellValue = 6;
				}
				if (selectedStyle == myGame.assetLoader.buzzsawBlockStyle)
				{
					image.cellValue = 7;
				}
				if (selectedStyle == myGame.assetLoader.tireBlockStyle)
				{
					image.cellValue = 8;
				}
				if (selectedStyle == myGame.assetLoader.iceBlockStyle)
				{
					image.cellValue = 9;
				}
			}
		};

		levelGrid.addListener(listener);
	}

	public void createGrid(GridImage[] grid)
	{
		levelGrid.clearChildren();
		levelGrid.clear();
		int index = 0;
		for (int y = 0; y < thePlane.getYSize(); y++)
		{
			for (int x = 0; x < thePlane.getXSize(); x++)
			{
				if (grid[index] == null)
				{
					grid[index] = new GridImage(
							myGame.assetLoader.blankBlockStyle);
					grid[index].cellValue = 0;
				}
				else if (grid[index].getValue() == 0)
				{
					grid[index] = new GridImage(
							myGame.assetLoader.blankBlockStyle);
					grid[index].cellValue = 0;
				}
				else if (grid[index].getValue() == 1)
				{
					grid[index] = new GridImage(
							myGame.assetLoader.groundBlockStyle);
					grid[index].cellValue = 1;
				}
				else if (grid[index].getValue() == 2)
				{
					grid[index] = new GridImage(
							myGame.assetLoader.boxBlockStyle);
					grid[index].cellValue = 2;
				}
				else if (grid[index].getValue() == 3)
				{
					grid[index] = new GridImage(
							myGame.assetLoader.safeZoneBlockStyle);
					grid[index].cellValue = 3;
				}
				else if (grid[index].getValue() == 4)
				{
					grid[index] = new GridImage(
							myGame.assetLoader.characterBlockStyle);
					grid[index].cellValue = 4;
				}
				else if (grid[index].getValue() == 5)
				{
					grid[index] = new GridImage(
							myGame.assetLoader.metalBoxBlockStyle);
					grid[index].cellValue = 5;
				}
				else if (grid[index].getValue() == 6)
				{
					grid[index] = new GridImage(
							myGame.assetLoader.spikedBlockStyle);
					grid[index].cellValue = 6;
				}
				else if (grid[index].getValue() == 7)
				{
					grid[index] = new GridImage(
							myGame.assetLoader.buzzsawBlockStyle);
					grid[index].cellValue = 7;
				}
				else if (grid[index].getValue() == 8)
				{
					grid[index] = new GridImage(
							myGame.assetLoader.tireBlockStyle);
					grid[index].cellValue = 8;
				}
				else if (grid[index].getValue() == 9)
				{
					grid[index] = new GridImage(
							myGame.assetLoader.iceBlockStyle);
					grid[index].cellValue = 9;
				}
				levelGrid.add(cell[index]).size(screenHeight / zoomFactor);
				index++;
			}
			levelGrid.row();
		}
		// addListeners();
	}

	public String getLastLevel()
	{
		String level = "";
		level = levelFile.LoadLevel(levelFile.getLastLevelName(), "Levels");
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

	public void save(String levelName)
	{
		levelFile = new LevelFile(myGame);
		String level = getLevelString();
		levelFile.SaveLevel(levelName, level, "Levels");
	}

	public void load(String file)
	{
		levelGrid.clearChildren();
		levelFile = new LevelFile(myGame);
		cell = levelFile.getLevelGrid(file, thePlane, "Levels");
		createGrid(cell);
		addListeners();
	}

	public void addColumn(boolean addToRightSide)
	{
		cell = gridResizer.addColumn(cell, addToRightSide, 0);
		createGrid(cell);
	}

	public void removeColumn(boolean removeFromRightSide)
	{
		cell = gridResizer.removeColumn(cell, removeFromRightSide);
		createGrid(cell);
	}

	public void addRow(boolean addToTop)
	{
		cell = gridResizer.addRow(cell, addToTop, 0);
		createGrid(cell);
	}

	public void removeRow(boolean removeFromTop)
	{
		cell = gridResizer.removeRow(cell, removeFromTop);
		createGrid(cell);
	}

	public void hide()
	{
		System.out.println("Disposing Level Editor Screen");
		this.dispose();
	}

	public void dispose()
	{
		super.dispose();
		stage.dispose();
		font = null;
	}

	public void pause()
	{
	}

	public void resume()
	{
	}

	public void resize(int width, int height)
	{
	}

}
