package expression;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import enums.Error;
import exception.InvalidOperator;
import exception.WrongIfException;

public class If extends ComplexExpression{
	Statement condition;
	public If(String statement, int currentLine, Map<String, String> strings, ExpressionParser expressionParser) throws WrongIfException, IOException {
		super(statement, currentLine, strings);
		Matcher arg = Patterns.arg.matcher(statement);
		Matcher states = Patterns.states.matcher(statement);
		String arguments, statements;
		if (arg.find())
			arguments = arg.group();
		else
			throw new WrongIfException(Error.InvalidArguments, statement);
		if (states.find())
			statements = states.group();
		else
			throw new WrongIfException(Error.InvalidBlock, statement);
		condition = new Statement(arguments);
		this.statements = expressionParser.parseExpressions(statements);
	}
	public If(String name, int currentLine, Statement condition2, Map<String, String> strings, List<Expression> statements) {
		super(name, currentLine, strings);
		this.condition = condition2;
		this.statements= statements;
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
		return "IF ";
	}
	protected Statement getCondition()
	{
		return condition;
	}
	protected List<Expression> getStatements()
	{
		return statements;
	}
	protected String getName()
	{
		return name;
	}
	@Override
	public boolean isValid() {
		if(!super.isValid())
			return false;
		try{
			condition.isValid();
		}catch(InvalidOperator e)
		{
			this.addError(e.getError());
			return false;
		}
		return true;
	}
}
