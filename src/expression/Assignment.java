package expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Atoms.Statement;
import Atoms.StringContainer;
import enums.Error;
import exception.InvalidFunction;
import exception.InvalidOperator;
import exception.WrongAssignmentException;
import parser.ParseUtils;
import parser.Patterns;
public class Assignment extends Expression {
	 Statement first;
	 Statement second;
	public Assignment(String statement, int currentLine, Map<String, StringContainer> strings) throws WrongAssignmentException {
		super(statement, strings);
		String side[] = statement.split(Patterns.assignDivisionS);
		line = currentLine + ParseUtils.getLinesBNS(statement);
		if(side.length == 2)
		{
			first = new Statement(side[0]);
			second = new Statement(side[1]);
		}
		else
			throw new WrongAssignmentException(Error.WrongAssignment, statement);
	}
	@Override
	public Expression get(int index) throws IndexOutOfBoundsException {
		if(index == 0)
			return this;
		else
			throw new IndexOutOfBoundsException();
	}
	public String toString()
	{
		return "Assignment";
	}
	@Override
	public boolean isValid() {
		try{
			first.isValid();
			second.isValid();
		}catch(InvalidOperator e)
		{
			this.addError(e.getError());
			return false;
		}catch(InvalidFunction e){
			this.addError(e.getError());
			return false;
		}
		return true;
	}
}
