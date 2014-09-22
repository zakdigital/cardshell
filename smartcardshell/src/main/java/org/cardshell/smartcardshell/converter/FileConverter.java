/******************************************************************************
 * FileConverter.java
 *
 * Author: Sascha Zak
 * Date  : 01.09.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.converter;

import static org.cardshell.smartcardshell.OptionContext.CARD_FILE_CONTEXT;

import java.util.List;

import org.cardshell.smartcardshell.DefaultFile;
import org.cardshell.smartcardshell.File;
import org.cardshell.smartcardshell.FileType;
import org.cardshell.smartcardshell.commons.Hex;
import org.springframework.shell.core.Completion;
import org.springframework.shell.core.Converter;
import org.springframework.shell.core.MethodTarget;
import org.springframework.stereotype.Component;

/**
 * Converter implementation for {@link File}s.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@Component
public final class FileConverter implements Converter<File> {

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.shell.core.Converter#convertFromText(java.lang.String, java.lang.Class, java.lang.String)
   */
  @Override
  public File convertFromText(final String value, final Class<?> targetType, final String optionContext) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    final FileType fileType = FileType.getBySynonym(value);
    if (fileType != null) {
      return new DefaultFile(fileType.getId());
    }
    return new DefaultFile(Hex.parseHexString(value));
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
    for (final String synonym : FileType.getAllSynonyms().keySet()) {
      if (synonym.startsWith(existingData)) {
        completions.add(new Completion(synonym));
      }
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
    return File.class.isAssignableFrom(type) && optionContext.contains(CARD_FILE_CONTEXT);
  }
}
