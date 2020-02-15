package com.cp4biz.cp4.uiinterface;

import java.io.Console;
import java.util.ArrayList;

import com.cp4biz.cp4.uiinterface.calculate.expression.ConstExpression;
import com.cp4biz.cp4.uiinterface.calculate.expression.Expression;
import com.cp4biz.cp4.uiinterface.calculate.expression.GetDataExpression;
import com.cp4biz.cp4.uiinterface.calculate.expression.IConstValue;
import com.cp4biz.cp4.uiinterface.calculate.expression.Operator;
import com.cp4biz.cp4.uiinterface.calculate.expression.OperatorExpression;
import com.cp4biz.cp4.uiinterface.calculate.expression.OperatorRepo;
import com.cp4biz.cp4.uiinterface.calculate.expression.UserFunction;
import com.cp4biz.cp4.uiinterface.calculate.expression.UserFunctionExpression;
import com.cp4biz.cp4.uiinterface.calculate.expression.UserFunctionRepo;
import com.cp4biz.cp4.uiinterface.dataflow.DataFlow;
import com.cp4biz.cp4.uiinterface.dataflow.DataProcessor;
import com.cp4biz.cp4.uiinterface.dataflow.DataType;
import com.cp4biz.cp4.uiinterface.dataflow.DataTypeRepo;
import com.cp4biz.cp4.uiinterface.dataflow.DataValue;
import com.cp4biz.cp4.uiinterface.dataflow.Terminal;
import com.cp4biz.cp4.uiinterface.dataflow.expressionProcessor.ExpressionProcessor;
import com.cp4biz.cp4.uiinterface.sample.numadd.AddOperator;
import com.cp4biz.cp4.uiinterface.sample.numadd.ConstNumber;
import com.cp4biz.cp4.uiinterface.sample.numadd.MultiplyFunc;
import com.cp4biz.cp4.uiinterface.sample.numadd.NumberValueType;
import com.cp4biz.cp4.uiinterface.system.AppLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	AppLoader.ini();
    	ExpressionProcessor ep2 = new ExpressionProcessor();
    	ConstExpression cst2 = new ConstExpression(new ConstNumber(5));
    	ep2.setExpression(cst2);
    	
    	
        ExpressionProcessor ep1 = new ExpressionProcessor();
        OperatorExpression ope = new OperatorExpression();
        DataTypeRepo dataTypeRepo = DataTypeRepo.getInstance();
        DataType numtype = dataTypeRepo.getType(NumberValueType.Key);
        Operator add = OperatorRepo.getInstance().getOp("+", numtype, numtype);
        ope.setOperator(add);
        Terminal t1 = new Terminal();
        t1.setKey("val1");
        Terminal t2 = new Terminal();
        t2.setValue(new ConstNumber(20));
        t2.setKey("val2");
        ArrayList<Terminal> inputTesr = new ArrayList<Terminal>();
        inputTesr.add(t1);
        inputTesr.add(t2);
        ep1.setInputTerminals(inputTesr);
        GetDataExpression gd1 = new GetDataExpression();
        gd1.setDataInterface(t1);
        ope.setLeftExpression(gd1);
        GetDataExpression gd2 = new GetDataExpression();
        gd2.setDataInterface(t2);
        ConstExpression cst1 = new ConstExpression(new ConstNumber(3));
        UserFunction multi = UserFunctionRepo.getInstance().getFunc(MultiplyFunc.Key);
        UserFunctionExpression ufe = new UserFunctionExpression();
        ufe.setFunc(multi);
        ArrayList<Expression> funcinputs = new ArrayList<Expression>();
        funcinputs.add(gd2);
        funcinputs.add(cst1);
        ufe.setInputs(funcinputs);
        ope.setRightExpression(ufe);
        ep1.setExpression(ope);
        
        DataFlow df = new DataFlow();
        df.connect(ep2.getOutputTerminal(ExpressionProcessor.OutputKey), ep1.getInputTerminal("val1"));
        
        ep2.run();
        DataValue dv = ep1.getOutputTerminal(ExpressionProcessor.OutputKey).getValue();
        System.out.println(((IConstValue)dv).toString());
    }
}
