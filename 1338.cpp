class Solution {
public:
    int minSetSize(vector<int>& arr) {
        priority_queue<int> q;
        map<int, int> numFreq;
        
        for (int x: arr) {
            if(numFreq.find(x) == numFreq.end()) {
                numFreq[x] = 0;
            }
            numFreq[x] += 1;
        }
        
        for(auto [k, v]: numFreq) {
            q.push(v);
        }
        
        int total, ans;
        total = ans = 0;
        while (total < arr.size() / 2) {
            total += q.top();
            q.pop();
            ans += 1;
        }
        return ans;
    }
};
