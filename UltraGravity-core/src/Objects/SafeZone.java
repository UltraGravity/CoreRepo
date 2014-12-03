package Objects;

import Physics.Constants;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class SafeZone extends StationaryItem
{

	public SafeZone(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		texture = myGame.assetLoader.safeZone;
		sprite = new Sprite(texture);
		shape = setShape();
		density = 1;
		System.out.println("Safe Zone Created");
	}
	
	public Shape setShape()
	{
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(Constants.SIZE_SCALE, Constants.SIZE_SCALE);
		return (Shape) boxShape;
	}
	

}
