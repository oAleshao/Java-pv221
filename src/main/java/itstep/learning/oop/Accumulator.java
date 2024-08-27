package itstep.learning.oop;

public class Accumulator extends Product {

    private double Capacity;

    public Accumulator(String manufacturer, double capacity) {
        super.setManufacturer(manufacturer);
        this.setCapacity(capacity);
    }

    public double getCapacity() {
        return Capacity;
    }

    public void setCapacity(double capacity) {
        Capacity = capacity;
    }

    @Override
    public String getCard(){
        return String.format("Accumulator: Manufacturer: %s, Capacity: %.1f mAh", super.getManufacturer(), this.getCapacity());
    }

    @Works("as accumulator")
    public void charge(){
        System.out.println("Working on " + getCard());
    }

    @Warranty("Has a warranty for 2 year |")
    public void hasWarranty(){
        System.out.println(getCard());
    }

}
