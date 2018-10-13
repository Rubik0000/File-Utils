/**
 * 
 */
package fileutils;

import java.io.File;

/**
 *
 */
public class MakeDir extends AbstractCommand {

  static private final int MAX_COUNT_PARAM = 1;
  
  /**
   * @param flagParser
   */
  public MakeDir(IFlagParser flagParser) {
    super(flagParser);
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see fileutils.ICommand#execute(java.lang.String[])
   */
  @Override
  public int execute(String... args) {
    if (args.length != MAX_COUNT_PARAM) {
      System.err.println("Invalid arguments");
      return 1;
    }
    var curDir = new File(".");
    for (var dir : curDir.listFiles()) {
      if (dir.getName().equals(args[0])) {
        System.err.println("The directory is already exist");
        return 2;
      }
    }
    new File(args[0]).mkdir();
    return 0;
  }

}
