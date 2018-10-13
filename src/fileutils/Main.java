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
  	// TODO Auto-generated method stub
    
    
    var t = new UnixFlagParser();
    var creater = new CommandCreater(t);
    
    if (args.length == 0) {
      return;
    }
    
    var command = creater.createCommand(args[0]);
    if (command != null) {
      int code = command.execute(Arrays.copyOfRange(args, 1, args.length));
      System.out.println("Status code: " + code);
    }
    else {
      System.out.println("Command not found");
    }
    
    /*var flags = t.getFlags(args);
    if (flags == null) {
      System.out.println("no flags");
      return;
    }
    for (var f : flags) {
      System.out.println(f);
    }*/
  }

}
