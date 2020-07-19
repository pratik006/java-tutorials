package hackerrank.dp;

public class RobHouse {
    int[] dp;
    public static void main(String[] args) {
        RobHouse robHouse = new RobHouse();
        System.out.println(robHouse.rob(new int[] {114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240}));
    }

    public int rob(int[] nums) {
        dp = new int[nums.length+1];
        for(int i=0;i<=nums.length;i++) dp[i] = -1;
        return rob(nums, nums.length-1);
    }

    public int rob(int[] nums, int n) {
        if (n < 0) return 0;
        if (dp[n] != -1) return dp[n];
        return dp[n] =  Math.max( nums[n] + rob(nums, n-2), rob(nums, n-1) );
    }
}
