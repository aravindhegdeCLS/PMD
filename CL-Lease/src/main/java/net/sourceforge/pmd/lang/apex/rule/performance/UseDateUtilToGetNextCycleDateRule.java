package net.sourceforge.pmd.lang.apex.rule.performance;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import java.util.*;

 

public class UseDateUtilToGetNextCycleDateRule extends AbstractApexRule{
    
    
    @Override
    public Object visit(ASTMethodCallExpression node, Object data)
    {
        //If user uses Date class methods to get next cycle date we can use this piece of code to detect that
        
        
        
        if(node.getMethodName().contains("getNextCycleDate"))
        {
            if(!(node.getFullMethodName().contains("DateUtil")))
            {
                //System.out.println("DateUtil not used");
                addViolation(data,node);
            }
        }
        
        else if(node.getMethodName().matches("(.*)Next(.*)Cycle(.*)Date(.*)" ) || node.getMethodName().matches("(.*)Cycle(.*)Date(.*)")){
         //  System.out.println("Similar methods found");
            addViolation(data,node);
    }
   
        return data;
        
    }
    
    
    
    

 

}