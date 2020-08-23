import edu.duke.*;
import edu.duke.Point;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
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

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for(Point p : s.getPoints()){
            if(s.getPoints().iterator().hasNext()==true){
                count++;
            }
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        return (getPerimeter(s)/getNumPoints(s));
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double maxDist = 0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            if(currDist>maxDist){
                maxDist=currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return maxDist;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double max_X = 0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double X = prevPt.getX();
            if(X>max_X){
                max_X=X;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return max_X;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        //printFileNames();
        double max_perim = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape (fr);
            double perim = getPerimeter(s);
            if(perim>max_perim){
                max_perim=perim;
            }
        }
        return max_perim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double max_perim = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape (fr);
            double perim = getPerimeter(s);
            if(perim > max_perim){
                max_perim=perim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        System.out.println("Start!");
        FileResource fr = new FileResource("datatest1.txt");
        Shape s = new Shape(fr);

        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int size = getNumPoints(s);
        System.out.println("Numpoints = " + size);
        double ave_length = getAverageLength(s);
        System.out.println("Ave_length = " +ave_length);
        System.out.println("Largest_side = " +getLargestSide(s));
        System.out.println("Largest X = "+getLargestX(s));
        testPerimeterMultipleFiles();
        testFileWithLargestPerimeter();

    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        printFileNames();
        System.out.println(getLargestPerimeterMultipleFiles());

    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println(getFileWithLargestPerimeter());

    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
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
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
