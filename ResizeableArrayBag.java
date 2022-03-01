import java.util.Arrays;

/**
 * A class of bags whos entries are stored in  a resizeable array
 * 
 */


public class ResizeableArrayBag<T> implements BagInterface<T> {

    private T[] bag;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private static final int MAX_CAPACITY = 10000;

    public ResizeableArrayBag() {

    }

    //actual constructor. creates a bag with given size capacity
    //@param size     the integer capacity desired
    public ResizeableArrayBag(int size) {
        if (size <= MAX_CAPACITY) {
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[])new Object[size]; //unchecked cast
            bag = tempBag;
            integrityOK = true;
        }
        else
            throw new IllegalStateException("Attempt to create a bag whose"
            + " capacity exceeds allowed maximum");
    }

    
    /** 
     * @param size the initial size of the bag
     */
    private void checkCapacity(int size) {
        if (size > MAX_CAPACITY) {
            throw new IllegalStateException(("Attempt to create a bag whose" 
            + "capacity exceeds allowed maximum of " + MAX_CAPACITY));

        }
    }

    
    /** 
     * @return boolean
     */
    public boolean isArrayFull() {
        return numberOfEntries == bag.length;
    }


    /** Adds a new entry to this bag
     * @param newEntry   The object to be added as a new entry
     * @return           True
     */
    public boolean add(T newEntry) {
        checkIntegrity();

        if(isArrayFull()) {
            doubleCapacity();
        }
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;

        return true;
    }

    private void doubleCapacity() {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    }

    private void checkIntegrity() {
        if(!integrityOK) {
            throw new SecurityException("Resizable Array Bag object is corrupt");
        }
    }

    
    /** 
     * @return T[] an array that holds the entries in the bag
     */
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            result[i] = bag[i];
        }
        return result;
    }

    
    /** 
     * @param newBag the bag to be unioned with the host bag
     * @return BagInterface<T> a bag containing all the elements of newBag and bag
     */
    //@param   a bag
    //@return a bag with the inputted bag and bag
    public BagInterface<T> union(BagInterface<T> newBag) {
        checkIntegrity();
        
        //creating the output bag that is the combined size of bag and newBag
        ResizeableArrayBag<T> outputBag = new ResizeableArrayBag<T>(newBag.getCurrentSize() + bag.length);

        
        
        //copy the contents of the original bag over
        for (int i = 0; i < bag.length; i++) {
            outputBag.add(bag[i]);
        }

        //copy the contents of the newBag over
        for (int i = 0; i < newBag.getCurrentSize(); i++) {
            outputBag.add(newBag.toArray()[i]);
        }

        //output the new bag with combined contents
        return outputBag;
            
    }

    
    /** 
     * @param inBag the bag to be intersected with
     * @return BagInterface<T> the bag that contains the same elements in bag and inBag
     */
    //@param   inBag the bag that is to be intersected with
    //@return   a bag with the intersecting elements of inBag and bag
    public BagInterface<T> intersection(BagInterface<T> inBag) {
        checkIntegrity();

        //this will be used to track the duplicate's indexes
        int[] dupCount = new int[bag.length];

        //go through each item in each bag and count how many are duplicate in inBag
        for (int i = 0; i < bag.length; i++) {
            if (inBag.contains(bag[i])) {
                dupCount[i]++; 
            }
        }

        //loop that adds up the ints inside
        int total = 0;
        for (int i = 0; i < dupCount.length; i++) {
            total += dupCount[i];
        }

        //make new bag that will hold the duplicate items
        BagInterface<T> dupItems = new ResizeableArrayBag<T>(total);

        //will check the indexes of bag and copy the duplicates into a new bag
        int index = 0;

        while(index < bag.length) {
            //if there are any duplicates in the bag
            if (dupCount[index] > 0) {
                //add the corresponding item in the original bag to the new bag
                dupItems.add(bag[index]);
                //since we added it, take away the item (if there's multiple this is how we add them all)
                dupCount[index] -= 1;
            } else {
                //if there's nothing here, move on to the next item in the bag
                index++;
            }
            
        }

        return dupItems;
    } //end union method

    
    /** 
     * @param diffBag the bag that will be the difference of bag (bag - diffBag)
     * @return BagInterface<T> the bag containing the difference of the entries
     */
    //returns a bag that takes the difference of any duplicate items in each bag
    //so bag1= a,b,c and bag2= b, b, c, d, bag1.diff(bag2) = a
    //and bag2.diff(bag1) = b, d
    public BagInterface<T> difference(BagInterface<T> diffBag) {

        BagInterface<T> newBag = diffBag;
        T[] temp = newBag.toArray();
        for(int i = 0; i < bag.length; i++) {
            for(int j = 0; j < newBag.getCurrentSize(); j++) {
                if(temp[j].equals(bag[i])) {
                    newBag.remove(bag[i]);
                }
            }
        }

        return newBag;
    }


    
    /** 
     * @return int the size of the bag
     */
    public int getCurrentSize() {
        return numberOfEntries;
    }


    
    /** 
     * @return boolean true if it is empty
     */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }


    
    /** 
     * @return T the empty space that remains
     */
    public T remove() {
        bag[numberOfEntries - 1] = null;
        return null;
    }

    
    /** 
     * @param anEntry the entry trying to find the index of
     * @return int the index of anEntry
     */
    private int getIndexOf(T anEntry) {
        int where = -1;
        boolean found = false;
        int i = 0;

        while(!found && (i < numberOfEntries)) {
            if(anEntry.equals(bag[i])) {
                found = true;
                where = i;
            }
            i++;
        }
        return where;
    }


    
    /** 
     * @param anEntry the entry to be removed
     * @return boolean True if removal was successful
     */
    public boolean remove(T anEntry) {

        checkIntegrity();
        int i = getIndexOf(anEntry);
        T result = removeEntry(i);
        return anEntry.equals(result);
    }

    
    /** 
     * @param givenIndex the index which to remove an entry
     * @return T the entry which is removed
     */
    private T removeEntry(int givenIndex) {
        T result = null;

        if (!isEmpty() && (givenIndex >= 0)) {
            result = bag[givenIndex];
            bag[givenIndex] = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
        return result;
    }


    public void clear() {
        while (!isEmpty()) {
            remove();
        }
        
    }


    
    /** 
     * @param anEntry an entry to find the frequency in the bag
     * @return int the frequency of the entry in the bag
     */
    public int getFrequencyOf(T anEntry) {
        checkIntegrity();
        int freq = 0;
        for(int i = 0; i < bag.length; i++) {
            if(bag[i].equals(anEntry)) {
                freq++;
            }
        }
        return freq;
    }


    
    /** 
     * @param anEntry the item to find if it's in the bag
     * @return boolean True if it's in the bag
     */
    public boolean contains(T anEntry) {
        checkIntegrity();
        return getIndexOf(anEntry) > -1;
    }




}