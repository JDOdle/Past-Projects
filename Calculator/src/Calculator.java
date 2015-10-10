import java.util.*;
public class Calculator {

	public static void main(String [] args) {
		
		double exp = Math.pow(2, 5.34945);
		double max = Double.MAX_VALUE;
		
		System.out.println(exp + ", " + max);
	}
	
	public static double operation(double num1, double num2, char operate) {
		
		switch (operate) {
		
			case '/': return num1/num2;
			case '*': return num1*num2;
			case '-': return num1-num2;
			case '+': return num1+num2;
			default: return 0;
		}
	}
	public static double parseNum1(String str, char operator) {
		
		int operateIndex = str.indexOf(operator);
		int start = 0;
		
		for (int i = operateIndex-1; i >= 0 && str.charAt(i) != '*' &&
				str.charAt(i) != '/' && str.charAt(i) != '+'&&
				str.charAt(i) != '-'; i--) {
			
			start = i;
		}
		
		return Double.parseDouble(str.substring(start, operateIndex));
	}
	
	public static double parseNum2(String str, char operator) {
		
		
		int operateIndex = str.indexOf(operator);
		int end = 0;
		
		for (int i = operateIndex+1; i < str.length() && str.charAt(i) != '*' &&
				str.charAt(i) != '/' && str.charAt(i) != '+'&&
				str.charAt(i) != '-'; i++) {
			
			end = i;
		}
		
		return Double.parseDouble(str.substring(operateIndex+1, end+1));
	}

	public static double parseNumTrig(String str, String trig) {
		
		int operateIndex = str.indexOf(trig);
		int end = 0;
		
		for (int i = operateIndex+3; i < str.length() && str.charAt(i) != '*' &&
				str.charAt(i) != '/' && str.charAt(i) != '+'&&
				str.charAt(i) != '-' && str.charAt(i) != 's' &&
				str.charAt(i) != 'c' && str.charAt(i) != 't'; i++) {
			
			end = i;
		}
		
		return Double.parseDouble(str.substring(operateIndex+3, end+1));
	}
	public static String calculator(String equate) {
		
		String calc;
		int start = -1, end = -1;
		
		double num1 = 0, num2 = 0;
		double answer = 0;
		
		char operation  ='i';
		
		int operatorNum = 0;
		
		//find innermost calculation
		while (equate.contains("(") || equate.contains("*") || 
				equate.contains("/") || (equate.indexOf("+") > 0) || 
				(equate.indexOf("-") > 0) || equate.contains("sin") ||
				equate.contains("cos") || equate.contains("tan")) {
			
			start = 0;
			end = 0;
			operatorNum = 0;
			
			//sets end to be the index at the end of the innermost calculation and start at the beginning
			for (int i = 0; i < equate.length() && equate.charAt(i) != ')'; i++) {
				
				if (equate.charAt(i) == '(')
					start = i;
				
				end = i;
			}
			
			//if their is no innermost operation, start is reset
			if (!equate.contains("("))
				start = -1;
			if (end < 0)
				end = equate.length()-1;
			//System.out.println(equate.charAt(start));
			//System.out.println(equate.charAt(end));
			
			calc = equate.substring(start+1, end+1);
			
			if (start < 0)
				start = 0;
			
			//System.out.println(calc);
			
			//get the numbers to operate on and do the operation
			if (calc.contains("sin")) {
				num1 = parseNumTrig(calc, "sin");
				
				answer = Math.sin(num1);
				operation = 's';
			}
			else if (calc.contains("*")) {
				num1 = parseNum1(calc, '*');
				num2 = parseNum2(calc, '*');
				
				answer = operation(num1, num2, '*');
				operation = '*';
			}
			else if (calc.contains("/")) {
				num1 = parseNum1(calc, '/');
				num2 = parseNum2(calc, '/');
				
				answer = operation(num1, num2, '/');
				operation = '/';
			}
			else if (calc.contains("+")) {
				num1 = parseNum1(calc, '+');
				num2 = parseNum2(calc, '+');
				
				answer = operation(num1, num2, '+');
				operation = '+';
			}
			else if (calc.contains("-")) {
				num1 = parseNum1(calc, '-');
				num2 = parseNum2(calc, '-');
				
				answer = operation(num1, num2, '-');
				operation = '-';
			}
			else {
				answer = Double.parseDouble(calc);
				operation = '(';
			}
			
				//System.out.println(num1);
				//System.out.println(num2);
				//System.out.println(answer);
			
			//sets start to the beginning of the operation
			start = 0;
			end = 0;
			for (int i = calc.indexOf(operation)-1; i >= 0 &&
					calc.charAt(i) != '*' && calc.charAt(i) != '/' &&
					calc.charAt(i) != '+' && calc.charAt(i) != '-' &&
					calc.charAt(i) != 's' && calc.charAt(i) != 'n'; i--) {
				
				start = i;
			}
			
			//sets end to the length of the operation
			for (int i = calc.indexOf(operation)+1; i < calc.length() &&
					calc.charAt(i) != '*' && calc.charAt(i) != '/' &&
					calc.charAt(i) != '+' && calc.charAt(i) != '-'; i++) {
				
				end = i;
			}
		
			start += equate.indexOf(calc);
			//now adds it to the start of the operation plus 1 to
			//get the location of the end of the operation plus 1
			end += equate.indexOf(calc)+1;
			
			//finds the number of operations within calc
			for (int i = 0; i < calc.length(); i++) {
				
				if (calc.charAt(i) == '*' || calc.charAt(i) == '/' || calc.charAt(i) == '+' || calc.charAt(i) == '-')
					operatorNum++;
			}
					
			//if the operation is within () and there is only one operation
			if (equate.contains("(") && operatorNum <= 1)
				equate = equate.replace(equate.substring(start-1, end+1), Double.toString(answer));
			
			//all other cases
			else
				equate = equate.replace(equate.substring(start, end), Double.toString(answer));
		}
		
		return equate;
	}

	
}
