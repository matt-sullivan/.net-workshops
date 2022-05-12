import * as numeral from 'numeral';

export default class SaleLine {
    private _productName: string;
    private _price: number;
    private _isImported: boolean;
    private _quantity: number;
    private _taxAmount: number;
    private _lineValue: number;

    get productName() {
        return this._productName;
    }

    get price() {
        return this._price;
    }

    get isImported() {
        return this._isImported;
    }

    get quantity() {
        return this._quantity;
    }

    get tax() {
        return this._taxAmount;
    }

    get lineValue() {
        return this._lineValue;
    }

    constructor(lineQuantity: number, name: string, unitPrice: number, itemIsImported: boolean) {
        let taxRate: number;
        
        if (!name) {
            name = '';
        }

        this._quantity = lineQuantity;
        this._productName = name;
        this._price = unitPrice;
        this._isImported = itemIsImported;
        this._lineValue = this.price * this.quantity;

        // calculate taxable amount
        // ideally should really have a product list and tax rules, but this'll have to do for the exercise.
        if (this.productName.indexOf('book') > -1 || this.productName.indexOf('tablet') > -1 || this.productName.indexOf('chip') > -1) {
            taxRate = 0 //No base tax applicable for books, medicals items or food
        } else {
            taxRate = 10 //10% base tax or general products
        } if (this.isImported) {
            taxRate = 5 //5% regardless for any imported items
        }

        this._taxAmount = SaleLine.calculateTax(this.lineValue, taxRate);
        //Add tax to line value
        this._lineValue += this._taxAmount;
        return;
    }

    static calculateTax(value: number, taxRate: number): number {
        let amount: number;
        let remainder: number;

        amount = Math.round((value * taxRate * 100)) / 100;

        //Now round up to nearest 5 cents.
        remainder = amount % .5;
        if (remainder > 0) {
            amount += .05 - remainder;
        }
        return amount;
    }

    toString(): string {
        return `${this.quantity} ${this.productName}: ${numeral(this.lineValue).format('0,0.00')}`;
    }
}