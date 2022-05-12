package main

import (
	"fmt"
	"math"
	"strings"
)

type SaleLine struct {
	Quantity       int
	ProductName    string
	Price          float64
	IsImported     bool
	LineValue, Tax float64
}

func (s SaleLine) ToString() string {
	return fmt.Sprintf("%d %s: %.2f", s.Quantity, s.ProductName, s.LineValue)
}

func NewSaleLine(quantity int, name string, price float64, isImported bool) SaleLine {
	var saleline SaleLine
	saleline.Quantity = quantity
	saleline.ProductName = name
	saleline.Price = price
	saleline.IsImported = isImported
	saleline.LineValue = price * float64(quantity)

	taxRate := 10

	if strings.Contains(name, "book") ||
		strings.Contains(name, "tablet") ||
		strings.Contains(name, "chip") {
		taxRate = 0
	}

	if isImported {
		taxRate = 5
	}

	taxAmt := CalculateTax(saleline.LineValue, taxRate)
	saleline.LineValue += taxAmt
	saleline.Tax = taxAmt

	return saleline
}

func CalculateTax(value float64, taxRate int) float64 {

	amount := roundPrecision((value*float64(taxRate))/100, 2)

	remainder := math.Remainder(amount, .05)

	if remainder > 0 {
		amount += .05 - remainder
	}
	return amount
}

func round(f float64) float64 {
	return math.Floor(f + .5)
}

func roundPrecision(f float64, places int) float64 {
	shift := math.Pow(10, float64(places))
	return round(f*shift) / shift
}
