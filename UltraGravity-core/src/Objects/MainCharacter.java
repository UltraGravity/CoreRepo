package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainCharacter extends MovingItem
{
	private MainCharacter character;
	TextureRegion texture = myGame.assetLoader.character;

	public MainCharacter(MyGame myGame, int x, int y)
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
