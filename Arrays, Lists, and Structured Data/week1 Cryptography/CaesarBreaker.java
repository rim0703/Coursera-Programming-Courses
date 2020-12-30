public class CaesarBreaker {
    public static int[] CountLetters(String message){
        String alph="abcdefghijklmnopqrstuvwxyz";
        int[] counts=new int[26];
        for(int i=0;i<message.length();i++){
            char ch=Character.toLowerCase(message.charAt(i));
            int index=alph.indexOf(ch);
            if(index!=-1){
                counts[index]++;
            }
        }
        return counts;
    }

    public static int maxIndex(int[] values){
        int max=0;
        int indexOfMax=0;
        for(int i=0;i<values.length;i++){
            if(values[i]>max){
                max=values[i];
                indexOfMax=i;
            }
        }
        return indexOfMax;
    }

    public static String decryptTwoKeys(String encrypted){
        CaesarCipher cc=new CaesarCipher();
        String msg1=halfOfString(encrypted,0);
        String msg2=halfOfString(encrypted,1);
        StringBuilder decrypt_two=new StringBuilder(encrypted);
        int key1=getKey(msg1);
        int key2=getKey(msg2);

        String encrypt_msg1=cc.encrypt(msg1,(26-key1));
        String encrypt_msg2=cc.encrypt(msg2,(26-key2));

        for(int i=0;i<msg1.length();i++){
            decrypt_two.setCharAt((2*i),encrypt_msg1.charAt(i));
        }
        for(int i=0;i<msg2.length();i++){
            decrypt_two.setCharAt((2*i)+1,encrypt_msg2.charAt(i));
        }
        System.out.println("The first key is "+key1+"\n"+"The second Key is: "+key2);
        System.out.println("The message decrypted with two keys: "+"\n"+decrypt_two.toString());
        return decrypt_two.toString();
    }

    public static String decryptTwoKeys(String encrypted, int key1, int key2){
        CaesarCipher cc=new CaesarCipher();
        String msg1=halfOfString(encrypted,0);
        String msg2=halfOfString(encrypted,1);
        StringBuilder decrypt_two=new StringBuilder(encrypted);

        String encrypt_msg1=cc.encrypt(msg1,(26-key1));
        String encrypt_msg2=cc.encrypt(msg2,(26-key2));

        for(int i=0;i<msg1.length();i++){
            decrypt_two.setCharAt((2*i),encrypt_msg1.charAt(i));
        }
        for(int i=0;i<msg2.length();i++){
            decrypt_two.setCharAt((2*i)+1,encrypt_msg2.charAt(i));
        }
        System.out.println("The first key is "+key1+"\n"+"The second Key is: "+key2);
        System.out.println("The message decrypted with two keys: "+"\n"+decrypt_two.toString());
        return decrypt_two.toString();
    }

    public static String halfOfString(String msg, int start){
        String result=new String();
        for(int i=0;i<msg.length();i=i+2){
            result+=msg.charAt(i);
        }
        return result;
    }

    public static int getKey(String s){
        int[] freq=CountLetters(s);
        int maxIndex=maxIndex(freq);
        int dKey=maxIndex-4;
        if(maxIndex<4){
            dKey=26-(4-maxIndex);
        }
        return dKey;
    }

    public static void main(String[] args){
        //decryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx",2,20);

    }
}
