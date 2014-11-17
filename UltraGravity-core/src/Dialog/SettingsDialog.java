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
	TextButton plusX;
	TextButton minusX;
	TextButton plusY;
	TextButton minusY;
	Label widthLabel;
	Label heightLabel;

	
	public SettingsDialog(MyGame myGame, String title, Skin skin)
	{
		super(title, skin);
		
		
		this.myGame = myGame;

		// Scale
		float s = myGame.screenHeight;
		
		table = new Table();
		settingsTable = new Table();
		backTable = new Table();
		
		TextureAtlas buttonAtlas = myGame.assetLoader.mainMenuButtonAtlas;
		skin.addRegions(buttonAtlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = myGame.assetLoader.font;
		textButtonStyle.up = skin.getDrawable("Button");
		textButtonStyle.down = skin.getDrawable("Button-Pressed");
		
		
		back = new TextButton("Select", skin);
		backTable.add(back);
		//back.getLabel().setFontScale(s/640);
			
		
		minusX = new TextButton("-", skin);
		widthLabel = new Label(" Width ", skin);
		plusX = new TextButton("+", skin);
		
		settingsTable.add(minusX);
		settingsTable.add(widthLabel);
		settingsTable.add(plusX);
		settingsTable.row();
		
		minusY = new TextButton("-", skin);
		heightLabel = new Label(" Height ", skin);
		plusY = new TextButton("+", skin);
		
		
		
		
		//heightLabel.setFontScale(s/640);
		//widthLabel.setFontScale(s/640);
		//minusX.getLabel().setFontScale(s/640);
		//minusY.getLabel().setFontScale(s/640);
		//plusX.getLabel().setFontScale(s/640);
		//plusY.getLabel().setFontScale(s/640);
		

		
		settingsTable.add(minusY);
		settingsTable.add(heightLabel);
		settingsTable.add(plusY);
		
		
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
				myGame.levelEditorScreen.addListeners(myGame.levelEditorScreen.levelGrid);
				hide();
			}
		});

		minusX.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				if (myGame.levelEditorScreen.thePlane.getXSize() != 1)
				{
					int x = myGame.levelEditorScreen.thePlane.getXSize() - 1;					
					myGame.levelEditorScreen.levelGrid.clear();
					myGame.levelEditorScreen.thePlane.setSize(x, myGame.levelEditorScreen.thePlane.getYSize());
					myGame.levelEditorScreen.cell = new GridImage[myGame.levelEditorScreen.thePlane.getXSize() * myGame.levelEditorScreen.thePlane.getYSize()];
					myGame.levelEditorScreen.createGrid(myGame.levelEditorScreen.cell);
				}
			}
		});
		
		minusY.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				if (myGame.levelEditorScreen.thePlane.getYSize() != 1)
				{
					int y = myGame.levelEditorScreen.thePlane.getYSize() - 1;					
					myGame.levelEditorScreen.levelGrid.clear();
					myGame.levelEditorScreen.thePlane.setSize(myGame.levelEditorScreen.thePlane.getXSize(), y);
					myGame.levelEditorScreen.cell = new GridImage[myGame.levelEditorScreen.thePlane.getXSize() * myGame.levelEditorScreen.thePlane.getYSize()];
					myGame.levelEditorScreen.createGrid(myGame.levelEditorScreen.cell);
				}
			}
		});
		

		plusY.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
					int y = myGame.levelEditorScreen.thePlane.getYSize() + 1;					
					myGame.levelEditorScreen.levelGrid.clear();
					myGame.levelEditorScreen.thePlane.setSize(myGame.levelEditorScreen.thePlane.getXSize(), y);
					myGame.levelEditorScreen.cell = new GridImage[myGame.levelEditorScreen.thePlane.getXSize() * myGame.levelEditorScreen.thePlane.getYSize()];
					myGame.levelEditorScreen.createGrid(myGame.levelEditorScreen.cell);
			}
		});
		
		plusX.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
					int x = myGame.levelEditorScreen.thePlane.getXSize() + 1;					
					myGame.levelEditorScreen.levelGrid.clear();
					myGame.levelEditorScreen.thePlane.setSize(x, myGame.levelEditorScreen.thePlane.getYSize());
					myGame.levelEditorScreen.cell = new GridImage[myGame.levelEditorScreen.thePlane.getXSize() * myGame.levelEditorScreen.thePlane.getYSize()];
					myGame.levelEditorScreen.createGrid(myGame.levelEditorScreen.cell);
			}
		});
		
	}
	
	

}
