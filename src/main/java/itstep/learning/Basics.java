package itstep.learning;

import java.util.ArrayList;
import java.util.List;

public class Basics {
    public void run(){
        System.out.println("Java Basics");
        // primitives
        byte b = 10;
        short s = 20;
        int i = 30;
        long l = 40;

        float f = 5.0f;
        double d = 6.0;

        char c = 'A';
        boolean bool = true;

        byte[] arr = {1,2,3,4,5};
        List<Integer> arr1 = new ArrayList<Integer>();
        for (int j = 0; j < arr.length; j++) {
            arr1.add((int) arr[j]);
        }

        for(int element : arr1){
            System.out.println(element);
        }
    }
}
