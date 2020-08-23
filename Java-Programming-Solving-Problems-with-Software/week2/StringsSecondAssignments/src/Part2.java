public class Part2 {
    public static int howMany(String stringa, String stringb){
        int startIndex = 0;
        int count = 0;
        int currIndex = stringb.indexOf(stringa,startIndex);
        while(currIndex!=-1){
            count++;
            startIndex = currIndex+stringa.length();
            currIndex = stringb.indexOf(stringa,startIndex);
        }
        return count;
    }
    public static void test(){
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA","ATAAAA"));
    }
    public static void main(String[] args){
        test();
    }
}
