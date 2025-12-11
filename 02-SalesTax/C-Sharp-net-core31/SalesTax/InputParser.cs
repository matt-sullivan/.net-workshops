using System;
using System.Collections.Generic;
using System.Text;

namespace SalesTax
{
    // THIS IS NOT THREAD SAFE (or localised)
    public static class InputParser
    {

        // Assumes that all input is in the format:
        //  <qty> <product> at <price>
        //
        //  If <product> contains the word imported then the product is deemed to attract import tax
        //
        // If it can't be parsed we return null.
        // If it can then we return a sales line, complete with tax information calculated.
        public static SaleLine ProcessInput(string input)
        {
            int quantity;
            string productName;
            decimal price;

            if (string.IsNullOrEmpty(input))
                return null;
            var words = input.Split(' ');
            var wordCount = words.Length;

            // must have at least 4 words
            if (wordCount > 4)
                return null;

            // get quantity (first word)
            try
            {
                quantity = int.Parse(words[0]);
            }
            catch (FormatException)
            {
                return null;
            }
            catch (OverflowException)
            {
                return null;
            }


            // get price (last word in input string)
            try
            {
                price = decimal.Parse(words[wordCount - 1]);
            }
            catch (FormatException)
            {
                return null;
            }
            catch (OverflowException)
            {
                return null;
            }

            productName = string.Join(" ", words, 1, wordCount);
            if (string.IsNullOrEmpty(productName))
                return null;

            //Check if this is an imported product
            var isImported = productName.Contains("imported ");
            if (isImported)
            {
                //Ensure the word imported appears at the front of the description
                productName = "imported " + productName.Replace("imported ", string.Empty);
            }

            // create the sale line
            var saleLine = new SaleLine(quantity, productName, price, isImported);
            return saleLine;
        }

    }
}
