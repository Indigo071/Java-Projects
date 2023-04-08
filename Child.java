/**
 * class that represents a Child
 * @author Indigo Flynch
 * 
 */
public class Child extends Person {
    /**
     * school represents the name of the school the child attends
     * tution is the cost of attending the childs school
     * deduction is the amount of deduction a child is eligble for
     */
    private String school;
    private float tuition;
    private float deduction;
    /**
     * Constructor that calls upon super class methods and constructor to set fields in this class
     * also sets school, tuition, and deduction
     * @param name name of the child
     * @param birthday birthday of the child
     * @param ssn social security number of the child
     * @param grossIncome gross Income of the child
     * @param school school that the child is attending
     * @param tuition the cost of tuition for the childs school
     */
    public Child(String name, String birthday, String ssn, float grossIncome, String school, float tuition){
        super();
        if(super.setName(name) == true){
            super.setName(name);
        }

        if(super.setBirthday(birthday) == true){
            super.setBirthday(birthday);
        }

        if(super.setSSN(ssn) == true){
            super.setSSN(ssn);
        }

        if(super.setGrossIncome(grossIncome) == true){
            super.setGrossIncome(grossIncome);
        }
        setTuition(tuition);
        setSchool(school);
    }
    /**
     * setter method for tution field that performs validation
     * @param tuition repesents the cost of attending school
     * @return return true or false based upon wheter the tuiton value is valid
     */
    public boolean setTuition(float tuition){
        if(tuition < 0)
            return(false);
        else{
            this.tuition = tuition;
            return(true);
        }
    }
    /**
     * setter method for school field that also performs validation
     * @param school school that child is attending
     * @return true if school is valid input otherwise return false
     */
    public boolean setSchool(String school){
        if(school.equals(null))
            return(false);
        else{
            this.school = school;
            return(true);
        }
    }

    /**
     * overrides super class method and uses its method to print out information plus the school name
     */
    @Override
    public String toString(){
        return(super.toString() + " "+school);
    }

    /**
     * getter method for the tuition field
     * @return returns the tuition cost 
     */

    public float getTuition(){
        return(tuition);
    }
    
    /**
     * calculates the amount of deduction a chld is eligible for
     * @param object represents a family
     * @return amount of gross income that is exempt from taxation
     */
    @Override
    public float deduction(Family object){
        float baseExemption = Taxation.getChildBaseExemption();
        float reduction = 0f;
        float income = getGrossIncome();

        if(object.getNumChildren() > 2){
            for(int i = 0; i < object.getNumChildren() - 2; i++){
                reduction += 0.05f;
                if(reduction == 0.5f)
                    break;
            }
            baseExemption -= baseExemption * reduction;
        }

        if(income <= baseExemption){
            this.deduction = income;
            return(income);
        }

        this.deduction = baseExemption;

        return(baseExemption);
    }
    /**
     * getter method for deduction field
     * @return returns the calcualted deduction
     */
    public float getDeduction(){
        return(deduction);
    }
}