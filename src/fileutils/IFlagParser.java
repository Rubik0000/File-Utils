/**
 * 
 */
package fileutils;

/**
 *
 */
public interface IFlagParser {

  /**
   * Extracts command flags from given strings
   * 
   * @return An array of flag strings without special splitters
   */
  String[] getFlags(String[] args, int maxCount);
  
  String[] getFlags(String[] args);
}
