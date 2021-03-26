import edu.duke.FileResource;

import java.io.IOException;

public class Part1 {
    public static String findSimpleGene(String dna){
        int startCodon=dna.indexOf("ATG");
        int stopCodon=dna.indexOf("TAA",startCodon+3);
        if(startCodon==-1||stopCodon==-1)
            return "";
        if((startCodon-stopCodon)%3==0)
            return dna.substring(startCodon,stopCodon+3);
        return "";
    }
    public static void testSimpleGene(){
        FileResource fr=new FileResource();
        String str=fr.toString();
        System.out.println(findSimpleGene(str));
        String s1="HSATTUIEIUVRIEDTAAU";
        System.out.println("DNA String is "+s1);
        System.out.println("Gene is "+findSimpleGene(s1));
        String s2="EDEATGIRNUIOEXN";
        System.out.println("DNA String is "+s2);
        System.out.println("Gene is "+findSimpleGene(s2));
        String s3="HUEDNEFRIUBC";
        System.out.println("DNA String is "+s3);
        System.out.println("Gene is "+findSimpleGene(s3));
        String s4="IUATGJRIEHTVDETAAFR";
        System.out.println("DNA String is "+s4);
        System.out.println("Gene is "+findSimpleGene(s4));
        String s5="DUEDDDEATGHEIDGFGTAA";
        System.out.println("DNA String is "+s5);
        System.out.println("Gene is "+findSimpleGene(s5));
    }
    public static void main(String[] args)throws IOException{
        testSimpleGene();
    }
}
