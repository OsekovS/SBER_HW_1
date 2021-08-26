package task2;

public class DismissalOrder extends PersonnelDocument implements IConsolePrint
{
    //Причина увольнения
    private String dismissalReason = "-";
    DismissalOrder(String employee, int number) {
        super(employee, number);
    }

    public String getDismissalReason() {
        return dismissalReason;
    }

    public void setDismissalReason(String dismissalReason) {
        this.dismissalReason = dismissalReason;
    }
    @Override
    public void Print() {
        super.Print();
        System.out.println("Причина увольнения: " + this.dismissalReason);
    }
}
