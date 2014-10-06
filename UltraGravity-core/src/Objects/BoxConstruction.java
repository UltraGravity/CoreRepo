package Objects;

import java.util.ArrayList;

public class BoxConstruction {
  int xSize;    //sets the level editor grid size of the construction of boxes
  int ySize;
  int xCenter;    //will be used to set the center of the object for physics
  int yCenter;
  
  ArrayList<Box> connectedBoxes;

  public BoxConstruction(ArrayList<Box> construction) {
    this.connectedBoxes = construction;
  }

}
