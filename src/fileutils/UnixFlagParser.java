/**
 * 
 */
package fileutils;

import java.util.ArrayList;

/**
 *
 */
class UnixFlagParser implements IFlagParser {

  private static final char splitter1 = '-';
  private static final String splitter2 = "--";
  
  @Override
  public String[] getFlags(String[] args) {
    return getFlags(args, -1);
  }
  
  @Override
  public String[] getFlags(String[] args, int maxCount) {
    int count = 0;
    var flags = new ArrayList<String>(args.length);
    for (var potFlag : args) {
      if (++count > maxCount) {
        break;
      }
      if (potFlag.length() > 1 &&
          potFlag.charAt(0) == splitter1 && 
          potFlag.charAt(1) != splitter1) {
        
        for (int i = 1; i < potFlag.length(); ++i) {
          var f = (Character)potFlag.charAt(i);
          flags.add(f.toString());
        }
      }
      else if (potFlag.length() > 2 &&
               potFlag.substring(0, splitter2.length()).equals(splitter2)) {
        
        flags.add(potFlag.substring(splitter2.length()));
      }
      // if the sequence of flags has ended
      else {
        break;
      }
    }
    return flags.toArray(new String[] {});
  }
}
