import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Vinit Vala
 * SBU ID: 114501080
 * Folder Class represents an email folder and will handle all of the logic for adding and removing emails.
 */


public class Folder implements Comparator<Email>, Serializable {

    private ArrayList<Email> emails=new ArrayList<Email>();

    private String name;

    private String currentSortingMethod;
    // emails added can be properly sorted without having to first resort the folder.
    //Notes: Default is date descending.


    /**
     * Accessor method for Name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method for CurrentSortingMethod
     * @return String
     */
    public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    /**
     * Mutator method for Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator method for CurrentSortingMethod
     * @param currentSortingMethod
     */
    public void setCurrentSortingMethod(String currentSortingMethod) {
        this.currentSortingMethod = currentSortingMethod;
    }

    /**
     * Accessor method for emails
     * @return ArrayList
     */
    public ArrayList<Email> getEmails() {
        return emails;
    }

    /**
     * Mutator method for emails
     * @param emails
     */
    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    /**
     * Method to compare 2 emails
     * @param o1
     * @param o2
     * @return int
     */
    public int compare(Email o1, Email o2) {  //this works!!

        Email first=(Email) o1;
        Email second=(Email) o2;


        if(first.getTimeStamp().compareTo(second.getTimeStamp())>0)
            return -1;
        else if(first.getTimeStamp().compareTo(second.getTimeStamp())==0)
            return 0;
        else
            return 1;
    }

    /**
     * Method to add Email
     * @param email
     */
    public void addEmail(Email email){
        emails.add(email);
        Collections.sort(emails,new Folder());  //check if this works!

    }

    /**
     * Method to remove email
     * @param index
     * @return Email
     */
    public Email removeEmail(int index){

        index=index-1;
        emails.remove(index);
       // return emails.get(index);
        return null;
    }

    /**
     * Method to sort Ascending by Subject
     */
    public void sortBySubjectAscending() {

//        emails.sort(((o1, o2) -> o1.getSubject().compareTo(o2.getSubject())));
//        emails.get(0).getSubject();  //WHY WRITTEN THIS?

        Collections.sort(emails,new ascendingSubjectComparator());
    }

    /**
     * Method to sort Descending by Subject
     */
    public void sortBySubjectDescending(){ //input the emails arraylist in parameter?

//        emails.sort(((o1, o2) -> o2.getSubject().compareTo(o1.getSubject())));
        //emails.sort(((o2, o1) -> o1.getSubject().compareTo(o2.getSubject())));

        Collections.sort(emails,new subjectComparator());
    }

    /**
     * Method to Sort Ascending by Date
     */
    public void sortByDateAscending(){

//        emails.sort(((o1, o2) -> o1.getTimeStamp().compareTo(o2.getTimeStamp())));

        Collections.sort(emails,new DateAscendingComparator());

    }

    /**
     * MEthod to sort Descending by Date
     */
    public void sortByDateDescending(){

//        emails.sort(((o1, o2) -> o2.getTimeStamp().compareTo(o1.getTimeStamp())));
        //emails.sort(((o2, o1) -> o1.getTimeStamp().compareTo(o2.getTimeStamp())));
        Collections.sort(emails,new Folder());

    }

    /**
     * Comparator method for Subject
     */
    public class subjectComparator implements Comparator<Email>{

        /**
         * Method to compare emails
         * @param o1
         * @param o2
         * @return int
         */
        public int compare(Email o1, Email o2) {

            Email first=(Email) o1;
            Email second=(Email) o2;

            if(first.getSubject().compareTo(second.getSubject())>0)
                return -1;
            else if(first.getSubject().compareTo(second.getSubject())==0)
                return 0;
            else
                return 1;
        }
    }

    /**
     * Comparator method Subject in Ascending order
     */
    public class ascendingSubjectComparator implements Comparator<Email>{

        /**
         * Method to compare Emails
         * @param o1
         * @param o2
         * @return int
         */
        public int compare(Email o1, Email o2) {

            Email first=(Email) o1;
            Email second=(Email) o2;

            if(first.getSubject().compareTo(second.getSubject())>0)
                return 1;
            else if(first.getSubject().compareTo(second.getSubject())==0)
                return 0;
            else
                return -1;
        }
    }

    /**
     * Comparator method Data in Ascending
     */
    public class DateAscendingComparator implements Comparator<Email>{

        /**
         * Method to compare Emails
         * @param o1
         * @param o2
         * @return int
         */
        public int compare(Email o1, Email o2) {  //this works!!

            Email first=(Email) o1;
            Email second=(Email) o2;


            if(first.getTimeStamp().compareTo(second.getTimeStamp())>0)
                return 1;
            else if(first.getTimeStamp().compareTo(second.getTimeStamp())==0)
                return 0;
            else
                return -1;
        }
    }

}
