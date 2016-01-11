package org.liukai.tutorial.service.impl;

import org.liukai.tutorial.service.CalculatorService;
import org.liukai.tutorial.utils.CalHelper;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Collections;
import java.util.Stack;

@Service("/calculatorService")
public class CalculatorServiceImpl implements CalculatorService {

    private Stack<String> postfixStack = new Stack<String>();//逆波兰表达式栈
    private Stack<Character> opStack = new Stack<Character>();//运算符表达式栈
    private int[] priority = new int[]{0, 3, 2, 1, -1, 1, -1, 2};//运算符优先级,ASCII

    /**
     * 计算1，调用js方法计算
     */
    public String cal1(String expression) {
        String result;
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("JavaScript");
        try {
            result = engine.eval(expression).toString();
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            result = "非算术表达式，无法计算……";
        }
        return result;
    }

    /**
     * 计算2，算法实现
     *
     * @return 计算结果
     */
    public String cal2(String expression) {

        Stack<String> resultStack = new Stack<String>();
        postfixStack = transform(expression);
        Collections.reverse(postfixStack);
        String v1, v2, currentValue;
        while (!postfixStack.isEmpty()) {
            currentValue = postfixStack.pop();
            if (!isOperator(currentValue.charAt(0))) {
                resultStack.push(currentValue);
            } else {
                v1 = resultStack.pop();
                v2 = resultStack.pop();
                String tempResult = calByOp(v1, v2, currentValue.charAt(0));
                resultStack.push(tempResult);
            }
        }
        System.out.println(expression + "=" + String.valueOf(resultStack.peek()));
        return String.valueOf(resultStack.pop());
    }

    /**
     * 中缀表达式转换成后缀表达式
     */
    private Stack<String> transform(String expression) {
        opStack.push('\0');//压入\0
        char arr[] = expression.toCharArray();
        int currentIndex = 0;//当前字符位置
        int count = 0;//上次运算符到本次运算符的的长度
        char currentOp, peekOp;//当前操作符和栈顶操作符
        for (int i = 0; i < arr.length; i++) {
            currentOp = arr[i];
            if (isOperator(currentOp)) {
                if (count > 0) {
                    postfixStack.push(new String(arr, currentIndex, count));//取两个字符之间的数字字符串
                }
                peekOp = opStack.peek();
                if (currentOp == ')') {//读到右括号，将栈中左括号之后的操作符都压到postfixStack中
                    while (opStack.peek() != '(') {
                        postfixStack.push(String.valueOf(opStack.pop()));
                    }
                    opStack.pop();
                } else {
                    while (currentIndex != '(' && peekOp != '\0' && compare(currentOp, peekOp)) {
                        postfixStack.push(String.valueOf(opStack.pop()));
                        peekOp = opStack.peek();
                    }
                    opStack.push(currentOp);
                }
                count = 0;
                currentIndex = i + 1;
            } else {
                count++;
            }
        }

        if (count > 1 || (count == 1 && !isOperator(arr[currentIndex]))) {
            postfixStack.push(new String(arr, currentIndex, count));
        }

        while (opStack.peek() != '\0') {
            postfixStack.push(String.valueOf((opStack.pop())));//将剩余的元素压到后缀表达式栈中
        }

        return postfixStack;

    }

    /**
     * 判断是否是运算符
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    /**
     * 比较读到的运算符与栈顶运算符的优先级，
     * cuurentOp < peekOp 返回true
     */
    private boolean compare(Character cuurentOp, Character peekOp) {
        return priority[peekOp - 40] >= priority[cuurentOp - 40];
    }


    /**
     * 根据给定的运算符进行运算
     *
     * @return 计算后得到的字符串
     */
    private String calByOp(String firstValue, String secondValue, char currentOp) {
        String result = "";
        switch (currentOp) {
            case '+':
                result = String.valueOf(CalHelper.add(firstValue, secondValue));
                break;
            case '-':
                result = String.valueOf(CalHelper.sub(firstValue, secondValue));
                break;
            case '*':
                result = String.valueOf(CalHelper.mul(firstValue, secondValue));
                break;
            case '/':
                result = String.valueOf(CalHelper.div(firstValue, secondValue));
                break;
        }
        return result;
    }
}
