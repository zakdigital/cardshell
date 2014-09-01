/******************************************************************************

 * ShowCommands.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.command;

import java.util.List;

import org.cardshell.smartcardshell.Card;
import org.cardshell.smartcardshell.Channel;
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
public class ShowCommands implements CommandMarker {

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
  @CliCommand(value = "show", help = "Shows a list of entities")
  public final String show(
      @CliOption(key = "terminals", help = "Shows a list of card terminals", specifiedDefaultValue = "") final String cardTerminal,
      @CliOption(key = "cards", help = "Shows a list of cards", specifiedDefaultValue = "") final String card,
      @CliOption(key = "channels", help = "Shows a list of card channels", specifiedDefaultValue = "") final String cardChannel) {
    if (cardTerminal != null) {
      return showTerminals();
    }
    if (card != null) {
      return showCards();
    }
    if (cardChannel != null) {
      return showCardChannels();
    }
    return "Show target missing or unknown";
  }

  private final String showCardChannels() {
    final Card selectedCard = shell.getSelectedCard();
    if (selectedCard == null) {
      return "No card selected";
    }
    final List<Channel> cardChannels = selectedCard.getCardChannels();
    if (cardChannels.isEmpty()) {
      return "No open card channels available";
    }
    final StringBuffer buffer = new StringBuffer();
    cardChannels.stream().map(c -> c.getName()).sorted().forEach(s -> buffer.append(s + "\n"));
    return buffer.toString();
  }

  private final String showCards() {
    final Terminal selectedCardTerminal = shell.getSelectedCardTerminal();
    if (selectedCardTerminal == null) {
      return "No card terminal selected";
    }
    final List<Card> cards = selectedCardTerminal.getCards();
    if (cards.isEmpty()) {
      return "No card available";
    }
    final StringBuffer buffer = new StringBuffer();
    cards.stream().map(c -> c.getName()).sorted().forEach(s -> buffer.append(s + "\n"));
    return buffer.toString();
  }

  private final String showTerminals() {
    final List<Terminal> terminals = shell.getCardTerminals();
    if (terminals.isEmpty()) {
      return "No card terminal available";
    }
    final StringBuffer buffer = new StringBuffer();
    terminals.stream().map(t -> t.getName()).sorted().forEach(s -> buffer.append(s + "\n"));
    return buffer.toString();
  }
}
