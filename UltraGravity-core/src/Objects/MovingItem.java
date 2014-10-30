package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class MovingItem extends Item{
  Fixture fixture;
  FixtureDef fixtureDef;
  int angle;
  int xSpeed;
  int ySpeed;
  int rotSpeed;
  int xAccel;
  int yAccel;
  int rotAccel;

  public MovingItem(MyGame myGame, int x, int y) {
    super(myGame, x, y);
  }

  public void update(int gravity) {
    if (gravity == 0) {  // direction towards home button when home button is in right hand
      xAccel++;
    }
    if (gravity == 1) { // up when home button is in right hand
      yAccel--;
    }
    if (gravity == 2) { // left of standard
      xAccel--;
    }
    if (gravity == 3) { // down of standard
      yAccel++;
    }
    xSpeed = xSpeed + xAccel;
    ySpeed = ySpeed + yAccel;

    x = x + xSpeed;
    y = y + ySpeed;
  }
  
}
