import edu.duke.*;

import java.io.IOException;

public class Part4 {
    public static void files(String url){
        URLResource ur=new URLResource(url);
        for (String word: ur.words()){
            int ytIdx=word.toLowerCase().indexOf("youtube.com");
            if(ytIdx>=0){
                int startIndex=word.indexOf("\"");
                int endIndex=word.indexOf("\"",startIndex+1);
                System.out.println("Youtube link "+word.substring(startIndex,endIndex+1));
            }
        }
    }
    public static void tester(){
        files("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
    public static void main(String[] args)throws IOException{
        tester();
    }
}
