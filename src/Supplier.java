import java.util.Random;

//This is the class for the supplier.
// They randomly pick 2 ingredients to put on the counter, similar to the producer from the
// producer and consumer example.
public class Supplier implements Runnable {

    // Array of ingredients to randomly pick from
    private final String[] ingredients = {"Rice", "Nori", "Filling"};
    private final Random randomDelivery = new Random();
    private SingleBuffer buffer;

    // Constructor
    public Supplier(SingleBuffer buffer) {
        this.buffer = buffer;
    }

    // Randomly select 2 DIFFERENT ingredients. This ensures there will always be chef to finish sushi.
    private String[] selectIngredients(){
        int ingredientIndex1 = randomDelivery.nextInt(ingredients.length);
        int ingredientIndex2 = -1;
        int rand;
        while (ingredientIndex2 == -1){
            rand = randomDelivery.nextInt(ingredients.length);
            if (rand != ingredientIndex1){
                ingredientIndex2 = rand;
            }
        }
        return new String[]{ingredients[ingredientIndex1], ingredients[ingredientIndex2]};
    }

    // Start serving ingredients
    // The structure and print statements are very similar to the box example from class.
    public void run(){
        // The specification states 20 sushi must be made.
        while (buffer.getSushiCount() < 20){
            String[] delivery = selectIngredients();
            buffer.add(delivery);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }

        System.out.println(Thread.currentThread().getName() + " realized that 20 sushi has been made, and is packing up.");
    }


}