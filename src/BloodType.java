//Rezvan Nafee
//112936468
//Recitation 04

import java.io.Serializable;

/**
 * This class represent a Patient's blood type that will be used to determine whether a recipient is compatible with
 * a donor.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Recitation 04
 */
public class BloodType implements Serializable {
    private String bloodType;

    /**
     * No-arg constructor that creates an empty BloodType for a patient.
     */
    public BloodType() {
    }

    /**
     * Construct that creates a BloodType with a specified String input and determines whether it is a valid type or
     * not. If it is not a valid BloodType, then the method will thrown an exception.
     * @param bloodType The Patient's BloodType.
     * @throws InvalidBloodType Thrown if the Patient's BloodType is invalid.
     */
    public BloodType(String bloodType) throws InvalidBloodType {
        switch (bloodType) {
            case ("A"):
            case ("AB"):
            case ("B"):
            case ("O"):
                this.bloodType = bloodType;
                break;
            default:
                throw new InvalidBloodType("\nInvalid Blood Type Error: The provided blood type is invalid!");
        }
    }

    /**
     * Sets the blood type of the of object to a specified blood type.
     * @param bloodType The specified blood type.
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Returns the blood type.
     * @return The blood type.
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * Determines whether or not if the the recipient's BloodType is compatible with a donor's BloodType. If the
     * recipient's BloodType is compatible with the donor's BloodType, then the method returns true. If it is not
     * compatible, the method returns false.
     * @param recipient The recipient's BloodType
     * @param donor The donor's BloodType
     * @return Returns true if the two BloodTypes are compatible.
     *         Returns false if the two BloodTypes are not compatible.
     */
    public static boolean isCompatible(BloodType recipient, BloodType donor) {
        switch (recipient.getBloodType()) {
            case ("O"):
                switch (donor.getBloodType()) {
                    case ("O"):
                        return true;
                    case ("A"):
                        return false;
                    case ("AB"):
                        return  false;
                    case ("B"):
                        return false;
                }
                break;
            case ("A"):
                switch (donor.getBloodType()) {
                    case ("O"):
                        return true;
                    case ("A"):
                        return true;
                    case ("B"):
                        return false;
                    case ("AB"):
                        return false;
                }
                break;
            case ("B"):
                switch (donor.getBloodType()) {
                    case ("O"):
                        return true;
                    case ("A"):
                        return false;
                    case ("B"):
                        return true;
                    case ("AB"):
                        return false;
                }
                break;
            case ("AB"):
                switch (donor.getBloodType()) {
                    case ("O"):
                        return true;
                    case ("A"):
                        return true;
                    case ("B"):
                        return true;
                    case ("AB"):
                        return true;
                }
                break;
        }
        return false;
    }
}
