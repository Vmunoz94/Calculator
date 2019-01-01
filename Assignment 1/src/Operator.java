
import java.util.HashMap;

public abstract class Operator {
  // The Operator class should contain an instance of a HashMap
  // This map will use keys as the tokens we're interested in,
  // and values will be instances of the Operators.

  // Example:
  // Where does this declaration go? What should its access level be?
  // Class or instance variable? Is this the right declaration?
  // HashMap operators = new HashMap();
  // operators.put( "+", new AdditionOperator() );
  // operators.put( "-", new SubtractionOperator() );
    
    protected static final HashMap operators = new HashMap();
    
    static
    {
        operators.put( "#", new PoundOperator() );
        operators.put( "!", new ExclamationOperator() );
        operators.put( "+", new AdditionOperator() );
        operators.put( "-", new SubtractionOperator() );
        operators.put( "*", new MultiplicationOperator() );
        operators.put( "/", new DivisionOperator() );
        operators.put( "^", new ExponentialOperator() );
        operators.put( "(", new ParenthesisOperator() );
        operators.put( ")", new ParenthesisOperator() );
    }
    
    

  public abstract int priority();
  public abstract Operand execute( Operand op1, Operand op2 );

  public static boolean check( String token ) {
    if (operators.containsKey(token))
        return true;
    else
        return false;
  }
}

//All subclasses below inherit from the abstract parent class Operator
//However, all abstract methods must be declared
class PoundOperator extends Operator{

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
    
}



class ExclamationOperator extends Operator{

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
    
}



class AdditionOperator extends Operator{

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        Operand result;
        result = new Operand (op1.getValue() + op2.getValue());
        return result;
    }
    
}



class SubtractionOperator extends Operator{

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        Operand result;
        result = new Operand (op1.getValue() - op2.getValue());
        return result;
    }
    
}



class MultiplicationOperator extends Operator{

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        Operand result;
        result = new Operand (op1.getValue() * op2.getValue());
        return result;
    }
    
}



class DivisionOperator extends Operator{

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        Operand result;
        result = new Operand ( op1.getValue() / op2.getValue());
        return result;
    }
    
}



class ExponentialOperator extends Operator{

    @Override
    public int priority() {
        return 4;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        Operand result;
        result = new Operand ((int) Math.pow(op1.getValue(), op2.getValue()));
        return result;

    }
    
}



class ParenthesisOperator extends Operator{

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
    
}
