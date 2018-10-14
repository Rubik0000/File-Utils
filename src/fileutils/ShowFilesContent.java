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
public class ShowFilesContent 
    extends AbstractCommand 
    implements IFileCodes {

  //static private final int MIN_COUNT_ARGS = 1;
  
  static private final int MAX_COUNT_FLAGS = 2;
  
  static private final String SYM_$ = "E";
  
  static private final String NUM_STRS = "n";
  
  /**
   * @param flagParser
   */
  public ShowFilesContent(IFlagParser flagParser, String name) {
    super(flagParser, name);
  }

  /**
   * Gets formated string
   * 
   * @param str
   * @param show$
   * @param showNumStr
   * @param numStr
   * @return
   */
  private String getFormatFileStr(
      String str,
      boolean show$, 
      boolean showNumStr, 
      int numStr) {
    
    var formatStr = str;
    if (showNumStr) {
      formatStr = numStr + " " + formatStr + " " + numStr;
    }
    if (show$) {
      formatStr += "$";
    }
    return formatStr;
  }

  /**
   * Prints files' content
   * 
   * @param files files it needs to show content
   * @param flags command flags
   * @return status code
   */
  private int showContent(String[] files, String[] flags) {
    // check on existing
    for (var file : files) {
      if (!new File(file).exists()) {
        System.err.println("The file " + file + " does not exist");
        return FILE_NOT_EXISTS;
      }
    }
    
    boolean show$ = containsFlag(flags, SYM_$);
    boolean showNumsStr = containsFlag(flags, NUM_STRS);
    for (var file : files) {
      try (var fi = new BufferedReader(new FileReader(file))) {
        System.out.println(file + ":\r\n");
        String str;
        int count = 1;
        while ((str = fi.readLine()) != null) {
          System.out.println(
              getFormatFileStr(str, show$, showNumsStr, count++));
        }
        System.out.println();
      }
      catch (IOException e) {
        System.err.println(e.getMessage());
        System.err.println(e.getStackTrace());
        return IOEX;
      }
    }        
    return SUCCESS_CODE;
  }
  
  @Override
  protected boolean hasFlag(String flag) {
    switch (flag) {
      case HELP:
      case NUM_STRS:
      case SYM_$:
        return true;
        
      default:
        return false;
    }
  }

  @Override
  protected void getHelp() {
    System.out.println("Shows files' content");
    System.out.println("  " + getName() + " [OPTIONS] FILE [FILES]");
    System.out.println("  " + 
        getFlagParser().getFullFlag(NUM_STRS) +
        " to number strings");
    System.out.println("  " + 
        getFlagParser().getFullFlag(SYM_$) +
        " to add $ at the end of strings");
  }
  
  @Override
  public int execute(String... args) {
   // getting the flags
    var recvFlags = getFlagParser().getFlags(args, MAX_COUNT_FLAGS);
    int code = super.execute(recvFlags);
    if (code == FLAG_ERR) {
      return FLAG_ERR;
    }
    if (code == HELP_SHOWED) {
      return SUCCESS_CODE;
    }
    
    if (getFlagParser().isFlag(args[args.length - 1])) {
      System.err.println("Invalid arguments");
      System.err.println("Try "+  getFlagParser().getFullFlag(HELP)
          + " for more info");
      return PARAM_ERR;
    }
    int startInd = 0;
    while (getFlagParser().isFlag(args[startInd])) {
      ++startInd;
    }
    var files = Arrays.copyOfRange(args, startInd, args.length);
    return showContent(files, recvFlags);
  }

}
