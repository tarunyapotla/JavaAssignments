import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
public class WeatherParser {
    public static CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldest=null;
        for (CSVRecord rec:parser){
            double currTemp=Double.parseDouble(rec.get("TemperatureF"));
            if(coldest==null&&currTemp!=-9999){
                coldest=rec;
            }
            else{
                double temp=Double.parseDouble(coldest.get("TemperatureF"));
                if(currTemp<temp&&currTemp!=-9999){
                    temp=currTemp;
                    coldest=rec;
                }
            }
        }
        return coldest;
    }
    public static String fileWithColdestTemperature(){
        DirectoryResource dr=new DirectoryResource();
        CSVRecord coldest=null;
        String filename="";
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource();
            CSVParser cp=fr.getCSVParser();
            CSVRecord cr=coldestHourInFile(cp);
            double currTemp=Double.parseDouble(cr.get("TemperatureF"));
            if (coldest!=null){
                double temp=Double.parseDouble(coldest.get("TemperatureF"));
                if(currTemp<temp){
                    coldest=cr;
                    filename=f.getName();
                }
            }
            else {
                coldest=cr;
                filename=f.getName();
            }
        }
        return filename;
    }
    public static CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord rec=null;
        for (CSVRecord record:parser){
            String humidity=record.get("Humidity");
            if(!humidity.equals("N/A")){
                int currHumid=Integer.parseInt(humidity);
                if(rec==null)
                    rec=record;
                else {
                    int humid=Integer.parseInt(rec.get("Humidity"));
                    if(currHumid<humid){
                        rec=record;
                    }
                }
            }
        }
        return rec;
    }
    public static CSVRecord  lowestHumidityInManyFiles(){
        CSVRecord rec=null;
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource();
            CSVParser parser=fr.getCSVParser();
            CSVRecord currRec=lowestHumidityInFile(parser);
            int humidity=Integer.parseInt(currRec.get("Humidity"));
            if(rec==null)
                rec=currRec;
            else {
                int currHumidity=Integer.parseInt(rec.get("Humidity"));
                if(currHumidity>humidity){
                    rec=currRec;
                }
            }
        }
        return rec;
    }
    public static double averageTemperatureInFile(CSVParser parser){
        double avg=0.0;
        int cnt=0;
        double sumofTemp=0;
        for(CSVRecord rec:parser){
            double currTemp=Double.parseDouble(rec.get("TemperatureF"));
            if(currTemp!=-9999) {
                sumofTemp+=currTemp;
                cnt++;
            }
        }
        return sumofTemp/cnt;
    }
    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        double avg=0.0;
        int cnt=0;
        double sumofTemp=0;
        for(CSVRecord rec:parser){
            int humidity=Integer.parseInt(rec.get("Humidity"));
            if(humidity>value){
                double currTemp=Double.parseDouble(rec.get("TemperatureF"));
                if(currTemp!=-9999) {
                    sumofTemp += currTemp;
                    cnt++;
                }
            }
        }
        if(cnt==0)
            return 0.0;
        return sumofTemp/cnt;
    }
    public static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord cr=coldestHourInFile(parser);
        System.out.println("Coldest hour in file is "+cr.get("TemperatureF"));
        System.out.println(fileWithColdestTemperature());
        CSVRecord cr1 = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity in file is " + cr1.get("Humidity") + " " + cr1.get("DateUTC"));

        FileResource fr1 = new FileResource();
        CSVParser parser1 = fr1.getCSVParser();
        CSVRecord cr2 = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity among many files is " + cr2.get("Humidity") + " " + cr2.get("DateUTC"));

        FileResource fr2 = new FileResource();
        CSVParser parser2 = fr2.getCSVParser();
        System.out.println("nAverage temperature in file is "+averageTemperatureInFile(parser2));

        FileResource fr3=new FileResource();
        CSVParser parser3 = fr3.getCSVParser();
        System.out.println("Avg temp with that humidity is "+averageTemperatureWithHighHumidityInFile(parser3,80));
    }
    public static void main(String[] args)throws IOException{
        tester();
    }
}
