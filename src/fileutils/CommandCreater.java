/**
 * 
 */
package fileutils;

/**
 *
 */
public class CommandCreater implements ICommandCreater {

  private IFlagParser _parser;
  
  public CommandCreater(IFlagParser parser) {
    _parser = parser;
  }
  
  /* (non-Javadoc)
   * @see fileutils.ICommandCreater#createCommand(java.lang.String)
   */
  @Override
  public ICommand createCommand(String name) {
    switch (name.toLowerCase()) {
      case "ls":
        return new ListFiles(_parser);
     
      case "mkdir":
        return new MakeDir(_parser);
        
      case "echo":
        return new CreateFile(_parser);
        
      case "cat":
        return new ShowFiles(_parser);
        
      default:
        return null;
    }
  }

}
