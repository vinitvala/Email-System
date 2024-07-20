import java.io.*;
import java.util.*;

/**
 * @author Vinit Vala
 * SBU ID: 114501080
 * Folder Class represents an email folder and will handle all of the logic for adding and removing emails.
 */

public class Mailbox implements Serializable  {


    private Folder inbox;
    //Stores the inbox, which is a special folder which should never be allowed to be deleted or renamed.
    // All new emails go here.

    private Folder trash;
    //which should never be allowed to be deleted or renamed. When an email is deleted, it is moved here.

    private ArrayList<Folder> folders = new ArrayList<Folder>();
    // 0 and 1 index can be inbox and trash and should not be deleted.

    public static Mailbox mailbox;
    //This mailbox should be instantiated in the main method.

    /**
     * Method to add Folder
     * @param folder
     */
    public void addFolder(Folder folder) {

        folders.add(folder);
        //check to make sure a folder with that given name does not already exist.
        // If it does, return an error in some manner.
    }

    /**
     * Method to delete Folder
     * @param name
     */
    public void deleteFolder(String name) {

        // inbox and mailbox cannot be deleted!

        int i = 0;
        while (i < folders.size()) {
            if (folders.get(i).getName().equalsIgnoreCase(name)) {
                folders.remove(i);
            }
            i++;
        }

    }

    /**
     * Method to Compose email
     * @throws IllegalArgumentException
     */
    public void composeEmail() throws IllegalArgumentException {

        try {

            Scanner stdin = new Scanner(System.in);
            boolean found = false;
            System.out.print("Enter recipient (To): ");
            String to = stdin.nextLine();

            for (int i = 0; i < to.length(); i++) {
                if (to.charAt(i) == '@') {
                    i = to.length();
                    found = true;
                    break;
                }
            }
            if (!found)
                throw new IllegalArgumentException("Invalid email input- should contain @");


            System.out.print("Enter carbon copy recipients (CC): ");
            String cc = stdin.nextLine();
            for (int i = 0; i < to.length(); i++) {
                if (to.charAt(i) == '@') {
                    i = to.length();
                    found = true;
                    break;
                }
            }
            if (!found)
                throw new IllegalArgumentException("Invalid email input- should contain @");

            System.out.print("Enter blind carbon copy recipients (BCC): ");
            String bcc = stdin.nextLine();
            for (int i = 0; i < to.length(); i++) {
                if (to.charAt(i) == '@') {
                    i = to.length();
                    found = true;
                    break;
                }
            }
            if (!found)
                throw new IllegalArgumentException("Invalid email input- should contain @");

            System.out.print("Enter subject line: ");
            String subject = stdin.nextLine();

            System.out.print("Enter body: ");
            String body = stdin.nextLine();

            Calendar calendar = Calendar.getInstance();
            GregorianCalendar timeStamp = (GregorianCalendar) calendar;

            Email email = new Email(to, cc, bcc, subject, body, timeStamp);

            inbox.addEmail(email);
            System.out.println("Email successfully added to Inbox.");
        } catch (IllegalArgumentException ee) {
            System.out.println("Invalid email input- should contain @");
        }
    }

    /**
     * Method for Deleting Email
     * @param email
     */
    public void deleteEmail(Email email) {
        //Moves the email to the trash.
        // the method removeEmail should be called and then deleteEmail is called on the result)

        folders.get(1).addEmail(email);
        //folder.removeEmail(index)

    }

    /**
     * Method to Clear Trash
     */
    public void clearTrash() {

        int i = 1;int j=0;
        while (i <= trash.getEmails().size()) {
            trash.removeEmail(i);
            j++;
        }
        System.out.println(j + " item(s) successfully deleted.");
        //incrementing i since i=0
    }

    /**
     * Method to move the email.
     * @param email
     * @param target
     */
    public void moveEmail(Email email, Folder target) {

        // 2 contradicting statements in the hw!
        // 1. If the folder cannot be found, instead move it to the Inbox.
        // 2. If the specified folder was not found, cancel the entire operation.
       // System.out.println("Target= "+ target.getName());  //
        target.addEmail(email); //email added to the folder
     //   System.out.println("target Size= "+target.getEmails().size());  //
        System.out.println(email.getSubject() + " successfully moved to " + target.getName());

        }

