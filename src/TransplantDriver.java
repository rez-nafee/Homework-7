//Rezvan Nafee
//112936468
//Recitation 04

import java.io.*;
import java.util.*;

/**
 * This class represents the main driver for the TransplantGraph class. In this class the user is able to manage/use
 * the functions that TransplantGraph has to offer.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Recitation 04
 */
public class TransplantDriver {

    /**
     * Data field that contains the file name of the information regarding donors to be added to the TransplantGraph.
     */
    public static final String DONOR_FILE = "donors.txt";

    /**
     * Data field that contains the file name of the information regarding recipients to be added to the
     * TransplantGraph.
     */
    public static final String RECIPIENT_FILE = "recipients.txt";

    /**
     * Data field that references to a null TransplantGraph object that is subjected to change.
     */
    public static TransplantGraph graph = null;

    /**
     * This is the main method of the program. This will begin by checking for an object by the name of
     * "transplant.obj". If the object is not found, then the method will create a new TransplantGraph by utilizing
     * the static method buildFromFiles and use the DONOR_FILE and RECIPIENT_FILE to load both recipient and donor
     * Patients to the TransplantGraph object. If the object exists, then the method will load its contents and data
     * and continue. After that, the use wll be presented with different operations they can perform on the object such
     * as adding and removing Patients, sorting Patients, and displaying the Patients currently in the system.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String key = "";
        boolean found = false;
        try {
            FileInputStream file = new FileInputStream("transplant.obj");
            ObjectInputStream objIn = new ObjectInputStream(file);
            graph = (TransplantGraph) objIn.readObject();
            found = true;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("transplant.obj does not exist! Creating new TransplantGraph...");
            try {
                System.out.println("Loading data from \'" + DONOR_FILE + "\'...");
                System.out.println("Loading data from \'" + RECIPIENT_FILE + "\'...");
                graph = TransplantGraph.buildFromFiles(DONOR_FILE, RECIPIENT_FILE);
            } catch (FileNotFoundException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        if (found)
            System.out.println("TransplantGraph is loaded from transplant.obj!");
        while (!key.equals("q")) {
            printTable();
            System.out.print("\nPlease select an option: ");
            key = input.nextLine().trim().toLowerCase();
            switch (key) {
                case ("lr"):
                    graph.printAllRecipients();
                    break;
                case ("lo"):
                    graph.printAllDonors();
                    break;
                case ("ao"): //add donor
                    String name = "";
                    String organ = "";
                    String bloodType = "";
                    int age = 0;
                    System.out.print("\nPlease enter the organ donor name: ");
                    name = formatString(input.nextLine().trim()).trim();
                    if (name.isEmpty()) {
                        System.out.print("Please enter the organ " + name + " is donating: ");
                        input.nextLine();
                        System.out.print("Please enter the blood type of " + name + ": ");
                        input.nextLine();
                        System.out.print("Please enter the age of " + name + ": ");
                        input.nextLine();
                        System.out.println("\nAdding Donor Error: Invalid information given!");
                        continue;
                    }
                    System.out.print("Please enter the organ " + name + " is donating: ");
                    organ = formatString(input.nextLine().trim());
                    if (organ.isEmpty()) {
                        System.out.print("Please enter the blood type of " + name + ": ");
                        input.nextLine();
                        System.out.println("Please enter the age of " + name + ": ");
                        input.nextLine();
                        System.out.println("\nAdding Donor Error: Invalid information given!");
                        continue;
                    }
                    System.out.print("Please enter the blood type of " + name + ": ");
                    bloodType = input.nextLine().trim();
                    if (bloodType.isEmpty()) {
                        System.out.println("Please enter the age of " + name + ": ");
                        input.nextLine();
                        System.out.println("\nAdding Donor Error: Invalid information given!");
                        continue;
                    }
                    try {
                        System.out.print("Please enter the age of " + name + ": ");
                        age = input.nextInt();
                        input.nextLine();
                    } catch (InputMismatchException ex) {
                        input.nextLine();
                        System.out.println("\nAdding Donor Error: Invalid information given!");
                        continue;
                    }
                    //String name, String organ, int age, BloodType bloodType)
                    try {
                        Patient patient = new Patient(name, organ, age, new BloodType(bloodType.toUpperCase()));
                        patient.setID(graph.getDonors().size());
                        System.out.println("\nThe organ donor with ID " + patient.getID() + " was successfully added to"
                                + " the donor list!");
                        graph.addDonor(patient);
                    } catch (InvalidBloodType ex) {
                        System.out.println(ex.getLocalizedMessage());
                    } catch (InvalidPatient ex) {
                        System.out.println(ex.getLocalizedMessage());
                    } catch (MaxPatientsError ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                    break;
                case ("ar"):
                    String recName = "";
                    String recBloodType = "";
                    int recAge = 0;
                    String recOrgan = "";
                    System.out.print("\nPlease enter recipient's name: ");
                    recName = formatString(input.nextLine().trim()).trim();
                    if (recName.isEmpty()) {
                        System.out.print("Please enter recipient's blood type: ");
                        input.nextLine();
                        System.out.print("Please enter the recipient's age: ");
                        input.nextLine();
                        System.out.print("Please enter the organ needed: ");
                        input.nextLine();
                        System.out.println("\nAdding Recipient Error: Invalid information given!");
                        continue;
                    }
                    System.out.print("Please enter recipient's blood type: ");
                    recBloodType = input.nextLine().trim().toUpperCase();
                    if (recBloodType.isEmpty()) {
                        System.out.print("Please enter the recipient's age: ");
                        input.nextLine();
                        System.out.print("Please enter the organ needed: ");
                        input.nextLine();
                        System.out.println("\nAdding Recipient Error: Invalid information given!");
                        continue;
                    }
                    try {
                        System.out.print("Please enter the recipient's age: ");
                        recAge = input.nextInt();
                        input.nextLine();
                    } catch (InputMismatchException ex) {
                        input.nextLine();
                        System.out.print("Please enter the organ needed: ");
                        input.nextLine();
                        System.out.println("\nAdding Recipient Error: Invalid information given!");
                        continue;
                    }
                    System.out.print("Please enter the organ needed: ");
                    recOrgan = formatString(input.nextLine().trim()).trim();
                    if (recOrgan.isEmpty()) {
                        System.out.println("\nAdding Recipient Error: Invalid information given!");
                    }
                    //String name, String organ, int age, BloodType bloodType)
                    try {
                        Patient patient = new Patient(recName, recOrgan, recAge, new BloodType(recBloodType));
                        patient.setID(graph.getRecipients().size());
                        System.out.println("\n" + patient.getName() + " is now on the organ transplant waitlist!");
                        graph.addRecipient(patient);
                    } catch (InvalidPatient ex) {
                        System.out.println(ex.getLocalizedMessage());
                    } catch (InvalidBloodType ex) {
                        System.out.println(ex.getLocalizedMessage());
                    } catch (MaxPatientsError ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                    break;
                case ("ro"):
                    String donorName = "";
                    System.out.print("\nPlease enter the name of the donor to remove: ");
                    donorName = formatString(input.nextLine().trim()).trim();
                    if (donorName.isEmpty()) {
                        System.out.println("\nRemoving Recipient Error: Invalid information given!");
                        continue;
                    } else {
                        try {
                            graph.removeDonor(donorName);
                            System.out.println("\n" + donorName + " was removed from the organ donor list!");
                        } catch (InvalidPatient ex) {
                            System.out.println(ex.getLocalizedMessage());
                            continue;
                        }
                    }
                    break;
                case ("rr"):
                    String recipientName = "";
                    System.out.print("\nPlease enter the name of the recipient to remove: ");
                    recipientName = formatString(input.nextLine().trim()).trim();
                    if (recipientName.isEmpty()) {
                        System.out.println("\nRemoving Recipient Error: Invalid information given!");
                        continue;
                    } else {
                        try {
                            graph.removeRecipient(recipientName);
                            System.out.println("\n" + recipientName + " was removed from the organ transplant " +
                                    "waitlist!");
                        } catch (InvalidPatient ex) {
                            System.out.println(ex.getLocalizedMessage());
                            continue;
                        }
                    }
                    break;
                case ("sr"):
                    String recKey = "";
                    while (!recKey.equals("q")) {
                        printSubTableRecipients();
                        System.out.print("\nPlease enter an option: ");
                        recKey = input.nextLine().trim().toLowerCase();
                        switch (recKey) {
                            case ("i"):
                                graph.printSortByID('r');
                                break;
                            case ("n"):
                                graph.printSortByNumber('r');
                                break;
                            case ("b"):
                                graph.printSortByBloodType('r');
                                break;
                            case ("o"):
                                graph.printSortByOrgan('r');
                                break;
                            case ("q"):
                                graph.printSortByOrgan('o');
                                System.out.println("\nReturning to main menu!");
                                break;
                            default:
                                System.out.println("\nNot a valid input!");
                        }
                    }
                    break;
                case ("so"):
                    String organKey = "";
                    while (!organKey.equals("q")) {
                        printSubTableDonors();
                        System.out.print("\nPlease enter an option: ");
                        organKey = input.nextLine().trim().toLowerCase();
                        switch (organKey) {
                            case ("i"):
                                graph.printSortByID('d');
                                break;
                            case ("n"):
                                graph.printSortByNumber('d');
                                break;
                            case ("b"):
                                graph.printSortByBloodType('d');
                                break;
                            case ("o"):
                                graph.printSortByOrgan('d');
                                break;
                            case ("q"):
                                System.out.println("\nReturning to main menu!");
                                break;
                            default:
                                System.out.println("\nNot a valid input!");
                        }
                    }
                    break;
                case ("q"):
                    try {
                        FileOutputStream file = new FileOutputStream("transplant.obj");
                        ObjectOutputStream fileOut = new ObjectOutputStream(file);
                        fileOut.writeObject(graph);
                        fileOut.close();
                    } catch (IOException ex) {
                    }
                    System.out.println("\nWriting data to transplant.obj...");
                    System.out.println("\nProgram terminating normally...");
                    break;
                case ("d"):
                    System.out.println(graph.getRecipients().size());
                    System.out.println(graph.getDonors().size());
                default:
                    System.out.println("\nNot a valid input!");
            }
        }
    }

    /**
     * A helper method that formats String inputs by ensuring that it follows proper capitalization.
     * @param str The String inputs that need to be formatted.
     * @return The formatted String.
     */
    public static String formatString(String str){
        String[] arr = str.split(" ");
        String resultant = "";
        for(int i = 0; i < arr.length;i++){
            if(arr[i].length() == 1)
                arr[i] = arr[i].toUpperCase();
            else if(arr[i].length() == 0)
                continue;
            else{
                arr[i] = Character.toUpperCase(arr[i].charAt(0)) + arr[i].substring(1);
            }
        }
        for(String s : arr)
            resultant += s + " ";
        return resultant.trim();
    }

