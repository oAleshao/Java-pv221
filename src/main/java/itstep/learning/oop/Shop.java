package itstep.learning.oop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Product> products;

    public Shop() {
        products = new ArrayList<Product>();
        products.add(new Lamp("Philips", 60.0));
        products.add(new Pump("Pumper", 100));
        products.add(new Lamp("Platinum", 75.0));
        products.add(new Accumulator("Alphaline", 15000.0));
        products.add(new Accumulator("Mutlu", 12000.0));
    }

    public void run(){
        this.PrintProducts();
        System.out.println("-------Manual---------");
        this.printManualProducts();
        System.out.println("-------Non-Manual---------");
        this.printNonManualProducts();
        System.out.println("-------Works---------");
        this.showWork();
        System.out.println("-------Warranty---------");
        this.showWarranty();
    }

    private void PrintProducts() {
        for (Product product : products) {
            if(product instanceof Testable){
                ((Testable) product).test();
            }
            else{
                System.out.println(product.getCard());
            }
        }
    }

    private void printManualProducts() {
        for (Product product : products) {
            if (product instanceof Manual){
                System.out.println(product.getCard());
            }
        }
    }

    private void printNonManualProducts() {
        for (Product product : products) {
            if (!(product instanceof Manual)){
                System.out.println(product.getCard());
            }
        }
    }

    private void showWork(){
        for (Product product : products) {
            for(Method method : product.getClass().getDeclaredMethods()){
                if(method.isAnnotationPresent(Works.class)){
                    method.setAccessible(true);
                    System.out.print(method.getAnnotation(Works.class).value() + " ");
                    try {
                        method.invoke(product);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                       System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private void showWarranty(){
        for (Product product : products) {
            for(Method method : product.getClass().getDeclaredMethods()){
                if(method.isAnnotationPresent(Warranty.class)){
                    method.setAccessible(true);
                    System.out.print(method.getAnnotation(Warranty.class).value() + " ");
                    try {
                        method.invoke(product);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}

/*

Shop is a happy house

Shop <>-----------------> Product
                              △             | Поліморфізм - існування одної
                            / | \           | сутності в різних формах
                 Accumulator  |  Pump
                             Lamp

- Product ( manufacturer )
-- Accumulator ( manufacturer, capacity )
-- Lamp ( manufacturer, power )
-- Pump ( manufacturer, productivity )

Product [manufacturer, getManufacturer(), setManufacturer()]
Lamp extends Product
Lamp [[<manufacturer>, getManufacturer(), setManufacturer()], power, set()]
 */
