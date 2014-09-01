/******************************************************************************

 * CardCommands.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.command;

import org.cardshell.smartcardshell.Card;
import org.cardshell.smartcardshell.Channel;
import org.cardshell.smartcardshell.Shell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

/**
 * Collection of commands related to {@link Card}s.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@Component
public class CardCommands implements CommandMarker {

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
  @CliCommand(value = "open channel", help = "Opens a new card channel")
  public final String openChannel() {
    final Card card = shell.getSelectedCard();
    if (card == null) {
      return "No card selected";
    }
    final Channel channel = card.openChannel();
    return "Opened " + channel.getName();
  }

  /**
   * Returns whether the open channel command is available.
   *
   * @return {@code true} if the open channel command is available, {@code false} otherwise
   */
  @CliAvailabilityIndicator(value = "open channel")
  public boolean openChannelAvailable() {
    return shell.getSelectedCard() != null;
  }
}
