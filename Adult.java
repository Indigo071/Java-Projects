/**
 * a class that represents an Adult
 * @author Indigo Flynch
 * 
 */
public class Adult extends Person {
    /**
     * employer field represents the adult's employer
     * deduction field stores the amount of deduction the adult is eligible for
     */
    private String employer;
    private float deduction;

    /**
     * Constructor that sets attributes
     * @param name name of the adult
     * @param birthday birthday of the adult
     * @param ssn social security number of the adult
     * @param grossIncome grossIncome of the adult
     * @param employer employer name of the adult
     */
    public Adult(String name, String birthday, String ssn, float grossIncome, String employer){
        super();
        super.setName(name);
        super.setBirthday(birthday);
        super.setSSN(ssn);
        super.setGrossIncome(grossIncome);
        setEmployer(employer);
    }
    
    /**
     * setter method for employer field
     * @param employer employer of the adult
     * @return returns true if employer is vaild otherwise return false
     */
    public boolean setEmployer(String employer){
        if(employer.equals(null))
            return false;
        else
            this.employer = employer;
            return true;
    }

    /**
     * Overrides super class method to add the gross income of adult to the toString method
     */
    @Override
    public String toString(){
        float grossIncome = super.getGrossIncome();
        return(super.toString()+" "+ String.format("%.2f", grossIncome));
    }

    /**
     * calculate adjusted income of adult
     * @return returns the adjusted income of the adult after social security and 
     * medicare have been payed by employee and employer
     */

    public float adjustedIncome(){
        float adjustedIncome = 0.0f;
        float income = getGrossIncome();
        float socialSecurityIncomeLimit = Taxation.getSocialSecurityIncomeLimit();

        //if income exceeds the amount of taxable social security income
        if(income > socialSecurityIncomeLimit){
            float socialSecurityTax = socialSecurityIncomeLimit * (Taxation.getSocialSecurityRate() / 100f);
            float medicareTax = getGrossIncome() * (Taxation.getMedicareRate() / 100f);
            float totalTax = medicareTax + socialSecurityTax;
            float employeeTax = totalTax / 2f;
            adjustedIncome = income - employeeTax;
        }

        else{
            float socialSecurityTax = getGrossIncome() * (Taxation.getSocialSecurityRate() / 100f);
            float medicareTax = getGrossIncome() * (Taxation.getMedicareRate() / 100f);
            float totalTax = (medicareTax /2 + socialSecurityTax / 2);
            float employeeTax = totalTax;
            adjustedIncome = income - employeeTax;
        }

        return adjustedIncome;
    }

    /**
     * caculates the amount of tax withheld from employees paycheck
     * @return the tax withheld from adults paycheck by the employer
     */

    public float taxWithheld(){

        float income = getGrossIncome();
        float amountWitheld = 0.0f;
        int count = 0;
        while(count < 3){
            if(count == 0){
                if(income > 50000f){
                    amountWitheld += 50000f * .10f;
                    income -= 50000f;
                }
                else{
                    amountWitheld += income * .10f;
                    return(amountWitheld);
                }
            }
            else if(count == 1){
                if(income > 100000.0f){
                    amountWitheld += 100000.0f *.15f;
                    income -= 100000f;
                }

                else{
                    amountWitheld += income * .15;
                    return(amountWitheld);
                }
            } 

            else if(count == 2){
                amountWitheld += income * .2;
                return(amountWitheld);
            }
            count += 1;
       }
       return amountWitheld;
    }
    /**
     * @param object represents a family 
     * @return the amount of eligble deduction an adult is eligible for
     */
    @Override
    public float deduction(Family object){
        float income  = adjustedIncome();
        float adultBaseExemption = Taxation.getAdultBaseExemption();

        if(object.getFilingStatus() == 1){
            adultBaseExemption *= 2.0f;
        }

        if(income > 100000f){
            if(income < 160000f){
                int numThousands = (int)(income - 100000) / 1000;
                float percent = (.5f * numThousands)/100.0f;
                adultBaseExemption -= (adultBaseExemption * percent);
            }
            else{
                adultBaseExemption -= (adultBaseExemption * .30f);
            }
        }
        if(income < adultBaseExemption){
            this.deduction = income;
            return income;
        }
        this.deduction = adultBaseExemption;
        return adultBaseExemption;
    }

    /**
     * calculates amount of deduction adult is eligible for
     * @return the amount of deduction adult is eligible for
     */
    public float getDeduction(){
        return deduction;
    }

    /**
     * getter method for employer field
     * @return the employer of the adult
     */
    public String getEmployer(){
        return employer;
    }
}
