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

package com.rapiddweller.benerator;

/**
 * Indicates exceptional generator states that stem from inappropriate
 * generator setup or use.<br/>
 * <br/>
 * Created: 07.06.2006 22:13:57
 *
 * @author Volker Bergmann
 * @since 0.1
 */
public class IllegalGeneratorStateException extends RuntimeException {

  private static final long serialVersionUID = -1403141408933329080L;

  /**
   * Instantiates a new Illegal generator state exception.
   *
   * @param message the message
   */
  public IllegalGeneratorStateException(String message) {
    super("Illegal Generator state: " + message);
  }

  /**
   * Instantiates a new Illegal generator state exception.
   *
   * @param message the message
   * @param cause   the cause
   */
  public IllegalGeneratorStateException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new Illegal generator state exception.
   *
   * @param cause the cause
   */
  public IllegalGeneratorStateException(Throwable cause) {
    super("Illegal Generator state: ", cause);
  }

}
