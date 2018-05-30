package by.gsu.epamlab;

public class MeanMark {

    private String student;
    private double meanMark;

    public MeanMark(String student, double meanMark) {
        this.student = student;
        this.meanMark = meanMark;
    }

    @Override
    public String toString() {
        return student + Constants.DELIMITER + meanMark;
    }
}
