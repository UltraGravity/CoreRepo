package Objects;

import java.util.ArrayList;

import com.APAAAEAIA.UltraGravity.MyGame;

public class World {
  int worldY = 6;
  int worldX = 12;

  Item[][] World = new Item[worldX][worldY];
  ArrayList<Item> physicalWorld = new ArrayList();
  
  MyGame myGame;
  
  public World(int editWorldX, int editWorldY) 
  {
    this.worldX = editWorldX;
    this.worldY = editWorldY;
    
    World = new Item[editWorldX][editWorldY];
  }
  
  public ArrayList<Item> physUpdate() {
    for(int i = 0; i < physicalWorld.size(); i++) {
      Item current = physicalWorld.get(i);
      current.updatePhysics();      
    }
    return physicalWorld;
  }
  
  public void setWorld(String world) {
    System.out.println("Setting to world");
    worldX = world.charAt(0);
    worldY = world.charAt(1);
    for(int i = 2; i < world.length(); i++) {
      
    }
    
  }
  
  public Item[][] getWorld(){
    return World;
  }
  
  public void addItem(int item, int x, int y) 
  {
    if(item == 2) {
      Item box = new Box(myGame, x,y);
      World[x][y] = box;
    }
    if(item == 3) {
      Item character = new Character(myGame, x,y);
      World[x][y] = character;
    }
    if(item == 1) {
      Item ground = new GroundBlock(myGame, x,y);
      World[x][y] = ground;
    }

  }
  
  public void removeItem(int x, int y) {
    World[x][y] = null;
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
    
    // TODO Auto-generated method stub
    
  }
  
//  public Item[][] getArray() {
//    return World;
//  }
  

  
}
