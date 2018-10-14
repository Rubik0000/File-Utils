
package fileutils;

import java.io.File;

/**
 * Provides a command that creates a directory
 */
public class MakeDir extends AbstractCommand implements IFileCodes {

  /** Max number of flags */
  static private final int MAX_COUNT_FLAGS = 1;
  
  /** Max number of command parameters */
  static private final int MAX_COUNT_PARAM = 1;
  
  /**
   * @param flagParser
   */
  public MakeDir(IFlagParser flagParser, String name) {
    super(flagParser, name);
  }
  
  /**
   * Creates new directory
   * 
   * @param dir
   * @return
   */
  private int makeDir(String dir) {
    var newDir = new File(dir);
    if (newDir.exists()) {
      System.err.println("The directory is already exist");
      return DIR_EXISTS;
    }
    newDir.mkdir();
    return SUCCESS_CODE;
  }

  @Override
  protected boolean hasFlag(String flag) {
    return flag.equals(HELP);
  }

  @Override
  protected void getHelp() {
    System.out.println("Creates directory");
    System.out.println(getName() + " [OPTIONS] DIRNAME");
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
    
    if (args.length == 0 || args.length > MAX_COUNT_FLAGS + MAX_COUNT_PARAM ||
        getFlagParser().isFlag(args[args.length - 1])) {
      
      System.err.println("Invalid parameters");
      System.err.println("Try "+  getFlagParser().getFullFlag(HELP)
          + " for more info");
      return PARAM_ERR;  
    }
    return makeDir(args[args.length - 1]);
  }

}
