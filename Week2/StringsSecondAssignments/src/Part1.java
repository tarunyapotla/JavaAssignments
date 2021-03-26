import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;

public class Part1 {
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
    public static void testFindStopCodon(){
        String dna="duduATiueTAAHUhhirfpTGAneyyxxTAG";
        System.out.println(findStopCodon(dna,5,"TGA"));
        System.out.println(findStopCodon(dna,0,"TAA"));
        System.out.println(findStopCodon(dna,9,"TAG"));
    }
    public static String findGene(String dna,int start){
        int atgIndex=dna.indexOf("ATG");
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
    public static void testFindGene(){
        String dna1="GAATGGYTXXETTTAATETAG";
        System.out.println("DNA String "+dna1);
        System.out.println("Gene is "+findGene(dna1,0));
        String dna2="XXATATGTXXETTTAXGETAG";
        System.out.println("DNA String "+dna2);
        System.out.println("Gene is "+findGene(dna2,0));
        String dna3="XAATGGYGYXETTATAATEGTAG";
        System.out.println("DNA String "+dna3);
        System.out.println("Gene is "+findGene(dna3,0));
        String dna4="ATGGYTXXETTTGATETAG";
        System.out.println("DNA String "+dna4);
        System.out.println("Gene is "+findGene(dna4,0));
        String dna5="GATGGYYYXETTTAATETAA";
        System.out.println("DNA String "+dna5);
        System.out.println("Gene is "+findGene(dna5,0));
    }
    public static void printAllGenes(String dna){
        int start=0;
        while (true){
            String gene=findGene(dna,start);
            if(gene.isEmpty())
                break;
            System.out.println(gene);
            start=dna.indexOf(gene,start)+gene.length();
        }
    }
    public static void main(String[] args)throws IOException {
        testFindGene();
        testFindStopCodon();
    }
}

