/******************************************************************************
 * CardTerminalCommands.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.command;

import org.cardshell.smartcardshell.Shell;
import org.cardshell.smartcardshell.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.stereotype.Component;

/**
 * Collection of commands related to {@link Terminal}s.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@Component
public class TerminalCommands implements CommandMarker {

  /** card shell instance */
  @Autowired
  private Shell shell;

}
