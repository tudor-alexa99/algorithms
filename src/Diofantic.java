import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Diofantic problem:
 * Given 4 integers a, b, x, y, a sum N, and a list of arguments args[]
 * Return the number of variables that, paired together, respect the condition that a*x^2 + b*y^2 = N, where x and y are part
 * of the args[] list
 * <p>
 * Exp: args[] = [0, 3, 4, 5, 8], N = 25, a = 1, b = 1,
 * the pairs will be (0,5), (5, 0) , (3, 4), (4, 3)
 * the output will be 4 (the number of pairs)
 */


public class Diofantic {
    /*
    Possible optimisation steps: go through each element of the given list and check whether a*elem^2 or b*elem^2 is
    greater than N. If so, it means the element can not be used, therefor it's redundant to keep it and check it

    Possible solution is decomposing the sum N into all the possible pairs of a*x^2 + b*y^2 elements. Good luck doing that

     */
    private int a, b, sum;
    private ArrayList<Integer> values;
    private int pairCount = 0;

    public Diofantic(int a,
                     int b,
                     int sum,
                     ArrayList<Integer> _values) {
        this.a = a;
        this.b = b;
        this.sum = sum;
        this.values = _values;
    }

    private boolean checkPair(int _x, int _y) {
        /*
        Check if the pair of elements (x, y) and its opposite pair (y, x) respects the condition
         */
        return a * _x * _x + b * _y * _y == sum;
    }

    private int getPair(int x) {
        // for a value x, get the pair value y for which a*x^2 + b*y^2 == sum
        int y_square = (sum - a * x * x) / b;
        int y = (int) Math.sqrt(y_square);
        return y;
    }

    private void runProgram() {
        // go through each element of the list
        // take 1 value of the list, compute the difference and check whether the pair for that given value exists on the list
        while (this.values.size() > 0) {
            int x = this.values.remove(0);
            int y = this.getPair(x);

            // If the pair of the current value exists in the list and checks the condition, remove both the value and its pair from the values list
            if (this.values.contains(y)) {
                if (this.checkPair(x, y))
                    this.pairCount += 1;
                if (this.checkPair(y, x))
                    this.pairCount += 1;
                this.values.remove((Integer) x);
                this.values.remove((Integer) y);
            }
        }

    }

    public int getPairCount(){
        return this.pairCount;
    }

    public static void main(String[] args) {
        ArrayList<Integer> values = new ArrayList<Integer>();
        values.add(0);
        values.add(2);
        values.add(3);
        values.add(5);
        values.add(20);
        Diofantic problem = new Diofantic(1, 1, 25, values);
        problem.runProgram();
        System.out.println("The number of corect pairs is: ");
        System.out.println(problem.getPairCount());
    }
}
