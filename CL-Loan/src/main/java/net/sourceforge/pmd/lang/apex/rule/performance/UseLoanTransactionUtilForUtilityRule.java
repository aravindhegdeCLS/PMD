package net.sourceforge.pmd.lang.apex.rule.performance;
import java.util.ArrayList;
import java.util.Arrays;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
public class UseLoanTransactionUtilForUtilityRule extends AbstractApexRule{
    
    ArrayList<String> functionsList;
    
    public UseLoanTransactionUtilForUtilityRule()
    {
        functionsList=new ArrayList<String>(
                Arrays.asList("getRoundedAmount", 
                        "postInterestTillDate",
                        "getDelinquencyDetails",
                        "interestBearingDelinquencyLogic",
                        "checkDelinquency",
                        "getAccrualBaseMethodCode",
                        "getInterestTillDate",
                        "getTotalOutstandingBalance",
                        "updateFeesRemaining"
                        )
                );
                
    }
    
    @Override
    public Object visit(ASTMethodCallExpression node, Object data)
    {
        if(functionsList.contains(node.getMethodName()))
        {
            if(!(node.getFullMethodName().contains("LoanTransactionUtil")))
            {
                //System.out.println("error");
                addViolation(data,node);
            }
        }
        return data;
        
    }

 

}