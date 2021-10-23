// loccapollo
class HitCounter {
    private Deque<Integer> dq;
    private Map<Integer, Integer> hitAtTime;
    
    public HitCounter() {
        dq = new LinkedList<Integer>();
        hitAtTime = new HashMap<Integer, Integer>();
    }
    
    public void hit(int timestamp) {
        if (hitAtTime.containsKey(timestamp)) {
            hitAtTime.put(timestamp, hitAtTime.get(timestamp) + 1);
        } else {
            dq.addLast(timestamp);
            hitAtTime.put(timestamp, 1);
        }
        return;
    }
    
    public int getHits(int timestamp) {
        while (this.dq.size() > 0) {
            int expiredTimestamp = this.dq.peekFirst();
            if (timestamp - expiredTimestamp >= 300) {
                this.dq.pollFirst();
                hitAtTime.remove(expiredTimestamp);
            } else {
                break;
            }
        }
        int totalHits = 0;
        for (int validTimestamp: this.hitAtTime.keySet()) {
            totalHits += this.hitAtTime.get(validTimestamp);
        }
        return totalHits;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
