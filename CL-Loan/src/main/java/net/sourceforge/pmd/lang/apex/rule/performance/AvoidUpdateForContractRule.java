package net.sourceforge.pmd.lang.apex.rule.performance;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.apex.ast.ASTDmlUpdateStatement;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableDeclaration;
import net.sourceforge.pmd.lang.apex.ast.ASTVariableExpression;
import net.sourceforge.pmd.lang.apex.ast.ApexNode;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;
import net.sourceforge.pmd.lang.ast.Node;

public class AvoidUpdateForContractRule extends AbstractApexRule{
	ArrayList<String> myList=new ArrayList<String>();
	
	@Override
    public Object visit(ASTDmlUpdateStatement node, Object data) {
		for(Node exp:node.children()) {
		if(myList.contains(exp.getImage())) {
			addViolation(data, node);
		}
		}
        return data;
    }
	@Override
    public Object visit(ASTVariableDeclaration node, Object data) {
		if(node.getType().equalsIgnoreCase("Contract")||node.getType().equalsIgnoreCase("List<Contract>")) {
			myList.add(node.getImage());
		}
        return data;
    }
}
