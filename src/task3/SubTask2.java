package task3;

import java.awt.*;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubTask2
{
    public static void main(String[] args)
    {
        //97-122 маленькие буквы
        //65-90 большие буквы
        int minSmallLetterCode = 97,
            maxSmallLetterCode = 122,
            minBigLetterCode = 65;

        Scanner in = new Scanner(System.in);
        int[] freqDict = new int[maxSmallLetterCode - minSmallLetterCode + 1];
        System.out.println("Для завершения ввода нажмите Enter на новой пустой строке");
        while(in.hasNextLine())
        {
            String input = in.nextLine();
            if(input.length() == 0) break;
            for(int i = 0; i < input.length() ; i++)
            {
                int symbCode = (int)input.charAt(i);
                //проверка что это буква, причем английская
                if(Character.isLetter(symbCode) && symbCode <= maxSmallLetterCode)
                {
                    if(Character.isLowerCase(symbCode))
                        freqDict[symbCode - minSmallLetterCode]++;
                    else
                        freqDict[symbCode - minBigLetterCode]++;
                }
            }
        }
        for(int i = 0; i < freqDict.length; i++)
        {
            System.out.println((char)(i + minSmallLetterCode) + ": " + freqDict[i]);
        }
    }
}
