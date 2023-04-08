/**
 * class that represents an individual person
 * @author Indigo Flynch
 * 
 */
public class Person{
    
    /**
     * id which represents unique id each time person is created
     * name represents the name of the person
     * birthday is the birthday of the person
     * ssn is the social security number of the person
     * gross income is the gross income of the person
     */
    private static int id_instance = 0;
    private int id;
    private String name;
    private String birthday;
    private String ssn;
    private float grossIncome;
    /**
     * constructor for Person class
     * creates unique id for each instance of the class
     */

    public Person(){
       id_instance += 1;
       this.id = id_instance;
    }

    /**
     * validates name attribute
     * @param name name for the Person
     * @return true if valid or false if invalid
     * 
     */

    public boolean setName(String name){
        //return true for valid input
        //false if invalid
        boolean flag = true;
        for(int i = 0; i < name.length();i++){

            //space ascii value check
            if((int)name.charAt(i) == 32){
                continue;
            }

            //uppercase ascii value check
            else if((int)name.charAt(i) >= 65 && (int)name.charAt(i) <= 90 ){
                continue;
            }

            //lowercase ascii value check
            else if((int)name.charAt(i) >= 97 && (int)name.charAt(i) <= 122){

            }

            else{
                flag = false;
                break;
            }
        }

        if(flag != true){
            return false;
        }

        this.name = name;
        return true;
    }
    /**
     * validates birthday attribute
     * @param birthday birthday for Person
     * @return true if birthday if valid otherwise returns false
     */

    public boolean setBirthday(String birthday){
        //true for valid input
        //false for invalid
        for(int i = 0; i<birthday.length(); i++){
            if((int)birthday.charAt(i) == 47){
                continue;
            }

            else if((int)birthday.charAt(i) >= 48 && (int)birthday.charAt(i)<=57){
                continue;
            }

            else{
                return false;
            }
        }
        this.birthday = birthday;
        return true;
    }
    /**
     * validates the social security number attribute
     * @param ssn represents the social security number of the instance
     * @return true if valid otherwise false
     */
    public boolean setSSN(String ssn){
        if(ssn.length() != 11)
            return false;
        for(int i = 0; i < ssn.length(); i++){
            
            //first three digits
            if(i >= 0 && i <= 2){
                if((int)ssn.charAt(i) >= 48 && (int)ssn.charAt(i) <= 57){
                    continue;
                }
                else{
                    return false;
                }
            }

            //dashes in between numbers
            else if(i == 3 || i == 6 ){
                if((int)ssn.charAt(i) == 45){
                    continue;
                }
                else{
                    return false;
                }
            }

            //two digits in the middle
            else if(i>=4 && i<=5){
                if((int)ssn.charAt(i) >= 48 && (int)ssn.charAt(i) <= 57){
                    continue;
                }
                else{
                    return false;
                }
            }
            //last four digits
            else if( i>= 7 && i<= 10){
                if((int)ssn.charAt(i) >= 48 && (int)ssn.charAt(i) <= 57){
                    continue;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        this.ssn = ssn;
        return true;
    }
    /**
     * validates the gross income is nonegative
     * @param grossIncome represents the gross income of the instance
     * @return true if valid else return false
     */
    public boolean setGrossIncome(float grossIncome){
        if(grossIncome < 0){
            return false;
        }
        this.grossIncome = grossIncome;
        return true;
    }

   /**
    * getter method for gross income
    * @return returns the gross income of the person
    */
    public float getGrossIncome(){
        return grossIncome;
    }

    /**
     * getter method for person id
     * @return returns the id of the person
     */
    public int getId(){
        return id;
    }

    /**
     * masks the first 5 ssn numbers and the date and month of birthday
     */
    public String toString(){
        String maskedBirthday = "";
        String maskedSSN = "";

        //create maskedSSN
        for(int i = 0; i < ssn.length();i++){
            if(i >= 0  && i <= 2 || i>= 4 && i<=5){
                maskedSSN += "x"; 
            }
            else if(i == 3 || i == 6){
                maskedSSN += "-";
            }
            else{
                maskedSSN += ssn.charAt(i);
            }
        }

        //create maskedBirthday
        for(int i = 0; i < birthday.length();i++){
            if(i <= 3){
                maskedBirthday += birthday.charAt(i);
            }
            else if(i == 4 || i == 7){
                maskedBirthday += "/";
            }
            else{
                maskedBirthday += "*";
            }
        }
        return name + " "+maskedSSN+" "+maskedBirthday;

    }
    /**
     * method that will be overridden by subclasses
     * @param object represents a family
     * @return a float the represents the deduction
     */
    public float deduction(Family object){
        return(0.0f);
    }

    public static void main(String[] args) {
        Adult a1 = new Adult("name1", "1232/02/22", "987-65-4320", 0.00f, "GMU");
        System.out.println(a1.getId());
        Adult a2 = new Adult("name2", "1332/02/22", "987-65-4321", 1234.56f, "GMU");
        System.out.println(a2.getId());
        
    }
}