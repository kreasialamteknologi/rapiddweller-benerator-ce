/*
 * (c) Copyright 2006-2020 by rapiddweller GmbH & Volker Bergmann. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, is permitted under the terms of the
 * GNU General Public License.
 *
 * For redistributing this software or a derivative work under a license other
 * than the GPL-compatible Free Software License as defined by the Free
 * Software Foundation or approved by OSI, you must first obtain a commercial
 * license to this software product from rapiddweller GmbH & Volker Bergmann.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * WITHOUT A WARRANTY OF ANY KIND. ALL EXPRESS OR IMPLIED CONDITIONS,
 * REPRESENTATIONS AND WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE
 * HEREBY EXCLUDED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.rapiddweller.model.data;

/**
 * Provides enumerations for different uniqueness requirements.<br/><br/>
 * Created: 17.02.2010 08:59:03
 *
 * @author Volker Bergmann
 * @since 0.6.0
 */
public enum Uniqueness {

  /**
   * None uniqueness.
   */
  NONE(false),
  /**
   * Simple uniqueness.
   */
  SIMPLE(true),
  /**
   * Ordered uniqueness.
   */
  ORDERED(true);

  private final boolean unique;

  Uniqueness(boolean unique) {
    this.unique = unique;
  }

  /**
   * Instance uniqueness.
   *
   * @param unique  the unique
   * @param ordered the ordered
   * @return the uniqueness
   */
  public static Uniqueness instance(boolean unique, boolean ordered) {
    return (unique ? (ordered ? Uniqueness.ORDERED : Uniqueness.SIMPLE) :
        Uniqueness.NONE);
  }

  /**
   * Is unique boolean.
   *
   * @return the boolean
   */
  public boolean isUnique() {
    return unique;
  }

}
