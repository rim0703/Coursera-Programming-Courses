import edu.duke.FileResource;

public class WordLengths {
    public static void countWordLengths(FileResource resource, int[] counts){
        String[] str=new String[counts.length];
        String[] res=resource.asString().split(" ");

        for(int i=0;i<res.length;i++){
            if(res[i].contains("\n")){
                res[i]=res[i].substring(0,res[i].length()-1);
            }
            if(res[i].length()>0){
                if(Character.isLetter(res[i].charAt(0))&&
                        Character.isLetter(res[i].charAt(res[i].length()-1))){
                    if(str[res[i].length()]==null){
                        str[res[i].length()]="";
                    }
                    str[res[i].length()]+=res[i]+" ";
                    counts[res[i].length()]++;
                }
                else if(!Character.isLetter(res[i].charAt(0))&&
                        Character.isLetter(res[i].charAt(res[i].length()-1))){
                    if(str[res[i].length()-1]==null){
                        str[res[i].length()-1]="";
                    }
                    str[res[i].length()-1]+=res[i].substring(1)+" ";
                    counts[res[i].length()-1]++;
                }
                else if(Character.isLetter(res[i].charAt(0))&&
                        !Character.isLetter(res[i].charAt(res[i].length()-1))){
                    if(str[res[i].length()-1]==null){
                        str[res[i].length()-1]="";
                    }
                    str[res[i].length()-1]+=res[i].substring(0,res[i].length()-1)+" ";
                    counts[res[i].length()-1]++;
                }
                else if(!Character.isLetter(res[i].charAt(0))&&
                        !Character.isLetter(res[i].charAt(res[i].length()-1))){
                    if(str[res[i].length()-2]==null){
                        str[res[i].length()-2]="";
                    }
                    str[res[i].length()-2]+=res[i].substring(1,res[i].length()-1)+" ";
                    counts[res[i].length()-2]++;
                }

            }


        }

        for(int i=0;i<str.length;i++){
            if(str[i]!=null){
                if(counts[i]>1){
                    System.out.print(counts[i]+" words");
                }else if(counts[i]==1){
                    System.out.print(counts[i]+" word");
                }
                System.out.println(" of length "+i+": "+str[i]);
            }
        }


        indexOfMax(counts);

    }
    public static void testCountWordLengths(){
        FileResource file=new FileResource();
        int[] arr=new int[31];
        countWordLengths(file,arr);
    }
    public static int indexOfMax(int[] values){
        int result=0;
        return result;

    }
    public static void main(String[] args){
        testCountWordLengths();
    }
}
