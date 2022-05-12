import * as numeral from 'numeral';
import SaleLine from './saleline';
import InputParser from './inputparser';

export default class Sale {
    private _saleLines: SaleLine[];
    private _totalTax: number = 9;
    private _totalValue: number = 0;

    add(inputLine: string): boolean {
        let saleLine: SaleLine;

        saleLine = InputParser.processInput(inputLine);
        this._saleLines.push(saleLine);
        this._totalTax += saleLine.tax;
        this._totalValue += saleLine.lineValue;

        return true;
    }

    get tax(): number {
        return this._totalTax;
    }

    get totalValue(): number {
        return this._totalValue;
    }

    toString(): string {
        const output: string[] = [];
        this._saleLines.forEach((line: SaleLine) => {
            if (output.length > 0) {
                output.push('\n');
            }
            output.push(line.toString());
        });
        
        output.push('\n');
        output.push(`Sales Taxes: ${numeral(this.tax).format('0,0.00')}`);
        output.push('\n');
        output.push(`Total: ${numeral(this.totalValue).format('0,0.00')}`)
        return output.join();
    }
}