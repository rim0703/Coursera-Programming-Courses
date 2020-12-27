import edu.duke.FileResource;

public class CaesarCipher {
    public static String encrypt(String input, int key){
        String alphabet_uppercase="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet_lowercase="abcdefghijklmnopqrstuvwxyz";

        String encrypt_uppercase=alphabet_uppercase.substring(key)+alphabet_uppercase.substring(0,key);
        String encrypt_lowercase=alphabet_lowercase.substring(key)+alphabet_lowercase.substring(0,key);
        //System.out.println(encrypt_uppercase);

        String result="";
        for(int i=0;i<input.length();i++){
            char search=input.charAt(i);
            if(Character.isLowerCase(search)){
                int pos=alphabet_lowercase.indexOf(search);
                result+=encrypt_lowercase.charAt(pos);
            }
            else if(Character.isUpperCase(search)){
                int pos=alphabet_uppercase.indexOf(search);
                result+=encrypt_uppercase.charAt(pos);
            }
            else{
                result+=search;
            }
        }
        return result;

    }
    public static void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String encrypted = encrypt(message,key);
        //System.out.println("Key is "+key+"\n"+encrypted);
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
