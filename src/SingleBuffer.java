import java.util.Arrays;

//This class acts as the counter the chefs and supplier interact with.
// The name is SingleBuffer for clarity as it is similar to the box and bounded buffer examples

//notifyAll() is used over notify() as the 3 chefs don't know which one needs to cook. notifyAll()
// guarantees that the right one will be woken up.
// Since there are only 3 chefs, the overhead is minimal. If n was bigger, this could be a different story.

public class SingleBuffer
{

    // Holds contents, in this case, an array of index 2 noting ingredients
    private Object contents;
    // Record the amount of sushi made so we can close the program upon hitting 20.
    private int sushiCount;

    public SingleBuffer(){
        contents = "empty";
        sushiCount = 0;
    }

    // Check if the ingredients match with those the buffer holds.
    private synchronized boolean checkIngredients(String[] missingIngredients){
        String[] bufferContents = (String[]) contents;

        Arrays.sort(missingIngredients);
        Arrays.sort(bufferContents);

        return Arrays.equals(missingIngredients, bufferContents);
    }

    // Put something on the counter. Similar to the producer and consumer example.
    // notifyAll() after something is added
    public synchronized void add(Object item) {
        while (contents != "empty") {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        // The reason the print statement is here is so the console output is less confusing.
        // Assuming condition sync and mutual exclusion are implemented correctly, this shouldn't really matter,
        // But this is some extra security so the console output will be more clear.
        System.err.println("The supplier has produced " + Arrays.toString((String[])(item)));
        contents = item;
        notifyAll();
    }

    // A chef takes the ingredients to make sushi
    // notifyAll() after something is removed
    public synchronized void remove(String chefName, String[] missingIngredients){
        while (contents == "empty") {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        if (checkIngredients(missingIngredients)){
            contents = "empty";
            ++sushiCount;

            // The print statement is here for the same reasons as above in add()
            System.err.println(chefName + " made some sushi! We're at " + getSushiCount() + " sushi now!");
            if (sushiCount >= 20) {
                System.out.println("20 sushi has been made!");
            }

        }
        notifyAll();
    }

    public Object getContents(){ return contents; }
    public int getSushiCount(){ return sushiCount; }

}