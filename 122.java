/*
If we were to buy only 2 situations can arise:
  z/                      /
 y/         or.        /\/ 
x/                   /

Case 1:
  y - x
  z - y
= z - x

Case 2:
Buy on day 1, sell on day 3   ==> case 1
Buy on day 4, sell on day 6   ==> case 1

*/
class Solution {
    public int maxProfit(int[] prices) {
        int totalProfit = 0;
        for (int i = 1; i < prices.length; ++i) {
            if(prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }
        return totalProfit;
    }
}
