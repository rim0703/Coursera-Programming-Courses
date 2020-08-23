public class Part3 {
    public static boolean twoOccurrences(String stringa, String stringb){
        int startIndex = 0;
        int count = 0;
        while(true){
            if(startIndex+stringa.length()>stringb.length()){
                break;
            }
            if(stringb.substring(startIndex,startIndex+stringa.length()).equals(stringa)){
                count ++;
                if(count==2){
                    return true;
                }
            }
            startIndex++;
        }
        return false;
    }

    public static void testing(){
        System.out.println(twoOccurrences("by","A story by Abby Long"));
        System.out.println(twoOccurrences("a","banana"));
        System.out.println(twoOccurrences("atg","ctgtatgta"));

    }
    public static String lastPart(String stringa, String stringb){
        int startIndex = 0;
        while(true){
            if(startIndex+stringa.length()>stringb.length()){
                break;
            }
            if(stringb.substring(startIndex,startIndex+stringa.length()).equals(stringa)){
                return stringb.substring(startIndex,startIndex+stringa.length()+1);
            }
            startIndex++;
        }
        return stringb;
    }
    public static void main (String[] args){
        System.out.println(lastPart("an","banana"));
        System.out.println((lastPart("zoo","forest")));
    }
}
