import java.util.Arrays;

/*
*
Write a client program, “BagDriver.java”, which contains a main method 
to demo some typical usage of the three methods (union, intersection, and difference) 
in BagInterface.
*
*/

public class BagDriver{


    public static void main(String[] args) {
        //use union, intersection, and difference

        //two bags to test with
        BagInterface<String> bag1 = new ResizeableArrayBag<String>(4);
        BagInterface<String> bag2 = new ResizeableArrayBag<String>(5);

        //creating the bags
        bag1.add("a");
        bag1.add("b");
        bag1.add("c");
        bag1.add("b");


        bag2.add("b");
        bag2.add("c");
        bag2.add("c");
    

        System.out.println("We have Bag 1 and Bag 2 here: ");
        System.out.println(Arrays.toString(bag1.toArray()));
        System.out.println(Arrays.toString(bag2.toArray()));

        BagInterface<String> unionBag = bag1.union(bag2);
        BagInterface<String> intersectionBag = bag1.intersection(bag2);
        BagInterface<String> diffBag1 = bag1.difference(bag2);
        //only one that doesn't work? just returned bag1 again.
        BagInterface<String> diffBag2 = bag2.difference(bag1);

        System.out.println("Union of bag 1 and bag 2: \n" + Arrays.toString(unionBag.toArray()) 
            + "\nIntersection bag: \n" + Arrays.toString(intersectionBag.toArray()) 
            + "\nDifference of Bag1 - Bag2: \n" + Arrays.toString(diffBag1.toArray())
            + "\nDifference of Bag2 - Bag1: \n" + Arrays.toString(diffBag2.toArray()));
        
    }
}