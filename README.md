# Calculator
Calculator emulates a Hewlett-Packard HP-12c Platinum calculator.
 
This calculator allows the user to enter Double values and then the operator. The calculator has an "enter" key to push each value
onto the stack, then pop the stack when an operation key (e.g. "+" or "-") is pressed. For example, "14", "enter", "16", "enter", "+" would result in 14 being pushed on the stack, 16 being pushed onto the stack, then 16 being popped from the stack and a placeholder used to temporarily hold its value, 14 is popped from the stack and 16 is added to it, then the value after addition is 30 and
30 is pushed onto the stack. 14|16|+ = 14 + 16 = 30
Another example, abc*+ = (c*b) + a
