
/**
 * Polygon is a class that provide the X and Y coordinate of a polygon.
 *
 * @author (Oren Shuker)
 * @version (12.12.2020)
 */
public class Polygon
{
    private final int MAX_VAL=10;
    private final int MIN_VAL=0;
    private final int NO=-1;
    private Point[] _vertices;
    private int _noOfVertices;
    /**
     *Construct a arry of points with 10 places.
     */
    public Polygon(){
        _vertices=new Point[MAX_VAL];
        _noOfVertices=0;
    }
    /**
     * Add a point to the arry at the first place avilable and will return true.
     * if there is no place will return false.
     * @param x the x coordinate of the point.
     * @param y the t coordinate of the point.
     * @return true if there is place in the arry
     */
    public boolean addVertex (double x,double y){
        if (_noOfVertices==_vertices.length){//if full
            return false;
        }
        _vertices[_noOfVertices]=new Point (x,y);
        _noOfVertices++;
        return true;
    }
    /**
     * Check in the arry for the hight point.
     * @return the highes point.
     */
    public Point highestVertex(){
        Point highestVertex=_vertices[0];
        for (int i=0;i<_noOfVertices;i++){
            if (_vertices[i].isAbove(highestVertex)){
                if (highestVertex.equals(_vertices[i])){
                highestVertex=highestVertex;
            }
                highestVertex=_vertices[i];
            }
        }
        return new Point (highestVertex);
    }
    /**
     * Return a string representation of the point in the arry.
     * @return string representation of the point in the arry.
     */
    public String toString(){
        if (_noOfVertices==0){
            return "The polygon has "+ _noOfVertices+" vertices.";
        }
        String str="The polygon has "+_noOfVertices+" vertices:\n(";
        for (int i=0;i<_noOfVertices-1;i++){
            str+=_vertices[i]+",";
        }
        str+=_vertices[_noOfVertices-1]+")";
        return str;
    }
    /**
     * Calculate the primeter of the polygon.
     * @return the primeter of the polyogon.
     */
    public double calcPerimeter (){
        if (_noOfVertices==0 || _noOfVertices==1){
            return MIN_VAL;
        }
        double calcPerimeter=0;
        for (int i=0;i<_noOfVertices-1;i++){
            calcPerimeter+=_vertices[i].distance(_vertices[i+1]);
            }
        calcPerimeter+=_vertices[_noOfVertices-1].distance(_vertices[0]);
        return calcPerimeter;
    }
    /**
     * Calculate the area of the polyogon.
     * @return the area of the polyogon.
     */
    public double calcArea(){
        if (_noOfVertices<2){
            return MIN_VAL;
        }
        double calcArea=0;
        for (int i=2;i<_noOfVertices;i++){
            calcArea+=triangularCalcArea(_vertices[0],_vertices[i],_vertices[i-1]);
        }
        return calcArea;
    }
    /**
     * Check if this polyogon is bigger then other polyogon.
     * @return true if this polyogon is bigger then other polyogon.
     */
    public boolean isBigger (Polygon other){
        if (this.calcArea()>other.calcArea()){
            return true;
        }
        return false;
    }
    /**
     * Check if a point is one of the vertices of the polgon.
     * @return the place of the verices in the arry.
     */
    public int findVertex (Point other){
        int count=0;
        for (int i=0; i<_noOfVertices;i++){
            if (_vertices[i].equals(other)){
                return count;
            }
            count++;
        }
        return NO;
    }
    /**
     * Check if a point is one of the vertices of the polgon.
     * If it is will return the next vertex in the arry
     * If the other point that was given is in the last plase of the arry will return the first vertex in the arry.
     */
    public Point getNextVertex (Point other){
        for (int i=0;i<_noOfVertices;i++){
            if (other.equals(_vertices[_noOfVertices-1])){
                return new Point (_vertices[0]);
            }
            if (other.equals(_vertices[i])){
                return new Point(_vertices[i+1]);
            }
        }
        return null;
    }
    /**
     * Crate a rectangle that block the polgon .
     * @return rectangle that block the pilygon.
     */
    public Polygon getBoundingBox(){
        if (_noOfVertices<=2){
            return null;
        }
        Point _left=_vertices[0];
        Point _right=_vertices[0];
        Point _up=_vertices[0];
        Point _dwon=_vertices[0];
        for (int i=1;i<_noOfVertices;i++){
            if (_vertices[i].isLeft(_left)){
                _left=_vertices[i];
            }
            if (_vertices[i].isRight(_right)){
                _right=_vertices[i];
            }
            if (_vertices[i].isAbove(_up)){
                _up=_vertices[i];
            }
            if (_vertices[i].isUnder(_dwon)){
                _dwon=_vertices[i];
            }
        }
        Polygon p=new Polygon();
        p.addVertex(_left.getX(),_dwon.getY());
        p.addVertex(_right.getX(),_dwon.getY());
        p.addVertex(_right.getX(),_up.getY());
        p.addVertex(_left.getX(),_up.getY());
        return p;
    }
    private double triangularCalcArea (Point a,Point b, Point c){
        double v1=a.distance(b);
        double v2=b.distance(c);
        double v3=c.distance(a);
        double p=(v1+v2+v3)/2.0;
        double triangularCalcArea=Math.sqrt(p*(p-v1)*(p-v2)*(p-v3));
        return triangularCalcArea;
    }
}
