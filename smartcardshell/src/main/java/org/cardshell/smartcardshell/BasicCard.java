/******************************************************************************
 * BasicCard.java
 *
 * Author: Sascha Zak
 * Date  : 31.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.cardshell.smartcardshell.commons.NonNull;

/**
 * Basic implementation of a {@link Card} without any dependencies to hardware or specific standards.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public abstract class BasicCard extends NamedEntity implements Card {

  /** internal list of open card channels */
  protected final List<Channel> openCardChannels = new CopyOnWriteArrayList<Channel>();

  /**
   * Creates a new instance with the given name.
   *
   * @param name
   *          immutable, human readable name of this entity, must not be {@code blank}
   */
  public BasicCard(@NonNull final String name) {
    super(name);
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Card#getCardChannels()
   */
  @Override
  public List<Channel> getCardChannels() {
    return Collections.unmodifiableList(openCardChannels);
  }
}
