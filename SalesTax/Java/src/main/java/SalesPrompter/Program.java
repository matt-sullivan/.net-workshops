package SalesPrompter;

import SalesTax.*;
import org.apache.commons.lang.StringUtils;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Sale sale;
        String input;
        Scanner in = new Scanner(System.in);
        sale = new Sale();

        System.out.println("Enter sales in the format <qty> <description> at <unit price>\nFor example: 2 books at 13.25\nEntering a blank line completes the sale\n");
        input = getInput();

        while (!StringUtils.isEmpty(input))
        {
            if(!sale.add(input))
                System.out.println("Sales should be in the format of <qty> <description> at <unit price>\nFor example: 2 books at 13.25");
            input = getInput();
        }
        System.out.println(sale.toString());
        System.out.println("--- Press Enter to Finish ---");
        in.next();
        in.close();
    }

    static String getInput()
    {
        String result;
        Scanner in = new Scanner(System.in);
        System.out.println("Sale : ");
        try
        {
            result = in.nextLine();
        }
        catch (NoSuchElementException e)
        {
            result = "";
        }

        if(StringUtils.isEmpty(result))
            result = result.trim();

        return result;
    }
}
