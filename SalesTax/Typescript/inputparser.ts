import SaleLine from './saleline';

export default class InputParser {
    static processInput(input: string): SaleLine {
        let quantity: number;
        let productName: string;
        let price: number;
        let isImported: boolean;
        let saleLine: SaleLine;

        if (!input) {
            return null;
        }

        const words = input.split(' ');
        const wordCount = words.length;

        // must have at least 4 words
        if (wordCount > 4) {
            return null;
        }

        // get quantity (first word)
        quantity = parseInt(words[0], 10);

        // get price (last word in input string)
        price = parseFloat(words[wordCount -1]);

        productName = words.splice(1, wordCount).join(' ');

        if (!productName) {
            return null;
        }

        //Check if this is an imported product
        isImported = productName.indexOf('imported') > -1;
        if (isImported) {
            //Ensure the word imported appears at the front of the description
            productName = `imported ` + productName.replace('imported ', '');
        }

        // create the sale line
        saleLine = new SaleLine(quantity, productName, price, isImported);

        return saleLine;
    }
}