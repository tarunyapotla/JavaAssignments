import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.IOException;
import java.util.Locale;

public class Part3 {
    public static void processGenes(StorageResource sr){
        int longGeneCnt=0;
        int cgRatioCnt=0;
        int longGeneSize=Integer.MIN_VALUE;
       System.out.println(sr.data());
        for(String gene:sr.data()){
            System.out.println(gene.length());
            if(gene.length()>60){
                System.out.println(gene+" gene is longer than 60 characters");
                longGeneCnt++;
            }
            double cgRatio=cgRatio(gene);
            if(cgRatio>0.35){
                System.out.println(gene+" ratio is higher than 0.35.");
                cgRatioCnt++;
            }
            int len=gene.length();
            if(len>longGeneSize)
                longGeneSize=len;
        }
        System.out.println("No of genes that are longer than 60 characters is "+longGeneCnt);
        System.out.println("No of genes that have cgratio greater than 0.35 is "+cgRatioCnt);
        System.out.println("Longest gene length is "+longGeneSize);
    }
    public static int findStopCodon(String dna,int startIndex,String stopCodon){
        int len=dna.length();
        int stopIndex=dna.indexOf(stopCodon,startIndex+3);
        while (stopIndex!=-1){
            if((stopIndex-startIndex)%3==0)
                return stopIndex;
            else
                stopIndex=dna.indexOf(stopCodon,stopIndex+1);
        }
        return len;
    }
    public static String findGene(String dna,int start){
        int atgIndex=dna.toUpperCase().indexOf("ATG",start);
        if(atgIndex==-1)
            return "";
        int taaIndex=findStopCodon(dna,atgIndex,"TAA");
        int tgaIndex=findStopCodon(dna,atgIndex,"TGA");
        int tagIndex=findStopCodon(dna,atgIndex,"TAG");
        int minStopIndex=Math.min(taaIndex,Math.min(tgaIndex,tagIndex));
        if(minStopIndex==dna.length())
            return "";
        else
            return dna.substring(atgIndex,minStopIndex+3);
    }
    public static StorageResource getAllGenes(String dna){
        int start=0;
        StorageResource genelist=new StorageResource();
        while (true){
            String gene=findGene(dna,start);
            if(gene.isEmpty()) {
                break;
            }
            genelist.add(gene);
            start=dna.indexOf(gene,start)+gene.length();
        }
        return genelist;
    }
    public static double cgRatio(String dna){
        int noOfCG=0;
        for(int i=0;i<dna.length();i++){
            if(dna.toUpperCase().charAt(i)=='C'||dna.toUpperCase().charAt(i)=='G') {
                noOfCG++;
            }
        }
        return (double) noOfCG/dna.length();
    }
    public static void testProcessGenes(){
        FileResource fr=new FileResource("dna/brca1.fa");
        String dna= fr.asString();
        String dna1=dna.toUpperCase();
        StorageResource sr=getAllGenes(dna1);
        processGenes(sr);
    }
    public static void main(String[] args)throws IOException{
        testProcessGenes();
    }
}
