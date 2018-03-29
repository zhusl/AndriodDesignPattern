//Copyright 2006 Stephane GINER
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package fr.expression4j.basic.operatorimpl.realcomplex;

import fr.expression4j.basic.MathematicalElement;
import fr.expression4j.basic.MathematicalException;
import fr.expression4j.basic.Operator;
import fr.expression4j.basic.OperatorImpl;
import fr.expression4j.basic.impl.ComplexImpl;
import fr.expression4j.core.exception.EvalException;

public class OperatorDivideRealComplex implements OperatorImpl {

	public boolean isUnaryOperator() {
		return false;
	}

	public int getLeftOperandeType() {
		return 1;
	}

	public int getRightOperandeType() {
		return 2;
	}

	public String getOperatorName() {
		return Operator.OPERATOR_DIVIDE;
	}

	public MathematicalElement compute(MathematicalElement leftElement,
			MathematicalElement rightElement) throws EvalException {
		
		try {
			double realValue;
			double complexValue;

			if (leftElement.getType() == 1 && rightElement.getType() == 2) {
				//a(b+ci) ==> a*(b-ci)/(b+ci)*(b-ci)
				double a = leftElement.getRealValue();
				double b = rightElement.getRealValue();
				double c = rightElement.getComplexValue();
				realValue = (a*b)/(b*b+c*c);
				if (c > 0) {
					complexValue = -(a*c)/(b*b+c*c);
				}
				else {
					complexValue = (a*c)/(b*b+c*c);
				}
				return new ComplexImpl(realValue,complexValue);
			}
			else if (leftElement.getType() == 2 && rightElement.getType() == 1) {
				realValue = leftElement.getRealValue() / rightElement.getRealValue();
				complexValue = leftElement.getComplexValue() / rightElement.getRealValue();
				return new ComplexImpl(realValue,complexValue);
			}
			else {
				throw new EvalException("Incosistent element type for operator minus real complex.");
			}
			
		}
		catch (MathematicalException me) {
			throw new EvalException("Cannot evaluate value for divide real complex.",me);
		}
	}

}
