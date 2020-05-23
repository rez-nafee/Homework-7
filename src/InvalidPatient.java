//Rezvan Nafee
//112936468
//Recitation 04

/**
 * This is a custom error class that is thrown when the creation of a Patient object contains invalid information
 * such as invalid age and/or invalid ID#.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Recitation 04
 */
public class InvalidPatient extends Exception {

    /**
     * Constructs the error by using a specified error message.
     * @param error
     */
    public InvalidPatient (String error){
        super(error);
    }
}
