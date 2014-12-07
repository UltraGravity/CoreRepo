package Objects;

import Physics.Constants;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class MainCharacter extends MovingItem
{

	public MainCharacter(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		
		if (myGame.gameScreen.levelName.equals("showMeTheMoney.txt"))
		{
			texture = myGame.assetLoader.easterEgg;
		}
		else
		{
			texture = myGame.assetLoader.character;	
		}
		sprite = new Sprite(texture);
		shape = setShape();
		density = 1;
	}

	public Shape setShape()
	{
		CircleShape circle = new CircleShape();
		circle.setRadius(Constants.SIZE_SCALE/2);
		return (Shape) circle;
	}

}
