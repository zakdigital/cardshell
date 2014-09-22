/******************************************************************************
 * CardShellPromptProvider.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

import org.cardshell.smartcardshell.pcsc.PCSCShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.PromptProvider;
import org.springframework.stereotype.Component;

/**
 * {@link PCSCShell}-implementation of a {@link PromptProvider}.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
final class ShellPromptProvider implements PromptProvider {

  /** card shell instance */
  @Autowired
  private Shell shell;

  private static final String PROVIDER = "zak digital";

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.shell.plugin.PromptProvider#getPrompt()
   */
  @Override
  public String getPrompt() {
    final Terminal selectedCardTerminal = shell.getSelectedCardTerminal();
    final Card selectedCard = shell.getSelectedCard();
    final Channel selectedCardChannel = shell.getSelectedCardChannel();
    final StringBuffer prompt = new StringBuffer(">");
    if (selectedCardTerminal != null) {
      prompt.append(" ");
      prompt.append(selectedCardTerminal.getName());
      prompt.append(" >");
    }
    if (selectedCard != null) {
      prompt.append(" ");
      prompt.append(selectedCard.getName());
      prompt.append(" >");
    }
    if (selectedCardChannel != null) {
      prompt.append(" ");
      prompt.append(selectedCardChannel.getName());
      prompt.append(" >");

      final File selectedFile = selectedCardChannel.getSelectedCardFile();
      if (selectedFile != null) {
        prompt.append(" ");
        prompt.append(selectedFile.toString());
        prompt.append(" >");
      }
    }
    return prompt.toString();
  }

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.shell.plugin.NamedProvider#getProviderName()
   */
  @Override
  public String getProviderName() {
    return PROVIDER;
  }
}
