/******************************************************************************

 * SelectCommands.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.command;

import static org.cardshell.smartcardshell.OptionContext.CARD_CHANNEL_CONTEXT;
import static org.cardshell.smartcardshell.OptionContext.CARD_CONTEXT;
import static org.cardshell.smartcardshell.OptionContext.CARD_FILE_CONTEXT;
import static org.cardshell.smartcardshell.OptionContext.CARD_TERMINAL_CONTEXT;

import org.cardshell.smartcardshell.Card;
import org.cardshell.smartcardshell.Channel;
import org.cardshell.smartcardshell.File;
import org.cardshell.smartcardshell.Shell;
import org.cardshell.smartcardshell.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * Collection of commands related to {@link Card}s.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@Component
public class SelectCommands implements CommandMarker {

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
  @CliCommand(value = "select", help = "Selects a choosen entity")
  public final String select(
      @CliOption(key = "terminal", help = "Selects a card terminal", optionContext = CARD_TERMINAL_CONTEXT) final Terminal cardTerminal,
      @CliOption(key = "card", help = "Selects a card from the selected card terminal", optionContext = CARD_CONTEXT) final Card card,
      @CliOption(key = "channel", help = "Selects a card channel from the selected card", optionContext = CARD_CHANNEL_CONTEXT) final Channel cardChannel,
      @CliOption(key = "file", help = "Selects a file from the selected  card channel", optionContext = CARD_FILE_CONTEXT) final File cardFile) {
    if (cardTerminal != null) {
      shell.selectCardTerminal(cardTerminal);
      return null;
    }
    if (card != null) {
      shell.selectCard(card);
      return null;
    }
    if (cardChannel != null) {
      shell.selectCardChannel(cardChannel);
      return null;
    }
    if (cardFile != null) {
      final Channel selectedCardChannel = shell.getSelectedCardChannel();
      if (selectedCardChannel == null) {
        throw new IllegalStateException("No card channel selected");
      }
      selectedCardChannel.selectCardFile(cardFile);
      return null;
    }
    return "Selection target missing or unknown";
  }
}
