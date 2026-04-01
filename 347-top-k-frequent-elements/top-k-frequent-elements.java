/**
I wrote the Python version. I used AI to convert the the Python code to Java code.
 */
class Solution {

    // Partition function
    private int findPivot(List<int[]> nums, int s, int e) {
        int pivotFreq = nums.get(e)[1]; // pivot = last element's frequency
        int inflectionPoint = s - 1;

        for (int i = s; i <= e; i++) {
            if (nums.get(i)[1] <= pivotFreq) {
                inflectionPoint++;

                // swap nums[i] and nums[inflectionPoint]
                int[] temp = nums.get(i);
                nums.set(i, nums.get(inflectionPoint));
                nums.set(inflectionPoint, temp);
            }
        }
        return inflectionPoint;
    }

    // QuickSelect function
    private void quickSelect(List<int[]> nums, int k, int s, int e) {
        if (s > e) return;

        int pivot = findPivot(nums, s, e);

        if (pivot == k) return;

        if (pivot > k) {
            quickSelect(nums, k, s, pivot - 1);
        } else {
            quickSelect(nums, k, pivot + 1, e);
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Frequency map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Convert to list of (num, freq)
        List<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            list.add(new int[]{entry.getKey(), entry.getValue()});
        }

        // Step 3: QuickSelect to position top k elements
        int n = list.size();
        quickSelect(list, n - k, 0, n - 1);

        // Step 4: Collect result
        int[] result = new int[k];
        int idx = 0;

        for (int i = n - k; i < n; i++) {
            result[idx++] = list.get(i)[0];
        }

        return result;
    }
}