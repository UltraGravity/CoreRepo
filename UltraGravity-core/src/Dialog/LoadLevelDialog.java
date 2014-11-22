package Dialog;

import Objects.LevelButton;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class LoadLevelDialog extends Dialog
{
	MyGame myGame;
	
	Table levelTable;
	Table backTable;
	ScrollPane scrollPane;
	TextButton back;
	FileHandle[] files;
	
	public LoadLevelDialog(MyGame myGame, String title, Skin skin)
	{
		super(title, skin);
		this.myGame = myGame;
		

		levelTable = new Table();
		backTable = new Table();
		scrollPane = new ScrollPane(levelTable);

		
		TextureAtlas buttonAtlas = myGame.assetLoader.mainMenuButtonAtlas;
		skin.addRegions(buttonAtlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = myGame.assetLoader.font;
		textButtonStyle.up = skin.getDrawable("Button");
		textButtonStyle.down = skin.getDrawable("Button-Pressed");
		
		
		back = new TextButton("Back", skin);
		backTable.add(back);
		levelTable.row();
		
		
		
		files = Gdx.files.local("Levels").list();
		int numFiles = files.length;
		
		for (int i = 0; i < numFiles; i++)
		{
			System.out.println(files[i].name());
		}
		
	
		for (int i = 0; i < numFiles; i++)
		{
			String name = files[i].name();
			name = name.replace(".txt", "");
			LevelButton button = new LevelButton(name, textButtonStyle, files[i].name(), "Levels");
			levelTable.add(button).height(myGame.screenWidth/5);
		}	
		
		//backTable.setFillParent(true);
		
		backTable.row();
		backTable.add(scrollPane);
		
		this.add(backTable);
		
		setupButtons();
	}
	
	
	public void setupButtons()
	{
		back.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				hide();
			}
		});

		levelTable.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	            LevelButton button = (LevelButton) actor;
	            myGame.levelEditorScreen.levelName = button.getLevelName();
	        	myGame.levelEditorScreen.load(button.getLevelName());
	        	hide();
	        }});
	}
	
	

}
