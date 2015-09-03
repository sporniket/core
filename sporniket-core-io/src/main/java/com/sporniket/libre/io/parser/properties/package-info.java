/**
 * Parser for properties file supporting multiple-line declaration using heredoc convention.
 * 
 * <p>The supported syntax follows those rules :</p>
 * <ul>
 * <li>A whitespace : ' ' (space) and tabulation.</li>
 * <li><code>name = value</code> : single line value identified by <code>name</code> ; any whitespace after '=' are stripped from the value.</li>
 * <li><code>name =>value</code> : single line value identified by <code>name</code> ; any whitespace after '=>' is part of the value.</li>
 * <li><code>name << endtag</code></li> : start of multiple line value identified by <code>name</code> ; the value start from the
 * next line until a line consisting of <code>endtag</code> surrounded with whitespaces or not (like heredoc syntax) ; any whitespace at the beginning of the lines are kept.
 * <li><code>name <<- endtag</code></li> : start of multiple line value identified by <code>name</code> ; the value start from the
 * next line until a line consisting of <code>endtag</code> surrounded with whitespaces or not (like heredoc syntax) ; any whitespace at the beginning of the lines are ignored.
 * <li>When not inside a multiple line value, lines starting with '#' or containing spaces only are ignored ; any other lines are
 * errors</li>
 * </ul>
 * 
 * <p>The parsing is event based, and consists of a line processor that notify listeners when a property is completed : one should use e.g. a {@link java.io.LineNumberReader} to read a source line by line, give the line to the parser and listen to events to get each parsed properties.</p>
 * 
 * <p>
 * &copy; Copyright 2002-2015 David Sporn
 * </p>
 * <hr>
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; io</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; io</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; io</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
 * more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211; io</i>.
 * If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN 
 * @version 15.09.00
 * @since 15.09.00
 */
package com.sporniket.libre.io.parser.properties;