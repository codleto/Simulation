package Simulation;

import java.util.ArrayList;



public class Test {

     ArrayList<Integer> oFood = new ArrayList<>();


    public void xx(Integer a){
        oFood.add(a);
        System.out.println(oFood);
    }



    public static void main(String[] args) {
        Test t = new Test();
        Test t2 = new Test();
        t2.oFood.add(5);
        t.xx(7);
        System.out.println(t2.oFood);

    }
}

class Rrr{
    Test t = new Test();

}
