package Shapes;

import Physics.Constants;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class roundedCornerRectangle extends Shape
{

	public roundedCornerRectangle(float radius)
	{

	}

	@Override
	public Type getType()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Shape setBoxShape()
	{
		PolygonShape box = new PolygonShape();
		box.setAsBox(Constants.SIZE_SCALE, Constants.SIZE_SCALE);
		return (Shape) box;
	}

}
