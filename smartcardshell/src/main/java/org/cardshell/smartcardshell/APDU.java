/******************************************************************************
 * APDU.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

import java.util.Arrays;

/**
 * Basic Application Protocol Data Unit (APDU) using an internal byte buffer.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public abstract class APDU {

  /** internal buffer to hold the APDU */
  protected byte[] apdu;

  /** logical length of the APDU currently in the buffer. */
  protected int apduLength = 0;

  /**
   * Creates a new mutable APDU and initializes it with the given buffer. The internal buffer's length is set to the
   * length of the buffer passed.
   *
   * @param buffer
   *          the byte array to be used for holding the APDU
   */
  public APDU(final byte[] buffer) {
    apdu = buffer;
    apduLength = buffer.length;
  }

  /**
   * Creates a new mutable APDU and initializes it with the given buffer. The buffer is assumed to hold an APDU. The
   * length of the internally buffered APDU is set to the given length.
   *
   * @param buffer
   *          the byte array to be used for holding the APDU
   * @param length
   *          the (logical) length of the APDU currently in the buffer
   * @throws IndexOutOfBoundsException
   *           if the (logical) length exceeds the size of the buffer
   */
  public APDU(final byte[] buffer, final int length) {
    apdu = buffer;
    apduLength = length;
  }

  /**
   * Creates a new mutable APDU with a given buffer size. A new buffer with the given size is allocated. The length of
   * the internally buffered APDU is set to 0.
   *
   * @param size
   *          the size of the buffer to create
   */
  public APDU(final int size) {
    apdu = new byte[size];
    apduLength = 0;
  }

  /**
   * Appends the given byte to the internally buffered APDU.
   *
   * @param b
   *          the byte to be appended
   * @throws IndexOutOfBoundsException
   *           if the buffer size is exceeded
   */
  public final void append(final byte b) throws IndexOutOfBoundsException {
    apdu[apduLength++] = b;
  }

  /**
   * Appends the given byte array to the internally buffered APDU.
   *
   * @param bytes
   *          the byte array to be appended
   * @throws IndexOutOfBoundsException
   *           if the buffer size is exceeded
   */
  public final void append(final byte[] bytes) throws IndexOutOfBoundsException {
    System.arraycopy(bytes, 0, apdu, apduLength, bytes.length);
    apduLength += bytes.length;
  }

  /**
   * Returns the internal APDU buffer. This method allows complex manipulations of the buffered APDU, for example MAC
   * calculation. If the length of the APDU is changed by such an operation, {@link #setLength(int)} has to be used to
   * store the new length.
   *
   * @return the buffer that holds the current APDU
   */
  public final byte[] getBuffer() {
    return apdu;
  }

  /**
   * Returns the byte at the specified position in the buffer. The byte is converted to a positive integer in the range
   * 0..255. This method can only be used to access the APDU currently stored. It is not possible to read beyond the end
   * of the APDU.
   *
   * @param index
   *          the position in the buffer
   * @return the value at the given position, or -1 if the position is invalid
   */
  public final int getByte(final int index) {
    if (index >= apduLength) {
      return -1;
    }
    return apdu[index] & 255;
  }

  /**
   * Returns a byte array holding the buffered APDU. The byte array returned gets allocated with the exact size of the
   * buffered APDU. To get direct access to the internal buffer, use {@link #getBuffer()}.
   *
   * @return the buffered APDU, copied into a new array
   */
  public final byte[] getBytes() {
    final byte[] apdu = new byte[apduLength];
    System.arraycopy(apdu, 0, apdu, 0, apduLength);
    return apdu;
  }

  /**
   * Returns the length of the buffered APDU.
   *
   * @return the length of the APDU currently stored
   */
  public final int getLength() {
    return apduLength;
  }

  /**
   * Sets the byte at the specified position in the buffer. The byte is passed as an integer, for consistence with
   * {@link #getByte(int)}. This method can only be used to <i>modify</i> an APDU already stored. It is not possible to
   * set bytes beyond the end of the current APDU, in this case it does nothing. Use {@link #append(byte)} to extend the
   * APDU.
   *
   * @param index
   *          the position in the buffer
   * @param value
   *          the byte to store there
   */
  public final void setByte(final int index, final int value) {
    if (index < apduLength) {
      apdu[index] = (byte) value;
    }
  }

  /**
   * Sets the length of valid range within the APDU buffer. This method can be used to cut off the end of the APDU. It
   * can also be used to increase the size of the APDU. In this case, it is the caller's responsibility to fill the
   * additional bytes with useful information.
   *
   * @param length
   *          new length of the valid range
   * @throws IndexOutOfBoundsException
   *           thrown when the buffer size is exceeded
   */
  public final void setLength(final int length) throws IndexOutOfBoundsException {
    if (length > apdu.length) {
      throw new IndexOutOfBoundsException();
    }
    apduLength = length;
  }

  /**
   * Returns a human-readable string representation of this APDU. This method does not use caching but creates the
   * string from scratch on each invocation.
   *
   * @return a hex dump of the APDU currently stored
   */
  @Override
  public final String toString() {
    return toHexString(Arrays.copyOfRange(apdu, 0, apduLength));
  }

  /**
   * Returns a hex string representation of the given byte array.
   *
   * @param bytes
   *          byte array to convert
   * @return hex string representation of the given byte array
   */
  public static final String toHexString(final byte[] bytes) {
    final StringBuilder sb = new StringBuilder(bytes.length * 2);
    for (final byte b : bytes) {
      sb.append(String.format("%02x", b & 0xff));
    }
    return sb.toString();
  }
}
