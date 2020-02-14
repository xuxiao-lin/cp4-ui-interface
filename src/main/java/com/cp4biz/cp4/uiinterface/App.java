package com.cp4biz.cp4.uiinterface;

import com.cp4biz.cp4.uiinterface.calculate.expression.GetDataExpression;
import com.cp4biz.cp4.uiinterface.calculate.expression.OperatorExpression;
import com.cp4biz.cp4.uiinterface.dataflow.DataProcessor;
import com.cp4biz.cp4.uiinterface.dataflow.DataTypeRepo;
import com.cp4biz.cp4.uiinterface.dataflow.DataValue;
import com.cp4biz.cp4.uiinterface.dataflow.Terminal;
import com.cp4biz.cp4.uiinterface.dataflow.expressionProcessor.ExpressionProcessor;
import com.cp4biz.cp4.uiinterface.sample.numadd.AddOperator;
import com.cp4biz.cp4.uiinterface.sample.numadd.ConstNumber;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	DataTypeRepo.init(new DataTypeRepo());
        ExpressionProcessor ep1 = new ExpressionProcessor();
        OperatorExpression ope = new OperatorExpression();
        AddOperator add = new AddOperator();
        ope.setOperator(add);
        Terminal t1 = new Terminal();
        t1.setValue(new ConstNumber(10));
        t1.setKey("val1");
        Terminal t2 = new Terminal();
        t2.setValue(new ConstNumber(20));
        t2.setKey("val2");
        GetDataExpression gd1 = new GetDataExpression();
        gd1.setDataInterface(t1);
        ope.setLeftExpression(gd1);
        GetDataExpression gd2 = new GetDataExpression();
        gd2.setDataInterface(t2);
        ope.setRightExpression(gd2);
        ep1.setExpression(ope);
        ep1.run();
        DataValue dv = ep1.getOutputTerminal(ExpressionProcessor.OutputKey).getValue();
        
    }
}
