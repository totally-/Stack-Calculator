import java.util.Stack;
import java.util.EmptyStackException;

/**
 * Calculator emulates a Hewlett-Packard HP-12c Platinum calculator.
 * 
 * This calculator allows the user to enter Double values and then the operator. The calculator has an "enter" key to push each value
 * onto the stack, then pop the stack when an operation key (e.g. "+" or "-") is pressed. 
 * For example, 14> enter> 16> enter> + would result in 14 being pushed on the stack, 16 being pushed onto the stack, then 16 being popped from the stack
 * and a placeholder used to temporarily hold its value, 14 is popped from the stack and 16 is added to it, then the value after addition is 30 and
 * 30 is pushed onto the stack. 14|16|+ = 14 + 16 = 30
 * abc*+ = (c*b) + a
 * 
 */
class Calculator implements StackCalculator{

	//instance or class variables
	private Stack<Double> numberStack; //the calculator's number stack 

	/**
	 * Precondition: none
	 * Postcondition: a new calculator with an empty number stack
	 */
	public Calculator() {
		numberStack = new Stack<Double>();
	} //end of constructor


	/**
	 * Precondition: entry is either a Double value or operator +,-,*, or /
	 * Postcondtion: if the entry is a double, the value is pushed onto the calculator's number stack. If an operator, the top two values are popped
	 * from the stack, the operation performed, and the result pushed onto the stack. NOTE: the second operator is popped first, i.e., the right-hand side
	 * operand of the expression is the second operator. For ex., enter("5"), enter("6"), enter("+"): right-hand side operand is 6: 5 + 6 = 11. The result, 11, is 
	 * pushed onto the stack. NOTE: if the user enters a operator when there are less than two operands on the numberStack an IllegalArgumentException is thrown.
	 * If the user enters a / and divide by zero occurs, an IllegalArgumentException is thrown.
	 */
	@Override
	public void enter(String entry) {
		
		if(size() >= 2 && (entry.equals("+") || entry.equals("-") || entry.equals("*") || entry.equals("/"))) {
			double rightHandSide = pop(); //the operand that represents the operand on the right-hand side of the equation
			double leftHandSide = pop(); //the operand that represents the operand on teh left-hand side of the equation
			if(entry.equals("+")) {
				numberStack.push(leftHandSide + rightHandSide);
			} //end of if
			else if(entry.equals("-")) {
				numberStack.push(leftHandSide - rightHandSide);
			} //end of else if
			else if(entry.equals("*")) {
				numberStack.push(leftHandSide * rightHandSide);
			} //end of else if
			else if(entry.equals("/") && rightHandSide !=0) {  
				numberStack.push(leftHandSide / rightHandSide);
			} //end of else if
			else {
				numberStack.push(leftHandSide);  
				numberStack.push(rightHandSide);
				throw new IllegalArgumentException("divide by zero"); //illegal argument exception
			} //end of else
		} //end of if
		else {
			try {
				double operand = Double.parseDouble(entry); //this will fail if the user passed an operator and size()<2 OR if the user passed an entry
				//that was not a double value or was not an operand or operator
				numberStack.push(operand);
			} //end of try
			catch(IllegalArgumentException iae) {
				throw new IllegalArgumentException();
			} //end of catch
		} //end of else

	} //end of enter

	/**
	 * Precondition: numberStack is not empty, otherwise throws EmptyStackException
	 * Postcondition: returns the value at the top of numberStack without removing it
	 */
	@Override
	public double peek() {
		if(numberStack.isEmpty()) {
			throw new EmptyStackException();
		} //end of if
		
		return numberStack.peek();
	} //end of peek

	/**
	 * Precondition: numberStack is not empty, otherwise throws EmptyStackException
	 * Postcondition: removes the value at the top of the numberStack and returns its value
	 */
	@Override
	public double pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		} //end of if
		
		return numberStack.pop();
	} //end of pop

	
	/**
	 * Removes all numbers from the numberStack. 
	 * Precondition: none
	 * Postcondition: numberStack will be empty
	 */
	@Override
	public void clear() {
		numberStack.clear();

	} //end of clear

	
	/**
	 * Precondition: none
	 * Postcondition: return true if the numberStack is empty or false if it is not empty
	 */
	@Override
	public boolean isEmpty() {
		return numberStack.isEmpty();
	} //end of isEmpty

	/**
	 * Returns the number of values in this calculator's numberStack
	 */
	@Override
	public int size() {
		return numberStack.size();
	} //end of size


} //end of Calculator class

