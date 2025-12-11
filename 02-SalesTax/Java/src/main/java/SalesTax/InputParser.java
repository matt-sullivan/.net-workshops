package SalesTax;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.Arrays;

// THIS IS NOT THREAD SAFE (or localised)
public class InputParser
{

    // Assumes that all input is in the format:
    //  <qty> <product> at <price>
    //
    //  If <product> contains the word imported then the product is deemed to attract import tax
    //
    // If it can't be parsed we return null.
    // If it can then we return a sales line, complete with tax information calculated.
    public static SaleLine processInput(String input)
    {
        int quantity;
        String productName;
        float price;
        Boolean isImported;
        SaleLine saleLine;

        if(StringUtils.isEmpty(input))
            return null;
        String[] words = input.split(" ");
        int wordCount = words.length;

        // must have at least 4 words
        if (wordCount > 4)
            return null;

        // get quantity (first word)
        try
        {
            quantity = Integer.parseInt(words[0]);
        }
        catch (NumberFormatException e)
        {
            return null;
        }

        // get price (last word in input string)
        try
        {
            price = Float.parseFloat(words[wordCount - 1]);
        }
        catch (NumberFormatException e)
        {
            return null;
        }

        productName = String.join(" ", Arrays.copyOfRange(words, 1, wordCount));
        if(StringUtils.isEmpty(productName))
            return null;

        isImported = StringUtils.contains(productName, "imported "); //Check if this is an imported product
        if(isImported)
        {
            //Ensure the word imported appears at the front of the description

            productName = "imported " + productName.replace("imported ", "");
        }

        saleLine = new SaleLine(quantity, productName, price, isImported);
        return saleLine;
    }


}
