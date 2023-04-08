/**
 * class that has tax information for all the families that filed taxes
 * @author Indigo
 * 
 */

public class TaxYear {
    /**
     * maxReturns represents the maximum number of tax returns to be filed this year
     * families is an array that serves as local storage for all the families filing taxes this year
     */
    private static int maxReturns = 0;
    private static Family[] families = new Family[maxReturns];

    /**
     * Constructor that sets the maxReturns field
     * @param max represents the maximum number of returns that can be filed this year
     */
    public TaxYear(int max){
        maxReturns = max;
    }

    /**
     * Method that adds a family to the local storage of the families
     * @param object represents a family
     */
    public void addFamily(Family object){
        Family[] array = new Family[families.length+1];
        for(int i = 0; i < families.length; i++){
            array[i] = families[i];
        }
        array[array.length - 1] = object;
        families = array;
    }

    /**
     * Method that will call the addFamily method to add the family to the local storage once validated
     * @param object represents a family
     * @return returns true if a family is valid for tax filing otherwise returns false
     * 
     */
    public boolean taxFiling(Family object){
        if(object.getFilingStatus() == 1)
            if(object.getNumAdults() != 1)
                return false;
        else if(object.getFilingStatus() == 2)
            if(object.getNumAdults() != 2)
                return false;
        else if (object.getFilingStatus() == 3)
            if(object.getNumAdults() != 1)
                return false;
        if(object.getNumAdults() == 0)
            return false;
        addFamily(object);
        return true;
    }

    /**
     * calculates total tax withheld from all families
     * @return the total tax withheld among all the families who filed taxes
     */
    public float taxWithheld(){
        float totalTaxWithheld = 0.0f;

        for(int i = 0; i < families.length; i++){
            totalTaxWithheld += families[i].getTaxWithheld();
        }
        return totalTaxWithheld;
    }

    /**
     * calculates total tax owed by all families
     * @return the total tax owed by all the families who filed taxes
     */
    public float taxOwed(){
        float totalTaxOwed = 0.0f;
        for(int i = 0; i < families.length; i++){
            totalTaxOwed += families[i].getPreCreditTax();
            //System.out.println(families[i].getPreCreditTax());
        }
        return totalTaxOwed;
    }

    /**
     * calculates total tax due by all familes
     * @return the amount of tax due by all the families who filed taxes this year
     */
    public float taxDue(){
        float totalTaxDue = 0.0f;
        for(int i = 0; i < families.length; i++){
            totalTaxDue += families[i].calculateTax();
        }
        return totalTaxDue;
    }

    /**
     * calculates the total amount of tax credits of all families
     * @return the total tax credits that all the families were elible for
     */
    public float taxCredits(){
        float totalTaxCredits = 0.0f;
        for(int i = 0; i < families.length; i++){
            totalTaxCredits += families[i].taxCredit();
        }
        return totalTaxCredits;
    }

    /**
     * returns number of tax returns filed
     * @return returns the number of tax returns filed this tax year
     */
    public int numberOfReturnsFiled(){
        return families.length;
    }

    /** 
     * returns number of people who filed taxes
     * @return the number of people who filed taxes
     */
    public int numberOfPersonsFiled(){
        int numPeople = 0;
        for(int i = 0; i < families.length; i++){
            numPeople += families[i].getNumAdults() + families[i].getNumChildren();
        }
        return numPeople;
    }

    /**
     * gets family at specified location
     * @param index represents a specific index of a family in the local storage
     * @return the family at the specied index
     */
    public Family getTaxReturn(int index){
        return families[index];
    }
    /**
     * getter method for families attribute
     * @return the local storage of families
     */
    public Family[] getFamilies(){
        return families;
    }
}