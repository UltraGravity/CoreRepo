package FileIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Objects.Box;
import Objects.Character;
import Objects.FinishLine;
import Objects.GroundBlock;
import Objects.Item;
import Objects.World;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.files.FileHandle;

public class LevelFile {
  // public FileHandle file;
  World  world;
  MyGame myGame;

  public LevelFile(MyGame myGame) {
    this.myGame = myGame;
  }

  public void SaveLevel(String fileName, String world) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
      System.out.println(world);
      writer.write(world);
      writer.close();
      System.out.println("File Saved");
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // public void SaveLevel(String fileName) {
  // Item level[][] = world.getWorld();
  // String out = "";
  // for (int x = 0; x < world.getXSize(); x++) {
  // for (int y = 0; y < world.getYSize(); y++) {
  // String item = "";
  // if (level[x][y] != null) {
  // if (level[x][y] instanceof Box) {
  // item = "2";
  // }
  // if (level[x][y] instanceof Character) {
  // item = "c";
  // }
  // if (level[x][y] instanceof GroundBlock) {
  // item = "1";
  // }
  // if (level[x][y] instanceof FinishLine) {
  // item = "3";
  // }
  // }
  // else {
  // item = "0";
  // }
  // out = out + item;
  // }
  // try {
  // BufferedWriter writer = new BufferedWriter (new FileWriter(".\\" + fileName + ".txt"));
  // writer.write(out);
  // writer.close();
  // }
  // catch (IOException e) {
  // // TODO Auto-generated catch block
  // e.printStackTrace();
  // }
  // }
  //
  //
  // }

  public Item[][] LoadLevel(FileHandle file) {
    Item next = null;
    Item level[][] = new Item[world.getXSize()][world.getYSize()];
    String obj = String.valueOf(file.readString());
    int count = 0;
    char Ochar;
    for (int x = 0; x < world.getXSize(); x++) {
      for (int y = 0; y < world.getYSize(); y++) {
        Ochar = obj.charAt(count);
        if (Ochar == '0') {
          next = null;
        }
        if (Ochar == 'b') {
          next = new Box(myGame, x, y);
        }
        if (Ochar == 'g') {
          next = new GroundBlock(myGame, x, y);
        }
        if (Ochar == 'c') {
          next = new Character(myGame, x, y);
        }

        level[x][y] = next;
        System.out.println(" added " + obj + " at " + x + y);
      }
    }
    return level;
  }

}
