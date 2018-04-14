package by.gsu.epamlab;


public abstract class Cargo {
    private String name;


    public Cargo() {
    }

    public Cargo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
