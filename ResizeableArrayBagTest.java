import java.util.Arrays;

public class ResizeableArrayBagTest {
    
    
    /** 
     * @param args
     */
    public static void main(String[] args) { 

        //creating the bags
        System.out.println("We have Bag 1 and Bag 2:");

        int[] num1 = {1, 2, 3, 4, 5};
        int[] num2 = {2, 4, 6, 8};

        BagInterface<Integer> bag1 = new ResizeableArrayBag<>(num1.length);
        BagInterface<Integer> bag2 = new ResizeableArrayBag<>(num2.length);

        testAdd(bag1, num1);
        testAdd(bag2, num2);

        //testing union method
        System.out.println("First we will test our union method");
        BagInterface<Integer> unionTest = bag1.union(bag2);
        System.out.println(Arrays.toString(unionTest.toArray()));

        //testing intersection method
        System.out.println("Testing intersection method:");
        BagInterface<Integer> intersectionTest = bag1.intersection(bag2);
        System.out.println(Arrays.toString(intersectionTest.toArray()));

        //testing difference method, bag1 - bag2
        System.out.println("Test difference method, bag1 - bag2:");
        BagInterface<Integer> diffBag1 = bag1.difference(bag2);
        System.out.println(Arrays.toString(diffBag1.toArray()));

        //testing difference method, bag2 - bag1
        System.out.println("Testing difference method, bag2 - bag1");
        BagInterface<Integer> diffBag2 = bag2.difference(bag1);
        System.out.println(Arrays.toString(diffBag2.toArray()));

    }

    public static void testAdd(BagInterface<Integer> bag, int[] num) {
        for(int i = 0; i < num.length; i++) {
            bag.add(num[i]);
        }
    }

}