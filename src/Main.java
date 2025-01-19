
//Main method, similar to BoxTest from class.
public class Main {
    public static void main(String[] args) {

        // Create the counter, chefs, and supplier.
        SingleBuffer counter = new SingleBuffer();

        Thread riceChef = new Thread(new SushiChef(counter, new String[]{"Nori", "Filling"}));
        riceChef.setName("RiceChef");

        Thread noriChef = new Thread(new SushiChef(counter, new String[]{"Rice", "Filling"}));
        noriChef.setName("NoriChef");

        Thread fillingChef = new Thread(new SushiChef(counter, new String[]{"Rice", "Nori"}));
        fillingChef.setName("FillingChef");

        Thread supplier = new Thread(new Supplier(counter));
        supplier.setName("Supplier");

        //run() all threads.
        riceChef.start();
        noriChef.start();
        fillingChef.start();
        supplier.start();

    }
}