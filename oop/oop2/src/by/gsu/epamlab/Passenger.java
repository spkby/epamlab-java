package by.gsu.epamlab;

public class Passenger implements IMass{
    private final double weight;
    private final String name;
    
    public Passenger() {
        this( null,0) ;
    }

    public Passenger(String name,double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public double getMass() {
        return weight;
    }

    @Override
    public int getOrder() {
        return 1;
    }

    public String toString(){
        return name+";"+getMass()+"kg";
    }
}
