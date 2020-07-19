## 1. 0-1 Knapsack

``` Java
knapsack(int[] val, int[] w, int W) {
	if (n == -1 || W == 0)
            return 0;
        if (w[n] > W)
            return findMax(val, w, W, n-1);

        return Math.max(val[n] + findMax(val, w, W-w[n], n-1), findMax(val, w, W, n-1));
}

```

#### 1.1 Knapsack memoization

``` Java
public static int knapsackMemo(int[] val, int[] w, int W, int n) {
    if (n == 0 || W <= 0)
        return 0;
    if (d[n][W] != -1)
        return d[n][W];

    if (w[n] > W)
        d[n][W] = findMaxDP(val, w, W, n-1);
    else
        d[n][W] = Math.max(val[n] + findMaxDP(val, w, W-w[n], n-1),
                findMaxDP(val, w, W, n-1));

    return d[n][W];
}
```

#### 1.2 Knapsack Top Down
``` Java
public static int findMaxTopDown(int[] val, int[] w, int W) {
    for (int i=1;i<=val.length;i++) {
        for (int j = 1; j <= W; j++) {
            int weight = w[i-1];
            if (weight > j) {
                d2[i][j] = d2[i-1][j];
            } else {
                d2[i][j] = Math.max((val[i-1] + d2[i-1][j-weight]),
                        d2[i-1][j]);
            }
        }
    }

    return d2[val.length][W];
}

```

#### 1.3 Unbounded Knapsack
Where repeatation is allowed so in the target code where selection is successful instead of passing [i-1] use [i].
``` Java
d2[i][j] = Math.max((val[i-1] + d2[i]/*notice not doing i-1*/[j-weight]),
                        d2[i-1][j]);
```

#### 1.4 Subset Sum Problem
For a given array return true if a subset exists for a give sum K.
``` Java

static boolean subsetSumBottomUup(int[] A, int S) {
    for (int i=1;i<=A.length;i++) {
        for (int j=1;j<=S;j++) {
            int item = A[i-1];
            if (item > j)
                d[i][j] = d[i-1][j];
            else
                d[i][j] = d[i-1][j-item] || d[i-1][j];
        }
    }

    return d[A.length][S];
}
```
Note the OR condition instead of 'max' as in of knapsack

#### 1.5 Equal sum/K partition problem
If equal sum partition is possible then (sum of the array) % K == 0
If the prev condition is true then execute SubsetSum(SUM/K). If this returns true then it is possible else not possible.

#### 1.6 Count of subsets for a given Sum
Same as subsetSum and replacing '||' with '+'

#### 1.7 Minimum subset sum difference
Qn Partition the given array into 2 subset such that the difference between their Sum is minimum
> Find the range of the array R by find the sum of elements. Then pass the array to subsetSumTopDown. Loop the last row till R/2 to find the minimum.
``` Java
int min = Integer.MAX_INT;
for (int i=1;i<=R/2;i++) 
    if ((R-a[i]) - a[i] < min)
        min = (R-a[i]) - a[i];
```

#### 1.8 Count the number of subset with a given difference
If we consider 2 subsets s1 and s2 and (s1-s2) will be given sak K. Then
> I) s1 + s2 = R (range of array)

> II) s1 - s2 = K

> Combining the 2 we get s1 = (K+R)/2

Now passing s1 value to countSubsetSum(int arr[], int S) should give the result

#### 1.9 Target Sum
Given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

> Sol. Same as "Count the number of subset with a given difference" where instead of difference the sum is given


#### 1.10 Rod cutting problem
Same as Unbounded knapsack

#### 1.11 Coin Change Problem Maximum Number of ways
Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change? The order of coins doesn’t matter.
> Sol. Almost similar to Unbounded Knapsack, in the selection code use '+' instead of 'max' 
```Java
//d2[i][j] = Math.max((val[i-1] + d2[i][j-weight]),d2[i-1][j]);
d2[i][j] = d2[i][j-weight]) + d2[i-1][j];
```

#### 1.12 Coin Change Problem Minimum Numbers of coins

Given a value V, if we want to make change for V cents, and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins, what is the minimum number of coins to make the change?
Example:

Input: coins[] = {25, 10, 5}, V = 30
Output: Minimum 2 coins required
We can use one coin of 25 cents and one of 5 cents 
> Sol.
Initialize first row with INT_MAX-1 and first col with 0
Here we have to initialize the 2nd row as well with the folloing code
``` Java
for (int j=1; j<=sum; j++) {
    if (j%arr[0] == 0)
        d2[i][j] = j/arr[0];
    else
        d2[i][j] = INT_MAX-1;
```
and the selection code will be
``` Java
 d2[i][j] = min(d2[i][j-weight]) + 1, d2[i-1][j]);
```

## 2. Longest Common Subsequence (LCS)
```Java
static int lcs(String s1, String s2, int l1, int l2) {
        if (l1 == 0 || l2 == 0)
            return 0;

        if (s1.charAt(l1-1) == s2.charAt(l2-1))
            return 1 + lcs(s1, s2, l1-1, l2-1);
        else return Math.max(lcs(s1, s2, l1-1, l2), lcs(s1, s2, l1, l2-1));
    }
```

