package Objects;

import java.util.ArrayList;

import com.APAAAEAIA.UltraGravity.MyGame;

public class World {
  //made this class a bit more usefull without those pesky arrays
  int worldX = 12;
  int worldY = 6;
  
  int boundX = worldX * 100;
  int boundY = worldY * 100;

  ArrayList<Item> world = new ArrayList();
  
  MyGame myGame;
  
  public World(int x, int y) {
    this.worldX = x;
    this.worldY = y;
    // TODO Auto-generated constructor stub
  }


  public ArrayList<Item> physUpdate() {
    for(int i = 0; i < world.size(); i++) {
      Item current = world.get(i);
      current.updatePhysics();      
    }
    return world;
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
    return worldX;
  }
  
  public int getYSize() {
    return worldY;
  }

  public void setSize(int x, int y) {
    System.out.println("new world created at size "  +x + "," + y);
    worldX =  x;
    worldY = y;
  }

  public void setBounds(int x, int y) {
    boundX = x;
    boundY = y;
  }
  
//  public Item[][] getArray() {
//    return World;
//  }
  

  
}
