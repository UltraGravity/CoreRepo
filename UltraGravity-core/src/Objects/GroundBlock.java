package Objects;

import Physics.Constants;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class GroundBlock extends StationaryItem
{

	public GroundBlock(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		texture = myGame.assetLoader.ground;
		sprite = new Sprite(texture);
		shape = setShape();
		density = 1;
		System.out.println("Ground Created");
	}

	public Shape setShape()
	{
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(Constants.GROUND_SCALE, Constants.GROUND_SCALE);
		return (Shape) boxShape;
	}

}
