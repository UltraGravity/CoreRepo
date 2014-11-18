package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainCharacter extends MovingItem
{
	
	public MainCharacter(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		texture = myGame.assetLoader.character;
		setShape();
	}
	
	public void setShape()
	{
		shape.setRadius(100);
	}
	
	public TextureRegion getTexture() {
		return texture;
	}

}
