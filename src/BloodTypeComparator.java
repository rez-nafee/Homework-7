//Rezvan Nafee
//112936468
//Recitation 04

import java.util.Comparator;


/**
 * This class represents a Comparator that will be used to compare Object that are of type Patient and sort them
 * accordingly in alphabetical order based on the BloodType of two Patient object has respectively.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Recitation 04
 */
public class BloodTypeComparator implements Comparator<Patient>{

    /**
     * The compare method to be used to sort Patients and also compare the BloodTypes of the patient. The comparison
     * will determine which BloodType from which Patient is lexicographically greater than the other.
     * @param o1 The Patient object to compare.
     * @param o2 The Patient object to compare.
     * @return 1 if o1's BloodType is lexicographically greater than o2's BloodType.
     *        -1 if o1's BloodType is lexicographically less than o2's BloodType.
     *         0 if o1's BloodType is lexicographically the same as o2's BloodType.
     */
    public int compare(Patient o1, Patient o2) {
        if (o1.getBloodType().getBloodType().equals(o2.getBloodType().getBloodType())) {
            return 0;
        } else if (o1.getBloodType().getBloodType().compareTo(o2.getBloodType()
                .getBloodType()) > 0) {
            return 1;
        } else
            return -1;
    }
}
