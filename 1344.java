class Solution {
    public double angleClock(int hour, int minutes) {
        double hourPosition = ((5 * minutes) / 60.0 ) + (hour % 12) * 5;
        double angle = Math.abs(hourPosition - minutes) * 6;
        return Math.min(angle, 360 - angle);
    }
}
