/******************************************************************************
 * CardTerminalConverter.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.converter;

import static org.cardshell.smartcardshell.OptionContext.CARD_TERMINAL_CONTEXT;

import java.util.List;

import org.cardshell.smartcardshell.Shell;
import org.cardshell.smartcardshell.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.Completion;
import org.springframework.shell.core.Converter;
import org.springframework.shell.core.MethodTarget;
import org.springframework.stereotype.Component;

/**
 * Converter implementation for {@link Terminal}s.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@Component
public final class TerminalConverter implements Converter<Terminal> {

  /** card shell instance */
  @Autowired
  private Shell shell;

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.shell.core.Converter#convertFromText(java.lang.String, java.lang.Class, java.lang.String)
   */
  @Override
  public Terminal convertFromText(final String value, final Class<?> targetType, final String optionContext) {
    for (final Terminal terminal : shell.getCardTerminals()) {
      if (terminal.getName().equals(value)) {
        return terminal;
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.shell.core.Converter#getAllPossibleValues(java.util.List, java.lang.Class,
   *      java.lang.String, java.lang.String, org.springframework.shell.core.MethodTarget)
   */
  @Override
  public boolean getAllPossibleValues(final List<Completion> completions, final Class<?> targetType,
      final String existingData, final String optionContext, final MethodTarget target) {
    for (final Terminal terminal : shell.getCardTerminals()) {
      completions.add(new Completion(terminal.getName()));
    }
    return true;

  }

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.shell.core.Converter#supports(java.lang.Class, java.lang.String)
   */
  @Override
  public boolean supports(final Class<?> type, final String optionContext) {
    return Terminal.class.isAssignableFrom(type) && optionContext.contains(CARD_TERMINAL_CONTEXT);
  }
}
