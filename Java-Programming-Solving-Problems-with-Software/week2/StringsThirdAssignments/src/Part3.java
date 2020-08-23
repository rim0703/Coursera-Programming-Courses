import edu.duke.FileResource;
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
    public static void processGenes(StorageResource sr){
        //print all the Strings in sr that are longer than 9 characters
        //print the number of Strings in sr that are longer than 9 characters
        //print the Strings in sr whose C-G-ratio is higher than 0.35
        //print the number of strings in sr whose C-G-ratio is higher than 0.35
        int count = 0;
        for(String s : sr.data()){
            if(s.length()>60){
                //System.out.println(s+" longer than 9 characters");
                count++;
            }
        }
        System.out.println("The number of Strings in sr that are longer than 60 characters: "+count);

        count=0;
        for(String s : sr.data()){
            if(cgRatio(s)>0.35){
                //System.out.println(s+" C-G-ratio is higher than 0.35");
                count++;
            }
        }
        System.out.println("The number of strings in sr whose C-G-ratio is higher than 0.35: "+count);


        //print the length of the longest gene in sr
        String longest_gene = "";
        for(String s : sr.data()){
            if(s.length()>longest_gene.length()){
                longest_gene=s;
            }
        }
        System.out.println("The longest gene in sr: "+longest_gene);
        System.out.println("Length: "+longest_gene.length());

    }

    public static void testProcessGenes(){
        FileResource fr = new FileResource("C:\\Users\\user\\IdeaProjects\\StringsThirdAssignments\\src\\test.fa.txt");
        String dna = fr.asString().toUpperCase();
        StorageResource sr = getAllGenes(dna);
        System.out.println("Number of genes: "+countGenes(dna));
        processGenes(sr);
        System.out.println("Number of CTGs: "+countCTG(dna));

    }
    public static void main(String[] args){
        testProcessGenes();
    }

}
