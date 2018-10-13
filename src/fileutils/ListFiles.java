/**
 * 
 */
package fileutils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author Oleg
 *
 */
public class ListFiles extends AbstractCommand {

  static private final int MAX_COUNT_IN_SAME_TIME = 2;
  
  static private final String REV_SORT = "r";
  
  static private final String REC_LIST = "R";
  
  static private final String HELP = "help";
  
  /**
   * @param flagParser
   */
  public ListFiles(IFlagParser flagParser) {
    super(flagParser);
    
  }

  /* (non-Javadoc)
   * @see fileutils.ICommand#execute()
   */
  @Override
  public int execute(String ...args) {
    var flags = getFlagParser().getFlags(args, MAX_COUNT_IN_SAME_TIME);
    var curDir = new File(".");
    var filesNames = new ArrayList<String>();
    for (var fd : curDir.listFiles()) {
      if (fd.isFile()) {
        filesNames.add(fd.getName());
      }
      else if (fd.isDirectory()) {
        filesNames.add(fd.getName());
      }
    }
    if (Arrays.asList(flags).contains(REV_SORT)) {
      Collections.sort(filesNames, (f1, f2) -> f2.compareTo(f1));
    }
    else {
      Collections.sort(filesNames, (f1, f2) -> f1.compareTo(f2));
    }
    for (var f : filesNames) {
      System.out.println(f);
    }
    return 0;
  }

}
