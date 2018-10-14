/**
 * 
 */
package fileutils;

/**
 *
 */
public interface ICommand extends ICommonCodes {
  
  /**
   * Executes a command
   * 
   * @param args flags and parameters
   * @return a status code 
   */
  int execute(String ...args);
}
