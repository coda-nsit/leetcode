class Cordinate {
    public int x;
    public int y;
    
    Cordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean equals (final Object O) {
        if (!(O instanceof Cordinate)) return false;
        if (((Cordinate) O).x != x) return false;
        if (((Cordinate) O).y != y) return false;
        return true;
    }
    
    public int hashCode() {
        return (x << 16) + y;
    }
    
}
