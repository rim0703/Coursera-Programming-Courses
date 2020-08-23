public class Part1 {

    public static int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon,startIndex+3);
        while (currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff%3==0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon,currIndex+1);
            }
        }
        return dna.length();
    }
    public static void testFindStopCodon(){
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna,0,"TAA");
        if(dex!=9) System.out.println("error on 9");
        dex = findStopCodon(dna,9,"TAA");
        if(dex!=21) System.out.println("error on 21");
        dex = findStopCodon(dna,1,"TAA");
        if(dex!=26) System.out.println("error on 26");
        dex=findStopCodon(dna,0,"TAG");
        if(dex!=26) System.out.println("error on 26 TAG");
        System.out.println("Test finished!");
    }

    public static String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex==-1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int temp = Math.min(taaIndex,tagIndex);
        int minIndex = Math.min(temp,tgaIndex);
        if(minIndex==dna.length()){
            return "";
        }

        return dna.substring(startIndex,minIndex+3);
    }


    public static void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene,startIndex)+
                         currentGene.length();
        }
    }
    public static void testOn (String dna){
        System.out.println("Testing printAllGenes on "+dna);
        printAllGenes(dna);
    }
    public static void test(){
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
    public static void main(String[] args){
        //testFindStopCodon();
        test();

    }
}
