
package fileutils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Provides a command that prints files and directories into
 * console
 */
public class ListFiles extends AbstractCommand {

  /** Max number of flags */
  static private final int MAX_COUNT_FLAGS = 3;
  
  /** Max number of command parameters */
  static private final int MAX_COUNT_PARAM = 1;
  
  /** The flag to sort files in inverse order */
  static private final String REV_SORT = "r";
  
  /** The flag to print files in sub-directories */
  static private final String REC_LIST = "R";
  
  /**
   * @param flagParser
   */
  public ListFiles(IFlagParser flagParser, String name) {
    super(flagParser, name);    
  }
  
  /**
   * Sorts files by their names
   * 
   * @param filelst files it needs to sort
   * @param reverseSort set true if it needs to sort in inverse order
   */
  private void SortFiles(List<File> filelst, boolean reverseSort) {
    if (reverseSort) {
      Collections.sort(
          filelst, 
          (f1, f2)-> f2.getName().compareTo(f1.getName()));
    }
    else {
      Collections.sort(
          filelst, 
          (f1, f2) -> f1.getName().compareTo(f2.getName()));
    }
  }
  
  /**
   * Prints files in given directory
   * 
   * @param dir where to print files
   * @param reverseSort true is they will be sorted in inverse order
   * @param rec recursive printing
   * @param indent an indent for sub-directories
   */
  private void getFileList(
      File dir,
      boolean reverseSort,
      boolean rec,
      int indent) {
    
    // get file list
    var fileList = 
        new ArrayList<File>(Arrays.asList(dir.listFiles()));
    
    SortFiles(fileList, reverseSort);
    
    // set the indent
    var chs = new char[indent]; 
    Arrays.fill(chs, ' ');
    var spaces = new String(chs);

    for (var fd : fileList) {
      if (fd.isFile()) {
        System.out.println(spaces + fd.getName());
      }
      else if (fd.isDirectory()) {
        System.out.println(spaces + fd.getName() + "/");
        if (rec) {
          getFileList(fd, reverseSort, rec, indent + 4);
        }
      }// else if
    }// for
  }
  
  /**
   * Prints a list of files form given directory 
   * according to given flags
   * 
   * @param dir
   * @param flags
   */
  private void listFiles(String dir, String[] flags) {
    getFileList(
        new File(dir), 
        containsFlag(flags, REV_SORT), 
        containsFlag(flags, REC_LIST),
        0);
  }
  
  @Override
  protected void getHelp() {
    System.out.println("Prints files and directories");
    System.out.println(getName() + " [OPTIONS] [DIRECTORY]");
    System.out.println("  " + getFlagParser().getFullFlag(REV_SORT) + 
        " sort in inverse order");
    System.out.println("  " + getFlagParser().getFullFlag(REC_LIST) +
        " show files in sub-directories");
  }
  
  @Override
  protected boolean hasFlag(String flag) {
    switch (flag) {
      case REV_SORT:
      case REC_LIST:
      case HELP:
        return true;
        
      default:
        return false;
    }  
  }
  
  @Override
  public int execute(String ...args) {
    // getting the flags
    var recvFlags = getFlagParser().getFlags(args, MAX_COUNT_FLAGS);
    int code = super.execute(recvFlags);
    if (code == FLAG_ERR) {
      return FLAG_ERR;
    }
    if (code == HELP_SHOWED) {
      return SUCCESS_CODE;
    }
    
    // if the command received only flag then show files
    // in current directory
    if (args.length == 0 
        || getFlagParser().isFlag(args[args.length - 1])) 
    {
      listFiles("./", recvFlags);
    }
    // if the command received a directory print files in it
    else if (args.length == 1 ||
        !getFlagParser().isFlag(args[args.length - 1]) && 
        getFlagParser().isFlag(args[args.length - 2])) {
      
      listFiles(args[args.length - 1], recvFlags);
    }
    // if there are more than one directory then show error
    else {
      System.err.println("Too much arguments ");
      return PARAM_ERR;
    }
    return SUCCESS_CODE;
  }

}
