/**
 * class that performs anaylysis 
 * @author Indigo Flynch
 * 
 */

public class Analytics {

    /**
     * povertyThreshold represents the poverty threshold for the tax year
     * taxInfo is an object that is used to for caluclating statistics
     */
    private static float povertyThreshold = 27750f;
    private TaxYear taxInfo;

    /**
     * Constructor that sets taxInfo field
     * @param object sets a tax year object that can be used to conduct statistical analysis
     */
    public Analytics(TaxYear object){
        taxInfo = object;
    }

    /**
     * sets the poverty threshold for the year
     * @param povertyThreshold represents the poverty threshold for the year
     * 
     */
    public void setPovertyThreshold(float povertyThreshold){
        this.povertyThreshold = povertyThreshold;
    }

    /**
     * calculates average family income
     * @return the average family income for the tax year
     */
    public float averageFamilyIncome(){
        float avgIncome = 0.0f;

        for(int i = 0; i < taxInfo.getFamilies().length; i ++){
            avgIncome += taxInfo.getFamilies()[i].getTaxableIncome();
        }

        avgIncome = avgIncome / taxInfo.getFamilies().length;
        return avgIncome;
    } 

    /**
     * calculates average person income
     * @return the average income of a person for the tax year
     */

    public float averagePersonIncome(){
        float avgPersonIncome = 0.0f;
        int numPeople = 0;

        Family[] fam = taxInfo.getFamilies();

        for(int i = 0; i < fam.length; i++){
            for(int j = 0; j < fam[i].getMembers().length; i++){
                if(fam[i].getMembers()[j] instanceof Adult){
                    Adult a = (Adult) fam[i].getMembers()[j];
                    avgPersonIncome = a.adjustedIncome() - a.getDeduction();
                }

                else if(fam[i].getMembers()[j] instanceof Child){
                    Child c = (Child) fam[i].getMembers()[j];
                    avgPersonIncome = c.getGrossIncome() - c.getDeduction();
                }
                numPeople ++;
            }
        }
        avgPersonIncome /= numPeople;

        return avgPersonIncome;
    }

    /**
     * calculates maximum family income
     * @return the maximum family income for the year
     */
    public float maxFamilyIncome(){
        float maxFamilyIncome = 0.0f;

        for(int i = 0; i < taxInfo.getFamilies().length; i++){
            if(taxInfo.getFamilies()[i].getTaxableIncome() > maxFamilyIncome())
                maxFamilyIncome = taxInfo.getFamilies()[i].getTaxableIncome();
        }

        return maxFamilyIncome;
    }

    /**
     * calculates maximum person income
     * @return the maximum income made by a person this tax year
     */
    public float maxPersonIncome(){
        float max = 0.0f;
        Family[] fam = taxInfo.getFamilies();

        for(int i = 0; i < fam.length; i++){
            for(int j = 0; j < fam[i].getMembers().length; i++){
                if(fam[i].getMembers()[j] instanceof Adult){
                    Adult a = (Adult) fam[i].getMembers()[j];
                    if(a.adjustedIncome() - a.getDeduction() > max)
                        max = a.adjustedIncome() - a.getDeduction();
                }

                else if(fam[i].getMembers()[j] instanceof Child){
                    Child c = (Child) fam[i].getMembers()[j];
                    if(c.getGrossIncome() - c.getDeduction() > max)
                        max = c.getGrossIncome() - c.getDeduction();
                }
            }
        }
        return max;
    }
    /**
     * determines number of families below poverty limit
     * @return the number of families whose taxable income falls below the poverty threshold
     */
    public int familiesBelowPovertyLimit(){
        int familiesBelowLimit = 0;
        for(int i = 0; i < taxInfo.getFamilies().length; i++){
            if(taxInfo.getFamilies()[i].getTaxableIncome() < povertyThreshold)
                familiesBelowLimit ++;
        }

        return familiesBelowLimit;
    }
    /**
     * ranks families
     * @param object represents a family
     * @return the rank of the family based on taxable income
     */
    public int familyRank(Family object){

        Family[] families = taxInfo.getFamilies();
        Family[] newArray = new Family[families.length];
        for(int i = 0; i < families.length; i++){

            int count = 0;
            for(int j = 0; j < families.length; j ++){
                if(families[i].getTaxableIncome() < families[j].getTaxableIncome()){
                count += 1;
                }
            }
            newArray[families.length - 1 - count] = families[i];
            for(int k = 0; k < families.length; k++){
                if(object.equals(families[k]))
                    return k + 1;
            }
        }
        return 0; 
    }
}
