import edu.duke.*;
public class Part2 {
    public double cgRatio(String dna){
        int noOfCG=0;
        for(int i=0;i<dna.length();i++){
            if(dna.charAt(i)=='C'||dna.charAt(i)=='G')
                noOfCG++;
        }
        return (double) noOfCG/dna.length();
    }
    public int countCTG(String dna){
        int cntCTG=0;
        int start=0;
        while (true){
            int idx=dna.indexOf("CTG",start);
            if(idx==-1)
                break;
            cntCTG++;
            start=idx+3;
        }
        return cntCTG;
    }
}


