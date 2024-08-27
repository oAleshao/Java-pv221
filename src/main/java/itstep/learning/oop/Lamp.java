package itstep.learning.oop;

public class Lamp
        extends Product
        implements Testable{

    private double power;

    public Lamp(String manufacturer, double power) {
        super.setManufacturer(manufacturer);
        this.setPower(power);
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        if (power < 0) {
            throw new IllegalArgumentException("Power must be a positive number");
        }
        this.power = power;
    }

    @Override
    public String getCard(){
        return String.format("Lamp: Manufacturer: %s, Power: %.1f W", super.getManufacturer(), this.getPower());
    }

    @Override
    public void test() {
        System.out.println("Testing: " + getCard());
    }

    @Works("as lamp")
    public void shine(){
        System.out.println("Working on " + getCard());
    }

    @Warranty("Does not have a warranty |")
    public void hasWarranty(){
        System.out.println(getCard());
    }
}
