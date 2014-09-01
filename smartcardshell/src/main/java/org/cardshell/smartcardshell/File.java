/******************************************************************************
 * CardFile.java
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
 * Represents a file on a {@link Card}. This can be the master file (MF), a dedicated file (DF), or an elementary file
 * (EF).
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public interface File {

  /**
   * Returns the 2 byte file identifier.
   * 
   * @return the 2 byte file identifier
   */
  public byte[] getId();
}
