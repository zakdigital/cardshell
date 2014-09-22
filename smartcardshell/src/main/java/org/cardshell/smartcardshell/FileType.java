/******************************************************************************
 * CardFileType.java
 *
 * Author: Sascha Zak
 * Date  : 31.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.Map;

import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.assertion.Assert;

/**
 * Enumerates common types of {@link File}s.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public enum FileType {

  /** master file */
  MASTER_FILE(0x3F, 0x00, "MF", "/");

  /** synonym map */
  private static final Map<String, FileType> synonyms;

  static {
    synonyms = new HashMap<String, FileType>();
    for (final FileType value : FileType.values()) {
      for (final String syn : value.syns) {
        synonyms.put(syn, value);
      }
    }
  }

  private final byte[] id = new byte[2];

  private final String[] syns;

  private FileType(final int idByte0, final int idByte1, @NonNull final String... synonyms) {
    id[0] = (byte) idByte0;
    id[1] = (byte) idByte1;
    syns = Assert.ARG.isNotNull(synonyms);
  }

  /**
   * Returns the 2-bytes file identifier.
   *
   * @return 2-bytes file identifier
   */
  public byte[] getId() {
    return id;
  }

  /**
   * Returns the complete map of synonyms to {@link FileType}s.
   *
   * @return complete map of synonyms to {@link FileType}s
   */
  public static Map<String, FileType> getAllSynonyms() {
    return unmodifiableMap(synonyms);
  }

  public static final FileType getBySynonym(@NonNull final String synonym) {
    Assert.ARG.isNotNull(synonym);
    return synonyms.get(synonym);
  }
}
