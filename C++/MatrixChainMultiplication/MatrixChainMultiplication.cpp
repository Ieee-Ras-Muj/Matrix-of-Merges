#include <iostream>
#include <vector>
#include <limits.h>
using namespace std;

// Function to find the most efficient way to multiply matrices
int matrixChainMultiplication(int p[], int n)
{
    // Create a 2D vector dp where dp[i][j] will store the minimum cost of multiplying matrices from i to j
    vector<vector<int>> dp(n, vector<int>(n, 0)); // Initialize with zeros

    // L is the chain length. We are looking for chain lengths from 2 to n-1
    for (int L = 2; L < n; L++)
    {
        for (int i = 1; i < n - L + 1; i++)
        {
            int j = i + L - 1;
            dp[i][j] = INT_MAX; // Initialize dp[i][j] to a large value

            // Try all possible places to split the product chain at k, and calculate the minimum cost
            for (int k = i; k < j; k++)
            {
                int q = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
                if (q < dp[i][j])
                {
                    dp[i][j] = q; // Update the minimum cost
                }
            }
        }
    }

    // dp[1][n-1] stores the minimum number of scalar multiplications needed
    return dp[1][n - 1];
}

int main()
{
    // Example usage
    int n;
    cout << "Enter the number of matrices: ";
    cin >> n;

    // Dimensions of the matrices
    int p[n + 1];
    cout << "Enter the dimensions of matrices in the form of an array: ";
    for (int i = 0; i <= n; i++)
    {
        cin >> p[i];
    }

    // Call the function and print the minimum cost of multiplying the matrices
    cout << "Minimum number of multiplications is: " << matrixChainMultiplication(p, n + 1) << endl;

    return 0;
}
