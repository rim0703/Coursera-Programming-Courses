import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;


public class csv_test {
    public static void readFood(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            System.out.println(record.get("Name"));
        }
    }
    public static void listExporters(CSVParser parser, String exportOfInterest){
        int count = 0;
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportOfInterest)){
                String country = record.get("Country");
                System.out.println(country);
                count ++;
            }
        }
        System.out.println("Result of counting: "+count);
    }
    public static void whoExportsCoffee(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser,"coffee");
    }
    public static void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
    }
    public static String countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            String country_List = record.get("Country");
            if(country_List.contains(country)){
                return country+": "+record.get("Exports")+": "+record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    public static void listExportsTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String Exports = record.get("Exports");
            if(Exports.contains(exportItem1)&&Exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    public static void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String values = record.get("Value (dollars)");
            //values = values.replaceAll(",","");
            //long val = Long.parseLong(values.substring(1));
            //amount = amount.replaceAll(",","");
            //long val_amount = Long.parseLong(amount.substring(1));
            if(values.length()>amount.length()){
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
            }
        }
    }


    public static CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for(CSVRecord currentRow : parser){
            largestSoFar = getLargestOfTwo(currentRow,largestSoFar);
        }
        return largestSoFar;
    }
    public static void testHottestInDay(){
        FileResource fr = new FileResource("src\\nc_weather\\2012\\weather-2012-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temperature was "+ largest.get("TemperatureF") +" at "+largest.get("TimeEST"));
    }
    public static CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = hottestHourInFile(fr.getCSVParser());
            largestSoFar=getLargestOfTwo(current, largestSoFar);
        }
        return largestSoFar;
    }
    public static CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar){
        if(largestSoFar==null){
            largestSoFar=currentRow;
        }
        else{
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if(currentTemp>largestTemp){
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    public static void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was "+largest.get("TemperatureF")+" at "+largest.get("DateUTC"));
    }

    public static CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow : parser){
            if(smallestSoFar==null){
                smallestSoFar=currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if(currentTemp<smallestTemp){
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }
    public static void testColdestInDay(){
        FileResource fr = new FileResource("src\\nc_weather\\2014\\weather-2014-05-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was "+ smallest.get("TemperatureF") +" at "+smallest.get("TimeEDT"));
    }
    public static String fileWithColdestTemperature(){
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        String fileName = "";
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if(coldestSoFar==null){
                coldestSoFar=currentRow;
                fileName = f.getName();
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp!=-9999&&currentTemp<smallestTemp){
                    coldestSoFar = currentRow;
                    fileName = f.getName();
                }
            }
        }
        return fileName;
    }
    public static void testFileWithColdestTemperature(){
        String fileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file "+fileName);
        FileResource fr = new FileResource("src\\nc_weather\\2013\\"+fileName);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was "+coldest.get("TemperatureF"));
        parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            System.out.println(record.get("DateUTC")+": "+record.get("TemperatureF"));
        }
    }

    public static CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumiditySoFar = null;

        for(CSVRecord currentRow : parser){
            if(lowestHumiditySoFar==null){
                lowestHumiditySoFar=currentRow;
            }
            else if(!currentRow.get("Humidity").equals("N/A") &&
                    !lowestHumiditySoFar.get("Humidity").equals("N/A")) {
                double currentHumi = Double.parseDouble(currentRow.get("Humidity"));
                double smallestHumi = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
                if(currentHumi<smallestHumi){
                    lowestHumiditySoFar = currentRow;
                }
            }
        }
        return lowestHumiditySoFar;
    }
    public static void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv =  lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    public static CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if(lowestHumiditySoFar==null){
                lowestHumiditySoFar=currentRow;
                //fileName = f.getName();
            }
            else if(!currentRow.get("Humidity").equals("N/A") &&
                    !lowestHumiditySoFar.get("Humidity").equals("N/A")){
                double currentHumi = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumi = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
                if(currentHumi<lowestHumi){
                    lowestHumiditySoFar = currentRow;
                    //fileName = f.getName();
                }
            }
        }
        return lowestHumiditySoFar;
    }
    public static void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+
                lowestHumidity.get("Humidity")+
                " at "+
                lowestHumidity.get("DateUTC"));
    }

    public static double averageTemperatureInFile(CSVParser parser){
        double sum = 0;
        int count = 0;
        for(CSVRecord currentRow : parser){
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            sum+=currentTemp;
            count++;
        }
        return sum/count;
    }
    public static void testAverageTemperatureInFile(){
        FileResource fr = new FileResource("src\\nc_weather\\2013\\weather-2013-08-10.csv");
        System.out.println("Average temperature in file is "+ averageTemperatureInFile(fr.getCSVParser()));

    }
    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        CSVRecord Humidity = null;
        CSVParser par = parser;
        double sum = 0;
        int count = 1;
        for(CSVRecord currentRow : parser){
            if(Humidity==null){
                Humidity=currentRow;
            }
            else if(!currentRow.get("Humidity").equals("N/A") &&
                    !Humidity.get("Humidity").equals("N/A")) {
                int humidity = Integer.parseInt(currentRow.get("Humidity"));
                if(humidity>=value){
                    double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                    sum+=currentTemp;
                    count++;
                }
            }
        }
        if(sum/count == 0){
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.print("Average Temp when high Humidity is ");
            return sum/(count-1);
        }
        return sum/count;
    }
    public static void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource("src\\nc_weather\\2013\\weather-2013-09-02.csv");
        System.out.println(averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80));
    }

    public static void main(String[] args){
        /*
        code here to call the methods above
        to solve your problems (quiz, test, etc...)

        BEFORE CALLING METHODS,
        DON'T FORGET TO CHECK THE FILE ADDRESS IN ABOVE METHODS
         */
    }
}
