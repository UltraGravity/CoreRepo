package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class GroundBlock extends StationaryItem
{

	public GroundBlock(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		texture = myGame.assetLoader.ground;
		setShape();
		System.out.println("Ground Created");
	}

	public void setShape()
	{
		shape.setAsBox(myGame.screenHeight/16, myGame.screenHeight/16);
	}

}
