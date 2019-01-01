public class Operand {
    
    private int value;

  public Operand( String token ) {
      
      value = Integer.parseInt(token);
      
  }

  public Operand( int value ) {

      this.value = value;
      
  }

  public int getValue() {
      
      return value;
      
  }

  public static boolean check( String token ) {
      
      try{        
          Integer.parseInt(token);
          return true;  
          
      } catch (NumberFormatException nfe){
          return false;
      }    
  }
}
