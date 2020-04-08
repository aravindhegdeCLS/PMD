package net.sourceforge.pmd.lang.apex.rule.performance;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class UseGlobalUtilFacadeForUtilityRule extends AbstractApexRule{
	List<String>listOfMethods=new ArrayList<>();
	public UseGlobalUtilFacadeForUtilityRule() {
		listOfMethods.add("addCycle");
		listOfMethods.add("calculateRepaymentSchedule");
		listOfMethods.add("getCurrentSystemDate");
		listOfMethods.add("getCurrentSystemDateTime");
		listOfMethods.add("getNextCycleDate");
		listOfMethods.add("getOrgParameters");
		listOfMethods.add("saveRateSchedule");
		listOfMethods.add("saveRepaymentPlan");
		listOfMethods.add("validateRateSchedule");
		listOfMethods.add("validateRepaymentPlan");
		
	}

	@Override
    public Object visit(ASTMethodCallExpression node, Object data) {
		if(listOfMethods.contains(node.getMethodName())&&!node.getFullMethodName().equals("loan.GlobalLoanUtilFacade."+node.getMethodName())) {
			addViolation(data,node);
		}
        return data;
    }

}
