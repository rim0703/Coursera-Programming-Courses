import edu.duke.FileResource;

public class WordLengths {
    public static void countWordLengths(FileResource Resource, int[] counts) {

        for (String word : Resource.words()){
            int Wordlength = word.length();
            for (int i=0; i<word.length();i++){
                char currChar = word.charAt(i);
                if ((i==0) || (i==word.length()-1)){
                    if (!Character.isLetter(currChar)) Wordlength--;
                }
            }
            counts[Wordlength]++;
            System.out.println(" Words of length "+ Wordlength +" "+ word);
        }

    }


    public static void indexOfMax(int[] values) {

        int max = 0;
        int position = 0;
        for (int i = 0; i <values.length;i++)
        {
            if (values[i] > max)
            {
                max = values[i];
                position = i;
            }
        }

        System.out.println("The most common word is :"+ position);

    }


    static void testCountWordLengths(){
        FileResource Resource = new FileResource();
        int [] counts = new int[31];
        countWordLengths(Resource,counts);
        indexOfMax(counts);
    }


    public static void main(String[] args){
        testCountWordLengths();
    }
}
