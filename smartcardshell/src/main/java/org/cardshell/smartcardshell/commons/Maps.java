/******************************************************************************
 * Maps.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.commons;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Utiliy class similar to {@link Collections} or {@link Arrays} to easily work with {@link Map} instances.‚
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public final class Maps {

  /**
   * Prevents instanciation.
   */
  private Maps() {}

  /**
   * Creates an {@link Entry} consisting of the given key and value.
   *
   * @param key
   *          entry key
   * @param value
   *          entry value
   * @return new {@link Entry} instance
   */
  public static final <K, V> Map.Entry<K, V> entry(final K key, final V value) {
    return new AbstractMap.SimpleImmutableEntry<>(key, value);
  }

  /**
   * Creates an {@link EnumMap} consisting of the given {@link Entry}s.
   *
   * @param entries
   *          variable number of {@link Map} {@link Entry}s or no {@link Map} {@link Entry} to create an empty
   *          {@link EnumMap}
   * @return new {@link EnumMap} instance
   */
  @SafeVarargs
  public static final <K extends Enum<K>, V> EnumMap<K, V> enumMap(final Entry<K, V>... entries) {
    return new EnumMap<>(map(entries));
  }

  /**
   * Creates a map consisting of the given {@link Entry}s.
   *
   * @param entries
   *          variable number of {@link Map} {@link Entry}s. or no {@link Map} {@link Entry}s to create an empty
   *          {@link Map}
   * @return new {@link Map} instance
   */
  @SafeVarargs
  public static final <K, V> Map<K, V> map(@NonNull final Entry<K, V>... entries) {
    return stream(entries).collect(toMap(Entry::getKey, Entry::getValue));
  }
}
