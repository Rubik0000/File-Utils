
package fileutils;

/**
 * Provides an interface to parse command flags from strings
 */
public interface IFlagParser {

  /**
   * Extracts command flags from given strings
   * 
   * @param args from which it needs to get flags
   * @param maxCount the max of flags that will be gotten
   * @return An array of flag strings without special splitters
   */
  String[] getFlags(String[] args, int maxCount);
  
  /**
   * Extracts command flags from given strings
   * 
   * @param args from which it needs to get flags
   * @return An array of flag strings without special splitters
   */
  String[] getFlags(String[] args);
  
  /**
   * Returns a flag with corresponding splitter
   * 
   * @param flag
   * @return
   */
  String getFullFlag(String flag);
  
  boolean isFlag(String str);
}
