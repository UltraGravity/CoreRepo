package Objects;

import Physics.Constants;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class WoodBox extends MovingItem
{

	public WoodBox(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		texture = myGame.assetLoader.box;
		sprite = new Sprite(texture);		
		shape = setShape();
		density = .8f;
		friction = .65f;
		restitution = 0f;
//		System.out.println("Box Created");
	}

	public Shape setShape()
	{
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Constants.SIZE_SCALE, Constants.SIZE_SCALE);
//		Vector2[] verts = new Vector2[8];
//		float scale = Constants.SIZE_SCALE;
//		float edgeScale = Constants.EDGE_SCALE;
//		verts[0] = (new Vector2(-(scale - edgeScale),-scale));
//		verts[1] = (new Vector2(scale - edgeScale,-scale));
//		verts[2] = (new Vector2(scale,-(scale - edgeScale)));
//		verts[3] = (new Vector2(scale,scale-edgeScale));
//		verts[4] = (new Vector2(scale -edgeScale,scale));
//		verts[5] = (new Vector2(-(scale - edgeScale),scale));
//		verts[6] = (new Vector2(-scale,scale-edgeScale));
//		verts[7] = (new Vector2(-scale,-(scale - edgeScale)));
//		shape.set(verts);
		
		return (Shape) shape;
	}
	
	

}
