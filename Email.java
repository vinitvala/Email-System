import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @author Vinit Vala
 * SBU ID: 114501080
 * Email Class which contains information about each Email.
 */

public class Email implements Serializable {

    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timeStamp;

    /**
     * Default Constructor of Email
     */
    public Email(){
        to="";
        cc="";
        bcc="";
        subject="";
        body="";
        timeStamp=null;
    }

    /**
     * Parameterized Constructor
     * @param to
     * @param cc
     * @param bcc
     * @param subject
     * @param body
     * @param timeStamp
     */
    public Email(String to, String cc, String bcc, String subject, String body, GregorianCalendar timeStamp){

        this.to=to;
        this.cc=cc;
        this.bcc=bcc;
        this.body=body;
        this.subject=subject;
        this.timeStamp=timeStamp;
    }

    /**
     * Accessor method for To
     * @return String
     */
    public String getTo() {
        return to;
    }

    /**
     * Accessor method for CC
     * @return String
     */
    public String getCc() {
        return cc;
    }

    /**
     * Accessor method for Bcc
     * @return String
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * Accessor method for Subject
     * @return String
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Accessor method for Body
     * @return String
     */
    public String getBody() {
        return body;
    }

    /**
     * Accessor method for TimeStep
     * @return GregorianCalendar
     */
    public GregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * Mutator method for To
     * @param to
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Mutator method for cc
     * @param cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * Mutator method for Bcc
     * @param bcc
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * Mutator method for Body
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Mutator method for Subject
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Mutator method for TimeStamp
     * @param timeStamp
     */
    public void setTimeStamp(GregorianCalendar timeStamp) {
        this.timeStamp = timeStamp;
    }


}
