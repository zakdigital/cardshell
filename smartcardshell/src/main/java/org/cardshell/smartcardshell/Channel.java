/******************************************************************************
 * CardChannel.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.Nullable;

/**
 * Represents a (logical) card channel used for communication.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public interface Channel {

  /**
   * Returns the name of this channel.
   *
   * @return channel name
   */
  public String getName();

  /**
   * Returns the {@link File} currently selected within this {@link Channel}.
   *
   * @return selected {@link File} or {@code null}, if no {@link File} is currently selected
   */
  @Nullable
  public File getSelectedCardFile();

  /**
   * Selects the given {@link File}.
   *
   * @param cardFile
   *          {@link File} to be selected
   */
  public void selectCardFile(@NonNull File cardFile);

  /**
   * Send an {@link CommandAPDU} request to this card and returns the {@link ResponseAPDU}.
   *
   * @param apdu
   *          {@link CommandAPDU} as command request
   * @return {@link ResponseAPDU} as command response
   */
  @NonNull
  public ResponseAPDU transmit(@NonNull CommandAPDU commandAPDU);
}
