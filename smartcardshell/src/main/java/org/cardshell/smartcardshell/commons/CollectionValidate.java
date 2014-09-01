/******************************************************************************
 * CollectionValidate.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.commons;

import java.util.Collection;

/**
 * Validator resposible to validate {@link Collection}s
 *
 * @author Sascha Zak
 * @since 0.1.0
 * @param <T>
 *          type of {@link Throwable} for invalid arguments
 */
public interface CollectionValidate<T extends Throwable> extends Validate<T> {

  /**
   * Validates that a given {@link Collection} contains a required element.
   *
   * @param collection
   *          collection to validate
   * @param element
   *          element that must be contained by the {@link Collection}
   * @return the validated collection
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
