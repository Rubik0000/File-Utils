/**
 * 
 */
package fileutils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 */
public class CreateFile 
    extends AbstractCommand 
    implements IFileCodes {

  static private final int COUNT_ARGS = 2;
  
  static private final int MAX_COUNT_FLAGS = 1;
  
  /**
   * @param flagParser
   */
  public CreateFile(IFlagParser flagParser, String name) {
    super(flagParser, name);
  }

  /**
   * Creates file with given name and content
   * 
   * @param name a file name
   * @param content a file content
   * @return status code
   */
  private int createFile(String name, String content) {
    if (new File(name).exists()) {
      System.err.println("The file is already exist");
      return FILE_EXISTS;
    }    
    try (var fw = new FileWriter(name)) {
      fw.write(content);    
    } 
    catch (IOException e) {
      System.err.println(e.getMessage());
      System.err.println(e.getStackTrace());
      return IOEX;
    }
    return SUCCESS_CODE;
  }
  
  @Override
  protected boolean hasFlag(String flag) {
    return flag.equals(HELP);
  }

  @Override
  protected void getHelp() {
    System.out.println("Creates a file with given content");
    System.out.println(getName() + "[OPTION] CONTENT FILENAME");
  }
  
  /* (non-Javadoc)
   * @see fileutils.ICommand#execute(java.lang.String[])
   */
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
    
    // getting help
    if (containsFlag(recvFlags, HELP)) {
      getHelp();
      return SUCCESS_CODE;
    }
        
    if (args.length < 2 || args.length > MAX_COUNT_FLAGS + COUNT_ARGS ||
        getFlagParser().isFlag(args[args.length - 1]) || 
        getFlagParser().isFlag(args[args.length - 2])) {
      
      System.err.println("Invalid parameters");
      System.err.println("Try "+  getFlagParser().getFullFlag(HELP)
          + " for more info");
      return PARAM_ERR;  
    }
    return createFile(args[args.length - 1], args[args.length - 2]);
  }

}
