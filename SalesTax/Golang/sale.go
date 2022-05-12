package main

import (
	"fmt"
)

type Sale struct {
	TotalTax, TotalValue float64
	SaleLines            []SaleLine
}

func (s Sale) ToString() string {
	output := ""

	for _, el := range s.SaleLines {
		output = fmt.Sprintf("%s\n%s", output, el.ToString())
	}

	output = fmt.Sprintf("%s\nSales Taxes: %.2f\n", output, s.TotalTax)
	output = fmt.Sprintf("%sTotal: %.2f\n", output, s.TotalValue)
	return output
}

func (s *Sale) Add(inputLine string) bool {

	saleline, _ := Processinput(inputLine)

	s.SaleLines = append(s.SaleLines, saleline)

	s.TotalTax += saleline.Tax
	s.TotalValue += saleline.LineValue
	return true
}
