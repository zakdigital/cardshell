/******************************************************************************
 * CardFileType.java
 *
 * Author: Sascha Zak
 * Date  : 31.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

/**
 * Enumerates common types of {@link File}s.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public enum FileType {

  /** master file */
  MASTER_FILE,
  /** answer to reset */
  ATR;
}
