/******************************************************************************
 * CardTerminal.java
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
 * Represents a card terminal abstracted from a concrete technology.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public interface Terminal {

  /**
   * Returns a list of available {@link Card}s.
   *
   * @return list of available {@link Card}s or an empty list, if no card is available
   */
  @NonNull
  public List<Card> getCards();

  /**
   * Returns a (human readable) name of this card terminal.
   *
   * @return (human readable) name of this card terminal
   */
  @NonNull
  public String getName();
}
