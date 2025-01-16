public class SushiChef {

    private boolean hasRice;
    private boolean hasNori;
    private boolean hasFilling;

    // Constructor
    public SushiChef(boolean hasRice, boolean hasNori, boolean hasFilling) {
        this.hasRice = hasRice;
        this.hasNori = hasNori;
        this.hasFilling = hasFilling;
    }

    public void makeSushi(){
        //Checks if all ingredients are true
        //if true, makes sushi, sets all to false with "useIngredients()"
    }

    public void useIngredients(){
        setHasRice(false);
        setHasNori(false);
        setHasFilling(false);
    }


    //Getters
    public boolean getHasRice() { return hasRice; }
    public boolean getHasNori() { return hasNori; }
    public boolean getHasFilling() { return hasFilling; }

    //Setters
    public void setHasRice(boolean setRice) { this.hasRice = setRice; }
    public void setHasNori(boolean setNori) { this.hasNori = setNori; }
    public void setHasFilling(boolean setFilling) { this.hasFilling = setFilling;}

}