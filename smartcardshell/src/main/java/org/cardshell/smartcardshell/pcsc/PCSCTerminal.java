/******************************************************************************
 * PCSCCardTerminal.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.pcsc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.smartcardio.CardException;

import org.cardshell.smartcardshell.Card;
import org.cardshell.smartcardshell.Terminal;
import org.cardshell.smartcardshell.commons.NonNull;

/**
 * PC/SC implementation of a {@link Terminal} (uses javax.smartcardio).
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@SuppressWarnings("restriction")
public class PCSCTerminal implements Terminal {

  /** logger */
  private static final Logger LOG = Logger.getLogger(PCSCTerminal.class.getName());

  /** protocol T=0 */
  public static final String PROTOCOL_T0 = "T=0";

  /** protocol T=1 */
  public static final String PROTOCOL_T1 = "T=1";

  /** internal PC/SC card terminal */
  private final javax.smartcardio.CardTerminal cardTerminal;

  /** friendly name */
  private final String name;

  /**
   * Creates a new instance based on the given PC/SC card terminal.
   *
   * @param cardTerminal
   *          PC/SC card terminal
   */
  public PCSCTerminal(final javax.smartcardio.CardTerminal cardTerminal) {
    this.cardTerminal = cardTerminal;
    name = cardTerminal.getName().replace(' ', '_');
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Terminal#getCards()
   */
  @NonNull
  @Override
  public List<Card> getCards() {
    try {
      if (cardTerminal.isCardPresent()) {
        final List<Card> cards = new ArrayList<>();
        try {
          cards.add(new PCSCCard(cardTerminal.connect(PROTOCOL_T1)));
        } catch (final CardException et1) {
          LOG.fine(String.format("Failed to connect with protocol [%s]", PROTOCOL_T1));
          try {
            cards.add(new PCSCCard(cardTerminal.connect(PROTOCOL_T0)));
          } catch (final CardException et0) {
            LOG.fine(String.format("Failed to connect with protocol [%s]", PROTOCOL_T0));
          }
        }
        return Collections.unmodifiableList(cards);
      }
    } catch (final CardException e) {
      throw new IllegalStateException("Card terminal not longer available");
    }
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Terminal#getName()
   */
  @NonNull
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return name;
  }
}
