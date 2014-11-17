package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Character extends MovingItem
{
	private Character character;
	TextureRegion texture = myGame.assetLoader.character;

	public Character(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		shape.setRadius(100);
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

}
