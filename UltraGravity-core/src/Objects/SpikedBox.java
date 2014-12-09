///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 SpikedBox.java
//
//							Notes:
//Constructor class for the Stationary Item Spiked Box
///////////////////////////////////////////////////////////////////////////


package Objects;

import Physics.Constants;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class SpikedBox extends StationaryItem
{

	public SpikedBox(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		texture = myGame.assetLoader.spikedBlock;
		sprite = new Sprite(texture);		
		shape = setShape();
		density = 1;
		friction = 1;
		System.out.println("Box Created");
	}

	public Shape setShape()
	{
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(Constants.GROUND_SCALE, Constants.GROUND_SCALE);
		return (Shape) boxShape;
	}
	
	

}
