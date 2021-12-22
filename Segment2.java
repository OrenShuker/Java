
/**
 * segment 2 is class that provide the coordinate of the center point and the value of length.
 *
 * @author (Oren Shuker)
 * @version (28.11.2020)
 */
public class Segment2
{
    private point _poCenter;
    private double _length;
    private final double DEFAULT_VAL=2.0;
    private final double MIN_VAL=0;
    /**
     * Crate a segment with the center point and length.
     * @param c the coordinate of the center point on the segment.
     * @param d the value of the length of the segment.
     */
    public Segment2 (point c,double d){
        _poCenter=new point (c);
        _length=d;
    }
    /**
     * Crate a segment.
     * @param x the x coordinate of the center point.
     * @param y the y coordinate of the center point.
     * @param d the value of the length of the segment.
     */
    public Segment2 (double x,double y,double d){
        _poCenter=new point (x,y);
        _length=d;
    }
    /**
     * Copy construct for segment
     * Construct a segment with the same coordinates as other segment
     * @param other the point object  which to construct the new segment.
     */
    public Segment2 (Segment2 other){
        _poCenter=other._poCenter;
        _length=other._length;
    }  
    /**
     * return the coordinate of the center point.
     * @return the coordinate of the center point.
     */
    public point getPoCenter (){
        return _poCenter;
    }
    /**
     * return the value of the length of a segment.
     * @return the value of the length of a segment.
     */
    public double getLength(){
        return _length;
    }
    /**
     * return a string representation of this segment.
     * @return a string representation of this segment.
     */
    public String toString(){
        return _length+"---"+_poCenter;
    }
    /**
     * Check if this segment equals other segment.
     * @param other segment to be compared with this segment.
     * @return true if this segment is equals other segment.
     */
    public boolean equals (Segment2 other){
        if (_poCenter==other._poCenter && _length==other._length){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Check if this segment above other segment.
     * @param other segment to be compared with this segment.
     * @return true if this segment is above other segment.
     */
    public boolean isAbove (Segment2 other){
        if (_poCenter.getX()>other._poCenter.getX()){
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
    public boolean isUnder (Segment2 other){
        return other.isAbove(this);
    }
    /**
     * Check if this segment to the left other segment.
     * @param other segment to be compared with this segment.
     * @return true if this segment is to the left other segment.
     */
    public boolean isLeft (Segment2 other){
        if (_poCenter.getX()-(_length/(double)2)>other._poCenter.getX()+(other._length/(double)2)){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Check if this segment to the right other segment.
     * @param other segment to be compared with this segment.
     * @return true if this segment is to the right other segment.
     */
    public boolean isRight (Segment2 other){
        return other.isLeft(this);
    }
    /**
     * Move the segment on the x axis.
     * @param delta the value that added to the recent x value 
     */
    public void moveVertical (double delta){
        _poCenter.setY(_poCenter.getY()+delta);
    }
    /**
     * Move the segment on the y axis.
     * @param delta the value that added to the recent y value 
     */
    public void changeSize (double delta){
        _poCenter.setX(_poCenter.getX()+delta);
    }
    /**
     * Check if other point is on this segment.
     * @param other point to be compared with this segment.
     * @return true if this point is on the segment.
     */
    public boolean pointOnSegment (point p){
        if (p.getX()>_poCenter.getX()-(_length/DEFAULT_VAL) && p.getX()<_poCenter.getX()+(_length/DEFAULT_VAL)){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Check if this segment is bigger other segment.
     * @param other segment to be compared with this segment.
     * @return true if this segment is bigger other segment.
     */
     public boolean isBigger (Segment2 other){
        if (_length>other._length){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Check if there is a overlap between two segment.
     * @param other segment to be compared with this segment.
     */
    public double overlap(Segment2 other){
        if (_poCenter.getX()+(_length/DEFAULT_VAL)>other._poCenter.getX()-(_length/DEFAULT_VAL)){
            return (_poCenter.getX()+(_length/DEFAULT_VAL))-(other._poCenter.getX()-(_length/DEFAULT_VAL));
        }
        if (_poCenter.getX()-(_length/DEFAULT_VAL)<other._poCenter.getX()+(_length/DEFAULT_VAL)){
            return (other._poCenter.getX()+(other._length/DEFAULT_VAL))-(_poCenter.getX()-(_length/DEFAULT_VAL));
        }
        else{
            return MIN_VAL;
        }
    }
    /**
     * Calculate the value of a trapeze.
     * @param other segment to crate the trapeze.
     */
    public double trapezePerimeter (Segment2 other){
        return (getLength()+other.getLength())*(_poCenter.getY()-other._poCenter.getY())/DEFAULT_VAL;
    }
}