import edu.duke.*;
import org.apache.commons.csv.*;
public class Exports {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser,"South Africa"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"coffee","tea");
        parser = fr.getCSVParser();
        System.out.println("No of exporters of diamond is"+ numberOfExporters(parser,"diamonds"));
        parser = fr.getCSVParser();
        bigExporters(parser,"$1,547,000,000,000");
    }
    public String countryInfo(CSVParser parser,String country){
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
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        for(CSVRecord rec:parser){
            String exports= rec.get(1);
            if(exports.contains(exportItem1)&&exports.contains(exportItem2)){
                System.out.println(rec.get(0));
            }
        }
    }
    public int numberOfExporters(CSVParser parser,String exportItem){
        int noofExports=0;
        for(CSVRecord rec:parser){
            String exports=rec.get(1);
            if(exports.contains(exportItem))
                noofExports++;
        }
        return noofExports;
    }
    public void bigExporters(CSVParser parser,String amount){
        for (CSVRecord rec:parser){
            String currAmnt=rec.get(2);
            if(currAmnt.length()>amount.length()) {
                System.out.println(rec.get(0)+" "+currAmnt);
            }
        }

}
/home/tharp/IdeaProjects/Parsing Export Data
