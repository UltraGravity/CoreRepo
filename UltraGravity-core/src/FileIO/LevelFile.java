package FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Objects.Box;
import Objects.Character;
import Objects.FinishLine;
import Objects.GroundBlock;
import Objects.Item;
import Objects.World;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class LevelFile {
  // public FileHandle file;
  World  world;
  MyGame myGame;

  public LevelFile(MyGame myGame) {
    this.myGame = myGame;
  }

  public void SaveLevel(String fileName, String world) {
    // BufferedWriter writer = new BufferedWriter(new FileWriter("Levels/" + fileName));
    FileHandle file = Gdx.files.local("Levels/" + fileName);
    System.out.println(world);
    file.writeString(world, false);
    System.out.println("File Saved");

  }

  public String LoadLevel(String fileName) {
    FileHandle file = Gdx.files.local("Levels/" + fileName);
    if(file.exists()) {
      System.out.println("The file exists");
    }
    else {
      System.out.println("No file exists");
    }
    String level = file.readString();
    System.out.println(level);
    return level;
  }
}
