package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Item {

  int                  gravity;
  int                  x;
  int                  y;

  World                world;
  MovingItem           movingItem;
  public MyGame        myGame;
  public TextureRegion texture;
  Sprite sprite;
  BodyDef bodyDef;
  Body body;
  PolygonShape shape;
  FixtureDef fixtureDef;
  Fixture fixture;
  

  public Item(MyGame myGame, int x, int y) {
    this.myGame = myGame;
    this.x = x;
    this.y = y;
    TextureRegion texture = new TextureRegion();
    sprite = new Sprite(texture);
    body = world.createBody(bodyDef);
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.density = 1f;
    
    Fixture fixture = body.createFixture(fixtureDef);
    
    shape.dispose();
  }

  public void draw() {

  }

  public TextureRegion getTexture() {
    return this.texture;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void update(int gravity) {
    this.gravity = gravity;
    if (this instanceof MovingItem) {
      movingItem.update(gravity);
    }
  }

}
