/******************************************************************************
 * Card.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

import java.util.List;

import org.cardshell.smartcardshell.commons.NonNull;

/**
 * Representation of a smartcard.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public interface Card {

  /**
   * Returns a list of open {@link Channel}s.
   *
   * @return list of open {@link Channel}s or an empty list, if no channel is open
   */
  @NonNull
  public List<Channel> getCardChannels();

  /**
   * Returns a (human readable) name of the card.
   *
   * @return (human readable) name
   */
  @NonNull
  public String getName();

  /**
   * Tries to open a new {@link Channel}.
   *
   * @return new opened {@link Channel}
   * @throws IllegalStateException
   *           if there are no more card channels to be opened
   */
  @NonNull
  public Channel openChannel();

}
