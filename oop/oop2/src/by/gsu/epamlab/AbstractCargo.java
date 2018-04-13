package by.gsu.epamlab;

public abstract class AbstractCargo implements IMass {
   private Material material;

   public AbstractCargo() {

   }

   public AbstractCargo(Material material) {
       this.material = material;
   }

   public Material getMaterial() {
        return material;
   }

   public void setMaterial(Material material) {
        this.material = material;
   }

   public abstract double getMass();
   public abstract int getOrder();

   public String toString() {
       return material+";"+getMass()+"kg";
   }

}
