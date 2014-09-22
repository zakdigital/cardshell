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

import java.nio.ByteBuffer;

import javax.smartcardio.CardException;

import org.cardshell.smartcardshell.BasicChannel;
import org.cardshell.smartcardshell.Channel;
import org.cardshell.smartcardshell.CommandAPDU;
import org.cardshell.smartcardshell.ResponseAPDU;
import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.assertion.Assert;

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

  private final byte number;

  /**
   * Creates a new instance from a given card channel.
   *
   * @param cardChannel
   *          card channel
   */
  public PCSCChannel(@NonNull final javax.smartcardio.CardChannel cardChannel) {
    super(String.format("Channel-%s", Assert.ARG.isNotNull(cardChannel).getChannelNumber()));
    this.cardChannel = cardChannel;
    number = (byte) cardChannel.getChannelNumber();
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Channel#getNumber()
   */
  @Override
  public byte getNumber() {
    return number;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Channel#transmit(byte[])
   */
  @Override
  public ResponseAPDU transmit(final byte[] commandAPDU) {
    try {
      final ByteBuffer commandBuffer = ByteBuffer.allocate(commandAPDU.length);
      commandBuffer.put(commandAPDU);
      commandBuffer.position(0);
      final ByteBuffer responseBuffer = ByteBuffer.allocate(256);
      final int bytes = cardChannel.transmit(commandBuffer, responseBuffer);
      final byte[] response = new byte[bytes];
      responseBuffer.get(response, 0, bytes);
      return new ResponseAPDU(response);
    } catch (final CardException e) {
      throw new IllegalStateException("Failed to transmit data to card channel", e);
    }
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
