
/**
 * Segment is a class that provid the coordinate of the right point amd left point of a segment.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Segment1
{
    private point _poLeft;
    private point _poRight;
    private final double DEFAULT_VAL=2.0;
    private final double MIN_VAL=0;
    /**
     * constract a segment with two point.
     * If the y coordinate of the right point is not equals to the y coordinate of the left point 
     * then the y coordinate of the right point will be set to the same number as left point
     * @param point left represent the coordinate of the left point
     * @param point right represent the coordinate of the right point
     */
    public Segment1 ( point left,point right){
        if (left.getY()!=right.getY()){
           right.setY(left.getY());
        }
        _poLeft= new point (left);
        _poRight=new point (right);
    }
    /**
     * consteact a segment 
     *  If the y coordinate of the right point is not equals to the y coordinate of the left point 
     * then the y coordinate of the right point will be set to the same number as left point
     * @pharam leftX the x coordinate of the left point.
     * @param leftY the y coordinate of the left point.
     * @param rightX the x coordinate of the right point.
     * @param rightY the y coordinate of the right point.
     */
    public Segment1 (double leftX, double leftY,double rightX, double rightY){
        _poLeft= new point (leftX,leftY);
        if (leftY!=rightY){
            _poRight= new point (rightX,leftY);
        }
        else {
            _poRight= new point (rightX,rightY);
        }
    }
    /**
     * return the coordinate of the left point
     * @return the coordinate of the left point
     */
    public point getPoLeft(){
        return _poLeft;
    }
    /**
     * return coordinate of the right point.
     * @return the coordinate of right point.
     */
    public point getPoRight(){
        return _poRight;
    }
    /**
     * return the value of the length of the segment.
     * @return the length of the segment.
     */
    public double getLength (){
        return _poLeft.distance(_poRight);
    }
    /**
     * return a string representation of this segment.
     * @return  a string representation of this segment.
     */
    public String toString (){
        return _poLeft+"---"+_poRight;
    }
    /**
     * Check if this segment equals other segment.
     * @param other segment to be compared with this segment.
     * @return true if this segment is equals other segment.
     */
    public boolean equals (Segment1 other){
        if (_poLeft==other._poLeft && _poRight==other._poRight){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Check if this segment above other segment.
     * @param other segment to be compared with this segment.
     * @return true if this segment is above other segment.
     */
    public boolean isAbove (Segment1 other){
        if (_poRight.getY()>other._poRight.getY()){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Check if this segment under other segment.
     * @param other segment to be compared with this segment.
     * @return true if this segment is under other segment.
     */
    public boolean isUnder (Segment1 other){
        return other.isAbove(this);
    }
    /**
     * Check if this segment is to the left other segment.
     * @param other segment to be compared with this segment.
     * @return true if this segment is to the left other segment.
     */
    public boolean isLeft (Segment1 other){
        if (_poRight.getX()<other._poLeft.getX()){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Check if this segment is to the right other segment.
     * @param other segment to be compared with this segment.
     * @return true if this segment is to the right other segment.
     */
    public boolean isRight(Segment1 other){
        return other.isLeft(this);
    }
    /**
     * Move the gegment on the y axis
     * @param delta the value that added to the recent y value 
     */
    public void moveHorizontal (double delta){
        _poRight.setX(_poRight.getX()+delta);
        _poLeft.setX(_poLeft.getX()+delta);
    }
    /**
     * Move the gegment on the x axis
     * @param delta the value that added to the recent x value 
     */
    public void moveVertical (double delta) {
        _poRight.setY(_poRight.getY()+delta);
        _poLeft.setY(_poLeft.getY()+delta);
    }
    /**
     * change the size of the segment
     * @param delta the value that need to be added to the curent x value.
     */
    public void changeSize (double delta){
        _poRight.setX(_poRight.getX()+delta);
    }
    /**
     * Check if a point is on the segment.
     * @param p the point that is beeing check.
     * @return true if other p is on the segment.
     */
    public boolean pointOnSegment (point p){
        if (p.getX()>_poLeft.getX() && p.getX()<_poRight.getX()){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Check if this segment is bigger then other segment.
     * @param other segment to be compared with this segment.
     * @return true if this segment is bigger then other segment.
     */
    public boolean isBigger (Segment1 other){
        if (_poLeft.distance(_poRight)>other._poLeft.distance(_poRight)){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Check if there is a overlap between the two segment
     * @param other segment to be compared with this segment.
     */
    public double overlap(Segment1 other){
        if (_poRight.getX()>other._poLeft.getX()){
            return _poRight.getX()-other._poLeft.getX();
        }
        if (_poLeft.getX()<other._poRight.getX()){
            return other._poRight.getX()-_poLeft.getX();
        }
        else{
            return MIN_VAL;
        }
    }
    /**
     * Calculate the value of a trapeze.
     * @param other segment to crate the trapeze.
     */
    public double trapezePerimeter (Segment1 other){
        return ((_poLeft.distance(_poRight))+(other._poLeft.distance(other._poRight)))*(other._poLeft.getY()-_poLeft.getY())/DEFAULT_VAL;
    }
    
    
}
