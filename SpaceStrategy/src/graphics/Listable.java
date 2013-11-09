package graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public interface Listable {
	
	/*
	 * This interface will be used by classes that will be listed in InfoBoxes on various windows to provide names, relevant descriptions, and an Image thumbnail
	 */

	public Image getThumbnail() throws SlickException;
	public String getDesc();
}
