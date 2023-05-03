/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    private int calls = 0;
    public int findInflectionIndex(MountainArray mountainArr, int start, int end)
    {
        int mid = (start + end) / 2;
        if (mid == 0)
        {
            return 1;
        }
        int midVal, midValMinus1, midValPlus1;
        midVal = mountainArr.get(mid);
        midValMinus1 = mountainArr.get(mid - 1);
        midValPlus1 = mountainArr.get(mid + 1);

        if (midValMinus1 < midVal &&  midVal> midValPlus1)
        {
            return mid;
        }

        // s <= m <= e
        if (midValMinus1 <= midVal && midVal <= midValPlus1)
        {
            return findInflectionIndex(mountainArr, mid + 1, end);
        }
        return findInflectionIndex(mountainArr, start, mid - 1);
    }

    public int binarySearch(MountainArray mountainArr, int start , int end, int needle, boolean isReverse)
    {
        // System.out.println(start + " " + end);
        if (start > end)
        {
            return -1;
        }
        int mid = (start + end) / 2;
        int midVal = mountainArr.get(mid);
        if (midVal == needle)
        {
            return mid;
        }
        if (!isReverse)
        {
            if (needle < midVal)
            {
                return binarySearch(mountainArr, start, mid - 1, needle, isReverse);
            }
            return binarySearch(mountainArr, mid + 1, end, needle, isReverse);
        }
        else
        {
            if (needle < midVal)
            {
                return binarySearch(mountainArr, mid + 1, end, needle, isReverse);
            }
            return binarySearch(mountainArr, start, mid - 1, needle, isReverse);
        }

    }

    public int findInMountainArray(int target, MountainArray mountainArr) 
    {
        int inflectionIndex = findInflectionIndex(mountainArr, 0, mountainArr.length() - 1);
        int left = binarySearch(mountainArr, 0, inflectionIndex, target, false);
        if (left != -1)
        {
            return left;
        }
        return binarySearch(mountainArr, inflectionIndex + 1, mountainArr.length() - 1, target, true);
    }
}
