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

package com.rapiddweller.benerator.distribution;

import static org.junit.Assert.assertEquals;

import com.rapiddweller.benerator.Generator;
import com.rapiddweller.benerator.NonNullGenerator;
import com.rapiddweller.benerator.SequenceTestGenerator;
import com.rapiddweller.benerator.test.GeneratorTest;
import com.rapiddweller.benerator.wrapper.WrapperFactory;
import org.junit.Test;

/**
 * Tests the {@link IndexBasedSampleGeneratorProxy}.<br/><br/>
 * Created: 21.07.2010 07:09:23
 * @since 0.6.3
 * @author Volker Bergmann
 */
public class IndexBasedSampleGeneratorProxyTest extends GeneratorTest {

	@Test
	public void testSourceHandling() {
		SequenceTestGenerator<Integer> source = new SequenceTestGenerator<>(1, 2, 3);
		Distribution distribution = new TestDistribution();
		NonNullGenerator<Integer> generator = WrapperFactory.asNonNullGenerator(
				new IndexBasedSampleGeneratorProxy<>(source, distribution, false));
		
		// on initialization, DistributingSampleGeneratorProxy scans throug all available 3 source values 
		// plus a single call that returns null for signaling unavailability
		generator.init(context);
		assertEquals(4, source.generateCount);
		assertEquals(Integer.valueOf(1), generator.generate());
		assertEquals(4, source.generateCount);

		// on reset(), the source must be scanned once more
		generator.reset();
		assertEquals(1, source.resetCount);
		assertEquals(8, source.generateCount);
		assertEquals(Integer.valueOf(1), generator.generate());
		
		// on close(), the source must be closed too
		generator.close();
		assertEquals(8, source.generateCount);
		assertEquals(1, source.closeCount);
	}
	
	public static class TestDistribution implements Distribution {

		@Override
		public <T> Generator<T> applyTo(Generator<T> source, boolean unique) {
	        throw new UnsupportedOperationException("not implemented");
        }

		@Override
		@SuppressWarnings("unchecked")
        public <T extends Number> NonNullGenerator<T> createNumberGenerator(Class<T> numberType, T min, T max, T granularity,
                boolean unique) {
	        return (NonNullGenerator<T>) WrapperFactory.asNonNullGenerator(new SequenceTestGenerator<>(0, 1, 2));
        }
	}
	
}