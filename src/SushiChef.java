
//This is the class for all 3 Sushi Chefs. I use "implements Runnable" as this is the prefered method for
// thead creation, as mentioned in class
public class SushiChef implements Runnable {

    //String[] missingIngredients holds the ingredients that the chef needs to serve sushi.
    // e.g. A chef with infinite Rice should have ["Nori", "Filling"]
    private String[] missingIngredients;

    // See SingleBuffer.java
    private SingleBuffer buffer;

    // Constructor
    public SushiChef(SingleBuffer buffer, String[] missingIngredients) {
        this.buffer = buffer;
        this.missingIngredients = new String[2];

        System.arraycopy(missingIngredients, 0, this.missingIngredients, 0, 2);
    }

    // Start making sushi
    // The structure and print statements are very similar to the box example from class.
    public void run() {
        // The specification states 20 sushi must be made.
        while (buffer.getSushiCount() < 20) {
            System.out.println(Thread.currentThread().getName() + " is ready to cook!");
            buffer.remove(Thread.currentThread().getName(), missingIngredients);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
        System.out.println(Thread.currentThread().getName() + " realized that 20 sushi has been made, and is closing up for the night.");
    }


}