import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.IOException;

public class Exports {
    public static String countryInfo(CSVParser parser,String country){
        String str="";
        for (CSVRecord rec:parser){
            String currCountry=rec.get(0);
            if(country.equals(currCountry)){
                String exports=rec.get(1);
                String value=rec.get(2);
                str+=country+": "+exports+":"+value;
                return str;
            }
        }
        return "NOT FOUND";
    }
    public static void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        for(CSVRecord rec:parser){
            String exports= rec.get(1);
            if(exports.contains(exportItem1)&&exports.contains(exportItem2)){
                System.out.println(rec.get(0));
            }
        }
    }
    public static int numberOfExporters(CSVParser parser,String exportItem){
        int noofExports=0;
        for(CSVRecord rec:parser){
            String exports=rec.get(1);
            if(exports.contains(exportItem))
                noofExports++;
        }
        return noofExports;
    }
    public static void bigExporters(CSVParser parser,String amount) {
        for (CSVRecord rec : parser) {
            String currAmnt = rec.get(2);
            if (currAmnt.length() > amount.length()) {
                System.out.println(rec.get(0) + " " + currAmnt);
            }
        }
    }
    public static void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser,"South Africa"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"coffee","tea");
        parser = fr.getCSVParser();
        System.out.println("No of exporters of diamond is"+ numberOfExporters(parser,"diamonds"));
        parser = fr.getCSVParser();
        bigExporters(parser,"$1,332,000,000");
    }
    public static void main(String[] args)throws IOException{
        tester();
    }

}

