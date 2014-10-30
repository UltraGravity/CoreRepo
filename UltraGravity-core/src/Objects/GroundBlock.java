package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class GroundBlock extends Item
{

  private GroundBlock groundBlock;
  
  public GroundBlock(MyGame myGame, int x, int y) 
  {
    super(myGame, x, y);
    texture = myGame.assetLoader.ground;
    shape = new PolygonShape();
    shape.setAsBox(100, 100);
  }
  
  public void setGroundBlock(int x, int y) 
  {
    this. x = x;
    this. y = y;
  }
  
  public GroundBlock getGroundBlock() 
  {
    return this.groundBlock;
  }

}
