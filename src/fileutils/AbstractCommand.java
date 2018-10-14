
package fileutils;

/**
 *
 */
abstract class AbstractCommand implements ICommand {
  
  static protected final int HELP_SHOWED = -1;
  
  /** The flag to get help */
  static protected final String HELP = "help";
  
  //static protected final int SUCCESS_CODE = 0;
  
  private IFlagParser _flagParser;
  
  private String _name;
  
  public AbstractCommand(IFlagParser flagParser, String name) {
    _flagParser = flagParser;
    _name = name;
  }
  
  final protected String getName() {
    return _name;
  }
  
  final protected IFlagParser getFlagParser() {
    return _flagParser;
  }
  
  final protected boolean containsFlag(String[] flagsSet, String flag) {
    for (var f : flagsSet) {
      if (f.equals(flag)) {
        return true;
      }
    }
    return false;
  }
  
  public int execute(String ...args) {
   String[] recvFlags = args;
    // checking whether the flags are corretc 
    for (var fl : recvFlags) {
      if (!hasFlag(fl)) {
        System.err.println("Unrecognized option " +
            getFlagParser().getFullFlag(fl));
        System.err.println("Try "+  _flagParser.getFullFlag(HELP)
            + " for more info");
        return FLAG_ERR;
      }
    }
    // getting help
    if (containsFlag(recvFlags, HELP)) {
      getHelp();
      return HELP_SHOWED;
    }
    return SUCCESS_CODE;
  }
  
  protected abstract boolean hasFlag(String flag);
  
  protected abstract void getHelp();
  
}
