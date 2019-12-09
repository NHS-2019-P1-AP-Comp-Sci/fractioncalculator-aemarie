/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {
	// This private static method is used and invoked only within the FracCalc class
	// for the Scanner under the variable input
	private static Scanner input;

	// TODO: Read the input from the user and call produceAnswer with an equation

	public static void main(String[] args) {
		System.out.println("Insert the function you want to evaluate (type 'quit' to exit program): ");
		String finish = "";
		// Scanner is used to take the the function the user inputs
		input = new Scanner(System.in);
		String total = input.nextLine();
		int quitTest = total.indexOf("quit");
		// A while loop is used to let user quit the program when wanted

		while (quitTest == -1) {
			finish = produceAnswer(total);
			System.out.println("answer: " + finish);

			total = input.nextLine();
			quitTest = total.indexOf("quit");
		}
	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input
		String finish = "";
		int function = input.indexOf(" ");
		String mathoperation = input.substring(function + 1, function + 2);
		String fraction2 = input.substring(function + 3);
		String fraction1 = input.substring(0, function);
		int wholeNum1 = findingWholeNum(fraction1);
		int num1 = findingNum(fraction1, wholeNum1);
		int denom1 = findingDenom(fraction1);
		int wholeNum2 = findingWholeNum(fraction2);
		int num2 = findingNum(fraction2, wholeNum2);
		int denom2 = findingDenom(fraction2);

		if (mathoperation.indexOf("+") != -1) {
			finish = add(wholeNum1, num1, denom1, wholeNum2, num2, denom2);
		}

		else if (mathoperation.indexOf("-") != -1) {
			finish = subtract(wholeNum1, num1, denom1, wholeNum2, num2, denom2);
		}

		else if (mathoperation.indexOf("*") != -1) {
			finish = multiply(wholeNum1, num1, denom1, wholeNum2, num2, denom2);
		}

		else {
			finish = divide(wholeNum1, num1, denom1, wholeNum2, num2, denom2);
		}
		return finish;
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need
	public static int findingWholeNum(String input) {
		int findWholeNum = input.indexOf("_");
		int findingFrac = input.indexOf("/");
		String wholeNum = "";

		if (findWholeNum != -1) {
			wholeNum = input.substring(0, findWholeNum);
		} else if (findingFrac == -1) {
			wholeNum = input;
		} else {
			wholeNum = "0";
		}
		int fraction = Integer.parseInt(wholeNum);
		return fraction;
	}

	public static int findingNum(String input, int num) {
		int findingFrac = input.indexOf("/");
		int findWholeNum = input.indexOf("_");
		int neg = input.indexOf("-");
		String numerator = "";

		if (findingFrac != -1) {
			numerator = input.substring(findWholeNum + 1, findingFrac);
		} else {
			numerator = "0";
		}

		int fraction = Integer.parseInt(numerator);

		if (neg != -1 && num != 0) {
			fraction = fraction * -1;
		}
		return fraction;
	}

	public static int findingDenom(String input) {
		int findingFrac = input.indexOf("/");
		String denominator = "";

		if (findingFrac != -1) {
			denominator = input.substring(findingFrac + 1);
		} else {
			denominator = "1";
		}

		int fraction = Integer.parseInt(denominator);

		return fraction;
	}

	public static String format(int num3, int n3, int d3) {
		String fraction = "";
		if (d3 == 1) {
			num3 = n3 + num3;
			n3 = 0;
		}
		if (n3 < 0 && num3 < 0) {
			n3 = n3 * -1;
		}
		if (n3 != 0 && num3 != 0) {
			fraction = num3 + "_" + n3 + "/" + d3;
		} else if (num3 != 0 && n3 == 0) {
			fraction = "" + num3;
		} else if (n3 == 0 && num3 == 0) {
			fraction = "" + 0;
		} else if (num3 == 0 && n3 != 0) {
			fraction = n3 + "/" + d3;
		}
		return fraction;
	}

	// This method is to subtract fractions
	public static String subtract(int num1, int n1, int d1, int num2, int n2, int d2) {
		int num3 = num1 - num2;
		n1 = n1 * d2;
		n2 = n2 * d1;
		int d3 = d1 * d2;
		int n3 = n1 - n2;
		System.out.println("subtract>>");

		int commonfactor = gcd(n3, d3);

		// Converting both terms into simpler
		// terms by dividing them by common factor
		d3 = d3 / commonfactor;
		n3 = n3 / commonfactor;

		String fraction = format(num3, n3, d3);
		return fraction;
	}

	// This method is to add fractions
	public static String add(int num1, int n1, int d1, int num2, int n2, int d2) {
		int num3 = num1 + num2;
		n1 = n1 * d2;
		n2 = n2 * d1;
		int d3 = d1 * d2;
		int n3 = n1 + n2;

		int commonfactor = gcd(n3, d3);

		// Converting both terms into simpler
		// terms by dividing them by common factor
		d3 = d3 / commonfactor;
		n3 = n3 / commonfactor;

		String fraction = format(num3, n3, d3);

		return fraction;
	}

	// This method is to multiply fractions
	public static String multiply(int num1, int n1, int d1, int num2, int n2, int d2) {
		num1 = (num1 * d1) + n1;
		num2 = (num2 * d2) + n2;
		int num3 = num1 * num2;
		int denom3 = d1 * d2;

		int commonfactor = gcd(num3, denom3);

		// Converting both terms into simpler
		// terms by dividing them by common factor
		denom3 = denom3 / commonfactor;
		num3 = num3 / commonfactor;

		int whole = 0;
		if (num3 > denom3) {
			whole = num3 / denom3;
		} else {
			whole = 0;
		}

		int num4 = 0;
		if (num3 > denom3) {
			num4 = num3 % denom3;
		} else {
			num4 = num3;
		}

		int commonfactor1 = gcd(num4, denom3);

		// Converting both terms into simpler
		// terms by dividing them by common factor
		denom3 = denom3 / commonfactor1;
		num4 = num4 / commonfactor1;

		String fraction = format(whole, num4, denom3);
		return fraction;
	}

	// This method is to divide to fractions
	public static String divide(int num1, int n1, int d1, int num2, int n2, int d2) {
		num1 = (num1 * d1) + n1;
		num2 = (num2 * d2) + n2;
		int num3 = num1 * d2;
		int denom3 = num2 * d1;

		int commonfactor = gcd(num3, denom3);

		// Converting both terms into simpler
		// terms by dividing them by common factor
		denom3 = denom3 / commonfactor;
		num3 = num3 / commonfactor;

		int whole = 0;
		if (num3 > denom3) {
			whole = num3 / denom3;
		} else {
			whole = 0;
		}

		int num4 = 0;
		if (num3 > denom3) {
			num4 = num3 % denom3;
		} else {
			num4 = num3;
		}

		int commonfactor1 = gcd(num4, denom3);

		// Converting both terms into simpler
		// terms by dividing them by common factor
		denom3 = denom3 / commonfactor1;
		num4 = num4 / commonfactor1;

		String fraction = format(whole, num4, denom3);
		return fraction;

		// String fraction = format(0, num3, denom3);
		// return fraction;
	}

	static int gcd(int a, int b) {
		if (a == 0)
			return b;
		return gcd(b % a, a);
	}

}
