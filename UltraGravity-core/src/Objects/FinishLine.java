package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class FinishLine extends Item
{
	private FinishLine finish;
	TextureRegion texture = myGame.assetLoader.safeZone;

	public FinishLine(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		shape.setAsBox(100, 100);
	}

}
