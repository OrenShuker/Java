    
    /**
     * Bignumber is a class that crate a list and provid ways to manege the list.
     *
     * @author (oren shuker)
     * @version (1.0)
     */
    public class BigNumber
    {
        private IntNode _head;
        /**
         * crate a empty list 
         * @param head is the first object in the list.
         */
        public BigNumber (){
            _head=null;
        }
        /**
         * crate a list that contain all the numbers separated form the number that provided
         * @param head is the first object in the list.
         * @param p is the cheker that help us move in the list
         */
        public BigNumber (long a){
            long tmp=a%10;
            _head=new IntNode((int)tmp);
            IntNode p=_head;
            a=a/10;
            while (a>10){
                long tmp2=a%10;
                p.setNext(new IntNode ((int)tmp2));
                p=p.getNext();
                a=a/10;
            }
            p.setNext(new IntNode((int)a));
        }
        /**
         * crate a new list identical to the one who activate the program
         */
        public BigNumber (BigNumber other){
            this._head=other._head;
        }
        /**
         * comper between list 
         * @param b4 is a list that will help us to switch the order of the number in this list
         * @param b5 is a list that will help us to switch the order of the number in other list
         * @param answer is the answer that will be send back to the user
         *  @param p is the cheker that help us move in the list
         *   @param o is the cheker that help us move in the list
         *   @return 1 if this is bigger or -1 if other is bigger or 0 if equals
         */
        //O(n)
        public int compareTo (BigNumber other){
            BigNumber b4= new BigNumber ();
            BigNumber b5= new BigNumber ();
            int answer=0;
            IntNode p=_head;
            IntNode o=other._head;
            while (p != null || o != null){//O(n)
               b4.add(p.getValue());
               b5.add(o.getValue());
               p=p.getNext();
               o=o.getNext();
            }
            p=b4._head;
            o=b5._head;
            while (p != null &&  o != null){
                if (p.getValue()>o.getValue()){
                    answer=1;
                    return answer;
                }
                if (p.getValue()<o.getValue()){
                    answer=-1;
                    return answer;
                }
                p=p.getNext();
                o=o.getNext();
            }
            return answer;
        }
        /**
         * adding the number in one list to the other
         *  @param p is the cheker that help us move in the list
         *   @param o is the cheker that help us move in the list
         *   @param save if the adding of 2 numbers are bigger ther 10 save will be the 1.
         *   @param tmp is the rsult of adding the two numbers
         *   @return a new list with the result of adding the 2 numbers
         */
        public BigNumber addBigNumber (BigNumber other){
            BigNumber b1 = new BigNumber();
            IntNode p=_head;
            IntNode o=other._head;
            int save=0;
            int tmp;
            while (p != null && o != null){
                if (save != 0){
                    tmp=p.getValue()+o.getValue()+save;
                }
                else {
                    tmp=p.getValue()+o.getValue();
                }
                if (tmp>9){
                    b1.addLast(tmp%10);
                    save=tmp/10;
                    p=p.getNext();
                    o=o.getNext();
                }
                else {
                    b1.addLast(tmp);
                p=p.getNext();
                o=o.getNext();
                save=0;
                }
            }
            if (o == null && p != null){
                while (p != null){
                    b1.addLast(p.getValue());
                    p=p.getNext();
                }
            }
            if  (p == null && o != null){
                while (o.getNext() != null){
                b1.addLast(o.getValue());
                o=o.getNext();
            }
            }
        return b1;
    }
    
    public BigNumber addLong (long num){
        BigNumber b2 = new BigNumber(num);
        return this.addBigNumber(b2);
        
    }
    
    public BigNumber subtractBigNumber (BigNumber other){
        BigNumber b3 = new BigNumber();
        IntNode p=_head;
        IntNode o=other._head;
        int tmp;
        while (p != null && o != null){
            if (this.compareTo(other) == 1){
                if (p.getValue()<o.getValue()){
                    p.setValue(p.getValue()+10);
                    tmp=p.getValue()-o.getValue();
                    b3.addLast(tmp);
                    p=p.getNext();
                    o=o.getNext();
                    p.setValue(p.getValue()-1);
                }
                else {
                    tmp=p.getValue()-o.getValue();
                    b3.addLast(tmp);
                    p=p.getNext();
                    o=o.getNext();
                }
            }
            if (this.compareTo(other) == -1){
                if (o.getValue()<p.getValue()){
                    o.setValue(o.getValue()+10);
                    tmp=o.getValue()-p.getValue();
                    b3.addLast(tmp);
                    p=p.getNext();
                    o=o.getNext();
                    o.setValue(o.getValue()-1);
                }
                else {
                    tmp=o.getValue()-p.getValue();
                    b3.addLast(tmp);
                    p=p.getNext();
                    o=o.getNext();
                }
            }
            if (this.compareTo(other) == 0){
                b3.add(0);
                return b3;
            }
        }
        return b3;
    }
    
    
    
    public BigNumber multBigNumber (BigNumber other){
        BigNumber b4 = new BigNumber();
        BigNumber b5 = new BigNumber();
        IntNode p=_head;
        IntNode o=other._head;
        int tmp;
        int save=0;
        int count=0;
        while (p != null){
            if (count != 0){
                    for (int i=0;count>i;i++){
                        b4.addLast(0);
                    }
                }
            while (o != null){
                if (save != 0){
                    tmp=(p.getValue()*o.getValue())+save;
                }
                else {
                    tmp=p.getValue()*o.getValue();
                }
                if (tmp>9){
                    b4.addLast(tmp%10);
                    save=tmp/10;
                    o=o.getNext();
                }
                else {
                    b4.addLast(tmp);
                    save=0;
                    o=o.getNext();
                }
            }
            b5=b4.addBigNumber(b5);
            clear(b4);
            p=p.getNext();
            o=other._head;
            count++;
        }
        return b5;
    }
    
    public void add(int data) {
        IntNode tmp = new IntNode(data, _head);
        _head = tmp;
    }
    
     public void addLast(int data) {
        IntNode p = _head;
        if (_head == null) {
            add(data);
            return;
        }
        while(p.getNext() != null) {
            p = p.getNext();
        }
        IntNode tmp = new IntNode(data, null);
        p.setNext(tmp);        
    }
    
    //O(n)
     public String toString() {
        if (_head == null){
            return null;
        }
        String str1 = new String();
        String str2 = new String();
        int count=0;
        IntNode p = _head;
        while (p != null) {//make a string O(n)
            str2 += p;
            count++;
            p = p.getNext();
        }
        
        while (count>0){//swap the string O(n)
            str1+=str2.charAt(count-1);
            count--;
        }
        return str1;
    }
    
    private BigNumber clear (BigNumber a){
        IntNode p=a._head;
        while (p != null){
            p=p.getNext();
        }
        a._head=p;
        return a;
    }
}
