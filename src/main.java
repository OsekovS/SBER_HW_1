import javax.naming.spi.DirectoryManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
    public static class Auto
    {
        /*
        функция которая в разрезе каждого типа авто выводят информацию о каждом авто
        (тип, номер, пробег, доп. параметр), с сортировкой по пробегу и доп параметру.
        конечно данную функцию можно сделать уникальной, но, коль требуется ряд функций, пише
         */
        public static void printAutos(List<List<Auto>> allAuto){
            for(int i = 0 ; i < Auto.autoNames.length; i++)
            {
                List<Auto> curAutoGroup = allAuto.get(i);
                //сортируем с начала по пробегу, потом по доп параметру
                Collections.sort(curAutoGroup, new AutoDistancePassedComparator());
                Collections.sort(curAutoGroup, new AutoAddParamComparator());
                //вывод категории
                System.out.println(Auto.autoNames[i] + ": ");
                //вывод машин из категории
                for (int j = 0; j < curAutoGroup.size(); j++)
                {
                    System.out.println("\tC" + Auto.autoCodes[i] + "_" +
                            curAutoGroup.get(j).gosNum + "-" + curAutoGroup.get(j).distancePassed
                            + (curAutoGroup.get(j).addParam == null ? "" : curAutoGroup.get(j).addParam));
                }
            }
        }
        //статические свойства
        //CODE_CAR
        public static String[] autoCodes = {
                new String("100"),
                new String("200"),
                new String("300"),
                new String("400")
        };
        public static String[] autoNames = {
                new String("легковой авто"),
                new String("грузовой авто"),
                new String("пассажирский транспорт"),
                new String("тяжелая техника(краны)")
        };
        //стоимость топлива
        public static double [] autoFuelPrices = {
                46.10, 47.50, 48.90, 48.90
        };
        //расход топлива
        public static double [] autoConsumptions = {
                12.5, 12, 11.5, 20
        };
        //расходы на каждый класс авто
        public static double [] autoTotalFuelCost = {
                0, 0, 0, 0
        };
        //госномер
        public int gosNum;
        //доппараметр
        public Integer addParam;
        //общий расход
        public int distancePassed;
        public Auto(int gosNum, Integer addParam, int totalFuelCost) {
            this.gosNum = gosNum;
            this.addParam = addParam;
            this.distancePassed = totalFuelCost;
        }
        public Auto(int gosNum) {
            this.gosNum = gosNum;
            this.addParam = null;
            this.distancePassed = 0;
        }
    }
    //сортировка по доп параметру
    //поскольку в задании не описано правило сортировки.
    // принято решение машины без параметра размещать первыми
    static class AutoAddParamComparator implements Comparator<Auto> {

        public int compare(Auto auto1, Auto auto2) {

            if (auto1.addParam != null && auto2.addParam == null)
            {
                return 1;
            } else if (auto1.addParam == null && auto2.addParam != null)
            {
                return -1;
            }
            else if (auto1.addParam == null && auto2.addParam == null)
            {
                return 0;
            }
            else if(auto1.addParam < auto2.addParam)
            {
                return 1;
            }
            else if(auto1.addParam > auto2.addParam)
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    }
    //сортировка по пробегу
    static class AutoDistancePassedComparator implements Comparator<Auto> {

        public int compare(Auto auto1, Auto auto2) {

            if (auto1.distancePassed > auto2.distancePassed)
            {
                return 1;
            } else if (auto1.distancePassed < auto2.distancePassed)
            {
                return -1;
            } else
            {
                return 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //список всех автомобилей по категориям (сквозная нумерация с Auto.autoCodes)
        List<List<Auto>> allAuto = new ArrayList<>();
        while(allAuto.size() != Auto.autoCodes.length) allAuto.add(new LinkedList<>());
        //общую стоимость расходов на ГСМ
        double totalFuelCost = 0;
        //считываем входные данные из файла
        FileInputStream in = new FileInputStream("./input.txt");
        String input = new Scanner(in).nextLine();
        //паттерн для поиска машин из входной стоки
        Pattern pattern = Pattern.compile(new String("C\\d+_\\d+-\\d+-?\\d*"));
        Matcher matcher = pattern.matcher(input);
        while (matcher.find())
        {
            String auto = input.substring(matcher.start(), matcher.end());
            //вычисляем индекс текущего авто
            int autoIndex = 0;
            String curAutoCode = auto.substring(auto.indexOf("C")+1, auto.indexOf("_"));
            for(int i = 0 ; i < Auto.autoCodes.length; i++)
            {
                if(Auto.autoCodes[i].equals(curAutoCode)){
                    autoIndex = i;
                    break;
                }
            }
            //Госномер текущего авто
            int gosNumber = Integer.parseInt(auto.substring(auto.indexOf("_")+1,auto.indexOf("-")));
            //Прошедшее расстояние текущего авто
            String distancePassed = "";
            Integer additionalParam = null;
            boolean hasAdditionalParam;
            //если нет доп параметра
            if(auto.indexOf("-") == auto.lastIndexOf("-"))
            {
                distancePassed = auto.substring(auto.indexOf("-") + 1);
                hasAdditionalParam = false;
            }
            else
            {
                distancePassed = auto.substring(auto.indexOf("-")+1, auto.lastIndexOf("-"));
                additionalParam = Integer.parseInt(auto.substring( auto.lastIndexOf("-")));
                hasAdditionalParam = true;
            }
            //индекс того же самого авто в сохраненном списке, с тем же госномером и доп параметром (если есть)
            int sameAutoIndex = -1;
            //список авто, к которым текущая модель относится
            List<Auto> curAutoGroup = allAuto.get(autoIndex);
            //добавляем в список авто, если оно уникально по госномеру и доп параметру.
            // если не уникально, добавляем пробег
            for (int i = 0; i < curAutoGroup.size(); i++) {
                if (curAutoGroup.get(i).gosNum == gosNumber &&
                        (!hasAdditionalParam || curAutoGroup.get(i).addParam == additionalParam))
                {
                    sameAutoIndex = i;
                }
            }
            int curDistancePassed = Integer.parseInt(distancePassed);
            //если машина уникальна добавляем ее
            if (sameAutoIndex == -1)
            {
                Auto newAuto = new Auto(
                    gosNumber, hasAdditionalParam ? additionalParam : null,
                    curDistancePassed
                );
                allAuto.get(autoIndex).add(newAuto);
            }
            else allAuto.get(autoIndex).get(sameAutoIndex).distancePassed += curDistancePassed;

            Double curFuelCost = (curDistancePassed / 100.0) *
                    Auto.autoConsumptions[autoIndex] * Auto.autoFuelPrices[autoIndex];
            //обновляем расходы на каждый класс авто
            Auto.autoTotalFuelCost[autoIndex] += curFuelCost;
            totalFuelCost += curFuelCost;

        }
        System.out.println("Общая стоимость расходов на ГСМ: " + totalFuelCost);
        System.out.println("Расход ГСМ по категориям: ");
        //индекс типа авто имеющего наибольшую стоимость расходов
        //индекс типа авто имеющего наименьшую стоимость расходов
        int minCostIndex = 0;
        int maxCostIndex = 0;
        for(int i = 0 ; i < Auto.autoTotalFuelCost.length; i++){
            minCostIndex = Auto.autoTotalFuelCost[i] < Auto.autoTotalFuelCost[minCostIndex] ? i : minCostIndex;
            maxCostIndex = Auto.autoTotalFuelCost[i] > Auto.autoTotalFuelCost[maxCostIndex] ? i : maxCostIndex;
            System.out.println(Auto.autoNames[i] +": " +Auto.autoTotalFuelCost[i]);
        }
        System.out.println("тип авто имеющий наибольшую стоимость расходов: " + Auto.autoNames[maxCostIndex]);
        System.out.println("тип авто имеющий наименьшую стоимость расходов: " + Auto.autoNames[minCostIndex]);

        Auto.printAutos(allAuto);

    }
}
