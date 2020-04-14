package net.sourceforge.pmd.lang.apex.rule.performance;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTDmlUpdateStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import net.sourceforge.pmd.lang.ast.Node;

public class UseObjectCacheQueryUtilToGetFieldsRule extends AbstractApexRule{
	List<String>listOfMethods=new ArrayList<>();
	public UseObjectCacheQueryUtilToGetFieldsRule() {
		listOfMethods.add("getAutomatedPaymentSetupFields");
		listOfMethods.add("getBalanceFields");
		listOfMethods.add("getBrokerFields");
		listOfMethods.add("getChargesFields");
		listOfMethods.add("getCommissionPlanFields");
		listOfMethods.add("getContractPrePaidFeesFields");
		listOfMethods.add("getDepositDetailFields");
		listOfMethods.add("getDisbursementPlanFields");
		listOfMethods.add("getDisbursementScheduleFields");
		listOfMethods.add("getDueDetailsFields");
		listOfMethods.add("getFrozenFeesConfigFields");
		listOfMethods.add("getHolidayScheduleFields");
		listOfMethods.add("getIptFields");
		listOfMethods.add("getLoanBlockCodeFields");
		listOfMethods.add("getLoanDisbursalTxnFields");
		listOfMethods.add("getLoanPaymentTxnFields");
		listOfMethods.add("getLoanSnapshotFields");
		listOfMethods.add("getLoanTransactionSummaryFields");
		listOfMethods.add("getOtherTxnFields");
		listOfMethods.add("getRateScheduleFields");
		listOfMethods.add("getRefinanceTxnFields");
		listOfMethods.add("getRepaymentPlanFields");
		listOfMethods.add("getScheduleFields");
		listOfMethods.add("getScheduleSummaryFields");
		listOfMethods.add("getSkinnyLoanObjectFields");
		listOfMethods.add("getSlidingBillingRangeFields");
		listOfMethods.add("getStepUpScheduleFields");
	}

	@Override
    public Object visit(ASTMethodCallExpression node, Object data) {
		if(listOfMethods.contains(node.getMethodName())&&!node.getFullMethodName().contains("ObjectCacheQueryUtil."+node.getMethodName())) {
			addViolation(data,node);
		}
        return data;
    }

}
