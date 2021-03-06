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

import com.rapiddweller.common.ArrayBuilder;
import com.rapiddweller.common.CollectionUtil;
import com.rapiddweller.common.StringUtil;
import com.rapiddweller.common.collection.ListBasedSet;
import com.rapiddweller.common.collection.NamedValueList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Describes a type that aggregates {@link ComponentDescriptor}s.<br/>
 * <br/>
 * Created: 03.03.2008 10:56:16
 *
 * @author Volker Bergmann
 * @since 0.5.0
 */
public class ComplexTypeDescriptor extends TypeDescriptor
    implements VariableHolder {

  /**
   * The constant __SIMPLE_CONTENT.
   */
  public static final String __SIMPLE_CONTENT = "__SIMPLE_CONTENT";

  private NamedValueList<InstanceDescriptor> parts;

  // constructors ----------------------------------------------------------------------------------------------------

  /**
   * Instantiates a new Complex type descriptor.
   *
   * @param name     the name
   * @param provider the provider
   */
  public ComplexTypeDescriptor(String name, DescriptorProvider provider) {
    this(name, provider, (String) null);
  }

  /**
   * Instantiates a new Complex type descriptor.
   *
   * @param name     the name
   * @param provider the provider
   * @param parent   the parent
   */
  public ComplexTypeDescriptor(String name, DescriptorProvider provider,
                               ComplexTypeDescriptor parent) {
    super(name, provider, parent);
    init();
  }

  /**
   * Instantiates a new Complex type descriptor.
   *
   * @param name       the name
   * @param provider   the provider
   * @param parentName the parent name
   */
  public ComplexTypeDescriptor(String name, DescriptorProvider provider,
                               String parentName) {
    super(name, provider, parentName);
    init();
  }

  // component handling ----------------------------------------------------------------------------------------------

  /**
   * Add part.
   *
   * @param part the part
   */
  public void addPart(InstanceDescriptor part) {
    if (part instanceof ComponentDescriptor) {
      addComponent((ComponentDescriptor) part);
    } else {
      addVariable((VariableDescriptor) part);
    }
  }

  /**
   * Add component.
   *
   * @param descriptor the descriptor
   */
  public void addComponent(ComponentDescriptor descriptor) {
    String componentName = descriptor.getName();
    if (parent != null &&
        ((ComplexTypeDescriptor) parent).getComponent(componentName) !=
            null) {
      descriptor.setParent(((ComplexTypeDescriptor) parent)
          .getComponent(componentName));
    }
    parts.add(componentName, descriptor);
  }

  /**
   * Sets component.
   *
   * @param component the component
   */
  public void setComponent(ComponentDescriptor component) {
    String componentName = component.getName();
    if (parent != null &&
        ((ComplexTypeDescriptor) parent).getComponent(componentName) !=
            null) {
      component.setParent(((ComplexTypeDescriptor) parent)
          .getComponent(componentName));
    }
    parts.set(componentName, component);
  }

  /**
   * Gets component.
   *
   * @param name the name
   * @return the component
   */
  public ComponentDescriptor getComponent(String name) {
    for (InstanceDescriptor part : parts.values()) {
      if (StringUtil.equalsIgnoreCase(part.getName(), name) &&
          part instanceof ComponentDescriptor) {
        return (ComponentDescriptor) part;
      }
    }
    if (getParent() != null) {
      return ((ComplexTypeDescriptor) getParent()).getComponent(name);
    }
    return null;
  }

  /**
   * Gets parts.
   *
   * @return the parts
   */
  public List<InstanceDescriptor> getParts() {
    NamedValueList<InstanceDescriptor> result =
        NamedValueList.createCaseInsensitiveList();

    for (InstanceDescriptor ccd : parts.values()) {
      result.add(ccd.getName(), ccd);
    }
    if (getParent() != null) {
      List<InstanceDescriptor> parentParts =
          ((ComplexTypeDescriptor) getParent()).getParts();
      for (InstanceDescriptor pcd : parentParts) {
        String name = pcd.getName();
        if (pcd instanceof ComponentDescriptor &&
            !parts.containsName(name)) {
          InstanceDescriptor ccd = parts.someValueOfName(name);
          result.add(name, Objects.requireNonNullElse(ccd, pcd));
        }
      }
    }
    return result.values();
  }

  /**
   * Gets components.
   *
   * @return the components
   */
  public List<ComponentDescriptor> getComponents() {
    List<ComponentDescriptor> result = new ArrayList<>();
    for (InstanceDescriptor instance : getParts()) {
      if (instance instanceof ComponentDescriptor) {
        result.add((ComponentDescriptor) instance);
      }
    }
    return result;
  }

  /**
   * Gets declared parts.
   *
   * @return the declared parts
   */
  public Collection<InstanceDescriptor> getDeclaredParts() {
    Set<InstanceDescriptor> declaredDescriptors =
        new ListBasedSet<>(parts.size());
    declaredDescriptors.addAll(parts.values());
    return declaredDescriptors;
  }

  /**
   * Is declared component boolean.
   *
   * @param componentName the component name
   * @return the boolean
   */
  public boolean isDeclaredComponent(String componentName) {
    return parts.containsName(componentName);
  }

  /**
   * Get id component names string [ ].
   *
   * @return the string [ ]
   */
  public String[] getIdComponentNames() {
    ArrayBuilder<String> builder = new ArrayBuilder<>(String.class);
    for (ComponentDescriptor descriptor : getComponents()) {
      if (descriptor instanceof IdDescriptor) {
        builder.add(descriptor.getName());
      }
    }
    return builder.toArray();
  }

  /**
   * Gets reference components.
   *
   * @return the reference components
   */
  public List<ReferenceDescriptor> getReferenceComponents() {
    return CollectionUtil.extractItemsOfExactType(ReferenceDescriptor.class,
        getComponents());
  }

  @Override
  public void addVariable(VariableDescriptor variable) {
    parts.add(variable.getName(), variable);
  }

  // construction helper methods -------------------------------------------------------------------------------------

  /**
   * With component complex type descriptor.
   *
   * @param componentDescriptor the component descriptor
   * @return the complex type descriptor
   */
  public ComplexTypeDescriptor withComponent(
      ComponentDescriptor componentDescriptor) {
    addComponent(componentDescriptor);
    return this;
  }

  @Override
  protected void init() {
    super.init();
    this.parts = new NamedValueList<>(NamedValueList.INSENSITIVE);
  }

  // java.lang.Object overrides --------------------------------------------------------------------------------------

  @Override
  public String toString() {
    if (parts.size() == 0) {
      return super.toString();
    }
    //return new CompositeFormatter(false, false).render(super.toString() + '{', new CompositeAdapter(), "}");
    return getName() + getParts();
  }

}