package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class GroundBlock extends Item
{

	private GroundBlock groundBlock;
	TextureRegion texture = myGame.assetLoader.ground;

	public GroundBlock(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
//		textureRegion = myGame.assetLoader.ground;
//		shape = new PolygonShape();
		shape.setAsBox(100, 100);
	}

}
