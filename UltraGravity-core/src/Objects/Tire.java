package Objects;

import Physics.Constants;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class Tire extends MovingItem
{

	public Tire(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		texture = myGame.assetLoader.tire;
		sprite = new Sprite(texture);		
		shape = setShape();
		density = 1;
		
		System.out.println("Box Created");
	}

	public Shape setShape()
	{
		CircleShape circle = new CircleShape();
		circle.setRadius(Constants.SIZE_SCALE);
		return (Shape) circle;
	}
	
	

}
