package Objects;

import Physics.Constants;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class Ice extends MovingItem
{

	public Ice(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		texture = myGame.assetLoader.ice;
		sprite = new Sprite(texture);		
		shape = setShape();
		density = 1.0f;
		System.out.println("Ice box Created");
	}

	public Shape setShape()
	{
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(Constants.SIZE_SCALE, Constants.SIZE_SCALE);
		return (Shape) boxShape;
	}
	
	

}
