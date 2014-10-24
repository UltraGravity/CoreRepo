package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;

public class GroundBlock extends Item
{

  private GroundBlock groundBlock;
  
  public GroundBlock(MyGame myGame, int x, int y) 
  {
    super(myGame, x, y);
    texture = myGame.assetLoader.ground;
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
