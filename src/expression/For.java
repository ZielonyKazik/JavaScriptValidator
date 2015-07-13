package expression;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

import exception.WrongComplexException;
import exception.WrongForException;

public class For extends Expression{
	String  condition;
	List<Expression> statements;
	public For(String name, String cond, List<Expression> stats) {
		super(name);
		condition = cond;
		statements = stats;		
	}

	@Override
	public Expression get(int index) throws IndexOutOfBoundsException {
		if(index == 0)
			return this;
		else
			return statements.get(index - 1);
	}

	@Override
	public String toString() {
		return "For";
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
/*
	private Expression makeFor(String group) throws IOException, WrongComplexException {
		Matcher arg = Patterns.arg.matcher(group);
		Matcher states = Patterns.states.matcher(group);
		String arguments,statesments; 
		if (arg.find())
			arguments = arg.group();
		else
			throw new WrongForException("Ivalid arguments", group);
		if (states.find())
			statesments = states.group();
		else {
			throw new WrongForException("Invalid block", group);
		}

		return new For(group, arguments, parseExpressions(statesments));

	}

*/