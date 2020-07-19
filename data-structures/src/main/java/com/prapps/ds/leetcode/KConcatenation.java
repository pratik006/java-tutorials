package leetcode;

public class KConcatenation {
    public static void main(String[] args) {
        KConcatenation kConcatenation=new KConcatenation();
        System.out.println(kConcatenation.kConcatenationMaxSum(new int[]{-5,-2,0,0,3,9,-2,-5,4}, 5));
    }

    public int kConcatenationMaxSum(int[] arr, int k) {
        if(arr == null || arr.length == 0) return 0;
        int maxSoFar = 0;
        long maxEndingHere = 0;
        int normalsum = 0;
        int M = 1000000000 + 7;
        int len = k<2 ? arr.length : arr.length*2;
        for (int i=0;i<len;i++) {
            maxEndingHere = (maxEndingHere+arr[i%arr.length]) % M;
            if (maxEndingHere < 0) maxEndingHere = 0;
            maxSoFar = (int)(Math.max(maxEndingHere, maxSoFar)% M);
            if (i<arr.length)
                normalsum += arr[i];
        }

        int maxSoFarOdd = 0;
        long maxEndingHereOdd = 0;
        if (normalsum < 0) {
            maxSoFar = k>=2?(maxSoFar*(k/2))%M:maxSoFar%M;

            if(k%2 == 1 && k>=3) {
                for (int i=0;i<arr.length;i++) {
                    maxEndingHereOdd = (maxEndingHereOdd+arr[i]) % M;
                    if (maxEndingHereOdd < 0) maxEndingHereOdd = 0;
                    maxSoFarOdd = (int)(Math.max(maxEndingHereOdd, maxSoFarOdd)% M);
                }
                maxSoFar = ((maxSoFar*(k/2))%M) + maxSoFarOdd;
            }
        } else {
            maxSoFar = Math.max(maxSoFar, normalsum*k)%M;
        }

        return maxSoFar;
    }
}
