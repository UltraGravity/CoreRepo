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

public class PlayDialog extends Dialog
{
	MyGame myGame;
	TextButton okay;
	Label text;
	
	public PlayDialog(MyGame myGame, String title, Skin skin)
	{
		super(title, skin);
		
		this.myGame = myGame;
		TextButtonStyle textButtonStyle = myGame.assetLoader.textButtonStyle;
		Label text = new Label("You need to save this level!", skin);
		
		Table table = new Table();
	
		okay = new TextButton("Okay", textButtonStyle);
		
		table.add(text);
		table.row();
		table.add(okay).width((float)(myGame.screenWidth/2.5));
		
		this.add(table);
		setupButtons();
	}
	
	
	public void setupButtons()
	{
		
		okay.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				hide();
			}
		});
		
		
	}
}