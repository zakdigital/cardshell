/******************************************************************************
 * BasicCardChannel.java
 *
 * Author: Sascha Zak
 * Date  : 31.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.Nullable;
import org.cardshell.smartcardshell.commons.Validate;

/**
 * Basic implementation of a {@link Channel} without any dependencies to harware or specific standards.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public abstract class BasicChannel implements Channel {

  /** currently selected card file */
  private File selectedCardFile;

  /** channel name */
  private final String name;

  /**
   * Creates a new instance with the given name.
   *
   * @param name
   *          name of this card channel, must not be {@code blank}.
   */
  public BasicChannel(@NonNull final String name) {
    this.name = Validate.ARG.isNotBlank(name);
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.cardshell.smartcardshell.Channel#getName()
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Channel#getSelectedCardFile()
   */
  @Nullable
  @Override
  public File getSelectedCardFile() {
    return selectedCardFile;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Channel#selectCardFile(org.cardshell.smartcardshell.File)
   */
  @Override
  public void selectCardFile(@NonNull final File cardFile) {
    selectedCardFile = Validate.ARG.isNotNull(cardFile);
  }
}
