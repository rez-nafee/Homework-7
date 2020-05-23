//Rezvan Nafee
//112936468
//Recitation 04

import java.io.*;
import java.util.*;

/**
 * This class represents a Graph data structure that will be used to create an adjacency matrix to represent a finite
 * graph. The matrix will be used to determine compatible recipients and donors based on the BloodType of the two and
 * the organ that is needed matches the organ donated.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Recitation 04
 */
public class TransplantGraph implements Serializable {
    private ArrayList<Patient> donors;
    private ArrayList<Patient> recipients;
    public static final int MAX_PATIENTS = 100;
    private boolean[][] connections;

    /**
     * Constructs with no-args to created a TransplantGraph object to manage the recipients and donors Patients in the
     * system.
     */
    public TransplantGraph() {
        donors = new ArrayList<>();
        recipients = new ArrayList<>();
    }

    /**
     * Returns the list of donor Patients currently in the system.
     * @return The list of donor Patients.
     */
    public ArrayList<Patient> getDonors() {
        return donors;
    }

    /**
     * Returns the list of recipient Patients currently in the system.
     * @return The list of recipient Patients.
     */
    public ArrayList<Patient> getRecipients() {
        return recipients;
    }

    /**
     * Returns the adjacency matrix of TransplantGraph.
     * @return The adjacency matrix.
     */
    public boolean[][] getConnections() {
        return connections;
    }

    /**
     * Sets the adjacency matrix of TransPlantGraph to a specified adjacency matrix.
     * @param connections The specified adjacency matrix.
     */
    public void setConnections(boolean[][] connections) {
        this.connections = connections;
    }

    /**
     * Sets the list of donor Patients to a specified list of donor Patients.
     * @param donors The specified list of donor Patients.
     */
    public void setDonors(ArrayList<Patient> donors) {
        this.donors = donors;
    }

    /**
     * Sets the list of recipient Patients to a specifies list of recipient Patients.
     * @param recipients The specified list of recipient Patients.
     */
    public void setRecipients(ArrayList<Patient> recipients) {
        this.recipients = recipients;
    }

    /**
     * Adds a donor Patient to the list of donor Patients and updates the adjacency matrix accordingly. If there is no
     * room for the donor Patient to be added, the method throws an exception.
     * @param patient The donor Patient to be added.
     * @throws MaxPatientsError Thrown when there is already 100 donor Patients found within the list of donor Patients,
     * signifying there is no room for the donor Patient.
     */
    public void addDonor(Patient patient) throws MaxPatientsError {
        if (donors.size() == MAX_PATIENTS)
            throw new MaxPatientsError("\nAdding Donor Error: The number of Patients on the donors list " +
                    "is currently full!");
        else {
            patient.setDonor(true);
            donors.add(patient);
            updateTable();
        }
    }

    /**
     * Adds a recipient Patient to the list of recipient Patients and updated the adjacency matrix accordingly. If there
     * is no room for the recipient Patient to be added, the method throws an exception.
     * @param patient The recipient Patient to be added.
     * @throws MaxPatientsError Thrown when there is already 100 recipient Patients found within the list of
     * recipient Patients, signifying there is no room for the recipient Patient.
     */
    public void addRecipient(Patient patient) throws MaxPatientsError {
        if (recipients.size() == MAX_PATIENTS)
            throw new MaxPatientsError("\nAdding Recipient Error: The number of Patients on the recipients list " +
                    "is currently full!");
        else {
            patient.setDonor(false);
            recipients.add(patient);
            updateTable();
        }
    }

