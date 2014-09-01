/******************************************************************************
 * CardShellBannerProvider.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.BannerProvider;
import org.springframework.stereotype.Component;

/**
 * {@link Shell}-implementation of a {@link BannerProvider}.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
final class ShellBannerProvider implements BannerProvider {

  private static final String VERSION = "0.1.0";
  private static final String PROVIDER = "zak digital";
  private static final String WELCOME = "Welcome to the Card Shell. For assistance type \"help\".";

  /** banner */
  private final String banner;

  /**
   * Creates a new instance and creates the banner.
   */
  public ShellBannerProvider() {
    final StringBuffer buffer = new StringBuffer();
    buffer.append("\n");
    buffer.append("    ___              _   __ _          _ _ \n");
    buffer.append("   / __\\__ _ _ __ __| | / _\\ |__   ___| | |\n");
    buffer.append("  / /  / _` | '__/ _` | \\ \\| '_ \\ / _ \\ | |\n");
    buffer.append(" / /__| (_| | | | (_| | _\\ \\ | | |  __/ | |\n");
    buffer.append(" \\____/\\__,_|_|  \\__,_| \\__/_| |_|\\___|_|_|\n");
    buffer.append(" __________________________________________\n");
    banner = buffer.toString();
  }

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.shell.plugin.BannerProvider#getBanner()
   */
  @Override
  public final String getBanner() {
    return banner;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.shell.plugin.NamedProvider#getProviderName()
   */
  @Override
  public final String getProviderName() {
    return PROVIDER;

  }

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.shell.plugin.BannerProvider#getVersion()
   */
  @Override
  public final String getVersion() {
    return VERSION;

  }

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.shell.plugin.BannerProvider#getWelcomeMessage()
   */
  @Override
  public final String getWelcomeMessage() {
    return WELCOME;
  }
}
