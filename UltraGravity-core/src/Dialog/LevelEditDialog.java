package Dialog;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class LevelEditDialog extends Dialog
{


	MyGame myGame;

	public LevelEditDialog(MyGame myGame, String title, Skin skin)
	{
		super(title, skin);
		this.myGame = myGame;
		

		text(" ");
		
		button("Yes", "button");
		button("No", "button");
		this.pack();

	}
	
	

}
