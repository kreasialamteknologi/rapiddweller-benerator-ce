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

package com.rapiddweller.platform;

import com.rapiddweller.common.ErrorHandler;
import com.rapiddweller.common.context.DefaultContext;
import com.rapiddweller.platform.contiperf.PerfTrackingTaskProxy;
import com.rapiddweller.stat.LatencyCounter;
import com.rapiddweller.task.Task;
import com.rapiddweller.task.TaskMock;
import com.rapiddweller.task.TaskResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the {@link PerfTrackingTaskProxy}.<br/><br/>
 * Created: 25.02.2010 09:16:55
 *
 * @author Volker Bergmann
 * @since 0.6.0
 */
public class PerfTrackingTaskProxyTest {

  /**
   * Test.
   */
  @Test
  public void test() {
    DefaultContext context = new DefaultContext();
    Task task = new TaskMock(0, context);
    PerfTrackingTaskProxy proxy = new PerfTrackingTaskProxy(task);
    for (int i = 0; i < 100; i++) {
      assertEquals(TaskResult.EXECUTING, proxy.execute(context, ErrorHandler.getDefault()));
    }
    LatencyCounter counter = proxy.getOrCreateTracker().getCounters()[0];
    assertEquals(100, counter.sampleCount());
    proxy.close();
  }

}
