/**
 * 
 */
package fileutils;

/**
 * The status codes common for all commands
 */
public interface ICommonCodes {
  
 /** A command has done successfully */
 static final int SUCCESS_CODE = 0;
 
 /** The Given flags are wrong*/
 static final int FLAG_ERR = 1;
 
 /** The given arguments are wrong */
 static final int PARAM_ERR = 2;
 
}
