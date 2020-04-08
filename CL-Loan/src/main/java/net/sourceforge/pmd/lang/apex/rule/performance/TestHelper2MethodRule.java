package net.sourceforge.pmd.lang.apex.rule.performance;


import net.sourceforge.pmd.lang.apex.ast.ASTMethod;
import net.sourceforge.pmd.lang.apex.ast.ASTMethodCallExpression;
import net.sourceforge.pmd.lang.apex.ast.ASTModifierNode;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import net.sourceforge.pmd.lang.ast.Node;
public class TestHelper2MethodRule extends AbstractApexRule{
	public Node n;
	public Node m;
	@Override
    public Object visit(ASTMethodCallExpression node, Object data) {
		if(node.getFullMethodName().contains("TestHelper2"))
		{
		n=node.getParent();
		while(!(n instanceof ASTUserClass)) {
			n = n.getParent();
		}
		ASTModifierNode n1=n.getFirstChildOfType(ASTModifierNode.class);
		if(!n1.isTest())
		{
			addViolation(data, node);
		}
		 m=node.getParent();
		while(!(m instanceof ASTMethod)) {
			m = m.getParent();
		}
		ASTModifierNode n2=m.getFirstChildOfType(ASTModifierNode.class);
		if(!n2.isTest())
		{
			addViolation(data, node);
		}
	}
return data;
}
}
