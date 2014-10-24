package Objects;

public class Character extends Item {

  private Character character;
  
  public Character(int x, int y) {
    this.x = x;
    this.y = y;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.rotSpeed = rotSpeed;
    this.angle = angle;
  }
  
  public Character getCharacter()
  {
    return this.character;
  }

  private void setCharacter(int x, int y, int angle, int xSpeed, int ySpeed, int rotSpeed) 
  {
    this.x = x;
    this.y = y;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.rotSpeed = rotSpeed;
    this.angle = angle;
  }
  
  private void setLocation(int x, int y) 
  {
    this.x = x;
    this.y = y;
  }
  
  private void setSpeed(int xSpeed, int ySpeed) 
  {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }
  
  private void setAngularData(int angle, int rotSpeed) 
  {
    this.angle = angle;
    this.rotSpeed = rotSpeed;
  }
  
  public int getX() 
  {
    return x;
  }
  
  public int getY()
  {
    return y;
  }
  
  public int getAngle()
  {
    return angle;
  }
  
  public int getXSpeed()
  {
    return xSpeed;
  }
  
  public int getYSpeed()
  {
    return ySpeed;
  }
  
  public int getRotSpeed()
  {
    return rotSpeed;
  }

}
