/******************************************************************************
 * OptionContext.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

/**
 * Enumerates option context constants.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public final class OptionContext {

  /** constant for card terminal context */
  public static final String CARD_TERMINAL_CONTEXT = "cardterminal.context";

  /** constant for card context */
  public static final String CARD_CONTEXT = "card.context";

  /** constant for card channel context */
  public static final String CARD_CHANNEL_CONTEXT = "cardchannel.context";

  /** constant for card file context */
  public static final String CARD_FILE_CONTEXT = "cardfile.context";

  /**
   * Prevents instantiation.
   */
  private OptionContext() {}
}
