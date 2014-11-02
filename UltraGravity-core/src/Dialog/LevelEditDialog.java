package Dialog;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LevelEditDialog extends Dialog
{
	MyGame myGame;

	public LevelEditDialog(MyGame myGame, String title, Skin skin)
	{
		super(title, myGame.assetLoader.menuButtonSkin);
		this.myGame = myGame;
		
		WindowStyle ws = new WindowStyle();
		ws.titleFont = myGame.assetLoader.font;
		ws.background = myGame.assetLoader.menuButtonSkin.getDrawable("default");
		
		
		//text("Load a previous file?");
		//button("Yes", "button");
		//button("No", "button");
		

	}
	
	

}
