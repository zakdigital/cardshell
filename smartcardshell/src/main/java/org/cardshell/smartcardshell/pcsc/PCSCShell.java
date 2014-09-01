/******************************************************************************
 * PCSCCardShell.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.pcsc;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.TerminalFactory;

import org.cardshell.smartcardshell.Card;
import org.cardshell.smartcardshell.Channel;
import org.cardshell.smartcardshell.Shell;
import org.cardshell.smartcardshell.Terminal;
import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.Nullable;
import org.springframework.stereotype.Component;

/**
 * PC/SC implementation of the {@link Shell}.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@Component
@SuppressWarnings("restriction")
public final class PCSCShell implements Shell {

  /** selected card terminal */
  private Terminal cardTerminal;

  /** selected smartcard */
  private Card card;

  /** selected card channel */
  private Channel cardChannel;

  /** card terminals */
  private final CardTerminals terminals = TerminalFactory.getDefault().terminals();

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Shell#getCardTerminals()
   */
  @NonNull
  @Override
  public final List<Terminal> getCardTerminals() {
    try {
      return Collections.unmodifiableList(terminals.list().stream().sorted().map(t -> new PCSCTerminal(t))
          .collect(Collectors.toList()));
    } catch (final CardException e) {
      return Collections.emptyList();
    }
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Shell#getSelectedCard()
   */
  @Nullable
  @Override
  public final Card getSelectedCard() {
    return card;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Shell#getSelectedCardChannel()
   */
  @Override
  public final Channel getSelectedCardChannel() {
    return cardChannel;

  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Shell#getSelectedCardTerminal()
   */
  @Nullable
  @Override
  public final Terminal getSelectedCardTerminal() {
    return cardTerminal;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Shell#selectCard(org.cardshell.smartcardshell.Card)
   */
  @Override
  public final void selectCard(@Nullable final Card card) {
    this.card = card;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Shell#selectCardChannel(org.cardshell.smartcardshell.Channel)
   */
  @Override
  public final void selectCardChannel(final Channel cardChannel) {
    this.cardChannel = cardChannel;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.Shell#selectCardTerminal(org.cardshell.smartcardshell.Terminal)
   */
  @Override
  public final void selectCardTerminal(@Nullable final Terminal cardTerminal) {
    this.cardTerminal = cardTerminal;
  }
}
