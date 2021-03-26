/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import com.sun.security.jgss.GSSUtil;
import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;
import java.io.IOException;

public class BabyBirths {
	public static long getRank(int year,String name,String gender){
		long rank=-1;
		FileResource fr=new FileResource("data"+"/"+"yob"+year+".csv");
		for(CSVRecord rec:fr.getCSVParser(false)){
			String currName=rec.get(0);
			String currGen=rec.get(1);
			if(currName.equals(name)&&currGen.equals(currGen)){
				rank=rec.getRecordNumber();
			}
		}
		return rank;
	}
	public static String getName(int year,long rank,String gender){
		String name="NO NAME";
		FileResource fr=new FileResource("data"+"/"+"yob"+year+".csv");
		int currRank=0;
		for(CSVRecord rec:fr.getCSVParser(false)){
			long recNo=rec.getRecordNumber();
			String currgen=rec.get(1);
			//long rank1=(long)rank;
			if(currgen.equals(gender)){
				currRank++;
				if(currRank==rank){
					name=rec.get(0);
					break;
				}
			}
		}
		return name;
	}
    public static void whatIsNameInYear(String name,int year,int newYear,String gender){
		long rank=getRank(year,name,gender);
		String newName=getName(newYear,rank,gender);
		System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newYear+".");
	}
	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource("data/yob2014.csv");
		totalBirths(fr);
	}
	public static int yearOfHighestRank(String name,String gender){
		long rank=Integer.MAX_VALUE;
		int year=Integer.MIN_VALUE;
		DirectoryResource dr=new DirectoryResource();
		for(File f:dr.selectedFiles()){
			int currYear=Integer.parseInt(f.getName().substring(3,7));
			long currRank=getRank(currYear,name,gender);
			if(currRank<rank&&currRank!=-1){
				rank=currRank;
				year=currYear;
			}
		}
		if(year==Integer.MIN_VALUE){
			return -1;
		}
		else
			return year;
	}
	public static double getAverageRank(String name,String gender){
		long tR=0;
		long sum=0;
		DirectoryResource dr=new DirectoryResource();
		for(File f:dr.selectedFiles()){
			int year=Integer.parseInt(f.getName().substring(3,7));
			long rank=getRank(year,name,gender);
			if(rank!=-1) {
				sum += rank;
				tR++;
			}
		}
		if(sum>0)
			return sum/tR;
		else
			return -1.0;
	}
	public static int getTotalBirthsRankedHigher(int year,String name,String gender){
		int sum=0;
		FileResource fr=new FileResource("data"+"/"+"yob"+year+".csv");
		long rank=getRank(year,name,gender);
		long currRank=0;
		for(CSVRecord rec:fr.getCSVParser(false)){
			String currName=rec.get(0);
			String currGen= rec.get(1);
			if(currGen.equals(gender)){
				currRank++;
				if(rank>currRank){
					sum+=Integer.parseInt(rec.get(2));
				}
				else
					break;
			}
		}
		return sum;
	}
	public static void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						" Gender " + rec.get(1) +
						" Num Born " + rec.get(2));
			}
		}
	}
	public static void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int girlCount=0;
		int boyCount=0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				girlCount++;
			}
			else {
				totalGirls += numBorn;
				boyCount++;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("total names= "+girlCount+boyCount);
		System.out.println("female girls = " + totalGirls);
		System.out.println("No of girl names = "+girlCount);
		System.out.println("male boys = " + totalBoys);
		System.out.println("No of boys names = "+boyCount);
	}
	public static void tester(){
		FileResource fr=new FileResource();
		totalBirths(fr);
		int year=2012;
		String name="Mason";
		String gender="M";
		System.out.println("Total births ranked higher "+getTotalBirthsRankedHigher(year,name,gender));
		System.out.println("Average rank "+getAverageRank(name,gender));
		System.out.println("Year of highest rank "+yearOfHighestRank(name,gender));
		whatIsNameInYear("Isabella",2012,2014,"F");
	}
	public static void main(String[] args)throws IOException{
		tester();
	}
}
