package net.sourceforge.pmd.lang.apex.rule.performance;
import java.util.ArrayList;
import java.util.Arrays;

import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;


import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
public class UseHolidayUtilToGetNextDateRule extends AbstractApexRule{
	
	ArrayList<String> functionsList;
	
	public UseHolidayUtilToGetNextDateRule()
	{
		functionsList=new ArrayList<String>(
				Arrays.asList("getNextWorkingDate", 
                        "getPreviousWorkingDate"
                        )
				);
				
	}
	
	@Override
	public Object visit(ASTMethodCallExpression node, Object data)
	{
		if(functionsList.contains(node.getMethodName()))
		{
			if(!(node.getFullMethodName().equals("loan.HolidayUtil.INSTANCE."+node.getMethodName())))
			{
				//System.out.println("error");
				addViolation(data,node);
			}
		}
		return data;
		
	}

}