    /**
     * A method that prints out all the main operations that the TransplantGraph can do.
     */
    public static void printTable() {
        System.out.println("\nMenu:");
        System.out.println("(LR) - List All Recipients");
        System.out.println("(LO) - List All Donors");
        System.out.println("(AO) - Add New Donor");
        System.out.println("(AR) - Add New Recipient");
        System.out.println("(RO) - Remove Donor");
        System.out.println("(RR) - Remove Recipient");
        System.out.println("(SR) - Sort Recipients");
        System.out.println("(SO) - Sort Donors");
        System.out.println("(Q) - Quit");
    }

    /**
     * Prints out the sub-table for operations that the user can do when electing to sort the recipient Patients in the
     * TransplantGraph object.
     */
    public static void printSubTableRecipients() {
        System.out.println("\n\t(I) - Sort by ID");
        System.out.println("\t(N) - Sort by Number of Donors");
        System.out.println("\t(B) - Sort by Blood Type");
        System.out.println("\t(O) - Sort by Organ Needed");
        System.out.println("\t(Q) - Back to Main Menu");
    }

    /**
     * Prints out the sub-table for operations that the user can do when electing to sort the donor Patients in the
     * TransplantGraph object.
     */
    public static void printSubTableDonors() {
        System.out.println("\n\t(I) - Sort by ID");
        System.out.println("\t(N) - Sort Number of Recipients");
        System.out.println("\t(B) - Sort by Blood Type");
        System.out.println("\t(O) - Sort by Organ Donated");
        System.out.println("\t(Q) - Back to Main Menu");
    }
}
