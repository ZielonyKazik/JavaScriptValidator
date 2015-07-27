package expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Error;
import parser.StringContainer;

public abstract class ComplexExpression extends Expression {

	public ComplexExpression(String name, int currentLine, Map<String, StringContainer> strings) {
		super(name, currentLine, strings);
	}
	protected List<Expression> statements;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, List<Error>> getAllErrors() {
		HashMap<Integer, List<Error>> hash = new HashMap<>();
		if(!errors.isEmpty())
			hash.put(line, getErrors());
		for(Expression exp : statements)
		{
			for(Integer l: exp.getAllErrors().keySet())
			{
				if(!hash.keySet().contains(l))
					hash.put(l, exp.getAllErrors().get(l));
				else
					hash.get(l).addAll(exp.getAllErrors().get(l));
			}
		}
		return hash;
	}
	@Override
	public boolean isValid() {
//		for(Expression exp : statements)
//			if(!exp.isValid())
//				return false;
		return true;
	}	

}
