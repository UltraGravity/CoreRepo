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
	TextButton cancelButton;
	TextButton yesButton;
	TextButton noButton;
	Label blank;
	String levelName;
	Label levelNameLabel;
	
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
		
		if (levelName == "")
		{
			setupNewLevel();
		}
		
		else
		{
			setupOverwrite();
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
		saveButton = new TextButton("Save", skin);
		cancelButton = new TextButton("Cancel", skin);
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
	
	
	public void setupOverwrite()
	{
		this.clearChildren();
		window = new Table();
		table = new Table();
		table2 = new Table();
		levelNameLabel = new Label(levelName, skin);
		nameLabel = new Label("Already Exists. Overwrite?", skin);
		yesButton = new TextButton("Yes", skin);
		noButton = new TextButton("No", skin);
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
				
				if (fileName != "" && !file.checkIfExists(fileName))
				{
					myGame.levelEditorScreen.save(fileName);
					Gdx.input.setOnscreenKeyboardVisible(false);
					if (closeAfterSave)
					{
						myGame.changeToMainMenuScreen();
					}
				}
				else
				{
					hide();
					setupOverwrite();
				}
			}
		});
		
		cancelButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				hide();
			}
		});
	}
}