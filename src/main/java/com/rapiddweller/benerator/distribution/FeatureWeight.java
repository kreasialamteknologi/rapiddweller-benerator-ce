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

import com.rapiddweller.common.Converter;
import com.rapiddweller.common.NullSafeComparator;
import com.rapiddweller.common.accessor.FeatureAccessor;
import com.rapiddweller.common.converter.AnyConverter;

/**
 * Implements the IndividualWeight function for arbitrary feature names,
 * supporting e.g. properties, attributes, or Map keys.<br/><br/>
 * Created at 27.04.2008 17:23:45
 *
 * @author Volker Bergmann
 * @since 0.5.2
 */
public class FeatureWeight extends IndividualWeight<Object> {

  private final String feature;

  private final FeatureAccessor<Object, Double> accessor;
  private final Converter<Object, Double> converter;

  // constructors ----------------------------------------------------------------------------------------------------

  /**
   * Instantiates a new Feature weight.
   */
  public FeatureWeight() {
    this("weight");
  }

  /**
   * Instantiates a new Feature weight.
   *
   * @param feature the feature
   */
  public FeatureWeight(String feature) {
    this.feature = feature;
    this.accessor = new FeatureAccessor<>(feature);
    this.converter = new AnyConverter<>(Double.class);
  }

  // interface -------------------------------------------------------------------------------------------------------

  /**
   * Gets weight feature.
   *
   * @return the weight feature
   */
  public String getWeightFeature() {
    return accessor.getFeatureName();
  }

  /**
   * Sets weight feature.
   *
   * @param weightFeature the weight feature
   */
  public void setWeightFeature(String weightFeature) {
    this.accessor.setFeatureName(weightFeature);
  }

  @Override
  public double weight(Object object) {
    return converter.convert(accessor.getValue(object));
  }

  // java.lang.Object overrides --------------------------------------------------------------------------------------

  @Override
  public int hashCode() {
    return accessor.getFeatureName().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final FeatureWeight that = (FeatureWeight) obj;
    return NullSafeComparator.equals(this.getWeightFeature(), that.getWeightFeature());
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + '[' + feature + ']';
  }

}
