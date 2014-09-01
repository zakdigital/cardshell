/******************************************************************************
 * NamedEntity.java
 *
 * Author: Sascha Zak
 * Date  : 31.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.Validate;

/**
 * Basic implementation of an entity with an immutable,human readable name. This name is also returned by
 * {@link #toString()}.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public abstract class NamedEntity {

  /** name of the entity */
  private final String name;

  /**
   * Creates a new instance with the given name.
   *
   * @param name
   *          immutable, human readable name of this entity, must not be {@code blank}
   */
  public NamedEntity(@NonNull final String name) {
    this.name = Validate.ARG.isNotBlank(name);
  }

  /**
   * Returns the immutable, human readable name of this entity.
   *
   * @return name of this entity
   */
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return name;
  }
}
