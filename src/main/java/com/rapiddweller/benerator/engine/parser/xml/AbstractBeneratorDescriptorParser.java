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

package com.rapiddweller.benerator.engine.parser.xml;

import com.rapiddweller.benerator.engine.Statement;
import com.rapiddweller.benerator.engine.expression.ErrorHandlerExpression;
import com.rapiddweller.benerator.engine.expression.context.DefaultPageSizeExpression;
import com.rapiddweller.benerator.engine.statement.GenerateOrIterateStatement;
import com.rapiddweller.benerator.engine.statement.RunTaskStatement;
import com.rapiddweller.benerator.engine.statement.WhileStatement;
import com.rapiddweller.common.ErrorHandler;
import com.rapiddweller.format.xml.AbstractXMLElementParser;
import com.rapiddweller.format.xml.ParseContext;
import com.rapiddweller.script.Expression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;

import java.util.Set;

import static com.rapiddweller.benerator.engine.DescriptorConstants.ATT_ON_ERROR;
import static com.rapiddweller.benerator.engine.DescriptorConstants.ATT_PAGESIZE;
import static com.rapiddweller.benerator.engine.parser.xml.DescriptorParserUtil.parseScriptableStringAttribute;

/**
 * Abstract parent class for Descriptor parsers.<br/><br/>
 * Created: 25.10.2009 00:43:18
 *
 * @author Volker Bergmann
 * @since 0.6.0
 */
public abstract class AbstractBeneratorDescriptorParser extends AbstractXMLElementParser<Statement> {

  /**
   * The Logger.
   */
  protected Logger logger = LogManager.getLogger(AbstractBeneratorDescriptorParser.class);

  /**
   * Instantiates a new Abstract benerator descriptor parser.
   *
   * @param elementName          the element name
   * @param requiredAttributes   the required attributes
   * @param optionalAttributes   the optional attributes
   * @param supportedParentTypes the supported parent types
   */
  public AbstractBeneratorDescriptorParser(String elementName,
                                           Set<String> requiredAttributes, Set<String> optionalAttributes, Class<?>... supportedParentTypes) {
    super(elementName, requiredAttributes, optionalAttributes, supportedParentTypes);
  }

  /**
   * Contains loop boolean.
   *
   * @param parentPath the parent path
   * @return the boolean
   */
  public static boolean containsLoop(Statement[] parentPath) {
    if (parentPath == null) {
      return false;
    }
    for (Statement statement : parentPath) {
      if (isLoop(statement)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Is loop boolean.
   *
   * @param statement the statement
   * @return the boolean
   */
  public static boolean isLoop(Statement statement) {
    return (statement instanceof RunTaskStatement)
        || (statement instanceof GenerateOrIterateStatement)
        || (statement instanceof WhileStatement);
  }

  /**
   * Contains generator statement boolean.
   *
   * @param parentPath the parent path
   * @return the boolean
   */
  public static boolean containsGeneratorStatement(Statement[] parentPath) {
    if (parentPath == null) {
      return false;
    }
    for (Statement statement : parentPath) {
      if (statement instanceof GenerateOrIterateStatement) {
        return true;
      }
    }
    return false;
  }

  @Override
  public final Statement doParse(Element element, Statement[] parentPath, ParseContext<Statement> context) {
    return doParse(element, parentPath, (BeneratorParseContext) context);
  }

  /**
   * Do parse statement.
   *
   * @param element    the element
   * @param parentPath the parent path
   * @param context    the context
   * @return the statement
   */
  public abstract Statement doParse(Element element, Statement[] parentPath, BeneratorParseContext context);

  /**
   * Parse on error attribute expression.
   *
   * @param element the element
   * @param id      the id
   * @return the expression
   */
  protected Expression<ErrorHandler> parseOnErrorAttribute(Element element, String id) {
    return new ErrorHandlerExpression(id, parseScriptableStringAttribute(ATT_ON_ERROR, element));
  }

  /**
   * Parse page size expression.
   *
   * @param element the element
   * @return the expression
   */
  protected Expression<Long> parsePageSize(Element element) {
    return DescriptorParserUtil.parseLongAttribute(ATT_PAGESIZE, element, new DefaultPageSizeExpression());
  }

}
