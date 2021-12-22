
/**
 * point is a class that provide the coordinate of x and y useing the Polar system.
 *
 * @author (Oren Shuker)
 * @version (28/11/2020)
 */
public class point
{
    private double _alpha;
    private double _radius;
    private final double DEFAULT_VAL=0;
    private final double MAX_VAL_OF_ALPHA=Math.PI/2;
    /**
     * construct a point on the Polar system.
     * If the parameter x and y are smaller then zero then it should be initialized to zero.
     * If alpha is bigger then PI/2 and smaller then zero alpha shuld be initialized to zero.
     * @param x the x value to calculate the value of alpha and radius.
     * @param y the y value to calculate the value of alpha and radius.
     */
    public point (double x, double y){
        if (x<DEFAULT_VAL){
            x=DEFAULT_VAL;
        }
        if (y<DEFAULT_VAL){
            y=x;
        }
        if (x==DEFAULT_VAL || y==DEFAULT_VAL && x==DEFAULT_VAL){
            _alpha=MAX_VAL_OF_ALPHA;
        }
        else {
            _alpha= calAlpha(x,y);
            _radius=calRadius(x,y);
        }
    }
    /**
     * Copy construct for point on the Polar system.
     * If a negative number is received then the coordinate of the point will be (DEFAULT_VAL,DEFAULT_VAL)
     *  Construct a point with the same coordinates as other point
     * @param other the point object  which to construct the new point.
     */
    public point (point other){
        _alpha=other._alpha;
        _radius=other._radius;
    }
    /**
     * Return the calculate value of x .
     * @return the calculate value of x.
     */
    public double getX(){
        return Math.round((calX(_alpha,_radius))*10000)/(double)10000;
    }
    /**
     * Return the calculate value of y.
     * @return the calculate value of y.
     */
    public double getY(){
        return Math.round((calY(_alpha,_radius))*10000)/(double)10000;
    }
    /**
     * Set the x coordinate the the calculate value of x.
     * If a negative number is received then the calculate x value will not change .
     * @param numX the new value to calculate the new coordintae of x
     */
    public void setX(double numX){
        if (numX>DEFAULT_VAL){
        _alpha=calAlpha(numX,this.getY());
        _radius=numX/Math.cos(_alpha);
        numX=calX(_alpha,_radius);
        }
    }
    /**
     * Set the y coordinate the the calculate value of y
     * If a negative number is received then the calculate y value will not change 
     * @param numY the new value to calculate the new coordintae of y
     */
    public void setY (double numY){
        if (numY>DEFAULT_VAL){
        _alpha=calAlpha(getX(),numY);
        _radius=numY/Math.sin(_alpha);
        numY=calY(_alpha,_radius);
        }
    }
    /**
     * Return a string representation of this point 
     * @return String representation of this point 
     */
    public String toString (){
        return "("+this.getX()+","+this.getY()+")";
    }
    /**
     * Check if this point equals other point.
     * @param other point to be compared with this point.
     * @return true if this point is equals other point.
     */
    public boolean equals (point other){
        if (_alpha==other._alpha && _radius==other._radius){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Check if this point above other point.
     * @param other point to be compared with this point.
     * @return true if this point is above other point.
     */
    public boolean isAbove (point other){
        if (this.getY()>other.getY()){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Check if this point under other point.
     * @param other point to be compared with this point.
     * @return true if this point is under other point.
     */
    public boolean isUnder(point other){
        return other.isAbove(this);
    }
    /**
     * Check if this point to the left other point.
     * @param other point to be compared with this point.
     * @return true if this point is to the left other point.
     */
    public boolean isLeft(point other){
        if (this.getX()>other.getX()){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Check if this point to the right other point.
     * @param other point to be compared with this point.
     * @return true if this point is to the right other point.
     */
    public boolean isRight(point other){
        return other.isLeft(this);
    }
    /**
     * Calculate the distance between this point and other point.
     * @param other the point to calculate the distance from.
     * @return the distance between this and pther points.
     */
    public double distance (point other){
        return Math.sqrt(Math.pow((this.getY()-other.getY()),2)+Math.pow((this.getX()-other.getX()),2));
    }
    /**
     * get to new parameter and calculate the new parameters of the point.
     * if movment move the point outside of quadeant the point will remain at the same place.
     * @param dX the new value that add to calculate the new coordinate of x 
     * @param dY the new value that add to calculate the new coordinate of y
     */
    public void move (double dX,double dY){
        if (dX+getX()>DEFAULT_VAL && dY+getY()>DEFAULT_VAL) {
        _alpha=calAlpha(this.getX()+dX,this.getY()+dY);
        _radius=calRadius(this.getX()+dX,this.getY()+dY);
        }
    } 
    private double calAlpha(double x,double y){
            return Math.atan(y/x);
    }
    private double calRadius(double x,double y){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
    private double calX(double a,double r){
        return Math.cos(_alpha)*_radius;
    }
    private double calY(double a,double y){
        return Math.sin(_alpha)*_radius;
    }
    
}
