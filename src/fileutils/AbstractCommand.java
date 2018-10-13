/**
 * 
 */
package fileutils;

/**
 *
 */
abstract class AbstractCommand implements ICommand {
  
  private IFlagParser _flagParser;
  
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
  
  public AbstractCommand(IFlagParser flagParser) {
    _flagParser = flagParser;
  }
  
}
