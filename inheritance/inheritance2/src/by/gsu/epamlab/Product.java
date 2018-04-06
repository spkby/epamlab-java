package by.gsu.epamlab;

public class Product {

    final private String productname;
    final private Byn price;

    public Product() {
        this(null, null);
    }

    public Product(String productname, Byn price) {
        this.productname = productname;
        this.price = price;
    }

    public String getProductname() {
        return productname;
    }

    public Byn getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return productname + ";" + price;
    }
}
