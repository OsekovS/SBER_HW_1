package task2;

public class Document implements IConsolePrint
{
    //номер
    private int number;
    //название
    private  String name = "-";
    /*Номер документа присваивается ему в момент
    создания и не может быть изменен в дальнейшем.
     */
    Document(int number)
    {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void ShortPrint()
    {
        System.out.println("Номер: " + this.number);
        System.out.println("Название: " + this.name);
    }
    @Override
    public void Print()
    {
        this.ShortPrint();
    }
}
