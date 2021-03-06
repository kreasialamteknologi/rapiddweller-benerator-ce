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

package com.rapiddweller.benerator.primitive;

import com.rapiddweller.benerator.Generator;
import com.rapiddweller.benerator.NonNullGenerator;
import com.rapiddweller.benerator.util.GeneratorUtil;
import com.rapiddweller.benerator.wrapper.GeneratorChain;
import com.rapiddweller.common.ArrayBuilder;

import java.util.Set;

/**
 * Creates Strings in an incremental manner.<br/><br/>
 * Created: 02.08.2011 10:34:08
 *
 * @author Volker Bergmann
 * @since 0.7.0
 */
public class IncrementalStringGenerator extends GeneratorChain<String> implements NonNullGenerator<String> {

  /**
   * Instantiates a new Incremental string generator.
   *
   * @param chars             the chars
   * @param minLength         the min length
   * @param maxLength         the max length
   * @param lengthGranularity the length granularity
   */
  public IncrementalStringGenerator(Set<Character> chars, int minLength, int maxLength, int lengthGranularity) {
    super(String.class, false, createSources(chars, minLength, maxLength, lengthGranularity));
  }

  @Override
  public String generate() {
    return GeneratorUtil.generateNonNull(this);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private static Generator<? extends String>[] createSources(Set<Character> chars, int minLength, int maxLength,
                                                             int lengthGranularity) {
    ArrayBuilder<Generator> builder = new ArrayBuilder<>(Generator.class);
    for (int i = minLength; i <= maxLength; i += lengthGranularity) {
      builder.add(new UniqueFixedLengthStringGenerator(chars, i, true));
    }
    return builder.toArray();
  }

}
