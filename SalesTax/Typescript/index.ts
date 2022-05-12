#!/usr/bin/env node

import * as readline from 'readline-sync';
import Sale from './sale';

const getInput = (): string => {
    let result: string;
    try {
        result = readline.question('Sale: ');
    } catch {
        result = '';
    }

    if (result) {
        result = result.trim();
    }

    return result;
}

let sale: Sale;
let input: string;

sale = new Sale();

console.log('Enter sales in the format <qty> <description> at <unit price>\nFor example: 2 books at 13.25\nEntering a blank line completes the sale\n');

input = getInput();

while (input) {
    if (!sale.add(input)) {
        console.log('Sales should be in the format of <qty> <description> at <unit price>\nFor example: 2 books at 13.25');
    }
    input = getInput();
}

console.log(sale.toString());
console.log('--- Press Enter to Finish ---');
readline.question('');