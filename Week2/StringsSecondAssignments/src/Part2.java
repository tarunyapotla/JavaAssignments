import java.io.IOException;

public class Part2 {
    public static int howMany(String stringa,String stringb){
        int totOccur=0;
        int start=0;
        while(true){
            int currIdx=stringb.indexOf(stringa,start);
            if(currIdx==-1)
                break;
            totOccur++;
            start=currIdx+stringa.length();
        }
        return totOccur;
    }
    public static void testHowMany(){
        String sa1="GAA";
        String sb1="ATGAACGAATTGAATC";
        System.out.println(sa1+" occurs "+howMany(sa1,sb1)+" times in "+sb1);
        String sa2="AA";
        String sb2="ATAAAA";
        System.out.println(sa2+" occurs "+howMany(sa2,sb2)+" times in "+sb2);
        String sa3="TTY";
        String sb3="ATTYUTYYTTY";
        System.out.println(sa3+" occurs "+howMany(sa3,sb3)+" times in "+sb3);
    }
    public static void main(String[] args)throws IOException {
        testHowMany();
    }
}

