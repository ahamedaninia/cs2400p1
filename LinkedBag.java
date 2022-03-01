public class LinkedBag<T> implements BagInterface<T> {

//Implement the three methods, union, intersection, and difference, for the class 
//LinkedBag. Note: the class LinkedBag presents an implementation of the ADT bag using 
//a linked chain. It is never filled

    private Node firstNode;
    private int numberOfEntries;

    //default constructor
    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    }


    
    /** 
     * @param bag
     * @return BagInterface<T>
     */
    //combine the two bags into one, duplicates and all
    //@param bag the bag that is being added to the bag that calls
    //@return LinkedBag a bag that contains both the original bag and the param bag
    public BagInterface<T> union(BagInterface<T> bag) {
        BagInterface<T> unionBags = new LinkedBag<>();
        //add the paramter bag to the new bag
        for(int i = 0; i < bag.getCurrentSize(); i++) {
            unionBags.add(bag.toArray()[i]);
         }
         //add the original (host) bag to the new bag
         Node currentNode = firstNode;
         for(int i = 0; i < numberOfEntries; i++) {
            unionBags.add(currentNode.data);
            currentNode = currentNode.getNextNode();
         }

        return unionBags;
    }

    
    /** 
     * @param bag
     * @return BagInterface<T>
     */
    //finds the common entries in each bag and returns a new bag containing them
    //includes duplicates
    //@param bag the bag that is intersecting with host bag
    //@return bag that holds the common entries
    public BagInterface<T> intersection(BagInterface<T> bag) {
        BagInterface<T> intBag = new LinkedBag<>();

        //find the common entries in each bag
        Node currentNode = firstNode;
        T[] temp = bag.toArray();
        for(int i = 0; i < numberOfEntries; i++) {
            if(currentNode.data.equals(temp[i])) {
                intBag.add(currentNode.data);
            }
            currentNode = currentNode.getNextNode();
        }
        return intBag;
    }

    
    /** 
     * @param bag
     * @return BagInterface<T>
     */
    public BagInterface<T> difference(BagInterface<T> bag) {
        BagInterface<T> diffBag = bag;
        
        //first traverse and find the common entries
        Node currentNode = firstNode;
        T[] tempArray = bag.toArray();

        for(int i = 0; i < numberOfEntries; i++) {
            //if the entries dont match up, then proceed to add to bag (adding the different ones)
            if(currentNode.data.equals(tempArray[i])) {
                diffBag.remove(currentNode.data);
            }
            currentNode = currentNode.getNextNode();
        }

        return diffBag;
    }


    
    /** 
     * @param newEntry
     * @return boolean
     */
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;

        firstNode = newNode;
        numberOfEntries++;
        return true;
    }

    
    /** 
     * @return T[]
     */
    //retrieves all entries that are in bag
    //@return a newly allocated array of all the entries in bag
    public T[] toArray() {
        //cast is safe cause new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries]; //unchecked cast

        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNextNode();
        }
        return result;
        
    } //end toArray

    
    /** 
     * @return int
     */
    //gets the number of entries in the bag
    //@return number of entries in the bag
    public int getCurrentSize() {
        return numberOfEntries;
    }

    
    /** 
     * @return boolean
     */
    //see whether this bag is empty
    //@return true if this bag is empty, or false if not
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    
    /** 
     * @return T
     */
    //remove the last entry in this bag
    //@ return a ference to the nod containing the entry
    public T remove() {
        T result = null;
        if(firstNode != null) {
            result = firstNode.getData();
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
        }
        return result;
    }

    
    /** 
     * @param anEntry
     * @return Node
     */
    //locates a given entry within this bag
    //returns a reference to the node containing the
    //entry, if located, or null otherwise
    private Node getReferenceTo(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while(!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData())) {
                found = true;
            } else {
                currentNode = currentNode.getNextNode();
            }
        }
        return currentNode;
    } //end getReferenceTo

    
    /** 
     * @param anEntry
     * @return boolean
     */
    //removes one occruence of a given entry from this bag, if possible
    //@param anEntry entry to be removed
    //@return True if removal was success, false otherwise
    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);

        if(nodeN != null) {
            //replace located entry with entry in  first node
            nodeN.setData(firstNode.getData());
            //remove first node
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
            result = true;
        }
        return result;
    } //end remove w entry



    //removes all entries in the bag
    public void clear() {
        while(!isEmpty()) {
            remove();
        }
        
    }



    
    /** 
     * @param anEntry
     * @return int
     */
    //counts the number of times a given entry appears in bag
    //@param anEntry the entry to be counted
    //@return the number of times the entry appears in bag
    public int getFrequencyOf(T anEntry) {
        int freq = 0;
        int counter = 0;
        Node currentNode = firstNode;

        while((counter < numberOfEntries) && (currentNode != null)) {
            if(anEntry.equals(currentNode.getData())) {
                freq++;
            }
            counter++;
            currentNode = currentNode.getNextNode();
        }
        return freq;
    } //end getFrequencyOf



    
    /** 
     * @param anEntry the entry to locate
     * @return boolean True if the bag contains anEntry, false otherwise
     */
    //tests whether this bag contains an entry
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if(anEntry.equals(currentNode.getData())) {
                found = true;
            } else {
                currentNode = currentNode.getNextNode();
            }
        }
        return found;
    } //end contains

    private class Node {
        private T data; //entry in bag
        private Node next; //link to next node
    
        private Node(T dataPortion) { 
            this(dataPortion, null);
        }
    
        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    
        //getData, setData, getNextNode, setNextNode
    
        private T getData() { return data; }
        
        private void setData(T data) {
            this.data = data;
        }
    
        private Node getNextNode() { return next; }
    
        // private void setNextNode(Node next) {
        //     this.next = next;
        // }
    }

    
}