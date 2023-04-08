import java.io.File;
import java.util.Scanner;
/**
 * class that holds and conducts various tax related processes
 * @author Indigo Flynch
 * 
 */
public class Taxation {
    /**
     * socialSecurityRate is the tax rate of social security
     * socialSecurityIncomeLimit is the maximum amount of income that can be taxed by social security
     * medicareRate is the tax rate of medicare
     * adultBaseExemption is the base amount of exemption an adult is eligble for
     * childBaseExemption is the base amount of exemption a child is eligible for
     * medianIncomePerCapita is the represents the median income per capita
     */
    private static float socialSecurityRate = 12.4f;
    private static float socialSecurityIncomeLimit = 137700.0f;
    private static float medicareRate = 2.9f;
    private static float adultBaseExemption = 3000.0f;
    private static float childBaseExemption = 2000.0f;
    private static float medianIncomePerCapita = 31099.0f;

    /*
     * singleBracket is the income bracket for people filing single
     * marriedJointBracket is the income bracket for people filing married jointly
     * marriedSeparateBracket is the income bracket for people filing married separately
     * singleBracketRate is the tax rate for people filing single
     * marriedJointBracketRate is the tax rate for people filing married jointly
     * marriedSeparateBracketRate is the tax rate for people filing married separate
     */
    private static float[] singleBracket = 
    {10000.0f, 10000.01f, 40000.0f, 40000.01f, 80000.0f, 80000.01f, 160000.0f, 160000.01f};

    private static float[] marriedJointBracket = 
    {20000.0f, 20000.01f, 70000.0f, 70000.01f, 160000.0f, 160000.01f, 310000.0f, 310000.01f};

    private static float[] marriedSeparateBracket = 
    {12000.0f, 12000.01f, 44000.0f, 44000.01f, 88000.0f, 88000.01f, 170000.0f, 170000.01f};

    private static float[] singleBracketRate = {.10f, .12f, .22f, .24f, .32f};
    private static float[] marriedJointBracketRate = {.10f, .12f, .23f, .25f, .33f};
    private static float[] marriedSeparateBracketRate = { .10f, .12f, .24f, .26f, .35f};
    
    /**
     * getter method for social security rate
     * @return social security tax rate
     */
    public static float getSocialSecurityRate(){
        return(socialSecurityRate);
    }

    /**
     * getter method for medicate tax rate
     * @return medicare tax rate
     */
    public static float getMedicareRate(){
        return(medicareRate);
    }
    /**
     * getter method for socialSecurityIncomeLimit field
     * @return max amount of taxable income by social security
     */
    public static float getSocialSecurityIncomeLimit(){
        return(socialSecurityIncomeLimit);
    }

    /**
     * getter  method for adultBaseExemption field
     * @return the base exemption for an adult
     */
    public static float getAdultBaseExemption(){
        return(adultBaseExemption);
    }
    
    /**
     * getter method for ChildBaseExemption field
     * @return the base exemption for a child
     */
    public static float getChildBaseExemption(){
        return(childBaseExemption);
    }

    /**
     * getter method for medianIncomePerCapita field
     * @return median income per capita
     */
    public static float getMedianIncomePerCapita(){
        return medianIncomePerCapita;
    }
    
