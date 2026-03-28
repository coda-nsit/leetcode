/**
Author: @Rishab Banerjee

Standard binary search.
 */
class Solution {
    private boolean isPossible(int[] dist, double maxHour, int speed) {
        double hourTaken;

        hourTaken = 0;
        for (int i = 0; i < dist.length - 1; ++i) {
            hourTaken += Math.ceil(dist[i] * 1.0 / speed);
        }
        hourTaken += (dist[dist.length - 1] * 1.0 / speed);
        return hourTaken <= maxHour;
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        int mini, maxi, mid, minSpeed;
        mini = 1;
        maxi = 100000000;
        minSpeed = -1;

        while (mini <= maxi) {
            mid = (mini + maxi) / 2;
            System.out.printf("mid:%d\n", mid);
            if (isPossible(dist, hour, mid)) {
                minSpeed = mid;
                maxi = mid - 1;
            } else {
                mini = mid + 1;
            }
        }

        return minSpeed;
    }
}