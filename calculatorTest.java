import static org.junit.Assert.*;
import java.util.EmptyStackException;

import org.junit.Test;


public class TestCalculator {


	@Test
	public void testConstructor() {
		Calculator calc = new Calculator();



		//no numbers on numberStack so peek method will throw EmptyStackException
		try {
			calc.peek();
			fail("Expected exception");
		} //end of try
		catch(EmptyStackException ese) {
			//expected
		} //end of catch


		//no numbers on numberStack so pop method will throw EmptyStackException
		try {
			calc.pop();
			fail("Expected exception");
		} //end of try
		catch(EmptyStackException ese) {
			//expected
		} //end of catch

		assertTrue(calc.isEmpty()); //there are no numbers on the numberStack

		assertEquals(0,calc.size()); //there are no numbers on the numberStack
		
    // Test the clear method when the stack is empty and the return type of
		//the clear method is void. 
	  try {
			calc.clear();
	  } //end of try
	  catch(Exception e) {
			//NOT expected
	  } //end of catch
	} //end of testConstructor


	@Test
	public void testEnter() {
		Calculator calc = new Calculator();

		try {
			calc.enter("p"); //invalid input
			fail("Expected exception");
		} //end of try
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch

		calc.enter("-5.00");
		assertEquals(-5.00, calc.peek(), 0.00001); 
		//numberStack from left-to-right with left-most value being the bottom of the stack [-5.0]
		try {
			calc.enter(")"); //invalid input
			fail("Expected exception");
		} //end of try
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch
		//numberStack is unchanged: [-5.0]

		calc.enter("4.00");
		assertEquals(4.00, calc.peek(), 0.00001);
		//numberStack [-5.0, 4.0]

		calc.enter("3");
		assertEquals(3, calc.peek(), 0.00001);
		//numberStack [-5.0, 4.0, 3.0]

		calc.enter("2");
		assertEquals(2, calc.peek(), 0.00001);
		//numberStack [-5.0, 4.0, 3.0, 2.0]

		//-5,4,3,2
		calc.enter("+"); //3 + 2 = 5
		assertEquals(5, calc.peek(), 0.00001);
		//numberStack [-5.0, 4.0, 5.0]

		calc.enter("-"); //4.00 - 5 = -1.00
		assertEquals(-1, calc.peek(), 0.00001);
		//numberStack [-5.0, -1.0]

		calc.enter("*"); // -5.00 * -1.00 = 5.00
		assertEquals(5, calc.peek(), 0.00001);
		//numberStack [5.0]

		calc.enter("-2");
		assertEquals(-2, calc.peek(), 0.00001);
		//numberStack [5.0, -2.0]
		
		calc.enter("0");
		assertEquals(0, calc.peek(), 0.0001);
		//numberStack [-5.0, -2.0, 0.0]

		try {
			calc.enter("/"); //cannot divide by zero
			fail("Expected exception");
		} //end of try
		catch(IllegalArgumentException ese) {
			//expected
		} //end of catch
		calc.pop(); //remove 0 from top of stack
		assertEquals(-2.0, calc.peek(), 0.00001);
		//numberStack [-5.0, -2.0]
		
		
		calc.enter("/"); // 5.0 / -2.0 = -2.5
		assertEquals(-2.5, calc.peek(), 0.00001); //-2.5 is only number on numberStack
		//numberStack [-2.5]

		try {
			calc.enter("+"); //numberStack only has one number on it so operators will throw an IllegalArumentException when passed as an argument
			fail("Expected exception");
		} //end of try
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch

	} //end of testEnter


	@Test
	public void testPeek() {
		Calculator calc = new Calculator();

		//no numbers on numberStack so peek method will throw EmptyStackException
		try {
			calc.peek();
			fail("Expected exception");
		} //end of try
		catch(EmptyStackException ese) {
			//expected
		} //end of catch

		calc.enter("5"); //enter a value of 5 to the stack
		assertEquals(5, calc.peek(),0.000001); //5 is on the top of the stack
		calc.enter("6"); 
		assertEquals(6, calc.peek(),0.000001); //6 is on the top of the stack
		calc.enter("7");
		assertEquals(7, calc.peek(),0.000001); //7 is on the top of the stack
		
		calc.enter("*"); // 6 * 7 = 42
		assertEquals(42, calc.peek(),0.000001); //42 is on the top of the stack
		calc.enter("+"); // 5 + 42 = 47. 47 is now at the top of the stack
		assertEquals(47, calc.peek(),0.000001);
		calc.enter("1"); 
		assertEquals(1, calc.peek(),0.000001); //1 is on the top of the stack
		calc.enter("-"); // 47 - 1 = 46
		assertEquals(46, calc.peek(),0.000001); //46 is on the top of the stack


	} //end of testPeek

