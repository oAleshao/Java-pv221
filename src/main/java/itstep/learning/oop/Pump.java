package itstep.learning.oop;

public class Pump
        extends Product
        implements Manual{
    private int productivity;

    public Pump(String manufacturer, int productivity) {
        super.setManufacturer(manufacturer);
        this.setProductivity(productivity);
    }

    public int getProductivity() {
        return productivity;
    }

    public void setProductivity(int productivity) {
        if (productivity < 0){
            throw new RuntimeException("Productivity must be a positive number");
        }
        this.productivity = productivity;
    }

    @Override
    public String getCard(){
        return String.format("Pump: Manufacturer: '%s', Productivity: %d ", super.getManufacturer(), this.getProductivity());
    }

    @Works("as pump")
    public void pump(){
        System.out.println("Working on " + getCard());
    }

    @Warranty("Has a warranty for 1 year |")
    public void hasWarranty(){
        System.out.println(getCard() + " has warranty for ");
    }
}
