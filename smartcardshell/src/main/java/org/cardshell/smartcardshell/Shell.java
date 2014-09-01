/******************************************************************************
 * CardShell.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

import java.util.List;

import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.Nullable;

/**
 * The {@link Shell} is the main controller component responsible to communicate with {@link Terminal}s and
 * {@link Card}s.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public interface Shell {

  /**
   * Returns a list of currently available {@link Terminal}s.
   *
   * @return list of available {@link Terminal}s or an empty list
   */
  @NonNull
  public List<Terminal> getCardTerminals();

  /**
   * Returns the currently selected {@link Card}.
   *
   * @return currently selected {@link Card}
   */
  @Nullable
  public Card getSelectedCard();

  /**
   * Returns the currently selected {@link Channel}.
   *
   * @return currently selected {@link Channel}
   */
  @Nullable
  public Channel getSelectedCardChannel();

  /**
   * Returns the currently selected {@link Terminal}.
   *
   * @return currently selected {@link Terminal}
   */
  @Nullable
  public Terminal getSelectedCardTerminal();

  /**
   * Selects the given {@link Card}. If another {@link Card} was selected before, it will be automatically unselected.
   *
   * @param card
   *          {@link Card} to select or {@code null} to unselect a currently selected {@link Card}
   */
  public void selectCard(@Nullable Card card);

  /**
   * Selects the given {@link Channel}. If another {@link Channel} was selected before, it will be automatically
   * unselected.
   *
   * @param cardChannel
   *          {@link Channel} to select or {@code null} to unselect the currently selected {@link Channel}
   */
  public void selectCardChannel(@Nullable Channel cardChannel);

  /**
   * Selects the given {@link Terminal}. If another {@link Terminal} was selected before, it will be
   * automatically unselected.
   *
   * @param cardTerminal
   *          {@link Terminal} to select or {@code null} to unselect a currently selected {@link Card}
   */
  public void selectCardTerminal(@Nullable Terminal cardTerminal);
}
