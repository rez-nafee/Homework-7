//Rezvan Nafee
//112936468
//Recitation 04

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a Patient that will be used in the system for organ donors to be matched with transplant
 * recipients. In other words there will be two types of Patients, a recipient and a donor. A Patient that is
 * a recipient will be receiving the organ transplant and donor will providing the organ for transplanting.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Recitation 04
 */
public class Patient implements Comparable, Serializable {
    private String name;
    private String organ;
    private int age;
    private BloodType bloodType;
    private int ID;
    private boolean isDonor;
    private ArrayList<Patient> connections = new ArrayList<>();

    /**
     * Constructor that creates a default Patient.
     */
    public Patient() {
    }

    /**
     * Constructor that creates a Patient with information such as the Patient's name, organ, BloodType, and ID#.
     *
     * @param name      The name of the Patient
     * @param organ     The organ that the Patient is donating/receiving.
     * @param age       The age of the Patient.
     * @param bloodType The BloodType of the Patient.
     * @param ID        The ID# of the Patient.
     * @throws InvalidPatient Thrown when the provided information is invalid such as the age being less than 0 and
     *                        the ID# of the Patient is negative.
     */
    public Patient(String name, String organ, int age, BloodType bloodType, int ID) throws InvalidPatient {
        if (age <= 0) {
            throw new InvalidPatient("Adding Patient Error: Invalid Patient information given!");
        }
        if (ID < 0) {
            throw new InvalidPatient("Adding Patient Error: Invalid Patient information given!");
        }
        this.name = name;
        this.organ = organ;
        this.age = age;
        this.bloodType = bloodType;
        this.ID = ID;
    }

    /**
     * Constructor that created a Patient's with information such as the Patient's name, organ, age, and BloodType.
     *
     * @param name      The name of the Patient.
     * @param organ     The organ that the Patient is donating/receiving.
     * @param age       The age of the Patient.
     * @param bloodType The BloodType of the Patient.
     * @throws InvalidPatient Thrown when the provided information is invalid such as the age being less than 0.
     */
    public Patient(String name, String organ, int age, BloodType bloodType) throws InvalidPatient {
        if (age < 0) {
            throw new InvalidPatient("Adding Patient Error: Invalid Patient information given!");
        }
        this.name = name;
        this.organ = organ;
        this.age = age;
        this.bloodType = bloodType;
    }

    /**
     * Return the BloodType of the Patient.
     *
     * @return The BloodType of the Patient.
     */
    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * Returns true if the whether or not if the Patient is a donor. In other words, if true, then the Patient is a
     * donor. If false, then the Patient is a recipient.
     *
     * @return Returns true if the Patient is a donor.
     * Returns false if the Patient is a recipient.
     */
    public boolean isDonor() {
        return isDonor;
    }

    /**
     * Returns the age of the Patient.
     *
     * @return The age of the Patient.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the ID# of the Patient.
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the name of the Patient.
     *
     * @return The name of the Patient.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the organ of the Patient.
     *
     * @return The orgran of the Patient.
     */
    public String getOrgan() {
        return organ;
    }

    /**
     * Sets the age of the Patient to a specified age.
     *
     * @param age The specified age of the Patient.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the name of the Patient to a specified name.
     *
     * @param name The specified name of the Patient.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the BloodType of the Patient of to specified BloodType.
     *
     * @param bloodType The specified BloodType.
     */
    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Sets the condition of whether or not if the Patient is a donor or recipient.
     *
     * @param donor The boolean value of whether or not if the Patient is a donor or recipient.
     */
    public void setDonor(boolean donor) {
        isDonor = donor;
    }

    /**
     * Compares an Object that will be casted as a Person and be used to sort Patients in ascending order based of the
     * ID# of the Patient.
     *
     * @param o The Object to be compared.
     * @return 0 if the Patient objects have the same ID
     * -1 if the current Patient's ID is greater than the Patient being compared.
     * 1 if the current Patient's ID is less than the Patient being compared.
     */
    public int compareTo(Object o) {
        if (this.getID() == ((Patient) o).getID()) {
            return 0;
        } else if (this.getID() > ((Patient) o).getID()) {
            return 1;
        } else
            return -1;
    }

    /**
     * Sets the ID number of the Patient to a specified ID#.
     *
     * @param ID The specified ID#.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Sets the organ of the Patient to a specified organ.
     *
     * @param organ The specified organ.
     */
    public void setOrgan(String organ) {
        this.organ = organ;
    }

    /**
     * Determines whether or not if an Object is the same as the current Patient.
     *
     * @param o The Object to be evaluated.
     * @return Returns true if the ID#s of the Object and the current Patient object is the same. If not, then it
     * returns false.
     */
    public boolean equals(Object o) {
        return this.getName().equalsIgnoreCase(((Patient) o).getName()) && this.ID == ((Patient) o).getID() &&
                this.getOrgan().equalsIgnoreCase(((Patient) o).getName()) &&
                this.getBloodType().getBloodType().equalsIgnoreCase(((Patient) o).getBloodType().getBloodType()) &&
                this.getAge() == ((Patient) o).getAge();
    }

    /**
     * Returns the connections that the Patient has with other Patients within the system.
     * @return The connections that Patient has with other Patients.
     */
    public ArrayList<Patient> getConnections() {
        return connections;
    }

    /**
     * Sets the connections that Patient has to specified list of Patients.
     * @param connections The specified list of Patients.
     */
    public void setConnections(ArrayList<Patient> connections) {
        this.connections = connections;
    }

    /**
     * Returns the String representation of the Patient object including information regarding the Patient's name,
     * organ, ID#, age, organ, BloodType, and the related Patients.
     * @return The String representation of the Patient object.
     */
    @Override
    public String toString() {
        return String.format("%-8s|%-20s|%-5s|%-18s|%-20s|%-20s", String.valueOf(ID), name, String.valueOf(age),
                organ, bloodType.getBloodType(), listOfConnections());
    }

    /**
     * A helper method the develops the list of connections that current Patient object has with other Patients in the
     * system to be used in the toString method.
     * @return The list of connections that the current Patient object has in a String format.
     */
    public String listOfConnections() {
        String list = "";
        for (int i = 0; i < connections.size(); i++) {
            if (i == connections.size() - 1) {
                list += connections.get(i).ID;
            } else {
                list += connections.get(i).ID;
                list += ", ";
            }
        }
        return list;
    }
}
