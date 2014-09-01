/******************************************************************************
 * ResponseAPDU.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zakdigital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

/**
 * Application Protocol Data Unit (APDU) for command responses delivered from smartcards.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public final class ResponseAPDU extends APDU {

  /**
   * Creates a new mutable response APDU and initializes it with the given APDU bytes. The internal buffer's length is
   * set to the length of the buffer passed.
   *
   * @param apdu
   *          byte array to be used for holding the APDU
   * @throws IllegalArgumentException
   *           if the given buffer is not a valid response APDU
   */
  public ResponseAPDU(final byte[] apdu) {
    super(apdu);
    if (apdu.length < 2) {
      throw new IllegalArgumentException("Invalid response APDU - length has to be at least 2 bytes");
    }
    apduLength = apdu.length;
  }

  /**
   * Creates a new mutable response APDU with the given buffer size. A new buffer with the given size is allocated. The
   * length of the internally buffered APDU is set to 0.
   *
   * @param size
   *          the size of the buffer to create
   * @throws IllegalArgumentException
   *           if the given buffer is not a valid response APDU
   */
  public ResponseAPDU(final int size) {
    super(size);
    if (size < 2) {
      throw new IllegalArgumentException("Invalid response APDU - length has to be at least 2 bytes");
    }
  }

  /**
   * Returns the data fields of the APDU.
   *
   * @return byte array containing the APDU data field
   */
  public final byte[] getData() {
    final byte[] data = new byte[apduLength - 2];
    System.arraycopy(apdu, 0, data, 0, apduLength - 2);
    return data;
  }

  /**
   * Returns the value of {@code SW1} and {@code SW2} as a short integer. It is computed as: (((SW1<<8)&0xFF00) |
   * (SW2&0xFF)).
   *
   * @return status word as integer
   */
  public final int getSW() {
    return getSW1() << 8 & 0xFF00 | getSW2() & 0xFF;
  }

  /**
   * Returns the value of {@code SW1} as a byte.
   *
   * @return status word 1 as byte
   */
  public final byte getSW1() {
    return apdu[apduLength - 2];
  }

  /**
   * Returns the value of {@code SW2} as a byte.
   *
   * @return status word 2 as byte
   */
  public final byte getSW2() {
    return apdu[apduLength - 1];
  }
}
