package FileIO;

import Objects.GridImage;
import Objects.ThePlane;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class LevelFile
{
	MyGame myGame;
	GridResizer gridResizer;
	
	public LevelFile(MyGame myGame)
	{
		this.myGame = myGame;
	}

	public void SaveLevel(String fileName, String world, String folder)
	{
		FileHandle file = Gdx.files.local(folder + "/" + fileName + ".txt");
		System.out.println(world);
		file.writeString(world, false);
		System.out.println("File Saved");
	}

	public String LoadLevel(String fileName, String folder)
	{
		FileHandle file = Gdx.files.local(folder + "/" + fileName);
		if (file.exists())
		{
			System.out.println("The file exists");
		}
		else
		{
			System.out.println("No file exists");
		}
		String level = file.readString();
		System.out.println(level);
		return level;
	}

	public String getLastLevelName()
	{
		boolean nameFound = false;
		String fileName = "";
		int fName = 0;
		while (!nameFound)
		{
			fileName = "CL_" + Integer.toString(fName) + ".txt";
			System.out.println("Checking if " + fileName
					+ " is an available name");
			FileHandle file = Gdx.files.local("Levels/" + fileName);
			if (file.exists())
			{
				System.out.println("File existed");
				fName++;
			}
			else
			{
				fName--;
				fileName = "CL_" + Integer.toString(fName) + ".txt";
				System.out.println(fileName + " is being loaded");
				nameFound = true;
			}
		}
		return fileName;
	}

	public String getNextLevelName()
	{
		boolean nameFound = false;
		String fileName = "";
		int fName = 0;
		while (!nameFound)
		{
			fileName = "CL_" + Integer.toString(fName) + ".txt";
			System.out.println("Checking if " + fileName
					+ " is an available name");
			FileHandle file = Gdx.files.local("Levels/" + fileName);
			if (file.exists())
			{
				System.out.println("File existed");
				fName++;
			}
			else
			{
				System.out.println(fileName + " is being created");
				nameFound = true;
			}
		}
		return fileName;
	}
	
	public GridImage[] getLevelGrid(String file, ThePlane thePlane, String folder)
	{
		GridImage[] cell;
		String level = LoadLevel(file, folder);

		int i = 0;
		String xSizeString = "";
		String ySizeString = "";
		int index = 0;

		while (!String.valueOf(level.charAt(i)).equals(","))
		{
			xSizeString = xSizeString + String.valueOf(level.charAt(i));
			i++;
		}
		int x = Integer.parseInt(xSizeString);
		System.out.println(x);

		i = xSizeString.length() + 1;
		while (!String.valueOf(level.charAt(i)).equals(":"))
		{
			ySizeString = ySizeString + String.valueOf(level.charAt(i));
			i++;
		}
		int y = Integer.parseInt(ySizeString);
		System.out.println(y);

		thePlane.setSize(x, y);
		System.out.println(thePlane.getXSize());
		System.out.println(thePlane.getYSize());
		cell = new GridImage[x * y];
		System.out.println("New grid created " + (x * y));

		i = xSizeString.length() + ySizeString.length() + 2;
		System.out.println(index);

		while (y > 0)
		{
			while (x > 0)
			{ // The x and y loops are here to help place in a grid
				int nextInt = level.charAt(i);
				if (nextInt == '0')
				{
					System.out.print(" " + 0 + " ");
					cell[index] = new GridImage(myGame.assetLoader.blankBlockStyle);
					cell[index].cellValue = 0;
				}
				if (nextInt == '1')
				{
					System.out.print(" " + 1 + " ");
					cell[index] = new GridImage(myGame.assetLoader.groundBlockStyle);
					cell[index].cellValue = 1;
				}
				if (nextInt == '2')
				{
					System.out.print(" " + 2 + " ");
					cell[index] = new GridImage(myGame.assetLoader.boxBlockStyle);
					cell[index].cellValue = 2;
				}
				if (nextInt == '3')
				{
					System.out.print(" " + 3 + " ");
					cell[index] = new GridImage(myGame.assetLoader.safeZoneBlockStyle);
					cell[index].cellValue = 3;
				}
				if (nextInt == '4')
				{
					System.out.print(" " + 4 + " ");
					cell[index] = new GridImage(myGame.assetLoader.characterBlockStyle);
					cell[index].cellValue = 4;
				}
				index++;
				i++;
				x--;
			}
			x = thePlane.getXSize();
			y--;
		}	
		return cell;
	}
	
	public GridImage[] addGroundBoarder(GridImage[] cell, ThePlane thePlane)
	{
		gridResizer = new GridResizer(myGame, thePlane);
		cell = gridResizer.addColumn(cell, false, 1);
		cell = gridResizer.addColumn(cell, true, 1);
		cell = gridResizer.addRow(cell, false, 1);
		cell = gridResizer.addRow(cell, true, 1);
		return cell;
	}

	public boolean checkIfExists(String fileName)
	{
		
		
		return true;
	}
	
}
