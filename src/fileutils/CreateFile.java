/**
 * 
 */
package fileutils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Oleg
 *
 */
public class CreateFile extends AbstractCommand {

  static private final int COUNT_ARGS = 2;
  
  /**
   * @param flagParser
   */
  public CreateFile(IFlagParser flagParser) {
    super(flagParser);
  }

  /* (non-Javadoc)
   * @see fileutils.ICommand#execute(java.lang.String[])
   */
  @Override
  public int execute(String... args) {
    if (args.length != COUNT_ARGS) {
      System.err.println("Invalid arguments");
      return 1;
    }
    
    if (new File(args[1]).exists()) {
      System.err.println("The file is already exist");
      return 2;
    }
    
    try (var fo = new FileWriter(args[1])) {
      fo.write(args[0]);    
    } 
    catch (IOException e) {
      System.err.println(e.getMessage());
      System.err.println(e.getStackTrace());
      return 3;
    }
    return 0;
  }

}
