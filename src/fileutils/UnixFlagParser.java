/**
 * 
 */
package fileutils;

import java.util.ArrayList;

/**
 * A flag parser for UNIX systems
 */
class UnixFlagParser implements IFlagParser {

  /** A one-character flag*/
  private static final char splitter1 = '-';
  
  /** A multi-characters flag */
  private static final String splitter2 = "--";
  
  @Override
  public String[] getFlags(String[] args) {
    return getFlags(args, -1);
  }
  
  private boolean isFlagType1(String flag) {
    return flag.length() > 1 &&
    flag.charAt(0) == splitter1 && 
    flag.charAt(1) != splitter1;
  }
  
  private boolean isFlagType2(String flag) {
    return flag.length() > 2 &&
    flag.substring(0, splitter2.length()).equals(splitter2);
  }
  
  @Override
  public String[] getFlags(String[] args, int maxCount) {
    int count = 0;
    var flags = new ArrayList<String>(args.length);
    for (var potFlag : args) {
      // if we have read more than needed
      if (++count > maxCount) {
        break;
      }
      if (isFlagType1(potFlag)) {        
        for (int i = 1; i < potFlag.length(); ++i) {
          var f = (Character)potFlag.charAt(i);
          flags.add(f.toString());
        }
      }
      else if (isFlagType2(potFlag)) {        
        flags.add(potFlag.substring(splitter2.length()));
      }
      // if the sequence of flags has ended
      else {
        break;
      }
    }
    return flags.toArray(new String[] {});
  }

  @Override
  public String getFullFlag(String flag) {
    if (flag == null || flag.length() == 0) {
      return null;
    }
    return flag.length() == 1 ? splitter1 + flag : splitter2 + flag;
  }

  @Override
  public boolean isFlag(String str) {
    return isFlagType1(str) || isFlagType2(str);
  }
}
