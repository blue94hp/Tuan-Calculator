/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Calculator;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author Anh
 */
public class InfixToPostfix {
    
    boolean check=true;

    public int priority(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        } else if (c == 's' || c == 'c' || c == 'q' || c == '^') {
            return 3;
        } else {
            return 0;
        }
    }

    public boolean isOperator(char c) {
        char operator[] = {'+', '-', '*', '/', ')', '(', 's', 'c', 'q', '^'};
        Arrays.sort(operator);
        if (Arrays.binarySearch(operator, c) > -1) {
            return true;
        } else {
            return false;
        }
    }

    public String[] processString(String sMath) {
        String s1 = "", elementMath[] = null;
        InfixToPostfix IFP = new InfixToPostfix();
        char sM = sMath.charAt(0);
        if(sM=='+'||sM=='-'||sM=='*'||sM=='/')
            sMath=0+sMath;
        sMath = sMath.trim();
        sMath = sMath.replaceAll("\\s+", " ");
        for (int i = 0; i < sMath.length(); i++) {
            char c = sMath.charAt(i);
            if (!IFP.isOperator(c)) {
                s1 = s1 + c;
            } else {
                s1 = s1 + " " + c + " ";
            }
        }
        s1 = s1.trim();
        s1 = s1.replaceAll("\\s+", " ");
        elementMath = s1.split(" ");
        return elementMath;
    }

    public String[] postfix(String[] elementMath) {
        InfixToPostfix IFP = new InfixToPostfix();
        String s1 = "", E[];
        Stack<String> S = new Stack<String>();
        for (int i = 0; i < elementMath.length; i++) {
            char c = elementMath[i].charAt(0);

            if (!IFP.isOperator(c)) {
                s1 = s1 + " " + elementMath[i];
            } else {
                if (c == '(') {
                    S.push(elementMath[i]);
                } else {
                    if (c == ')') {
                        char c1;
                        do {
                            c1 = S.peek().charAt(0);
                            if (c1 != '(') {
                                s1 = s1 + " " + S.peek();
                            }
                            S.pop();
                        } while (c1 != '(');
                    } else {
                        while (!S.isEmpty() && IFP.priority(S.peek().charAt(0)) >= IFP.priority(c)) {
                            s1 = s1 + " " + S.peek();
                            S.pop();
                        }
                        S.push(elementMath[i]);
                    }
                }
            }
        }
        while (!S.isEmpty()) {
            s1 = s1 + " " + S.peek();
            S.pop();
        }
        s1 = s1.trim();
        E = s1.split(" ");
        return E;
    }

    public String valueMath(String[] elementMath) {
        double pi = Math.PI;
        InfixToPostfix IFP = new InfixToPostfix();
        Stack<String> S = new Stack<String>();
        for (int i = 0; i < elementMath.length; i++) {
            char c = elementMath[i].charAt(0);
            if (!IFP.isOperator(c)) {
                if (c == 'p') {
                    S.push(Double.toString(pi));
                } else {
                    S.push(elementMath[i]);
                }
            } else {
                if (c != 's' && c != 'c' && c != 'q') {
                    double num = 0;
                    double num1 = Float.parseFloat(S.pop());
                    double num2 = Float.parseFloat(S.pop());
                    switch (c) {
                        case '+':
                            num = num2 + num1;
                            break;
                        case '-':
                            num = num2 - num1;
                            break;
                        case '*':
                            num = num2 * num1;
                            break;
                        case '/':
                            if(num1==0){
                                check=false;
                                break;
                            } else{
                                num = num2 / num1;
                                break;}
                        case '^':
                            num = Math.pow(num2, num1);
                            break;
                        default:
                            break;
                    }
                    S.push(Double.toString(num));
                } else {
                    double num = 0;
                    double num1 = Float.parseFloat(S.pop());
                    switch (c) {
                        case 's':
                            num = Math.round(Math.sin(num1));
                            break;
                        case 'c':
                            num = Math.round(Math.cos(num1));
                            break;
                        case 'q':
                            num = Math.sqrt(num1);
                            break;
                        default:
                            break;
                    }
                    S.push(Double.toString(num));
                }
            }
        }
        if(check==true)
            return S.pop();
        else
            return "Error";
    }
}

