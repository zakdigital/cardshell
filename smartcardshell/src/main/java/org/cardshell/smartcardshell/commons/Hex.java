/******************************************************************************
 * Hex.java
 *
 * Author: Sascha Zak
 * Date  : 09.09.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.commons;

import org.cardshell.smartcardshell.commons.assertion.Assert;

/**
 * Utility class to support handling with hexadecimal encoded values.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public final class Hex {

  /** auxillary string array */
  private static final String[] HEX_CHARS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
    "E", "F" };

  /**
   * Prevents instantiation.
   */
  private Hex() {}

  /**
   * Parses the given hexadecimal represented {@link String} into an array of bytes.
   *
   * @param hexString
   *          the hexadecimal encoded {@link String} to be parsed, must not be {@code blank} and has to use two
   *          characters to represent one byte
   * @return the parsed byte array or an empty byte array
   */
  @NonNull
  public static final byte[] parseHexString(@NonNull final String hexString) {
    Assert.ARG.isNotBlank(hexString);
    Assert.ARG.isTrue(hexString.length() % 2 == 0);
    final byte[] result = new byte[hexString.length() / 2];
    for (int i = 0; i < hexString.length(); i += 2) {
      final String toParse = hexString.substring(i, i + 2);
      result[i / 2] = (byte) Integer.parseInt(toParse, 16);
    }
    return result;
  }

  /**
   * Converts the given byte array into a hexadecimal {@link String} representation.
   *
   * @param data
   *          Byte array to convert to HexString
   * @return hexadecimal representation
   */
  @NonNull
  public static final String toHexString(@NonNull final byte[] data) {
    Assert.ARG.isNotNull(data);

    final StringBuffer out = new StringBuffer();
    for (final byte element : data) {
      out.append(HEX_CHARS[element >> 4 & 0x0f]);
      out.append(HEX_CHARS[element & 0x0f]);
    }

    return out.toString();
  }

  /**
   * Converts the given int (as byte value) into a hexadecimal {@link String} representation.
   *
   * @param value
   *          value to be converted
   * @return converted hexadecimal representation
   */
  public static final String toHexString(final int value) {
    return HEX_CHARS[(value & 0xff & 0xf0) >>> 4] + HEX_CHARS[value & 0x0f];
  }
}
