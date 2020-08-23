import edu.duke.StorageResource;

public class Part3 {
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

    public static int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene,startIndex)+
                    currentGene.length();
            count++;
        }
        return count;
    }


    //3rd String Part1
    public static StorageResource getAllGenes(String dna){
        int startIndex=0;
        StorageResource geneList = new StorageResource();
        while(true){
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene,startIndex)+
                    currentGene.length();
        }
        return geneList;
    }
    public static void testOn(String dna){
        System.out.println("Testing getAllGenes on "+dna);
        StorageResource genes = getAllGenes(dna);
        for(String g: genes.data()){
            System.out.println(g);
        }
    }


    public static void main(String[] args){
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}