#### 2.1 LCS with memoization
```Java

    static int lcsMemo(String s1, String s2, int l1, int l2) {
        if (l1 == 0 || l2 == 0)
            return 0;

        if (d[l1][l2] != -1)
            return d[l1][l2];

        if (s1.charAt(l1-1) == s2.charAt(l2-1))
            return d[l1][l2] = 1 + lcsMemo(s1, s2, l1 - 1, l2 - 1);
        else
            return d[l1][l2] = Math.max(lcsMemo(s1, s2, l1-1, l2), lcsMemo(s1, s2, l1, l2-1));
    }

```
#### 2.2 LCS with Top Down
```Java
    static int lcsTopDown(String s1, String s2) {
        for (int i=1;i<=s1.length();i++) {
            for (int j=1;j<=s2.length();j++) {
                char ch1 = s1.charAt(i-1);
                char ch2 = s2.charAt(j-1);
                if (ch1 == ch2) {
                    d2[i][j] = 1 + d2[i-1][j-1];
                } else {
                    d2[i][j] = Math.max(d2[i-1][j], d2[i][j-1]);
                }
            }
        }
        return d2[s1.length()][s2.length()];
    }
```
#### 2.3 Printing LCS string
```Java
    static String printLcs(String s1, String s2) {
        int i = s1.length();
        int j = s2.length();
        String res = "";
        while (i>0 && j>0) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                res = s1.charAt(i-1) + res;
                i--;j--;
            } else if (d2[i-1][j] > d2[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }

        return res;
    }
```
#### 2.4 Longest Common Substring
```Java
    static int lcsubstringTopDown(String s1, String s2) {
        for (int i=1;i<=s1.length();i++) {
            for (int j=1;j<=s2.length();j++) {
                char ch1 = s1.charAt(i-1);
                char ch2 = s2.charAt(j-1);
                if (ch1 == ch2) {
                    d2[i][j] = 1 + d2[i-1][j-1];
                } else {
                    d2[i][j] = 0;//Math.max(d2[i-1][j], d2[i][j-1]);
                }
            }
        }

        int max = 0;
        for (int i=1;i<=s1.length();i++)
            for (int j=1;j<=s2.length();j++)
                if(max < d2[i][j])
                    max = d2[i][j];
        return max;
    }
```
#### 2.5 Shortest Common Super-sequence (SCS)
For two given strings find the Shortest common sequence length
> Sol. For input strign m and n, it will be 'm+n-lcs'

Printing Shortest common super sequence
```Java
    static String printLcs(String s1, String s2) {
        int i = s1.length();
        int j = s2.length();
        StringBuilder res = StringBuilder();
        while (i>0 && j>0) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                res.append( s1.charAt(i-1) );
                i--;j--;
            } else if (d2[i-1][j] > d2[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }
        
        while (i>0)
            res.append( s1.charAt(i--) );
        while (j>0)
            res.append( s1.charAt(j--) );
        

        return res;
    }
```

##### Minimum Number of `Insertion` and `Deletion` to convert String a to String b
> Sol. a.length() + b.length() - 2*LCS

#### 2.6 Longest Palindroming Subsequence (LPS)
> Sol LCS(a, rev(a))

##### Minimum number of 'Insertion' or 'Deletion' in a string to make it palindrome
> Sol strlen(s) - LPS(s)

##### Longest Common Repeating sequence in a String
> sol. Same as LCS code with a restriction of 'i != j'
```Java
 if (ch1 == ch2 && i != j) {
    d2[i][j] = 1 + d2[i-1][j-1];
} else {
    d2[i][j] = Math.max(d2[i-1][j], d2[i][j-1]);
}
```

#### Sequence Pattern Matching
> Sol - Is String b a subsequence of string a ? If LCS(a,b) == b.length()



## 3. Matrix Chain Multiplication (MCM)

#### Format
1. find valid i/j
2. find Base Condition. For ex if (i <= j) return 0;
3. find k loop
4. Consolidate temporary answers using either SUM or MIN etc

#### 3.2 Palindrome Partitioning (Recursion)
Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. 
Example:
  “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”.
```Java
int solve(int[] arr, int i, int j) {
    if (i >= j || isPalindrome(arr, i, j))
        return 0;// string is empty or 1 letter
    int min = Integer.MIN_INT;
    for (int k=i;i<j-1;i++) {        
        int temp = 1 + solve(arr, i, k) + solve(arr, k+1, j);
        if (temp < min)
            min = temp;
    }
    
    return min;
}
    
```
#### 3.2 Palindrome Partitioning (Memoized)
titioning of “ababbbabbababa”.
```Java
static int[][] d = new int[arr.length+1][arr.length+1];
// initialize the array with a value of -1
int solve(int[] arr, int i, int j) {
    if (i >= j || isPalindrome(arr, i, j))
        return 0;// string is empty or 1 letter
    
    if (d[i][j] != -1) {
        return d[i][j];
    }
    
    int min = Integer.MIN_INT;
    for (int k=i;i<j-1;i++) {        
        int temp = 1 + solve(arr, i, k) + solve(arr, k+1, j);
        if (temp < min)
            min = temp;
    }
    
    return d[i][j] = min;
}
    
```