    /**
     * If the program is unable to find a TransplantGraph object to be loaded, the program will build a new
     * TransplantGraph from two files and construct the adjacency matrix based on the files given. If the file is not
     * found or is not a valid file to load Patients from, then the method throws an exception.
     * @param donorFile The file that contains information regarding donor Patients.
     * @param recipientFile The file that contains information regarding recipient Patients.
     * @return TransplantGraph object with an adjacency matrix constructed based on the information from the donorFile
     * and recipientFile.
     * @throws FileNotFoundException Thrown when the method is unable to load, open, or locate the donorFile or
     * recipientFile.
     */
    public static TransplantGraph buildFromFiles(String donorFile, String recipientFile) throws FileNotFoundException {
        TransplantGraph graph = new TransplantGraph();
        try {
            graph.setDonors(graph.loadPatients(donorFile));
            for(Patient patient : graph.getDonors())
                patient.setDonor(true);
            graph.setRecipients(graph.loadPatients(recipientFile));
            for(Patient patient : graph.getRecipients())
                patient.setDonor(false);
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException(ex.getLocalizedMessage());
        }
        graph.updateTable();
        return graph;
    }

    /**
     * A helper method that creates the list of Patients from a specified from file given the file name. If the file
     * does not exist or does not contain the correct information regarding a Patient, an exception is thrown.
     * @param fileName The name of the file to construct the list of Patients from.
     * @return The list of Patients.
     * @throws FileNotFoundException Thrown when the file does not exist of the file does not correct information
     * regarding a Patient.
     */
    public ArrayList<Patient> loadPatients(String fileName) throws FileNotFoundException {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        Scanner reader;
        try {
            File file = new File(fileName);
            reader = new Scanner(file);
            ArrayList<String> donorInfo = new ArrayList<>();
            while (reader.hasNext()) {
                donorInfo.add(reader.nextLine().trim().replaceAll(", ", ","));
            }
            for (int i = 0; i < donorInfo.size(); i++) {
                String[] donor = donorInfo.get(i).split(",");
                int ID = Integer.parseInt(donor[0]);
                String[] name = donor[1].trim().split(" ");
                String names = "";
                for(int j = 0; j < name.length; j++){
                    if(name[j].length() ==1)
                        name[j].toUpperCase();
                    else if(name[j].length() == 0 || name[j] == null){
                        continue;
                    }
                    else{
                        name[j] = Character.toUpperCase(name[j].charAt(0)) + name[j].substring(1);
                    }
                }
                for(String s : name)
                    names += s + " ";
                names = names.trim();
                int donorAge = Integer.parseInt(donor[2].trim());
                String[] organInfo = donor[3].split(" ");
                String organ = "";
                for(int j = 0; j < organInfo.length; j++){
                    if(organInfo[j].length() == 1)
                        organInfo[j].toUpperCase();
                    else if(organInfo[j].length() == 0 || organInfo[j] == null)
                        continue;
                    else
                        organInfo[j] = Character.toUpperCase(organInfo[j].charAt(0)) + organInfo[j].substring(1);
                }
                for(String s : organInfo)
                    organ += s + " ";
                organ = organ.trim();
                String bloodType = donor[4].trim().toUpperCase();
                //String name, String organ, int age, BloodType bloodType, int ID
                try {
                    Patient patient = new Patient(names, organ, donorAge, new BloodType(bloodType), ID);
                    patientArrayList.add(patient);
                } catch (InvalidBloodType ex) {
                    System.out.println("\nBuilding From Files Error: Invalid patient information in "+ fileName +"!");
                   continue;
                } catch (InvalidPatient ex) {
                    System.out.println("\nBuilding From Files Error: Invalid patient information in "+ fileName +"!");
                    continue;
                }
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Loading Patient's Information Error: File is an invalid file!");
        }
        return patientArrayList;
    }

    /**
     * Removes a donor found within the list of donor Patients given the donor's name. If the name of the donor Patient
     * is not found, then the method throws an exception. Once the removal occurs, the adjacency matrix and the ID#s are
     * also updated accordingly.
     * @param name The name of the donor Patient.
     * @throws InvalidPatient Thrown when the name of the Patient is not found within the list of donor Patients.
     */
    public void removeDonor(String name) throws InvalidPatient {
        int position = -1;
        for (int i = 0; i < donors.size(); i++) {
            Patient patient = donors.get(i);
            if (patient.getName().equalsIgnoreCase(name)) {
                position = i;
                break;
            }
        }
        if (position == -1)
            throw new InvalidPatient("\nRemoving Donor Error: The provided name does not exist!");
        else {
            donors.remove(position);
            updateTable();
        }
    }

    /**
     * Removes a recipient found within the list of recipient Patients given the recipient's name. If the name of the
     * recipient Patient is not found, then the method throws an exception. Once the removal occurs, then the the
     * adjacency matrix and the ID#s are also updated accordingly.
     * @param name
     * @throws InvalidPatient Thrown when the name of the Patient is not found within the list of recipient Patients.
     */
    public void removeRecipient(String name) throws InvalidPatient {
        int position = -1;
        for (int i = 0; i < recipients.size(); i++) {
            Patient patient = recipients.get(i);
            if (patient.getName().equalsIgnoreCase(name)) {
                position = i;
                break;
            }
        }
        if (position == -1)
            throw new InvalidPatient("\nRemoving Recipient Error: The provided name does not exist!");
        else {
            recipients.remove(position);
            updateTable();
        }
    }

    /**
     * Prints the list of donor Patients in a formatted table displaying information about each donor Patient's
     * information regrading the index, or ID#, the age, the organ donated, the BloodType, and the connections that
     * each Patient has with the each recipient Patient found within in the system.
     */
    public void printAllDonors() {
        System.out.printf("\n%-8s|%-20s|%-5s|%-18s|%-20s|%-20s", "Index", "Name", "Age", "Organ Donated", "Blood Type",
                "Recipient IDs");
        System.out.println();
        System.out.println("========================================================================================" +
                "====");
        for (int i = 0; i < donors.size(); i++) {
            if(i == donors.size()-1)
                System.out.print(donors.get(i).toString()+"\n");
            else {
                System.out.println(donors.get(i).toString());
            }
        }
    }

    /**
     * Prints the list of recipient Patients in a formatted table displaying information about each recipient Patient's
     * information regrading the index, or ID#, the age, the organ donated, the BloodType, and the connections that
     * each Patient has with the each donor Patient found within in the system.
     */
    public void printAllRecipients() {
        System.out.printf("\n%-8s|%-20s|%-5s|%-18s|%-20s|%-20s", "Index", "Name", "Age", "Organ Needed", "Blood Type"
                , "Donor ID");
        System.out.println();
        System.out.println("=========================================================================================" +
                "===");
        for (int i = 0; i < recipients.size(); i++) {
            if(i == recipients.size()-1)
                System.out.print(recipients.get(i).toString()+"\n");
            else {
                System.out.println(recipients.get(i).toString());
            }
        }
    }

    /**
     * After list of donor/recipient Patients have been modified, the adjacency matrix is updated along with the ID#s of
     * each patient found within the system. In other words, the method makes sure that the adjacency matrix is updated
     * accurately and accordingly.
     */
    public void updateTable() {
        for (int i = 0; i < donors.size(); i++) {
            Patient patient = donors.get(i);
            patient.setID(i);
        }
        for (int i = 0; i < recipients.size(); i++) {
            Patient patient = recipients.get(i);
            patient.setID(i);
        }
        connections = new boolean[donors.size()][recipients.size()];
        for (int i = 0; i < donors.size(); i++) {
            for (int j = 0; j < recipients.size(); j++) {
                connections[i][j] = BloodType.isCompatible(recipients.get(j).getBloodType(),
                        donors.get(i).getBloodType()) && recipients.get(j).getOrgan().equalsIgnoreCase
                        (donors.get(i).getOrgan());
            }
        }
        makeLinks();
    }

    /**
     * A helped method used by the updateTable function to help find and establish links between the donors and
     * recipients and vice versa. The links are determined by whether or not if the donor has and the recipiet have a
     * compatible BloodType and same organ.
     */
    public void makeLinks() {
        for (int i = 0; i < connections.length; i++) {
            Patient donor = donors.get(i);
            ArrayList<Patient> donorConnections = new ArrayList<>();
            for (int j = 0; j < connections[i].length; j++) {
                if (connections[i][j])
                    donorConnections.add(recipients.get(j));
            }
            donor.setConnections(donorConnections);
        }
        for (int i = 0; i < recipients.size(); i++) {
            Patient recipient = recipients.get(i);
            ArrayList<Patient> recipientsConnection = new ArrayList<>();
            for (int j = 0; j < donors.size(); j++) {
                if (connections[j][i])
                    recipientsConnection.add(donors.get(j));
            }
            recipient.setConnections(recipientsConnection);
        }
    }

    /**
     * A sort method that is used to sort the list of either recipients or donors Patients based on the character passed
     * through the method. If the character is r, then the method sorts the recipients list and if the character is d,
     * then the donor list is sorted. Either list is sorted by their ID# by utilizing the Patient compareTo method.
     * After sorting has been done, the list is printed to be displayed to the user.
     * @param c The character to identify which list should be sorted and printed.
     */
    public void printSortByID(char c){
        switch (c){
            case('r'):
                Collections.sort(recipients);
                printAllRecipients();
                break;
            case ('d'):
                Collections.sort(donors);
                printAllDonors();
                break;
        }
    }

    /**
     * A sort method that is used to sort the list of either recipients or donor Patients based on the characters passed
     * through the method. If the character is r, then the method will sort the recipients list and if the character is
     * d, then the donor list is sorted. Either list is sorted by the number of connections they have by using the
     * NumConnectionsComparator. After the sorting has been done, the list that was chosen will be printed and displayed
     * to the user.
     * @param c The character to identify which list should be sorted and printed.
     */
    public void printSortByNumber(char c){
        switch (c){
            case('r'):
                ArrayList<Patient> originalRecipients = (ArrayList<Patient>) this.recipients.clone();
                Collections.sort(recipients, new NumConnectionsComparator());
                printAllRecipients();
                setRecipients(originalRecipients);
                break;
            case ('d'):
                ArrayList<Patient> originalDonors = (ArrayList<Patient>) this.donors.clone();
                Collections.sort(donors, new NumConnectionsComparator());
                printAllDonors();
                setDonors(originalDonors);
                break;
        }
    }

    /**
     * A sort method that is used to sort the list of either recipients or donor Patients based on the characters passed
     * through the method. If the character is r, then the method will sort the recipients list and if the character is
     * d, then the donor is sorted. Either list is sorted by their BloodType in alphabetical order that each patient has
     * by using the BloodTypeComparator. After the sorting has been done, the list that was chosen will be printed and
     * displayed to the user.
     * @param c The character to identify which list should be sorted and printed.
     */
    public void printSortByBloodType(char c){
        switch (c){
            case('r'):
                ArrayList<Patient> originalRecipients = (ArrayList<Patient>) this.recipients.clone();
                Collections.sort(recipients, new BloodTypeComparator());
                printAllRecipients();
                setRecipients(originalRecipients);
                break;
            case ('d'):
                ArrayList<Patient> originalDonors = (ArrayList<Patient>) this.donors.clone();
                Collections.sort(donors, new BloodTypeComparator());
                printAllDonors();
                setDonors(originalDonors);
                break;
        }
    }

    /**
     * A sort method that is used to sort the list of either recipients or donor Patients based on the characters passed
     * through the method. If the character is r, then the method will be sort the recipients list and if the character
     * is d, then the donor list is sorted. Either list is sorted by their organ in alphabetical order that each patient
     * has by using the OrganComparator. After the sorting has been done, the list that was chosen will be printed and
     * displayed to the user.
     * @param c The character to identify which list should be sorted and printed.
     */
    public void printSortByOrgan(char c){
        switch (c){
            case('r'):
                ArrayList<Patient> originalRecipients = (ArrayList<Patient>) this.recipients.clone();
                Collections.sort(recipients, new OrganComparator());
                printAllRecipients();
                setRecipients(originalRecipients);
                break;
            case ('d'):
                ArrayList<Patient> originalDonors = (ArrayList<Patient>) this.donors.clone();
                Collections.sort(donors, new OrganComparator());
                printAllDonors();
                setDonors(originalDonors);
                break;
        }
    }
}