	@Test 
	public void testPop() {
		Calculator calc = new Calculator();

		//no numbers on numberStack so pop method will throw EmptyStackException
		try {
			calc.pop();
			fail("Expected exception");
		} //end of try
		catch(EmptyStackException ese) {
			//expected
		} //end of catch
		
		calc.enter("34");
		assertEquals(34, calc.peek(), 0.00001); //34 is on the top of the stack
		//numberStack from left-to-right with left-most value being the bottom of the stack: [34.0]

		calc.enter("58");
		assertEquals(58, calc.peek(), 0.00001); //58 is on top of the stack
		//numberStack [34.0, 58.0]
		
		calc.enter("42");
		assertEquals(42, calc.peek(), 0.00001); //42 is on the top of the stack
		//numberStack [34.0, 58.0, 42.0]
		
		calc.enter("+"); //58 + 42 = 100
		assertEquals(100, calc.peek(), 0.00001); //100 is on the top of the stack
		//numberStack [34.0, 100.0]
		
		calc.enter("*"); // 34 * 100 = 3400
		assertEquals(3400, calc.peek(), 0.00001); //3400 is on the top of the stack
		
		calc.pop(); // numberStack is now empty
		assertTrue(calc.isEmpty());
		//numberStack[empty] aka numberStack[]

		//no numbers on numberStack so pop method will throw EmptyStackException
		try {
			calc.pop();
			fail("Expected exception");
		} //end of try
		catch(EmptyStackException ese) {
			//expected
		} //end of catch

	} //end of testPop

	@Test
	public void testClear() {
		Calculator calc = new Calculator(); //numberStack is empty
		
		assertEquals(0, calc.size()); // numberStack is empty
		
		calc.clear(); //has no effect
		assertEquals(0, calc.size()); //numberStack is still empty
		
		calc.enter("55");
		assertEquals(1, calc.size()); //numberStack has one number on it
		calc.clear();
		assertEquals(0, calc.size()); //numberStack is now empty
		
		calc.enter("1");
		assertEquals(1, calc.size()); //numberStack has one number on it
		
		calc.enter("2");
		assertEquals(2, calc.size()); //numberStack has two numbers on it
		
		calc.enter("3");
		assertEquals(3, calc.size()); //numberStack has three numbers on it
		
		calc.enter("4");
		assertEquals(4, calc.size()); //numberStack has four numbers on it
		
		calc.enter("5");
		assertEquals(5, calc.size()); //numberStack has five numbers on it
		
		calc.clear(); //numberStack is now empty
		assertEquals(0, calc.size());

	} //end of testClear

	@Test
	public void testIsEmpty() {
		Calculator calc = new Calculator(); //numberStack is empty
		assertTrue(calc.isEmpty());
		
		try {
			calc.enter("+"); 
			fail("Expected exception");
		} //end of try
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch
		
		assertTrue(calc.isEmpty()); //numberStack is still empty
		calc.enter("44");
		assertFalse(calc.isEmpty());
		calc.clear(); //numberStack is now empty
		assertTrue(calc.isEmpty());


	} //end of testIsEmpty

	@Test
	public void testSize() {
		Calculator calc = new Calculator(); //numberStack is empty
		assertEquals(0,calc.size());
		
		calc.enter("44");
		assertEquals(1, calc.size());
		
		try {
			calc.enter("+"); 
			fail("Expected exception");
		} //end of try
		catch(IllegalArgumentException iae) {
			//expected
		} //end of catch
		assertEquals(1, calc.size()); //size of numberStack unchanged because of 'bad input' so size of numberStack still equals to 1
		
		calc.enter("34");
		assertEquals(2, calc.size());
		
		calc.clear();
		assertEquals(0, calc.size());
	} //end of testSize

} //end of TestCalculator

