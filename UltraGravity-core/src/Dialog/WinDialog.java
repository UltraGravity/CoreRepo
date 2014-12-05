package Dialog;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
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
	
	public WinDialog(MyGame myGame, String title, Skin skin)
	{
		super(title, skin);
		
		this.myGame = myGame;
		TextButtonStyle textButtonStyle = myGame.assetLoader.textButtonStyle;
		
		Table table = new Table();
		nextLevel = new TextButton("Play Next Level", textButtonStyle);
		mainMenu = new TextButton("Main Menu", textButtonStyle);
		
		table.add(nextLevel).width((float)(myGame.screenWidth/2.5)).row();
		table.add(mainMenu).width((float)(myGame.screenWidth/2.5));
		
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