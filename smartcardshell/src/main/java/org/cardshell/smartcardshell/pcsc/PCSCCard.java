/******************************************************************************
 * PCSCCard.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.pcsc;

import javax.smartcardio.CardException;

import org.cardshell.smartcardshell.BasicCard;
import org.cardshell.smartcardshell.Card;
import org.cardshell.smartcardshell.Channel;
import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.assertion.Assert;

/**
 * PC/SC implementation of a {@link Card} (using javax.smartcardio).
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@SuppressWarnings("restriction")
public final class PCSCCard extends BasicCard {

  /** internal card instance */
  private final javax.smartcardio.Card card;

  /**
   * Creates a new instance for the given {@link javax.smartcardio.Card}.
   *
   * @param card
   *          {@link javax.smartcardio.Card} instance
   */
  public PCSCCard(@NonNull final javax.smartcardio.Card card) {
    super("Card-" + Assert.ARG.isNotNull(card).getProtocol());
    this.card = card;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Card#openChannel()
   */
  @Override
  public Channel openChannel() {
    try {
      final javax.smartcardio.CardChannel channel = openCardChannels.isEmpty() ? card.getBasicChannel() : card
          .openLogicalChannel();
      final Channel cardChannel = new PCSCChannel(channel);
      openCardChannels.add(cardChannel);
      return cardChannel;
    } catch (final CardException e) {
      throw new IllegalStateException("Failed to open card channel", e);
    }
  }
}
