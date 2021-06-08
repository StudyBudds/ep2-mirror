public interface BodyComparator {

    // A comparison function, which imposes a total ordering on objects of class 'Body'.
    // The ordering imposed by this function needs to be consistent with 'equals' of class
    // 'Body', i.e. c.compare(e1, e2)==0 has the same boolean value as b1.equals(b2).
    int compare(Body b1, Body b2);

}
