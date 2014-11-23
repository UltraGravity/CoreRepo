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
	
	Table table;
	Label nameLabel;
	TextField textBox;
	TextButton saveButton;
	TextButton cancelButton;
	
	public SaveLevelDialog(MyGame myGame, Skin skin, String levelName)
	{
		super("", skin);
		this.myGame = myGame;
		
		
		if (levelName == "")
		{
			System.out.println("New Level!");
			table = new Table();
			nameLabel = new Label("Level Name: ", skin);
			textBox = new TextField("", skin);
			saveButton = new TextButton("Save", skin);
			cancelButton = new TextButton("Cancel", skin);
			table.add(nameLabel);
			table.add(textBox);
			table.row();
			table.add(cancelButton);
			table.add(saveButton);
			this.add(table);
			setupButtons();
		}
		
		else
		{
			// Change this to overwrite? yes or no
			System.out.println("New Level!");
			table = new Table();
			nameLabel = new Label("Level Name: ", skin);
			textBox = new TextField("", skin);
			saveButton = new TextButton("Save", skin);
			cancelButton = new TextButton("Cancel", skin);
			table.add(nameLabel);
			table.add(textBox);
			table.row();
			table.add(cancelButton);
			table.add(saveButton);
			this.add(table);
			setupButtons();
		}
		

	}
	
	
	public void setupButtons()
	{
		saveButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				String fileName = textBox.getText();
				LevelFile file = new LevelFile(myGame);
				
				if (fileName != "" && file.checkIfExists(fileName))
				{
					myGame.levelEditorScreen.save(fileName);
					Gdx.input.setOnscreenKeyboardVisible(false);
					hide();
				}
			}
		});
		
		cancelButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				hide();
			}
		});
	}
}