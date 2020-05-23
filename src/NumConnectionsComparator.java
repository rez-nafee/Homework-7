//Rezvan Nafee
//112936468
//Recitation 04

import java.util.Comparator;

/**
 * This class represents a Comparator that will be used to compare Patients and sort them
 * accordingly in ascending order based on the number of connections that the two Patient object has respectively.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Recitation 04
 */
public class NumConnectionsComparator implements Comparator<Patient> {

    /**
     * The compare method to be used to sort Patients and also compare the number of connections the Patient has. The
     * comparison will determine which Patient has more number of connections than the other Patient.
     * @param o1 The Patient object to compare.
     * @param o2 The Patient object to compare.
     * @return 1 if o1 has more connections than o2.
     *        -1 if o1 has less connections than o2.
     *         0 if o1 has the same amount of connections as o2.
     */
    public int compare(Patient o1, Patient o2) {
        if (o1.getConnections().size() == o2.getConnections().size())
            return 0;
        else if (o1.getConnections().size() < o2.getConnections().size())
            return -1;
        else
            return 1;
    }
}
