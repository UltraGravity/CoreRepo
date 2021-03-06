///////////////////////////////////////////////////////////////////////////
// Title:            Ultra Gravity
// File:			 LevelButton.java
//
//							Notes:
//Constructor class for the each on the level buttons that appear within 
//Level Select Screen
///////////////////////////////////////////////////////////////////////////


package Objects;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class LevelButton extends TextButton
{
	String fileName;
	String folder;

	public LevelButton(String text, TextButtonStyle style, String fileName, String folder)
	{
		super(text, style);
		this.fileName = fileName;
		this.folder = folder;
	}

	public String getLevelName()
	{
		return fileName;
	}
	
	public String getFolder()
	{
		return folder;
	}

}
