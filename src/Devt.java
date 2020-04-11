/*
foaie A4 pe care era trecut șirul denumit "devt" sub forma 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, ... , n.
 Dedesubtul acestui șir găsește un text alcătuit din k întrebări de forma a, b cu semnificația “Câte numere din acest șir se află în intervalul [a,b]?”
 
 >> Count the number non-prime elements withing a given range
 */

/*
    Make a prime and nonPrime array, containing the values 0 or 1 if the position the numbers are in contain any prime / on-prime values
 */



public class Devt {
    private int[] nonPrimes;
    private int startInterval;
    private int stopInterval;
    private int size;

    public Devt(int start, int stop, int maxSize) {
        this.startInterval = start;
        this.stopInterval = stop;
        this.size = maxSize;
        this.nonPrimes = new int[this.size];
        this.initialiseNonPrime();
    }

    private void initialiseNonPrime(){
        // Makes a list of all values from 1 to the max size
        // if the value within a position is = 1, it means the number is not prime
        // else, if value = 0, the number is prime 
        nonPrimes[0] = 1;
        nonPrimes[1] = 1;
        nonPrimes[2] = 0;
        // iterate through each even position and set its value to 1
        for (int i = 2; i < this.size; i += 2){
            nonPrimes[i] = 1;
        }
        // iterate through each prime position's multiples and set its value to 1
        for (int i = 3; i * i < this.size; i += 2)
            if(nonPrimes[i] == 0)
                for(int j = i*i; j < this.size; j+= 2*i)
                    nonPrimes[j] = 1;
    }
    public int getInterval(){
        // count how many non-prime values exist within a given interval
        int total = 0;
        for (int i = this.startInterval; i <= this.stopInterval; i+= 1){
            total += this.nonPrimes[i];
        }
        return total;
    }

    public static void main(String[] args) {
        Devt problem = new Devt(100, 120, 100001);
        System.out.println("The number of elements between these 2 values is: ");
        System.out.println(problem.getInterval());
    }
}
