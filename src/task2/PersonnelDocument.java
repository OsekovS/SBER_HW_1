package task2;

public class PersonnelDocument extends Document implements IConsolePrint
{
    //сотрудник
    private final Employee employee;
    //текст приказа
    private String orderText = "-";
    //статус
    private String status;
    /*
    Поле сотрудник присваивается в момент создания кадрового документа
    и не может быть изменено в дальнейшем.
    * */
    PersonnelDocument(String employeeName, int number) {
        super(number);
        this.employee = new Employee(employeeName);
        this.status = "СОЗДАН";
    }
    @Override
    public void Print() {
        super.Print();
        System.out.println("Сотрудник: " + this.employee);
        System.out.println("Текст приказа: " + this.orderText);
        System.out.println("Статус: " + this.status);
    }
    public Employee getEmployee() {
        return employee;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }

    public String getStatus() {
        return status;
    }
    /*
        Механизм изменения статуса должен быть единым для
         кадровых документов и не может быть изменен даже
         при добавлении в программу других видов кадровых документов.
     * */
    public final void setStatus(String status) {
        if(this.status.equals("СОЗДАН") && status.equals("ИСПОЛНЕН"))
            this.status = status;
    }
}
