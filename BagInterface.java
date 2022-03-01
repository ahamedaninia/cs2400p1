
//An interface thatdescribes the operations of a bag of objects

public interface BagInterface<T> {
    
    //returns a new bag the union, containing any duplicates.
    //for ex, bag1 = a,a,b and bag2 = a, b, b, c
    //bag1 U bag2 = a, a, a, b, b, b, c (order doesnt matter)
    //should not affect contents of either bag
    public BagInterface<T> union(BagInterface<T> bag);
    //create new Bag. add contents of Bag <T> to it
    //function executes as <BagInterface> everything = bag1.union(bag2)

    //finds where there are copies in each bag and outputs new bag with those copies
    public BagInterface<T> intersection(BagInterface<T> bag);

    //takes away any copies from bag to called bag
    public BagInterface<T> difference(BagInterface<T> bag);

    //adds new entry to bag. returns boolean if this is possible
    public boolean add(T newEntry);

    //newly allocated array of all the entries in the bag
    //if bag is empty, returned array is empty too.
    public T[] toArray();

    //forgot to add these till after making ResizeableArrayBag oops

    //gets current number of entries in bag
    public int getCurrentSize();

    //sees whether bag is empty. returns true if empty, false if not
    public boolean isEmpty();

    //removes one unspecificed entry in bag, if possible. returns removed entry, or null if not possible
    public T remove();

    //removes an occruence of given entry, if possible
    //@param entry to be removed
    //@return true if removal was successful, false if not
    public boolean remove(T anEntry);

    //removes all entries in bag
    public void clear();

    //counts number of times given entry appears in bag
    //@param entry to be counted
    //@return number of times anEntry appears in bag
    public int getFrequencyOf(T anEntry);

    //tests whether this bag contains a given entry
    //@param anEntry the entry to find
    //@return True if the bag contains anEntry, false if not
    public boolean contains(T anEntry);

} //endInterface