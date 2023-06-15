public class WhitespaceTest {
    public static void main(String[] args) {  
        // Create three char primitives:ch1, ch2 and ch3  
        char ch1, ch2, ch3  
                    ;  
        // Assign the values to the char primitives.  
        ch1 = '\n';  
        ch2 = 'f';  
        ch3 = '\b';  
              
        //Create three boolean primitives.  
        boolean b1, b2, b3;  
        b1 =  Character.isWhitespace(ch1);  
        b2 =  Character.isWhitespace(ch2);  
        b3 =  Character.isWhitespace(ch3);  
        System.out.println("The first character is a white space character :"+b1);  
        System.out.println("The second character is a white space character :"+b2);  
        System.out.println("The third character is a white space character :"+b3);  
    }  
}
