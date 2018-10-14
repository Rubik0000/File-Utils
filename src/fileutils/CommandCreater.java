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
        return new ListFiles(_parser, "ls");
     
      case "mkdir":
        return new MakeDir(_parser, "mkdir");
        
      case "echo":
        return new CreateFile(_parser, "echo");
        
      case "cat":
        return new ShowFilesContent(_parser, "cat");
        
      default:
        return null;
    }
  }

}
