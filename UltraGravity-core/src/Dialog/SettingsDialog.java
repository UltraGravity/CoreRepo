package Dialog;

import Objects.GridImage;
import Objects.ThePlane;

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

public class SettingsDialog extends Dialog
{
	MyGame myGame;
	
	Table table;
	Table backTable;
	Table settingsTable;
	TextButton back;
	TextButton plusLeft;
	TextButton plusRight;
	TextButton minusLeft;
	TextButton minusRight;
	TextButton plusTop;
	TextButton plusBottom;
	TextButton minusTop;
	TextButton minusBottom;
	
	Label addRow;
	Label removeRow;
	Label addColumn;
	Label removeColumn;

	
	public SettingsDialog(MyGame myGame, String title, Skin skin)
	{
		super(title, skin);
		
		this.myGame = myGame;
		
		table = new Table();
		settingsTable = new Table();
		backTable = new Table();
		
		TextureAtlas buttonAtlas = myGame.assetLoader.mainMenuButtonAtlas;
		skin.addRegions(buttonAtlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = myGame.assetLoader.font;
		textButtonStyle.up = skin.getDrawable("Button");
		textButtonStyle.down = skin.getDrawable("Button-Pressed");
		
		
		back = new TextButton("Back to Map", skin);
		backTable.add(back);
			
		
		plusLeft = new TextButton("+", skin);
		plusRight = new TextButton("+", skin);
		plusTop = new TextButton("+", skin);
		plusBottom = new TextButton("+", skin);
		
		minusLeft = new TextButton("-", skin);
		minusRight = new TextButton("-", skin);
		minusTop = new TextButton("-", skin);
		minusBottom = new TextButton("-", skin);
		
		
		addRow = new Label(" Add Row", skin);
		removeRow = new Label("Remove Row", skin);
		addColumn = new Label("Add Column", skin);
		removeColumn = new Label(" Remove Column ", skin);
		
		settingsTable.add(plusLeft);
		settingsTable.add(addColumn);
		settingsTable.add(plusRight);
		settingsTable.row();
		settingsTable.add(minusLeft);
		settingsTable.add(removeColumn);
		settingsTable.add(minusRight);
		settingsTable.row();
		settingsTable.add(plusBottom);
		settingsTable.add(addRow);
		settingsTable.add(plusTop);
		settingsTable.row();
		settingsTable.add(minusBottom);
		settingsTable.add(removeRow);
		settingsTable.add(minusTop);
	
		table.add(backTable).top();
		table.row();
		table.add(settingsTable);
		settingsTable.pack();
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
		
		plusRight.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.levelEditorScreen.addColumn(true);
			}
		});

		plusLeft.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.levelEditorScreen.addColumn(false);
			}
		});
		
		minusRight.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.levelEditorScreen.removeColumn(true);
			}
		});

		minusLeft.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.levelEditorScreen.removeColumn(false);
			}
		});
		
		plusTop.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.levelEditorScreen.addRow(true);
			}
		});

		plusBottom.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.levelEditorScreen.addRow(false);
			}
		});
		
		minusTop.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.levelEditorScreen.removeRow(true);
			}
		});

		minusBottom.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.levelEditorScreen.removeRow(false);
			}
		});
	}
}
