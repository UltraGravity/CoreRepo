package Dialog;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LoseDialog extends Dialog
{
	MyGame myGame;
	
	public LoseDialog(MyGame myGame, String title, Skin skin)
	{
		super(title, skin);
		
		this.myGame = myGame;
	}
	
}
