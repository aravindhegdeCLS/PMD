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
            if(!(node.getFullMethodName().contains("HolidayUtil")))
            {
                //System.out.println("error");
                addViolation(data,node);
            }
        }
        
        else if(node.getMethodName().matches("(.*)Next(.*)Working(.*)Date(.*)" ) || node.getMethodName().matches("(.*)Working(.*)Date(.*)") || node.getMethodName().matches("(.*)Previous(.*)Working(.*)Date(.*)"))
        {
             //  System.out.println("Similar methods found");
                addViolation(data,node);
        }
        return data;
        
    }

 

}