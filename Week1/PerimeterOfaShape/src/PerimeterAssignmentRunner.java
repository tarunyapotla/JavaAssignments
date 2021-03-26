import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public static double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public static int getNumPoints (Shape s) {
        // Put code here
        int numPoints=0;
        for(Point p:s.getPoints()){
            numPoints+=1;
        }
        return numPoints;
    }

    public static double getAverageLength(Shape s) {
        double lengthOfShape=getPerimeter(s);
        double numOfSides=(double) getNumPoints(s);
        double avgLength=lengthOfShape/numOfSides;
        return avgLength;
    }

    public static double getLargestSide(Shape s) {
        Point lastPoint=s.getLastPoint();
        double largestSide=0.0;
        for(Point p:s.getPoints()){
            double dist= lastPoint.distance(p);
            if(dist>largestSide){
                largestSide=dist;
            }
            lastPoint=p;
        }
        return largestSide;
    }

    public static double getLargestX(Shape s) {
        Point lastPoint=s.getLastPoint();
        double largestX=(double) lastPoint.getX();
        for(Point p:s.getPoints()){
            int currentX=p.getX();
            double currX=(double) currentX;
            if(currX>largestX){
                largestX=currX;
            }
        }
        return largestX;
    }

    public static String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr=new DirectoryResource();
        double largestPerim=0.0;
        File largestPerimFile=null;
        for(File f:dr.selectedFiles()){
            FileResource fe=new FileResource(f);
            Shape s=new Shape(fe);
            double per=getPerimeter(s);
            if(per>largestPerim){
                largestPerim=per;
                largestPerimFile=f;
            }
        }
        return largestPerimFile.getName();
        //File temp = null;    // replace this code
      //  return temp.getName();
    }
    public static double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr=new DirectoryResource();
        double largestPerim=0.0;
        // FileResource fr;
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            Shape s=new Shape(fr);
            double per=getPerimeter(s);
            if(per>largestPerim){
                largestPerim=per;
            }
        }
        return largestPerim;
    }

    // This method creates a triangle that you can use to test your other methods
    public static void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public static void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println("File names"+f);
        }
    }
    public static void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("No of points = "+getNumPoints(s));
        System.out.println("perimeter = " + length);
        System.out.println("AverageLength of shape = "+getAverageLength(s));
        System.out.println("Largest side of given shape = "+ getLargestSide(s));
        System.out.println("Largest X point = "+getLargestX(s));
        System.out.println("Largest Perimeter among the files is = "+getLargestPerimeterMultipleFiles());
        System.out.println("File with largest perimeter is = "+getFileWithLargestPerimeter());
    }
    public static void main (String[] args) {
        printFileNames();
        testPerimeter();
    }
}
