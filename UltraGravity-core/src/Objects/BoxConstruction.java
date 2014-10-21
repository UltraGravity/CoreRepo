package Objects;

import java.util.ArrayList;

public class BoxConstruction 
{
//  int xSize;    //sets the level editor grid size of the construction of boxes
//  int ySize;
//  int xCenter;    //will be used to set the center of the object for physics
//  int yCenter;
  
  ArrayList<Box> boxConstruct;

  public BoxConstruction(ArrayList<Box> construction) 
  {
    this.boxConstruct = construction;
  }
  
  public void addToConstruct(Box box)
  {
    boxConstruct.add(box);
  }
  
  public void removeFromConstruct(Box killBox)
  {
    for(int i = 0; i < boxConstruct.size(); i++) 
    {
      Box check = boxConstruct.get(i);
      if(check.getX() == killBox.getX() && check.getY() == killBox.getY()) {
        boxConstruct.remove(i);
        break;
      }
      
    }
  }

}
