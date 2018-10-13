/**
 * 
 */
package fileutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 */
public class ShowFiles extends AbstractCommand {

  static private final int MIN_COUNT_ARGS = 1;
  
  static private final int MAX_COUNT_FLAGS = 2;
  
  static private final String SYM_$ = "E";
  
  static private final String NUM_STRS = "n";
  
  /**
   * @param flagParser
   */
  public ShowFiles(IFlagParser flagParser) {
    super(flagParser);
  }

  private String getFormatFileStr(String str, int numStr, String[] flags) {
    var formatStr = str;
    if (containsFlag(flags, NUM_STRS)) {
      formatStr = numStr + " " + formatStr + " " + numStr;
    }
    if (containsFlag(flags, SYM_$)) {
      formatStr += "$";
    }
    return formatStr;
  }
  
  /* (non-Javadoc)
   * @see fileutils.ICommand#execute(java.lang.String[])
   */
  @Override
  public int execute(String... args) {
    if (args.length < MIN_COUNT_ARGS) {
      System.err.println("Invalid arguments");
      return 1;
    }
    var flags = getFlagParser().getFlags(args, MAX_COUNT_FLAGS);
    int startInd = flags.length;
    var files = Arrays.copyOfRange(args, startInd, args.length);
    
    for (var file : files) {
      if (!new File(file).exists()) {
        System.err.println("The file " + file + " does not exist");
        return 2;
      }
    }
    for (var fileName : files) {
      try (var fi = new BufferedReader(new FileReader(fileName))) {
        System.out.println(fileName);
        System.out.println(">>");
        String str;
        int count = 1;
        while ((str = fi.readLine()) != null) {
          System.out.println(getFormatFileStr(str, count++, flags));
        }
        System.out.println();
      }
      catch (IOException e) {
        System.err.println(e.getMessage());
        System.err.println(e.getStackTrace());
        return 3;
      }
    }        
    return 0;
  }

}
