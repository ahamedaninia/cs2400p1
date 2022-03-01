import java.util.Arrays;

public class LinkedBagTest {

    
    /** 
     * @param args
     */
    //Write a client program, “LinkedBagTest.java”, which contains a main method to 
    //test the three methods (union, intersection, and difference) you will implement for the 
    //class LinkedBag.
    public static void main(String[] args) {

        BagInterface<Integer> bag1 = new LinkedBag<Integer>();
        bag1.add(1);
        bag1.add(2);
        bag1.add(3);

        BagInterface<Integer> bag2 = new LinkedBag<Integer>();
        bag2.add(2);
        bag2.add(2);
        bag2.add(4);

        //works!
        BagInterface<Integer> unionBag = bag1.union(bag2);
        //nope (only returning bag1)
        BagInterface<Integer> intersectionBag = bag1.intersection(bag2);
        //nope ^ 
        BagInterface<Integer> diffBag1 = bag1.difference(bag2);
        //same but bag 2
        BagInterface<Integer> diffBag2 = bag2.difference(bag1);


        System.out.println("LinkedBag Test");
        System.out.println("We start with Bag 1 and Bag 2: \n" + Arrays.toString(bag1.toArray())
         + "\n" + Arrays.toString(bag2.toArray()));
        System.out.println("First we'll show the union of Bag 1 and Bag 2:\n" + Arrays.toString(unionBag.toArray()));
        System.out.println("Intersection of Bag 1 and Bag 2:\n" + Arrays.toString(intersectionBag.toArray()));
        System.out.println("Difference of Bag 1 - Bag 2:\n" + Arrays.toString(diffBag1.toArray()));
        System.out.println("Difference of Bag 2 - Bag 1:\n" + Arrays.toString(diffBag2.toArray()));


    }
    
}
