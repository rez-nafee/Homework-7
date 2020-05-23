//Rezvan Nafee
//112936469
//Recitation 04

/**
 * This class represents a custom error that is thrown when the number of Patients that is being added to either to the
 * donors Patient list or recipient Patient list in the TransplantGraph has reached it's maximum capacity of 100
 * patients.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Recitation 04
 */
public class MaxPatientsError extends Exception {

    /**
     * Constructs the error by using a specified error message.
     * @param error
     */
    public MaxPatientsError(String error){
        super(error);
    }

}
