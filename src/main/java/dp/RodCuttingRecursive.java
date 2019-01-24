package dp;

public class RodCuttingRecursive {

    public int cutRot(int[] profits, int n) {
        if (n == 0) {
            return 0;
        }

        int revenue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            revenue = Math.max(revenue, profits[i] + cutRot(profits, n - i - 1));
        }
        return revenue;
    }

    public static void main(String[] args) {
        int[] profits = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        RodCuttingRecursive rodCuttingRecursive = new RodCuttingRecursive();
        for (int i = 0; i < profits.length; i++) {
            System.out.println(String.format("i = %d, Revenue = %d", i, rodCuttingRecursive.cutRot(profits, i+1)));
        }
    }
}
