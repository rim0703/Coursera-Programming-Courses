public class Part2 {
    public static float cgRatio(String dna){
        int count_c=0;
        int count_g=0;
        for(int i=0;i<dna.length();i++){
            if(dna.charAt(i)=='C'){
                count_c++;
            }
            if(dna.charAt(i)=='G'){
                count_g++;
            }
        }

        return (float)(count_c+count_g)/dna.length();
    }

    public static int countCTG(String dna){
        int count = 0;
        int startIndex = 0;

        while(true){
            if(startIndex+3>dna.length()){
                break;
            }
            if(dna.substring(startIndex,startIndex+3).equals("CTG")){
                count++;
                startIndex+=3;
            }
            else{
                startIndex++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(countCTG("CTGCTGAAACTG"));
    }
}
