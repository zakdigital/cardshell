/******************************************************************************
 * CollectionAssert.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.commons.assertion;

import java.util.Collection;

import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.Nullable;

/**
 * Assertion resposible to assert properties on {@link Collection}s
 *
 * @author Sascha Zak
 * @since 0.1.0
 * @param <T>
 *          type of {@link Throwable} for invalid assertion arguments
 */
public interface CollectionAssert<T extends Throwable> extends Assert<T> {

  /**
   * Asserts that a given {@link Collection} contains a required element.
   *
   * @param collection
   *          collection to assert
   * @param element
   *          element that must be contained by the {@link Collection}
   * @return the asserted collection
   * @throws T
   *           if the required element is not contained by the given {@link Collection}
   */
  @NonNull
  public default <E, C extends Collection<E>> C contains(@NonNull final C collection, @Nullable final E element)
      throws T {
    isNotNull(collection);
    if (!collection.contains(element)) {
      throw invalid("The given collection [" + collection + "] does not contain the element[" + element + "]!");
    }
    return collection;
  }
}
