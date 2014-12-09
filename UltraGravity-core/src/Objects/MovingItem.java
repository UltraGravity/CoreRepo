///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 MovingItem.java
//
//							Notes:
//Constructor super class for any Moving Items
///////////////////////////////////////////////////////////////////////////


package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class MovingItem extends Item
{
	
	public MovingItem(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		setBodyDef();
		
	}
	
	public void setBodyDef(){
		this.bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x,y);
	}

}
