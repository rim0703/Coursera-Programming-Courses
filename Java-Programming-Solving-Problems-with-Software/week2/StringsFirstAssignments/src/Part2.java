public class Part2 {
    public static String findSimpleGene (String DNA, String startCodon, String stopCodon) {
        int startIndex = 0;
        int stopIndex = 0;
        if(DNA.toLowerCase().equals(DNA)){
            startCodon=startCodon.toLowerCase();
            stopCodon=stopCodon.toLowerCase();
        }
        while (true){
            if(startIndex+3>DNA.length()){
                return " ";
            }
            if (DNA.substring(startIndex, startIndex + 3).equals(startCodon)) {
                stopIndex = startIndex+3;
                break;
            }
            startIndex += 1;
        }
        while (true){
            if(stopIndex+3>DNA.length()){
                return " ";
            }
            if(DNA.substring(stopIndex,stopIndex+3).equals(stopCodon)){
                stopIndex=stopIndex+3;
                break;
            }
            stopIndex += 1;
        }
        if((stopIndex-startIndex)%3!=0){
            return " ";
        }


        return DNA.substring(startIndex,stopIndex);
    }
    public static void testSimpleGene(){
        String DNA_1 = "CGAAAAATAA"; // NO ATG
        String DNA_2 = "CGATGAAAAA"; // NO TAA
        String DNA_3 = "AAAAAAAAAA"; // NO ATG OR TAA
        String DNA_4 = "AAATGCCCTAACTAGATTAAGAAACC"; // correct one
        String DNA_5 = "CCGATGAAACCCGTAA"; //incorrect one
        System.out.println(findSimpleGene(DNA_1,"ATG","TAA"));
        System.out.println(findSimpleGene(DNA_2,"ATG","TAA"));
        System.out.println(findSimpleGene(DNA_3,"ATG","TAA"));
        System.out.println(findSimpleGene(DNA_4,"ATG","TAA"));
        System.out.println(findSimpleGene(DNA_5,"ATG","TAA"));
    }

    public static void main (String[] args){
        testSimpleGene();
    }
}
