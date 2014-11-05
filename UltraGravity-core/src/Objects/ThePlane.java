package Objects;

import java.util.ArrayList;

import com.APAAAEAIA.UltraGravity.MyGame;

public class ThePlane {
  //made this class a bit more usefull without those pesky arrays
  int planeX = 12;
  int planeY = 6;
  
  int boundX = planeX * 100;
  int boundY = planeY * 100;
  
  int gravity;

  ArrayList<Item> world = new ArrayList();
  
  MyGame myGame;
  
  public ThePlane(int x, int y) {
    this.planeX = x;
    this.planeY = y;
    // TODO Auto-generated constructor stub
  }


  public ArrayList<Item> update(int gravity) {
    for(int i = 0; i < world.size(); i++) {
      Item current = world.get(i);
      current.update(gravity);      
    }
    return world;
  }
  
  public void setGravity(int grav) {
    this.gravity = grav;
  }
  
  public void addItem(int item, int x, int y) 
  {
    if(item == 2) {
      Item box = new Box(myGame, x,y);
      world.add(box);
    }
    if(item == 3) {
      Item character = new Character(myGame, x,y);
      world.add(character);
    }
    if(item == 1) {
      Item ground = new GroundBlock(myGame, x,y);
      world.add(ground);
    }

  }
  
  public int getXSize() {
    return planeX;
  }
  
  public int getYSize() {
    return planeY;
  }

  public void setSize(int x, int y) {
    System.out.println("new world created at size "  +x + "," + y);
    planeX =  x;
    planeY = y;
  }

  public void setBounds(int x, int y) {
    boundX = x;
    boundY = y;
  }
  
//  public Item[][] getArray() {
//    return World;
//  }
  

  
}