    /**
     * reads a text file with the values of the field for this class
     * and sets the respective fields
     * @param filename name of the file that the attributes values are set from
     */
    public static void loadParameters(String filename){
        try{
            File file = new File(filename);
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String field = "";
                float value = 0.0f;

                for(int i = 0; i < line.length(); i++){
                    if(line.charAt(i) == ' '){
                        field = line.substring(0, i);
                        value = Float.parseFloat(line.substring(i+1,line.length()));
                        break;
                    }
                }
                
                switch(field){
                    case("socialSecurityRate"):
                        socialSecurityRate = value;
                        break;
                    case("socialSecurityIncomeLimit"):
                        socialSecurityIncomeLimit = value;
                        break;
                    case("medicareRate"):
                        medicareRate = value;
                        break;
                    case("adultBaseExemption"):
                        adultBaseExemption = value;
                        break;
                    case("childBaseExemption"):
                        childBaseExemption = value;
                        break;
                    case("medianIncomePerCapita"):
                        medianIncomePerCapita = value;
                        break;
                }
            }
        }
        catch(Exception e){}
        
    }
    /**
     * calculates the max tax bracket a family falls in
     * @param object represents a family
     * @return the maximum tax bracket that a families income falls in
     */
    public static byte maxIncomeTaxBracket(Family object){

        float taxableIncome = object.getTaxableIncome();
        byte filingStatus = object.getFilingStatus();

        if(filingStatus == 1){

            if(taxableIncome <= 10000)
                return 1;
            else if(taxableIncome <=40000)
                return 2;
            else if(taxableIncome <= 80000)
                return 3;
            else if(taxableIncome <= 160000)
                return 4;
            else if(taxableIncome > 160000)
                return 5;
        }

        else if (filingStatus == 2){
            if(taxableIncome <= 20000)
                return 1;
            else if(taxableIncome <= 70000)
                return 2;
            else if(taxableIncome <= 160000)
                return 3;
            else if(taxableIncome <= 310000)
                return 4;
            else if (taxableIncome > 310000)
                return 5;
        }

        else if(filingStatus == 3){
            if(taxableIncome <= 12000)
                return 1;
            else if(taxableIncome <= 44000)
                return 2;
            else if(taxableIncome <= 88000)
                return 3;
            else if(taxableIncome <= 170000)
                return 4;
            else if(taxableIncome > 170000)
                return 5;
        }
        return 0;
    }
    /**
     * returns the number of tax brackets
     * @return the number of tax brackets
     */
    public static byte getNumTaxBrackets(){
        return 5;  
    }

    /**
     * calculates the a portion of a family's taxable income based upon the specified tax bracket
     * @param object represents a family
     * @param b represent a tax bracket
     * @return the portion of a family's taxable income that fall into the specified tax bracket (b)
     */

    public static float bracketIncome(Family object, byte b){
        byte bracket = b;
        float taxableIncome = object.getTaxableIncome();
        int filingStatus = object.getFilingStatus();

        if(bracket == 1){
            if(filingStatus == 1){
                if(taxableIncome >= singleBracket[0])
                    return singleBracket[0];
                else
                    return taxableIncome;
            }

            else if(filingStatus == 2)
                if(taxableIncome >= marriedJointBracket[0])
                    return marriedJointBracket[0];
                else
                    return taxableIncome;

            else if(filingStatus == 3)
                if(taxableIncome >= marriedSeparateBracket[0])
                    return marriedSeparateBracket[0];
                else
                    return(taxableIncome);
            }

        else if(bracket == 2){

            if(filingStatus == 1){
                if(taxableIncome >= singleBracket[2])
                    return singleBracket[2] - singleBracket[1];
                else
                    return taxableIncome - singleBracket[1];    
            }

            else if(filingStatus == 2){
                    if(taxableIncome >= marriedJointBracket[2])
                        return marriedJointBracket[2] - marriedJointBracket[1];
                    else
                        return taxableIncome - marriedJointBracket[1];
            }

            else if(filingStatus == 3){
                if(taxableIncome >= marriedSeparateBracket[2])
                    return marriedSeparateBracket[2] - marriedSeparateBracket[1];
                else
                    return taxableIncome - marriedSeparateBracket[1];
            }
        }

        else if(bracket == 3){
            if(filingStatus == 1){
                if(taxableIncome >= singleBracket[4])
                    return singleBracket[4] - singleBracket[3];
                else
                    return taxableIncome - singleBracket[3];
            }

            else if(filingStatus == 2){
                if(taxableIncome >= marriedJointBracket[4])
                    return marriedJointBracket[4] - marriedJointBracket[3];
                else
                    return taxableIncome - marriedJointBracket[3];
            }

            else if(filingStatus == 3){
                if(taxableIncome >= marriedSeparateBracket[4])
                    return marriedSeparateBracket[4] - marriedSeparateBracket[3];
                else
                    return taxableIncome - marriedSeparateBracket[3];
            }
        }

        else if(bracket == 4){

            if(filingStatus == 1){
                if(taxableIncome >= singleBracket[6])
                    return singleBracket[6] - singleBracket[5];
                else
                    return taxableIncome - singleBracket[5];
            }

            else if(filingStatus == 2){
                if(taxableIncome >= marriedJointBracket[6])
                    return marriedJointBracket[6] - marriedJointBracket[5];
                else
                    return taxableIncome - marriedJointBracket[5];
            }

            else if(filingStatus == 3){
                if(taxableIncome >= marriedSeparateBracket[6])
                    return marriedSeparateBracket[6] - marriedSeparateBracket[5];
                else
                    return taxableIncome - marriedSeparateBracket[5];
            }
        }

        else if(bracket == 5){
            if(filingStatus == 1){
                if(taxableIncome > singleBracket[7])
                    return taxableIncome - singleBracket[7];
                else
                    return taxableIncome;
            }

            else if(filingStatus == 2)
                if(taxableIncome > marriedJointBracket[7])
                    return taxableIncome - marriedJointBracket[7];
                else
                    return taxableIncome;

            else if(filingStatus == 3)
                if(taxableIncome > marriedSeparateBracket[7])
                    return taxableIncome - marriedSeparateBracket[7];
                else
                    return taxableIncome;
        }
        return 0f;
    }

    /**
     * returns tax bracket rate associated with specified parameters
     * @param b represents a specific tax bracket
     * @param f represents the filing status of a family
     * @return returns the tax bracket rate associated with the specified tax bracket (b)
     */
    public static float bracketTaxRate(byte b, byte f){
        byte bracket = b;
        byte filingStatus = f;

        switch(bracket){
            case(1):
                if(filingStatus == 1)
                    return singleBracketRate[0];
                else if(filingStatus == 2)
                    return marriedJointBracketRate[0];
                else if(filingStatus == 3)
                    return marriedSeparateBracketRate[0];
            
            case(2):
                if(filingStatus == 1)
                    return singleBracketRate[1];
                else if(filingStatus == 2)
                    return marriedJointBracketRate[1];
                else if(filingStatus == 3)
                    return marriedSeparateBracketRate[1];

            case(3):
                if(filingStatus == 1)
                    return singleBracketRate[2];
                else if(filingStatus == 2)
                    return marriedJointBracketRate[2];
                else if(filingStatus == 3)
                    return marriedSeparateBracketRate[2];
            
            case(4):
                if(filingStatus == 1)
                    return singleBracketRate[3];
                else if(filingStatus == 2)
                    return marriedJointBracketRate[3];
                else if(filingStatus == 3)
                    return marriedSeparateBracketRate[3];
            
            case(5):
                if(filingStatus == 1)
                    return singleBracketRate[4];
                else if(filingStatus == 2)
                    return marriedJointBracketRate[4];
                else if(filingStatus == 3)
                    return marriedSeparateBracketRate[4];
        }
        return(0.0f);
    }
}