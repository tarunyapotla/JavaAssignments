import java.io.IOException;

public class Part3 {
    public static boolean twoOccurrences(String stringa,String stringb){
        int fstOcur=stringb.indexOf(stringa);
        //int la=stringa.length();
        if(fstOcur==-1)
            return false;
        else{
            int secOcur=stringb.indexOf(stringa,fstOcur+1);
            if(secOcur!=-1)
                return true;
        }
        return false;
    }
    public static String lastPart(String stringa,String stringb){
        int fstOcur=stringb.indexOf(stringa);
        int lenA=stringa.length();
        int lenB=stringb.length();
        if(fstOcur==-1)
            return stringb;
        else
            return stringb.substring(fstOcur+lenA,lenB);
    }
    public static void testing(){
        String sa1="by";
        String sb1="A Story by Abby Long";
        System.out.println(sa1+" "+sb1+" "+twoOccurrences(sa1,sb1));
        System.out.println("The Part of the String after "+sa1+" in "+sb1+" is "+lastPart(sa1,sb1));
        String sa2="BRU";
        String sb2="ATHOVBRU";
        System.out.println(sa2+" "+sb2+" "+twoOccurrences(sa2,sb2));
        System.out.println("The Part of the String after "+sa2+" in "+sb2+" is "+lastPart(sa2,sb2));
        String sa3="atg";
        String sb3="ctgtatgta";
        System.out.println(sa3+" "+sb3+" "+twoOccurrences(sa3,sb3));
        System.out.println("The Part of the String after "+sa3+" in "+sb3+" is "+lastPart(sa3,sb3));
        String sa4="a";
        String sb4="Banana";
        System.out.println(sa4+" "+sb4+" "+twoOccurrences(sa4,sb4));
        System.out.println("The Part of the String after "+sa4+" in "+sb4+" is "+lastPart(sa4,sb4));
    }
    public static void main(String[] args)throws IOException {
        testing();
    }
}
