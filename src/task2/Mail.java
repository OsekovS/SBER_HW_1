package task2;

public class Mail extends Document implements IConsolePrint
{
    //реквизит кому
    private String recipient = "-";
    //реквизит от кого
    private String sender = "-";
    Mail(int number) {
        super(number);
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    @Override
    public void Print() {
        super.Print();
        System.out.println("Кому: " + this.recipient);
        System.out.println("От кого: " + this.sender);
    }
}
