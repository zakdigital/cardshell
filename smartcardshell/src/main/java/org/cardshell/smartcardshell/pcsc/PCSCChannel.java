/******************************************************************************
 * PCSCCardChannel.java
 *
 * Author: Sascha Zak
 * Date  : 30.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.pcsc;

import javax.smartcardio.CardException;

import org.cardshell.smartcardshell.BasicChannel;
import org.cardshell.smartcardshell.Channel;
import org.cardshell.smartcardshell.CommandAPDU;
import org.cardshell.smartcardshell.ResponseAPDU;
import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.Validate;

/**
 * PC/SC implementation of a {@link Channel}.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@SuppressWarnings("restriction")
public class PCSCChannel extends BasicChannel {

  /** internal card channel */
  private final javax.smartcardio.CardChannel cardChannel;

  /**
   * Creates a new instance from a given card channel.
   *
   * @param cardChannel
   *          card channel
   */
  public PCSCChannel(@NonNull final javax.smartcardio.CardChannel cardChannel) {
    super(String.format("Channel-%s", Validate.ARG.isNotNull(cardChannel).getChannelNumber()));
    this.cardChannel = cardChannel;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Channel#transmit(CommandAPDU)
   */
  @Override
  public final ResponseAPDU transmit(final CommandAPDU commandAPDU) {
    final javax.smartcardio.CommandAPDU request = new javax.smartcardio.CommandAPDU(commandAPDU.getBytes());
    try {
      final javax.smartcardio.ResponseAPDU response = cardChannel.transmit(request);
      return new ResponseAPDU(response.getBytes());
    } catch (final CardException e) {
      throw new IllegalStateException("Failed to transmit data to card channel", e);
    }
  }
}
