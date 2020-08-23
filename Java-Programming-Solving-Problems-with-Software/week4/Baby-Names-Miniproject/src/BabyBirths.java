import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class BabyBirths {
    public static void printName(){
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn<=100){
                System.out.println("Name "+rec.get(0)+
                        " Gender "+rec.get(1)+
                        " Num Born "+rec.get(2));
            }

        }
    }
    public static void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M")){
                totalBoys++;
            }
            else {
                totalGirls++;
            }
        }
        System.out.println("Total births = "+totalBirths);
        System.out.println("the number of unique boys names = "+totalBoys);
        System.out.println("the number of unique girls names = "+totalGirls);
        System.out.println("Total names = "+(totalBirths+totalGirls));
    }
    public static void testTotalBirths(){
        FileResource fr = new FileResource("src\\us_babynames_by_year\\yob1900.csv");
        System.out.println("========1=======");
        totalBirths(fr);
        fr = new FileResource("src\\us_babynames_by_year\\yob1905.csv");
        System.out.println("========2=======");
        totalBirths(fr);
    }

    public static int getRank(int year, String name, String gender){
        int rank = 0;
        FileResource fr = new FileResource("src\\us_babynames_by_year\\yob"+year+".csv");
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                rank++;
                if(rec.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }

    public static String getName(int year, int rank, String gender){
        int count = 0;
        FileResource fr = new FileResource("src\\us_babynames_by_year\\yob"+year+".csv");
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                count++;
                if(count==rank){
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }

    public static void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year,name,gender);
        String newName = getName(newYear,rank,gender);
        System.out.println(name+" born in "+year+" would be "+newName+" if born in "+newYear);
    }

    public static int yearOfHighestRank(String name, String gender){
        int rank = 0;
        int rec_year = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            //FileResource fr = new FileResource();
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            int currRank=getRank(year,name,gender);
            if(rank==0){
                rank=currRank;
                rec_year=year;
            }
            else if(rank>currRank){
                rank=currRank;
                rec_year=year;
            }
        }
        if(rank>0){
            System.out.println("Highest ranking was in "+rec_year+". RANKING: "+rank);
            return rank;
        }
        return -1;
    }

    public static double getAverageRank(String name, String gender){
        double rank_count = 0;
        int file_count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            int currRank=getRank(year,name,gender);
            if(currRank!=-1){
                file_count++;
                rank_count+=currRank;
            }
        }
        if(rank_count!=0){
            System.out.println("Average Ranking: "+(rank_count/file_count));
            return rank_count/file_count;
        }

        return -1.0;
    }

    public static int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource();
        int rank = getRank(year,name,gender);
        int count = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                int newRank = getRank(year,rec.get(0),rec.get(1));
                if(newRank<rank){
                    count+=Integer.parseInt(rec.get(2));
                }
                if(rank<=newRank){
                    return count;
                }
            }
        }

        return count;
    }

    public static void main(String[] args){
        /*
        testTotalBirths();
        System.out.println("==3==  "+getRank(1960,"Emily","F"));
        System.out.println("==4==  "+getRank(1971,"Frank","M"));

        System.out.println("==5== "+getName(1980,350,"F"));
        System.out.println("==6== "+getName(1982,450,"M"));


        System.out.println("==7==");
        whatIsNameInYear("Susan",1972,2014,"F");
        System.out.println("==8==");
        whatIsNameInYear("Owen",1974,2014,"M");

        System.out.println("==9==");
        yearOfHighestRank("Genevieve","F");
        System.out.println("==10==");
        yearOfHighestRank("Mich","F");
        System.out.println("==11==");
        getAverageRank("Susan","F");
        System.out.println("==12==");
        getAverageRank("Robert","M");
        */
        System.out.println("==13==");
        System.out.println(getTotalBirthsRankedHigher(1990,"Emily","F"));
        System.out.println("==14==");
        System.out.println("NUM: "+getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
}
