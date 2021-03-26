import java.io.IOException;

public class Part3 {
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
        int atgIndex=dna.indexOf("ATG",start);
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
    public static int countGenes(String dna){
        int start=0;
        int totGenes=0;
        while (true){
            String gene=findGene(dna,start);
            if(gene.isEmpty())
                break;
            totGenes++;
            start=dna.indexOf(gene,start)+gene.length();
        }
        return totGenes;
    }
    public static void testCountGenes(){
        String dna1="ATGERRUWETGAUTIUTGTGA";
        System.out.println("Total genes in dna1 "+countGenes(dna1));
        String dna2="ATGTAAGATGCCCTAGT";
        System.out.println("Total genes in dna2 "+countGenes(dna2));
    }
    public static void main(String[] args)throws IOException {
        testCountGenes();
    }
}

