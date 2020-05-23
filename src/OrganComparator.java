//Rezvan Nafee
//112936468
//Recitation 04

import java.util.Comparator;

/**
 * This class represents a Comparator that will be used to compare Object that are of type Patient and sort them
 * accordingly in alphabetical order based on the organ of two Patient object has respectively.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Recitation 04
 */
public class OrganComparator implements Comparator<Patient> {

    /**
     * The compare method to be used to sort Patients and also compare the BloodTypes of the patient. The comparison
     * will determine which organ from which Patient is lexicographically greater than the other.
     * @param o1 The Patient to compare.
     * @param o2 The Patient object to compare.
     * @return 1 if o1's organ is lexicographically greater than o2's organ.
     *        -1 if o1's organ is lexicographically less than o2's organ.
     *         0 if o1 is lexicographically the same as o2's organ.
     */
    public int compare(Patient o1, Patient o2) {
        if (o1.getOrgan().equals(o2.getOrgan())) {
            return 0;
        } else if (o1.getOrgan().compareTo(o2.getOrgan()) > 0) {
            return 1;
        } else
            return -1;
    }
}
