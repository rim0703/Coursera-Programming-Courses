import edu.duke.FileResource;

public class CaesarCipher {
    public static String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
                alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }

    public static String encryptTwoKeys(String input, int key1, int key2){
        String result="";
        for(int i=0;i<input.length();i++){
            if((i+1)%2==0){
                result+=encrypt(input.substring(i,i+1),key2);
            }
            else if((i+1)%2==1){
                result+=encrypt(input.substring(i,i+1),key1);
            }
            else{
                result+=input.charAt(i);
            }
        }
        return result;
    }

    public static void main(String[] args){
        //System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!",23));
        //System.out.println(encrypt("First Legion",23));
        //System.out.println(encrypt("First Legion",17));

        //System.out.println(encryptTwoKeys("First Legion",23,17));

        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",8,21));
    }
}
