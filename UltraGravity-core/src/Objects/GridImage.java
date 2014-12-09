///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 GridImage.java
//
//							Notes:
//Adds elements to the level editor grid and holds the cell value.
///////////////////////////////////////////////////////////////////////////


package Objects;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class GridImage extends ImageButton 
{

	// -1 is a blank cell
	public int cellValue = 0;
	
	public GridImage(ImageButtonStyle style) 
	{
		super(style);
	}
	
	public int getValue()
	{
		return cellValue;
	}

}
