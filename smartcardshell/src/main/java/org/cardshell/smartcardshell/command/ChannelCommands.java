/******************************************************************************

 * CardChannelCommands.java
 *
 * Author: Sascha Zak
 * Date  : 30.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.command;

import org.cardshell.smartcardshell.Channel;
import org.cardshell.smartcardshell.ResponseAPDU;
import org.cardshell.smartcardshell.Shell;
import org.cardshell.smartcardshell.apdu.Transmit;
import org.cardshell.smartcardshell.commons.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * Collection of commands related to {@link Channel}s.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@Component
public class ChannelCommands implements CommandMarker {

  /** card shell instance */
  @Autowired
  private Shell shell;

  /**
   * Selects the choosen card.
   *
   * @param card
   *          card to select
   * @return confirmation text
   */
  @CliCommand(value = "cd", help = "Changes the current DF")
  public final String cd(@CliOption(key = "path", unspecifiedDefaultValue = ".") final String path) {
    final Channel cardChannel = shell.getSelectedCardChannel();
    if (cardChannel == null) {
      return "No card channel selected";
    }
    if (path == null || path.trim().isEmpty() || path.equals(".")) {
      return null;
    }
    // TODO (Sascha Zak): implement cd command
    return "Nothing read";
  }

  /**
   * Returns whether the read command is available.
   *
   * @return {@code true}, if the read command is available, {@code false} otherwise
   */
  @CliAvailabilityIndicator(value = { "read", "cd" })
  public boolean isChannelCommandAvailable() {
    return shell.getSelectedCardChannel() != null;
  }

  /**
   * Selects the choosen card.
   *
   * @param card
   *          card to select
   * @return confirmation text
   */
  @CliCommand(value = "read", help = "Reads data from a card channel")
  public final String read() {
    final Channel cardChannel = shell.getSelectedCardChannel();
    if (cardChannel == null) {
      return "No card channel selected";
    }
    // TODO (Sascha Zak): implement read command
    return "Nothing read";
  }

  @CliCommand(value = "transmit", help = "Transmits an APDU")
  public final String transmit(@CliOption(key = "apdu", mandatory = true) final String apdu) {
    final Channel cardChannel = shell.getSelectedCardChannel();
    if (cardChannel == null) {
      return "No card channel selected";
    }

    final Transmit transmit = new Transmit(apdu);
    final byte[] transmitBytes = Hex.parseHexString(apdu);
    final ResponseAPDU response = cardChannel.transmit(transmitBytes);
    return transmit.interprete(response);
  }
}
