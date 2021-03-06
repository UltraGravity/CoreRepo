///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 Ice.java
//
//							Notes:
//Constructor class for the Stationary Item Ice
///////////////////////////////////////////////////////////////////////////


package Objects;

import Physics.Constants;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class Ice extends StationaryItem
{

	public Ice(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		texture = myGame.assetLoader.ice;
		sprite = new Sprite(texture);		
		shape = setShape();
		density = 2.4f;
		friction = 0;
		restitution = 0f;
//		System.out.println("Ice box Created");
	}

	public Shape setShape()
	{
		PolygonShape shape = new PolygonShape();
		Vector2[] verts = new Vector2[8];
		float scale = Constants.GROUND_SCALE;
		float edgeScale = Constants.EDGE_SCALE;
		verts[0] = (new Vector2(-(scale - edgeScale),-scale));
		verts[1] = (new Vector2(scale - edgeScale,-scale));
		verts[2] = (new Vector2(scale,-(scale - edgeScale)));
		verts[3] = (new Vector2(scale,scale-edgeScale));
		verts[4] = (new Vector2(scale -edgeScale,scale));
		verts[5] = (new Vector2(-(scale - edgeScale),scale));
		verts[6] = (new Vector2(-scale,scale-edgeScale));
		verts[7] = (new Vector2(-scale,-(scale - edgeScale)));
		shape.set(verts);
		return (Shape) shape;
	}
	
	

}
