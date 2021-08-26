package task2;

public class Main
{

    public static void main(String[] args) {
        boolean isSimple = false;
        for(String key: args)
        {
            if("-simple".equals(key))
            {
                isSimple = true;
                break;
            }
        }
        /*
        При запуске программы происходит создание по одному экземпляру
        каждого документа и печать (в консоль) созданных документов. Затем
        * */
        Mail mail = new Mail(1);
        EmploymentOrder employmentOrder = new EmploymentOrder("Vasilii", 2);
        DismissalOrder dismissalOrder = new DismissalOrder("Grigorii", 3);
        //печать
        System.out.println("Печать письма");
        if(isSimple) mail.ShortPrint();
        else mail.Print();
        System.out.println("---------------------------------------");
        System.out.println("Печать приказа о приеме на работу");
        if(isSimple) employmentOrder.ShortPrint();
        else employmentOrder.Print();
        System.out.println("---------------------------------------");
        System.out.println("Печать приказа об увольнении");
        if(isSimple) dismissalOrder.ShortPrint();
        else dismissalOrder.Print();
        //производится смена статуса документов
        mail.setName("Кадровая смена Григория на Василия");
        mail.setSender("Нач. отдела кадров В.Ю. Шишкин");
        mail.setRecipient("Дир. завода А.В. Мишкин");

        employmentOrder.setName("Прием Василия на работу");
        employmentOrder.setOrderText("Прошу принять Василия на работу программистом. Он пишет комментарии.");
        employmentOrder.setStatus("ИСПОЛНЕН");

        dismissalOrder.setName("Увольнение Григория");
        dismissalOrder.setOrderText("Прошу уволить Григория");
        dismissalOrder.setDismissalReason("Отказывается комментировать код.");
        dismissalOrder.setStatus("ИСПОЛНЕН");
        //снова печать
        System.out.println("Печать письма");
        if(isSimple) mail.ShortPrint();
        else mail.Print();
        System.out.println("---------------------------------------");
        System.out.println("Печать приказа о приеме на работу");
        if(isSimple) employmentOrder.ShortPrint();
        else employmentOrder.Print();
        System.out.println("---------------------------------------");
        System.out.println("Печать приказа об увольнении");
        if(isSimple) dismissalOrder.ShortPrint();
        else dismissalOrder.Print();
    }
}
