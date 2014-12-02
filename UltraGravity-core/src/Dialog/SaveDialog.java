package Dialog;

import Objects.GridImage;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class SaveDialog extends Dialog
{
	MyGame myGame;
	
	Table table;
	Table backTable;
	Table yesNoTable;
	TextButton yes;
	TextButton no;
	TextButton back;
	Label saveLabel;
	
	public SaveDialog(MyGame myGame, String title, Skin skin)
	{
		super(title, skin);
		this.myGame = myGame;
		
		table = new Table();
		yesNoTable = new Table();
		backTable = new Table();
		
		TextureAtlas buttonAtlas = myGame.assetLoader.mainMenuButtonAtlas;
		skin.addRegions(buttonAtlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = myGame.assetLoader.font;
		textButtonStyle.up = skin.getDrawable("Button");
		textButtonStyle.down = skin.getDrawable("Button-Pressed");
		
		
		back = new TextButton("Back", skin);
		backTable.add(back);
		backTable.row();

		saveLabel = new Label("Save Level?", skin);
		backTable.add(saveLabel);
		
		yes = new TextButton("Yes", skin);
		no = new TextButton("No", skin);

		yesNoTable.add(yes).width((float)myGame.screenWidth/6);
		yesNoTable.add(no).width((float)myGame.screenWidth/6);;

		table.add(backTable);
		table.row();
		table.add(yesNoTable);

		
	
		this.add(table);
		
		setupButtons();
	}
	
	
	public void setupButtons()
	{
		back.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.levelEditorScreen.addListeners();
				hide();
			}
		});
		
		yes.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.levelEditorScreen.saveDialog(true);
				hide();
			}
		});
		
		no.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				hide();
				myGame.changeToMainMenuScreen();
			}
		});
	}
	
	

}