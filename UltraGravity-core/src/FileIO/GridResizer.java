///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 GridResizer.java
//
//							Notes:
//This class is in charge of resizing the level when the level grid is 
//changed within the level editor.
///////////////////////////////////////////////////////////////////////////

package FileIO;

import com.APAAAEAIA.UltraGravity.MyGame;

import Objects.GridImage;
import Objects.ThePlane;

public class GridResizer
{
	ThePlane thePlane;
	MyGame myGame;
	
	public GridResizer(MyGame myGame, ThePlane thePlane)
	{
		this.myGame = myGame;
		this.thePlane = thePlane;
	}
	
	public GridImage[] addColumn(GridImage[] cell, boolean addToRightSide, int blockStyle)
	{
		/* 
		 * if addToRightSide = true,
		 * then add the column to the right. 
		 * Otherwise, add the column to the left side.
		 * 
		 * blockStyle can be 0 or 1
		 * 0 - blank block
		 * 1 - ground block
		 */
		
		int cellIndex = 0;
		int oldXSize = thePlane.getXSize();
		int newXSize = oldXSize + 1;
		int ySize = thePlane.getYSize();
		thePlane.setSize(newXSize, ySize);
		GridImage biggerGrid[] = new GridImage[newXSize * ySize];
		GridImage newBlock;
		
		if (blockStyle == 0)
			newBlock = new GridImage(myGame.assetLoader.blankBlockStyle);
		else
			newBlock = new GridImage(myGame.assetLoader.groundBlockStyle);
		
		if (addToRightSide)
		{	
			for (int i = 0; i < biggerGrid.length; i++)
			{
				if (i % newXSize + 1 == newXSize)
				{
					biggerGrid[i] = newBlock;
					biggerGrid[i].cellValue = blockStyle;
				}
				else
				{
					biggerGrid[i] = cell[cellIndex];
					cellIndex++;
				}
			}
		}
		else
		{
			for (int i = 0; i < biggerGrid.length; i++)
			{
				if (i % newXSize != 0)
				{
					biggerGrid[i] = cell[cellIndex];
					cellIndex++;
				}
				else
				{
					biggerGrid[i] = newBlock;
					biggerGrid[i].cellValue = blockStyle;
				}
			}
		}
		
		cell = biggerGrid;
		return cell;
	}
	
	public GridImage[] removeColumn(GridImage[] cell, boolean removeFromRightSide)
	{
		/* 
		 * if removeFromRightSide = true,
		 * then remove the column from the right. 
		 * Otherwise, remove the column from the left side.
		 * 
		 * Grid must be at least 1 cell wide.
		 */
		
		if (thePlane.getXSize() - 1 > 0)
		{
			int cellIndex = 0;
			int oldXSize = thePlane.getXSize();
			int newXSize = oldXSize - 1;
			int ySize = thePlane.getYSize();
			thePlane.setSize(newXSize, ySize);
			GridImage smallerGrid[] = new GridImage[newXSize * ySize];

			if (removeFromRightSide)
			{	
				for (int i = 0; i < smallerGrid.length; i++)
				{
					if (i % newXSize + 1 == newXSize)
					{
						smallerGrid[i] = cell[cellIndex];
						cellIndex = cellIndex + 2;
					}
					else
					{
						smallerGrid[i] = cell[cellIndex];
						cellIndex++;
					}
				}
			}
			else
			{
				for (int i = 0; i < smallerGrid.length; i++)
				{
					if (i % newXSize == 0)
					{
						smallerGrid[i] = cell[cellIndex + 1];
						cellIndex = cellIndex + 2;
					}
					else
					{
						smallerGrid[i] = cell[cellIndex];
						cellIndex++;
					}
				}
			}
			cell = smallerGrid;
		}
		return cell;
	}
	
	public GridImage[] addRow(GridImage[] cell, boolean addToTop, int blockStyle)
	{
		/* 
		 * if addToTop = true,
		 * then add the row to the top. 
		 * Otherwise, add the row to the bottom.
		 * 
		 * blockStyle can be 0 or 1
		 * 0 - blank block
		 * 1 - ground block
		 */
		
		int newYSize = thePlane.getYSize() + 1;
		int xSize = thePlane.getXSize();
		thePlane.setSize(xSize, newYSize);
		GridImage biggerGrid[] = new GridImage[xSize * newYSize];
		GridImage newBlock;
		
		if (blockStyle == 0)
			newBlock = new GridImage(myGame.assetLoader.blankBlockStyle);
		else
			newBlock = new GridImage(myGame.assetLoader.groundBlockStyle);
		
		if (addToTop)
		{	
			for (int i = 0; i < biggerGrid.length; i++)
			{
				if (i < xSize)
				{
					biggerGrid[i] = newBlock;
					biggerGrid[i].cellValue = blockStyle;
					
				}
				else
				{
					biggerGrid[i] = cell[i - xSize];
				}
			}
		}
		else
		{
			for (int i = 0; i < biggerGrid.length; i++)
			{
				if (i < cell.length)
				{
					biggerGrid[i] = cell[i];
				}
				else
				{
					biggerGrid[i] = newBlock;
					biggerGrid[i].cellValue = blockStyle;
				}
			}
		}
		
		cell = biggerGrid;
		return cell;
	}

	public GridImage[] removeRow(GridImage[] cell, boolean removeFromTop)
	{
		/*
		 * if addToTop = true, then remove the row from the top. Otherwise,
		 * remove the row from the bottom.
		 * 
		 * Grid must be at least 1 cell high.
		 */

		if (thePlane.getYSize() - 1 > 0)
		{
			int newYSize = thePlane.getYSize() - 1;
			int xSize = thePlane.getXSize();
			thePlane.setSize(xSize, newYSize);
			GridImage smallerGrid[] = new GridImage[xSize * newYSize];

			if (removeFromTop)
			{
				for (int i = 0; i < smallerGrid.length; i++)
				{
					smallerGrid[i] = cell[i + xSize];
				}
			}
			else
			{
				for (int i = 0; i < smallerGrid.length; i++)
				{
					smallerGrid[i] = cell[i];
				}
			}

			cell = smallerGrid;
		}
		return cell;
	}
}
