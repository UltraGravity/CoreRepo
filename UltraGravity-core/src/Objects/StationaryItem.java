package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class StationaryItem extends Item
{

	public StationaryItem(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		setBodyDef();
	}
	
	public void setBodyDef(){
		this.bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x,y);
	}

}
