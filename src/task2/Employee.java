package task2;

public class Employee {
    //имя
    private String name = "безымянный";
    Employee(String name)
    {
        this.name = name;
    }
    Employee()
    {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee " + name + '\'';
    }
}
