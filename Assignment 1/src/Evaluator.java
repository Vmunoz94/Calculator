import java.util.*;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;

  private StringTokenizer tokenizer;
  //private static final String DELIMITERS = "+-*^/#! ";
  //changed in order for the tokenizer to accept parenthesis
  //as acceptable tokens
  private static final String DELIMITERS = "+-*^/#!() ";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int eval( String expression ) {
    String token;
    //denotes the end of the expression
    expression += "!";

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

    // initialize operator stack - necessary with operator priority schema
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators

    // TODO Operator is abstract - this will need to be fixed:
    // operatorStack.push( new Operator( "#" ));
    //denotes the beginning of the operatorStack
    operatorStack.push(new PoundOperator());

    // When is it a good time to add the "!" operator?
    //beginning 
    
    while ( this.tokenizer.hasMoreTokens() ) {
      // filter out spaces
      if ( !( token = this.tokenizer.nextToken() ).equals( " " )) {
        // check if token is an operand
        if ( Operand.check( token )) {
          operandStack.push( new Operand( token ));
        } else {
             //If the token is (, 
             //an Operator object is created from the token, and pushed to the operator Stack 
            if (token.contains("(")){
                Operator openParenthesisOperator = (Operator) Operator.operators.get(token);
                operatorStack.push(openParenthesisOperator);
                continue;
            }
            // If the token is ), 
            //then process Operators until the corresponding ( is encountered.  
            //Pop the ( Operator.
            else if (token.contains(")")){
                Operator closeParenthesisOperator = (Operator) Operator.operators.get(token);
                while ( operatorStack.peek().priority() >= closeParenthesisOperator.priority() && operatorStack.peek().priority() != 0) {
                    processingOperator(operatorStack);
                    //pop the ( Operator
                    if (operatorStack.peek().priority() == 0){
                        operatorStack.pop();
                        break;
                        }
                }
                continue;
                
            }
            
            else if ( ! Operator.check( token )) {
            System.out.println( "*****invalid token******" );
            System.exit( 1 );
            }
            
          // TODO Operator is abstract - these two lines will need to be fixed:
          // The Operator class should contain an instance of a HashMap,
          // and values will be instances of the Operators.  See Operator class
          // skeleton for an example.
          //Operator newOperator = new Operator( token );
          Operator newOperator = (Operator) Operator.operators.get(token);
          
          while ( operatorStack.peek().priority() >= newOperator.priority() ) {
            // note that when we eval the expression 1 - 2 we will
            // push the 1 then the 2 and then do the subtraction operation
            // This means that the first number to be popped is the
            // second operand, not the first operand - see the following code
            processingOperator(operatorStack);
          }
          
          operatorStack.push( newOperator );  
        }
      }
    }

    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks (except
    // the init operator on the operator stack); that is, we should keep
    // evaluating the operator stack until it only contains the init operator;
    // Suggestion: create a method that takes an operator as argument and
    // then executes the while loop; also, move the stacks out of the main
    // method
    while(operandStack.size()>1){
        processingOperator(operatorStack);
    }
    return operandStack.pop().getValue(); 
  }
  
//* Pop the operand Stack twice (for each operand - note the order!!) 
//* Pop the operator Stack 
//* Execute the Operator with the two Operands 
//* Push the result onto the operand Stack    
private void processingOperator(Stack operator){
    Operator oldOpr = (Operator) operatorStack.pop();
    Operand op2 = operandStack.pop();
    Operand op1 = operandStack.pop();
    operandStack.push( oldOpr.execute( op1, op2 ));
    }
}
