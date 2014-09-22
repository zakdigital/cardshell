/******************************************************************************
 * DefaultFile.java
 *
 * Author: Sascha Zak
 * Date  : 08.09.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

import org.cardshell.smartcardshell.commons.Hex;
import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.assertion.Assert;

/**
 * Default implementation of a card {@link File}.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public class DefaultFile implements File {

  private final byte[] id;

  /**
   * Creates a new instance with the given file identifier.
   *
   * @param id
   *          identifier of the file
   */
  public DefaultFile(@NonNull final byte[] id) {
    this.id = Assert.ARG.isNotNull(id);
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.File#getId()
   */
  @Override
  public byte[] getId() {
    return id;
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return Hex.toHexString(id);
  }
}
