package SalesTax;

import com.sun.javafx.css.CalculatedValue;
import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;

public class SaleLine {

    private String productName;
    private float price;
    private Boolean isImported;
    private int quantity;
    private float taxAmount;
    private float lineValue;

    public String getProductName() {
        return productName;
    }

    public float getPrice() {
        return price;
    }

    public Boolean getImported() {
        return isImported;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTaxAmount() {
        return taxAmount;
    }

    public float getLineValue() {
        return lineValue;
    }

    /**
     * Default constructor not publicly accessible
     */
    private SaleLine()
    {

    }

    /**
     * Public constructor for the sale line
     * @param lineQuantity Quantity on order
     * @param name name of the product
     * @param unitPrice price of a single item
     * @param itemIsImported flag to indicate if the item is imported
     */
    public SaleLine(int lineQuantity, String name, float unitPrice, Boolean itemIsImported)
    {
        int taxRate;
        if(StringUtils.isEmpty(name))
        {
            name = "";
        }

        this.quantity = lineQuantity;
        this.productName = name;
        this.price = unitPrice;
        this.isImported = itemIsImported;
        this.lineValue = this.price * this.quantity;

        // calculate taxable amount
        // ideally should really have a product list and tax rules, but this'll have to do for the exercise.
        if (StringUtils.containsIgnoreCase(productName, "book") || StringUtils.containsIgnoreCase(productName, "tablet") || StringUtils.containsIgnoreCase(productName, "chip"))
            taxRate = 0; //No base tax applicable for books, medical items or food
        else
            taxRate = 10; //10% base tax or general products
        if (isImported)
            taxRate = 5; //5% regardless for any imported items

        taxAmount = calculateTax(lineValue, taxRate);
        lineValue += taxAmount; //Add tax to line value

    }

    /**
     * Calculates the amount of tax for a value, rounded up to the nearest 5 cents
     * @param value The original value
     * @param taxRate The tax rate to apply
     * @return The calculated tax on the original value
     */
    public static float calculateTax(float value, int taxRate)
    {
        double amount;
        double remainder;
        DecimalFormat df = new DecimalFormat("###.##");
        amount = Double.parseDouble(df.format(Math.round((value * taxRate)/100)));

        remainder = amount % .05;

        if(remainder > 0)
            amount += .05 - remainder;

        return (float)amount;
    }

    /**
     * Converts the sale line to a string
     * @return The string representation of the sale line
     */
    @Override
    public String toString()
    {
        return String.format("%d %s: %s", quantity, productName, new DecimalFormat("#,###.00").format(lineValue));
    }
}
