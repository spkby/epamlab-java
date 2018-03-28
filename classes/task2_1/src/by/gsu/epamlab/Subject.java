package by.gsu.epamlab;

public class Subject {

    private String name;
    private Material material;
    private double volume;

    public Subject() {
    }

    public Subject(String name, Material material, double volume) {
        this.name = name;
        this.material = material;
        this.volume = volume;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getMass() {
        return volume * material.getDensity();
    }

    @Override
    public String toString() {
        return name + ";" + material + ";" + volume + ";" + getMass();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
