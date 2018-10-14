/**
 * 
 */
package fileutils;

import java.util.Arrays;

/**
 *
 */
public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    var flagParser = new UnixFlagParser();
    var commandCreater = new CommandCreater(flagParser);
    
    if (args.length == 0) {
      return;
    }
    
    var command = commandCreater.createCommand(args[0]);
    if (command != null) {
      int code = command.execute(Arrays.copyOfRange(args, 1, args.length));
      System.out.println("\r\nStatus code: " + code);
    }
    else {
      System.out.println("Command not found");
    }
  }
}
