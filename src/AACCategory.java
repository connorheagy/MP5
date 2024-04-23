import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;

/**
 *
 * Represents the mappings for items that should be displayed
 * @author Connor Heagy
 */
public class AACCategory {

  /**
   * that maps image locations (strings) to words (also strings)
   */
  AssociativeArray<String, String> arr;

  String category;

  public AACCategory(String category) {
    this.category = category;
    this.arr = new AssociativeArray<String, String>();
  } // AACCategory(category)

  /**
   * Returns the name of the category, if the category is null, returns null
   * 
   */
  public String getCategory() {
    // checks to see if the category is null
    if (category == null) {
      return null;
    }
    return this.category;
  }

  /**
   * the text associated with the image
   * @param imageLoc
   * 
   * @throws KeyNotFoundException
   */
  public String getText​(String imageLoc) throws KeyNotFoundException {
      return arr.get(imageLoc);
  } // getText(imageLoc)

  /**
   * returns true if it is in the category, false otherwise
   * @param imageLoc
   * 
   */
  public boolean hasImage​(String imageLoc) {
    return arr.hasKey(imageLoc);
  } // hasImage()

  /**
   * Returns an array of all the images in the category
   * 
   * @throws KeyNotFoundException
   */
  public String[] getImages() throws KeyNotFoundException {
    return arr.getAllKeys();
  } // getImages()

  /**
   * Adds the mapping of the imageLoc to the text to the category.
   * @param imageLoc
   * @param text
   * @throws NullKeyException
   */
  public void addItem(String imageLoc, String text) throws NullKeyException {
    arr.set(imageLoc, text);
  } // addItem(imageLoc, text)
} // class AACCategory