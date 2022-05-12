package SalesTax;

import java.text.DecimalFormat;
import java.util.List;

public class Sale {
    private List<SaleLine> saleLines;
    private float totalTax;
    private float totalValue;

    /**
     * Adds a line to the sale.
     * @param inputLine The line to add.
     * @return True for success, False for failure.  Failures are usually caused via incorrect formatting of the input
     */
    public Boolean add(String inputLine)
    {
        SaleLine saleLine;

        saleLine = InputParser.processInput(inputLine);
        saleLines.add(saleLine);
        totalTax += saleLine.getTaxAmount();
        totalValue += saleLine.getLineValue();
        return true;
    }

    /**
     *
     * @return The total Tax amount for the sale
     */
    public float getTotalTax() {
        return totalTax;
    }

    /**
     *
     * @return The total value of the sale (including Tax)
     */
    public float getTotalValue() {
        return totalValue;
    }

    /**
     * Converts the sale to a string
     * @return
     */
    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();

        for (SaleLine line : saleLines)
        {
            if(output.length() > 0)
                output.append("\n");
            output.append(line.toString());
        }

        //Now add footer information
        output.append("\n");
        output.append(String.format("Sales Taxes: %s", new DecimalFormat("#,##0.00").format(getTotalTax())));
        output.append("\n");
        output.append(String.format("Total: %s", new DecimalFormat("#,##0.00").format(getTotalValue())));
        return output.toString();
    }
}