    /**
     * Method to get the Folder
     * @param name
     * @return Folder
     */
    public Folder getFolder(String name) {  // this name entered should be case-insensitive

        int i = 0;
        boolean found = false;

        for(Folder temp:mailbox.folders){

            if (temp.getName().equalsIgnoreCase(name)) {
                found=true;
                break;
            }
            i++;
        }
        if (!found)
            System.out.println("Folder not found! Invalid folder name!");

    //    System.out.println("Get folder name= "+folders.get(i).getName());
        return folders.get(i);
    }

    /**
     * Method to print folder names
     */
    public void printFolderNames() {

        int i = 0;
        System.out.println("Mailbox");
        System.out.println("---------");
        while (i < folders.size()) {
            //System.out.println(folders.size());
            System.out.println(folders.get(i).getName());
            ++i;
        }
        //System.out.println();
    }

    /**
     * Method to print the Folder
     * @param index
     * @param folder
     */
    public void printFolder(int index,Folder folder) {

        int j = 0;
        System.out.printf("%-10s%-35s%-30s\n", "Index", "Time", "Subject");

        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < folder.getEmails().size(); i++) {
            if(folder.getEmails().size()==0)
                System.out.println("Inbox is empty");

            else{
            j = i + 1;
            System.out.printf("%-10s%-35s%-30s\n", j, folder.getEmails().get(i).getTimeStamp().getTime(), folder.getEmails().get(i).getSubject());
            }
        }
        System.out.println();
        //use arraylist of emails
    }

    /**
     * Method to view Email
     * @param email
     */
    public void viewEmail(Email email){

        System.out.println("To: "+email.getTo());
        System.out.println("CC: "+email.getCc());
        System.out.println("BCC: "+email.getBcc());
        System.out.println("Subject: "+email.getSubject());
        System.out.println(email.getBody());

    }

    /**
     * Method to print the sub Folder
     * @param currentFolder
     */
    public void subFolder(Folder currentFolder) {

        int counter = 0;
        Scanner stdin = new Scanner(System.in);
        while (counter == 0) {

            System.out.println("\nM – Move email\n" +
                    "D – Delete email\n" +
                    "V – View email contents\n" +
                    "SA – Sort by subject line in ascending order\n" +
                    "SD – Sort by subject line in descending order\n" +
                    "DA – Sort by date in ascending order\n" +
                    "DD – Sort by date in descending order\n" +
                    "R – Return to mailbox");

            System.out.println("Please select an option:");
            String input = stdin.next();
            input = input.toLowerCase();
            //stdin.nextLine();

            switch (input) {

                //EMAIL IS MOVED BUT NOT DELETED FROM FOLDER
                case "m": {

                    try {

                        System.out.println("Enter the index of the email to move: ");
                        int in = stdin.nextInt();
                        stdin.nextLine();
                        if(in==0)
                            throw new IllegalArgumentException("Invalid index entered!");


                        if (currentFolder.getEmails().size() < in)
                            throw new IllegalArgumentException("Invalid index entered!");

                        mailbox.printFolderNames();

                        System.out.println("Select a folder to move " + currentFolder.getEmails().get(in-1).getSubject()
                                + " to: ");
                        String folderName = stdin.nextLine();

                        boolean found = false;

                        for(Folder temp:mailbox.folders){

                           // System.out.println("Temp: "+temp.getName());
                            //System.out.println(folderName);

                            if (temp.getName().equalsIgnoreCase(folderName)) {
                                mailbox.moveEmail(currentFolder.getEmails().get(in-1),temp);
                                currentFolder.removeEmail(in);
                             //   System.out.println("temp size= "+temp.getEmails().size());
                                found=true;
                                break;
                            }
                        }

                        if(!found){
                            System.out.println("Folder to be moved not found! So operation has been canceled ");
                            //move to inbox if folder not found??
                        }
                        //getting IndexOutOfBoundsException: Index 3 out of bounds for length 3 here!!!

                    } catch (IllegalArgumentException ee) {
                        System.out.print("Invalid index entered! No email at index");
                    }

                }
                break;

                case "d": {

                    try {

                        Email email = new Email();
                        System.out.print("Enter email index to delete: ");
                        int index = stdin.nextInt();

                        if (index == 0)
                            throw new IllegalArgumentException("Invalid index entered!");

                        if (currentFolder.getEmails().size() < index)
                            throw new IllegalArgumentException("Invalid index entered!");

                        if (index <= currentFolder.getEmails().size()) {
                            email = currentFolder.getEmails().get(index - 1);
                            mailbox.deleteEmail(email);
                            System.out.println(email.getSubject()+" has successfully been moved to the trash.");
                            currentFolder.removeEmail(index);

                        } else
                            System.out.println("invalid index entered!");

                    }catch (IllegalArgumentException rr){
                        System.out.println("Invalid index entered");
                    }
                }
                break;

                case "v": {

                    try {
                        System.out.println("Enter email index ");
                        int index = stdin.nextInt();
                        index=index-1;
                        Email email;

                        if(index==-1)
                            throw new IllegalArgumentException();

                        if (index < currentFolder.getEmails().size()) {
                            email = currentFolder.getEmails().get(index);
                            mailbox.viewEmail(email);
                        } else
                            throw new IllegalArgumentException();
                    }
                    catch (IllegalArgumentException ee){
                        System.out.println("Invalid index entered");
                    }

                }
                break;

                case "sa": {

                    System.out.println("Sorting by Subject Ascending: \n");
                    currentFolder.setCurrentSortingMethod("sa");
                    currentFolder.sortBySubjectAscending();
                    mailbox.printFolder(0,currentFolder);
                }
                break;

                case "sd": {

                    System.out.println("Sorting by Subject Descending: \n");
                    currentFolder.setCurrentSortingMethod("sd");
                    currentFolder.sortBySubjectDescending();
                    mailbox.printFolder(0,currentFolder);
                }
                break;

                case "da": {

                    System.out.println("Sorting by Date Ascending: \n");
                    currentFolder.setCurrentSortingMethod("da");
                    currentFolder.sortByDateAscending();
                    mailbox.printFolder(0,currentFolder);
                }
                break;

                case "dd": {

                    System.out.println("Sorting by Date Descending: \n");
                    currentFolder.setCurrentSortingMethod("dd");
                    currentFolder.sortByDateDescending();
                    mailbox.printFolder(0,currentFolder);
                }
                break;

                case "r": {
                    System.out.println("Returning to mailbox");
                    counter++;
                }
                break;

                default:
                    System.out.println("Invalid input enter again!");

            }


        }
    }

    /**
     * Main method to handle the main menu
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        Scanner stdin = new Scanner(System.in);

        //see if the file "mailbox.obj" exists in the current directory.
        // If so, it should initialize the mailbox with the data in this file using serialization.
        // Otherwise, your program will start with an empty mailbox
        mailbox = new Mailbox();
        boolean fileLoad = false;
        //Email myObject = new Email();


        try {
            FileInputStream file = new FileInputStream("mailbox.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
           // myObject= (Email)inStream.readObject();
            Mailbox obj = (Mailbox)inStream.readObject();
            //IS THIS CORRECT?

            if (obj != null) {
                mailbox=(Mailbox)obj;
                System.out.println("mailbox.obj was found and loaded.\n");
                fileLoad = true;
            }
            else{
//                mailbox.inbox = new Folder();
//                mailbox.trash = new Folder();
            }
            inStream.close();

        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Previous save not found, starting with an empty mailbox...");
        }


//        mailbox.folders = new ArrayList<Folder>();

        boolean flag = true;

        if (fileLoad == false) {
            mailbox.inbox = new Folder();
            mailbox.trash = new Folder();
            mailbox.addFolder(mailbox.inbox);
            mailbox.addFolder(mailbox.trash);

            mailbox.folders.get(0).setName("Inbox");
            mailbox.folders.get(1).setName("Trash");
        }

        while (flag) {

            mailbox.printFolderNames();

            System.out.println(" \nMenu:\n"
                    + "    A) Add Folder\n"
                    + "    R) Remove Folder\n"
                    + "    C) Compose Email\n"
                    + "    F) Open Folder\n"
                    + "    I) Open Inbox\n"
                    + "    T) Open Trash\n"
                    + "    E) Clear Trash\n"
                    + "    Q) Quit "); //Previous save not found, starting with an empty mailbox.

            System.out.println("Please select an option:");
            char userInput = stdin.next().charAt(0);
            userInput = Character.toLowerCase(userInput);
            stdin.nextLine();

            //DOUBT:
            //WHAT DOES CURRENT SORTING METHOD DO?

            // CHECK ALL SORTING METHODS IN FOLDER CLASS!

            Folder currentFolder = new Folder();
            currentFolder = mailbox.inbox;

            if (userInput == 'a') {

                System.out.println("Please enter folder name:");
                String name = stdin.nextLine(); //.toUpperCase()

                if (name.equalsIgnoreCase("inbox") || name.equalsIgnoreCase("trash")) {
                    System.out.println("New folder name cannot be inbox or trash!");
                } else {
                    Folder newFolder = new Folder();
                    newFolder.setName(name);
                    mailbox.addFolder(newFolder);

                    //mailbox.printFolderNames();
                }

            }

            if (userInput == 'r') {

                int i=0;boolean delete=false;
                try {
                    System.out.println("Please enter folder name you want to remove:");
                    System.out.println("Note: Inbox and Trash cannot be deleted");
                    String name = stdin.nextLine();

                    if (name.equalsIgnoreCase("inbox") || name.equalsIgnoreCase("trash")) {
                        throw new IllegalArgumentException("");
                    } else {
                            for(Folder temp:mailbox.folders){

                            if (temp.getName().equalsIgnoreCase(name)) {
                                System.out.println("Folder "+name+ " has been deleted!");
                                mailbox.deleteFolder(name);
                                delete=true;
                                break;
                            }
                        }
                            if(!delete){
                                System.out.println("Folder to be deleted not found!");
                            }
                    }
                }catch (IllegalArgumentException ee){
                    System.out.println("Inbox or trash folders cannot be deleted!");
                }
            }

            if (userInput == 'c') {

                mailbox.composeEmail();
                // Add exceptions to all user inputs inside compose email!

            }

            //INCOMPLETE
            if (userInput == 'f') {

                System.out.print("Enter folder name: ");
                String folderName=stdin.nextLine();
                boolean folder=false;

                for(Folder temp:mailbox.folders){

                    //System.out.println("temp: "+temp.getName());
                    //System.out.println(folderName);

                    if (temp.getName().equalsIgnoreCase(folderName)) {
                        //System.out.println(temp.getName());
                        mailbox.printFolder(0,temp);

                        currentFolder = mailbox.getFolder(folderName);
          //              System.out.println("current folder size= "+currentFolder.getEmails().size());
                        folder=true;
                        mailbox.subFolder(currentFolder);

                        break;
                    }
                }
                if(!folder){
                    System.out.println("Folder to be moved not found!");
                }

            }

            if (userInput == 'i') {

                System.out.println("Inbox\n");
                currentFolder= mailbox.inbox;

                mailbox.printFolder(0,currentFolder);

                mailbox.subFolder(currentFolder);

                }

            if (userInput == 't') {

                System.out.println("Trash\n");
                currentFolder= mailbox.trash;

                mailbox.printFolder(1,currentFolder);

                mailbox.subFolder(currentFolder);

            }

            if(userInput == 'e'){

                mailbox.clearTrash();
            }

            if (userInput == 'q') {

                try {
                    FileOutputStream file = new FileOutputStream("mailbox.obj");
                    ObjectOutputStream outStream= new ObjectOutputStream(file);

                    outStream.writeObject(mailbox);
                    outStream.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Program successfully exited and mailbox saved!");
                break;
            }

            }

        }

    }

