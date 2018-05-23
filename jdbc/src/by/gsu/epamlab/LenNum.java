package by.gsu.epamlab;

public class LenNum {

    private final int len;
    private int num;

    public LenNum(int len, int num) {
        this.len = len;
        this.num = num;
    }

    public int getLen(){
        return len;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return len + ";" + num;
    }
}
