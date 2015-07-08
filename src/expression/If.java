package expression;

import java.util.List;

public class If extends Expression{
	String condition;
	List<Expression> statements;
	public If(String name, String cond, List<Expression> stat) {
		super(name);
		condition = cond;
		statements = stat;
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
	private String isIfValid(){
		String error=OperatorCorrect.isOpreratorCorrect(condition);
		for(int i=0 ; i<statements.size(); i++){
			error+=OperatorCorrect.isOpreratorCorrect(statements.get(i).toString());
		}
		
		return error;
	}
	protected String getCondition()
	{
		return condition;
	}
	protected List<Expression> getStatements()
	{
		return statements;
	}
}