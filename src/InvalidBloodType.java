//Rezvan Nafee
//112936468
//Recitation 04

/**
 * This class represents a custom error class that will be used when a BloodType object that is being made
 * is not of blood type "O", "A", "B", or "AB".
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitaton Recitation 04
 *
 */
public class InvalidBloodType extends Exception {

    /**
     * Constructs the error by using a specified error message.
     * @param error
     */
    public InvalidBloodType (String error){
        super(error);
    }
}
