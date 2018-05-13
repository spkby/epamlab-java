package by.gsu.epamlab;

public class LenNum {

    private final int len;
    private int num;

    public LenNum(int len) {
        this.len = len;
        num = 1;
    }

    public int getLen() {
        return len;
    }

    public int getNum() {
        return num;
    }

    public void increment() {
        num++;
    }

    @Override
    public String toString() {
        return len + ";" + num;
    }
}
