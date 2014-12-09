///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 WinDialog.java
//
//							Notes:
//Pop up screen appears when a user wins the current level. This dialog 
//congratulates the user then offers to return the level select screen.
//or to return to the Main Menu
///////////////////////////////////////////////////////////////////////////

package Dialog;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class WinDialog extends Dialog
{
	MyGame myGame;
	TextButton mainMenu;
	TextButton nextLevel;
	Label text;
	
	public WinDialog(MyGame myGame, String title, Skin skin)
	{
		super(title, skin);
		
		this.myGame = myGame;
		TextButtonStyle textButtonStyle = myGame.assetLoader.textButtonStyle;
		
		
		Table table = new Table();
		text = new Label("YOU WIN", myGame.assetLoader.uiSkin);
		nextLevel = new TextButton("Select Next Level", textButtonStyle);
		mainMenu = new TextButton("Main Menu", textButtonStyle);
		
		table.add(text).row();
		table.add(nextLevel).width((float)(myGame.screenWidth/2)).row();
		table.add(mainMenu).width((float)(myGame.screenWidth/2));
		
		this.add(table);
		setupButtons();
	}
	
	
	public void setupButtons()
	{
		nextLevel.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.nextLevel();
			}
		});
		
		mainMenu.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.changeToMainMenuScreen();
			}
		});
		
		
	}
}