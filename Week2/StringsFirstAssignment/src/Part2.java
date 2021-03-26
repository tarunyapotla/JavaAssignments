import java.io.IOException;
import java.util.Locale;

public class Part2 {
    public static String findSimpleGene(String dna,String startCodon,String stopCodon){
        if(Character.isUpperCase(dna.charAt(0))){
            startCodon=startCodon.toUpperCase();
            stopCodon=stopCodon.toUpperCase();
        }
        else{
            startCodon=startCodon.toLowerCase();
            stopCodon=stopCodon.toLowerCase();
        }
        int startIndex=dna.indexOf(startCodon);
        int stopIndex=dna.indexOf(stopCodon);
        if(startIndex==-1||stopIndex==-1)
            return "";
        if((stopIndex-startIndex)%3==0)
            return dna.substring(startIndex,stopIndex+3);
        return "";
    }
    public static void testSimpleGene(){
        String s1="HSATTUIEIUVRIEDTAAU";
        System.out.println("DNA String is "+s1);
        System.out.println("Gene is "+findSimpleGene(s1,"ATG","TAA"));
        String s2="dusgdatghinrnrun";
        System.out.println("DNA String is "+s2);
        System.out.println("Gene is "+findSimpleGene(s2,"ATG","TAA"));
        String s3="HUEDNEFRIUBC";
        System.out.println("DNA String is "+s3);
        System.out.println("Gene is "+findSimpleGene(s3,"ATG","TAA"));
        String s4="IUATGJRIEHTVDETAAFR";
        System.out.println("DNA String is "+s4);
        System.out.println("Gene is "+findSimpleGene(s4,"ATG","TAA"));
        String s5="DUEDDDEATGHEIDGFGTAA";
        System.out.println("DNA String is "+s5);
        System.out.println("Gene is "+findSimpleGene(s5,"ATG","TAA"));
    }
    public static void main(String[] args)throws IOException {
        testSimpleGene();
    }
}
