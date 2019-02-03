package dp;

public class FibonacciRecursive {

    public int calculateFibonacci(int n) {
        if (n < 2) {
            return n;
        } else {
            return calculateFibonacci(n-1) + calculateFibonacci(n-2);
        }
    }

    public static void main(String[] args) {
        FibonacciRecursive fibonacci = new FibonacciRecursive();
        System.out.println("5th Fibonacci is ---> " + fibonacci.calculateFibonacci(5));
        System.out.println("6th Fibonacci is ---> " + fibonacci.calculateFibonacci(6));
        System.out.println("7th Fibonacci is ---> " + fibonacci.calculateFibonacci(7));
    }
}
