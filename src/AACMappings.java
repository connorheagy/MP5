import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;
import java.util.Scanner;

/**
 * reads in the file and maps its contents into the categories
 * @author Connor Heagy
 * 
 */
public class AACMappings {

  AssociativeArray<String,AACCategory> map;

  // file to be read
  String filename;

  // current category we are in
  AACCategory current;

  // the home category
  AACCategory home;

  public AACMappings(String filename) {
    try {
      // ititalize home category
      home = new AACCategory("");
      // make the current category home
      current = home; 
      this.map = new AssociativeArray<String, AACCategory>();
      map.set("", current);
      // read in the file
      Scanner scanner = new Scanner(new File (filename));
    // while the there is a newline of content within the file, continue parsing data
    while (scanner.hasNext()) {
      String lines = scanner.nextLine();
      String[] arr = lines.split(" ", 2);
      // checking to see if the textline is going to a subcategory or not
      if (lines.charAt(0) != '>') {
        this.current = new AACCategory(arr[1]);
        map.set(arr[0], current);
        home.addItem(arr[0], arr[1]);
      } // if
      else {
        arr[0] = arr[0].substring(1);
        current.addItem(arr[0], arr[1]);
      } // else
    } // while
      scanner.close();
      // reset the current category to home
      reset();
    } catch (Exception e) {}
  } // AACMappings(String)

  /**
   * Given the image location selected, it determines the associated text with the image. 
   * If the image provided is a category, it also updates the AAC's current category 
   * to be the category associated with that image
   * @param name
   * @throws KeyNotFoundException
   */
  public String getText​(String name) throws KeyNotFoundException {
    // checking if the name of the category is valid
    if (isCategory​(name)) {
      // sets the current to the new category
      this.current = map.get(name);
      return current.getCategory();
    } // if
    return current.getText​(name); 
  } // getText(name)

  /**
   * Provides an array of all the images in the current category
   * @throws KeyNotFoundException
   */
  public String[] getImageLocs() throws KeyNotFoundException {
    // checks to see that the current category is not null
    if (current == null) {
      return null;
    } // if 
    return current.getImages();
  } // getImageLocs()

  /**
   * Resets the current category of the AAC back to the default category
   */
  public void reset() {
    current = home;
  } // reset

  /**
   * returns the current category or the empty string if on the default category
   * 
   */
  public String getCurrentCategory() {
    // checks to see if the current category is null
    if (this.current == null) {
      return null;
    } // if
    return this.current.getCategory();
  } // getCurrentCategory()

  /**
   * true if the image represents a category, false if the image represents text to speak
   * @param name
   * 
   */
  public boolean isCategory​(String name) {
    // checks to see the map associative array contains the key
    if (map.hasKey(name) == false) {
      return false;
    } // if
    return true;
  } // isCategory(name)
  
  /**
   * Writes the ACC mappings stored to a file. 
   * The file is formatted as the text location of the category followed by the text name of the category 
   * and then one line per item in the category that starts with > and then has the file name and text of that image
   * @param filename
   * @throws FileNotFoundException
   * @throws KeyNotFoundException
   */
  public void writeToFile(String filename) throws FileNotFoundException, KeyNotFoundException {
    // create new pen
    PrintWriter pen = new PrintWriter(filename);
    for (String categories: map.getAllKeys()) {
      AACCategory images = map.get(categories);
      pen.print(images + "/" + images.getCategory());
      for (String pictures : images.getImages()) {
        if (images != home) {
          pen.println(">" + images + images.getText​(pictures));
        } // if 
      } // for
    } // for
    pen.close();
  } // writeToFile(filename)

  /**
   * Adds the mapping to the current category (or the default category if that is the current category)
   * @param imageLoc
   * @param text
   * @throws NullKeyException
   */
  public void add(String imageLoc, String text) throws NullKeyException {
    // checks to see if the current category is null
    if (this.current == null) {
      map.set(imageLoc, new AACCategory(text));
    } // if
    else {
      current.addItem(imageLoc, text);
    } // else
  } // add
} // class AACMappings

