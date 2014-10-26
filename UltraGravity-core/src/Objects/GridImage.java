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
	

}
