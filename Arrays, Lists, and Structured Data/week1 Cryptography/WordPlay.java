
public class WordPlay {
    public static boolean isVowel(char ch){
        if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||
                ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U'){
            return true;
        }
        return false;
    }
    public static String replaceVowels(String phrase, char ch){
        String str_result="";
        for(int i=0;i<phrase.length();i++){
            if(isVowel(phrase.charAt(i))){
                str_result+=ch;
            }
            else{
                str_result+=phrase.charAt(i);
            }
        }
        return str_result;
    }

    public static String emphasize(String phrase, char ch){
        String str_result="";
        char ch_lowercase=Character.toLowerCase(ch);
        char ch_uppercase=Character.toUpperCase(ch);
        for(int i=0;i<phrase.length();i++){
            if(phrase.charAt(i)==ch_uppercase||phrase.charAt(i)==ch_lowercase){
                if((i+1)%2==0){
                    str_result+='+';
                }
                else if((i+1)%2==1){
                    str_result+='*';
                }
            }
            else{
                str_result+=phrase.charAt(i);
            }
        }
        return str_result;
    }
    public static void main(String[] args){
        //testcase for isVowel
        //System.out.println(isVowel('F'));
        //System.out.println(isVowel('a'));

        //testcase for replaceVowels
        //System.out.println(replaceVowels("Hello World",'*'));

        //testcase for emphasize
        System.out.println(emphasize("dna ctgaaactga",'a'));
        System.out.println(emphasize("Mary Bella Abracadabra",'a'));
    }

}
