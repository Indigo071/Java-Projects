/**
 * Class that represents a family
 * @author Indigo Flynch
 *
 */
public class Family{
    /**
     * numMembers is the number of family members
     * filingStatus represetns whether they are filing single,
     * married jointly, or married filing separate
     * members is a Person arrray of the family members
     */
    private byte numMembers;
    private byte filingStatus;
    private Person[] members;

    /**
     * Constructor for a family object
     * @param numMembers number of family members
     * @param filingStatus filing stauts for tax filing
     */

    public Family(byte numMembers, byte filingStatus){
        this.numMembers = numMembers;
        this.filingStatus = filingStatus;
        this.members = new Person[numMembers];
    }

    /**
     * calculates and returns the amount of tax withheld from a family
     * @return calculates and returns the taxwithheld of a family
     */
    public float getTaxWithheld(){
        float taxWithheld = 0.0f;

        for(int i = 0; i < members.length; i++){
            if(members[i] instanceof Adult){
                Adult a = (Adult) members[i];
                taxWithheld += a.taxWithheld();
            }
        }
        return taxWithheld;
    }

    /**
     * adds a family member to the family member array
     * @param person represents a family member to be added to family
     * 
     */
    public void addMember(Person person){
        for(int i = 0; i < members.length; i++){
            if(this.members[i] == null){
                this.members[i] = person;
                break;
            }
        }
    }

    /**
     * gets the amount of adults in a family
     * @return returns the number of adults in a family
     */
    public byte getNumAdults(){
        byte numAdults = 0;
        for(int i = 0; i < members.length; i++){
            if(members[i] instanceof Adult){
                numAdults += 1;
            }
        }
        return numAdults;
    }
    /**
     * gets the amount of children in a family
     * @return returns the number of children in a family 
     */
    public byte getNumChildren(){
        byte numChildren = 0;
        for(int i = 0; i < members.length; i++){
            if(members[i] instanceof Child){
                numChildren += 1;
            }
        }
        return numChildren;
    }

    /**
     * getter method for the filing status field
     * @return returns the number associated with the filing status
     */
    public byte getFilingStatus(){
        return filingStatus;
    }

    /**
     * calculates and returns the taxable income of a family
     * @return returns the total taxable income of a family
     */
    public float getTaxableIncome(){
        float totalTaxableIncome = 0.0f;
        for(int i = 0; i < members.length; i ++){
            if(members[i] instanceof Adult){
                Adult a = (Adult) members[i];
                totalTaxableIncome += a.adjustedIncome() - a.deduction(this);
            }

            else if(members[i] instanceof Child){
                Child c = (Child) members[i];
                totalTaxableIncome += c.getGrossIncome() - c.deduction(this);
            }
        }
        return totalTaxableIncome;
    }
    
    /**
     * calculates and returns the tax credit for a family
     * @return returns the total tax credit a family is eligible for
     */

    public float taxCredit(){
        float medianIncomePerCapita = Taxation.getMedianIncomePerCapita() / 2.0f;
        float taxCredit = 0.0f;

        if(getTaxableIncome() <= medianIncomePerCapita){
            int numThousands = (int)getTaxableIncome() / 1000;
            taxCredit += numThousands * 30.0f;
            for(int i = 0; i < members.length; i++){
                if(members[i] instanceof Child){
                    Child c = (Child) members[i];
                    if(c.getTuition() < 1000f){
                        taxCredit += c.getTuition();
                    }
                    else
                        taxCredit += 1000f;
                }
            }
        }
        else
            return(0.0f);
        if(taxCredit > 2000.0f)
            taxCredit = 2000.0f;
        if(getPreCreditTax() < taxCredit)
            return getPreCreditTax();
        if(filingStatus == 3)
            taxCredit /= 2.0f;
        return(taxCredit);
    }
    
    /**
     * calculates and returns the amount of pre tax credit for a family
     * @return returns the amount of tax before tax credit is applied
     */
    public float getPreCreditTax(){
        float taxableIncome = this.getTaxableIncome();
        int maxBracket = Taxation.maxIncomeTaxBracket(this);
        float precreditTax = 0.0f;

        for(int i = 0; i < maxBracket; i++){
            precreditTax += Taxation.bracketIncome(this, (byte)(i + 1)) * Taxation.bracketTaxRate((byte)(i + 1), this.filingStatus);
        }
        return(precreditTax);
    }

    /**
     * calculates the amount of tax to be payed by a family
     * @return returns the amount of tax that is owed by the family
     */
    public float calculateTax(){
        
        float taxableIncome = this.getTaxableIncome();
        int maxBracket = Taxation.maxIncomeTaxBracket(this);
        float precreditTax = getPreCreditTax();
        int index = 0;

        float taxWitheld = getTaxWithheld();
        float tax = precreditTax;
        tax = tax - taxCredit();
        tax -= taxWitheld;

        return tax;
    }
    /**
     * getter method for members field
     * @return returns the family member array
     */
    public Person[] getMembers(){
        return members;
    }
}