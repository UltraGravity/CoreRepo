///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 SaveLevelDialog.java
//
//							Notes:
//Pop up screen that appears when the save button within the level editor
//is pressed. This dialog is the same as the Save Dialog.
///////////////////////////////////////////////////////////////////////////

package Dialog;

import FileIO.LevelFile;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class SaveLevelDialog extends Dialog
{
	MyGame myGame;
	Table window;
	Table table;
	Table table2;
	Label nameLabel;
	Skin skin;
	TextField textBox;
	TextButton saveButton;
	TextButton okayButton;
	TextButton cancelButton;
	TextButton yesButton;
	TextButton noButton;
	TextButton dontSaveButton;
	Label blank;
	String levelName;
	Label levelNameLabel;
	TextButtonStyle textButtonStyle;
	boolean closeAfterSave;
	
	Label alreadyExists;
	
	public SaveLevelDialog(MyGame myGame, Skin skin, String levelName, boolean closeAfterSave)
	{
		super("", skin);
		this.myGame = myGame;
		this.skin = skin;
		this.levelName = levelName.replace(".txt", "");
		LevelFile levelFile = new LevelFile(myGame);
		this.closeAfterSave = closeAfterSave;
		textButtonStyle = myGame.assetLoader.textButtonStyle;
		
		if (levelName == "")
		{
			setupNewLevel();
		}
		
		else
		{
			setupOverwrite(false);
		}
	}
	
	
	public void setupNewLevel()
	{
		this.clearChildren();
		window = new Table();
		table = new Table();
		table2 = new Table();
		nameLabel = new Label("Level Name ", skin);
		textBox = new TextField("", skin);
		saveButton = new TextButton("Save", textButtonStyle);
		cancelButton = new TextButton("Cancel", textButtonStyle);
		blank = new Label("     ", skin);
		table.add(nameLabel);
		table.add(textBox).width(myGame.screenWidth/2);
		table.row();
		
		table2.add(cancelButton).width(myGame.screenWidth/3).center();
		table2.add(saveButton).width(myGame.screenWidth/3).center();
			
		window.add(table);
		window.row();
		window.add(table2);
		this.add(window);
		setupNewLevelButtons();
		show(myGame.levelEditorScreen.stage);
		setPosition(this.getX(), myGame.screenHeight - myGame.screenHeight/3);
	}
	
	
	public void setupOverwrite(boolean blankString)
	{
		if (blankString)
		{
			this.clearChildren();
			window = new Table();
			table = new Table();
			table2 = new Table();
			levelNameLabel = new Label(levelName, skin);
			nameLabel = new Label("You cannot have a blank file name!", skin);
			okayButton = new TextButton("Okay", textButtonStyle);
			yesButton = new TextButton("Yes", textButtonStyle);
			dontSaveButton = new TextButton("Don't Save", textButtonStyle);
			noButton = new TextButton("No", textButtonStyle);
			table.add(levelNameLabel);
			table.row();
			table.add(nameLabel);
			table2.add(okayButton).width(myGame.screenWidth/3).center();
			table2.add(dontSaveButton).width(myGame.screenWidth/3).center();
			window.add(table);
			window.row();
			window.add(table2);
			this.add(window);
			setupOverwriteButtons();
			pack();
			show(myGame.levelEditorScreen.stage);
			setPosition(this.getX(), myGame.screenHeight - myGame.screenHeight/3);
		}
		else
		{
			this.clearChildren();
			window = new Table();
			table = new Table();
			table2 = new Table();
			levelNameLabel = new Label(levelName, skin);
			nameLabel = new Label("Already Exists. Overwrite?", skin);
			yesButton = new TextButton("Yes", textButtonStyle);
			noButton = new TextButton("No", textButtonStyle);
			okayButton = new TextButton("Okay", textButtonStyle);
			dontSaveButton = new TextButton("Don't Save", textButtonStyle);
			table.add(levelNameLabel);
			table.row();
			table.add(nameLabel);
			table2.add(yesButton).width(myGame.screenWidth/4).center();
			table2.add(noButton).width(myGame.screenWidth/4).center();
			window.add(table);
			window.row();
			window.add(table2);
			this.add(window);
			setupOverwriteButtons();
			pack();
			show(myGame.levelEditorScreen.stage);
			setPosition(this.getX(), myGame.screenHeight - myGame.screenHeight/3);
		}
	}
	
	public void setupOverwriteButtons()
	{
		yesButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				LevelFile file = new LevelFile(myGame);
				myGame.levelEditorScreen.save(levelName);
				myGame.levelEditorScreen.levelName = levelName + ".txt";
				Gdx.input.setOnscreenKeyboardVisible(false);
				
				if (closeAfterSave)
				{
					hide();
					myGame.changeToMainMenuScreen();
				}
				
				hide();
			}
		});
		
		noButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				setupNewLevel();
				Gdx.input.setOnscreenKeyboardVisible(false);
			}
		});
		
		okayButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				setupNewLevel();
				Gdx.input.setOnscreenKeyboardVisible(false);
			}
		});
		
		dontSaveButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				hide();
				Gdx.input.setOnscreenKeyboardVisible(false);
			}
		});
	}
	
	public void setupNewLevelButtons()
	{
		saveButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				String fileName = textBox.getText();
				levelName = fileName;
				LevelFile file = new LevelFile(myGame);
				Gdx.input.setOnscreenKeyboardVisible(false);
				
				
				if (fileName.equals(""))
				{
					setupOverwrite(true);
				}
				else if (!file.checkIfExists(fileName))
				{
					myGame.levelEditorScreen.save(fileName);
					if (closeAfterSave)
					{
						myGame.changeToMainMenuScreen();
					}
					hide();
					myGame.levelEditorScreen.levelName = fileName + ".txt";
				}
				else
				{
					hide();
					setupOverwrite(false);
				}
				
				
			}
		});
		
		cancelButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				Gdx.input.setOnscreenKeyboardVisible(false);
				hide();
			}
		});
	}
}