/******************************************************************************
 * CommandAPDU.java
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
 * Application Protocol Data Unit (APDU) for command requests to smartcards.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public class CommandAPDU extends APDU {

  /**
   * Creates a new mutable command APDU and initializes it with the given buffer. The internal buffer's length is set to
   * the length of the buffer passed.
   *
   * @param buffer
   *          the byte array to be used for holding the APDU
   */
  public CommandAPDU(final byte[] buffer) {
    super(buffer);
  }

  /**
   * Creates a new mutable command APDU and initializes it with the given buffer. The buffer is assumed to hold an APDU.
   * The (logical) length of the internally buffered APDU is set to length.
   *
   * @param buffer
   *          the byte array to be used for holding the APDU
   * @param length
   *          the (logical) length of the APDU currently in the buffer
   * @throws IndexOutOfBoundsException
   *           if the given length exceeds the size of the buffer
   */
  public CommandAPDU(final byte[] buffer, final int length) throws IndexOutOfBoundsException {
    super(buffer, length);
  }

  /**
   * Creates a new mutable command APDU with a given buffer size. A new buffer with the given size is allocated. The
   * length of the internally buffered APDU is set to 0.
   *
   * @param size
   *          the size of the buffer to create
   * @see #getLength
   */
  public CommandAPDU(final int size) {
    super(size);
  }
}
