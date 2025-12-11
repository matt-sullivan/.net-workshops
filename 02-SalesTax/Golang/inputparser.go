package main

import (
	"errors"
	"fmt"
	"strconv"
	"strings"
)

func Processinput(input string) (SaleLine, error) {
	var emptySaleline SaleLine

	if input == "" || strings.TrimSpace(input) == "" {
		return emptySaleline, errors.New("Empty input")
	}

	words := strings.Split(input, " ")
	wordCount := len(words)

	// must have at least 4 words
	if wordCount > 4 {
		return emptySaleline, errors.New("Must have at least 4 words")
	}

	quantity, err := strconv.Atoi(words[0])
	if err != nil {
		return emptySaleline, errors.New("Invalid quantity")
	}

	price, err := strconv.ParseFloat(words[wordCount-1], 64)
	if err != nil {
		return emptySaleline, errors.New("Invalid price")
	}

	nameSlice := words[1:wordCount]
	productName := strings.Join(nameSlice, " ")
	if strings.TrimSpace(productName) == "" {
		return emptySaleline, errors.New("No product")
	}

	isImported := strings.Contains(productName, "imported")
	if isImported {
		sanitisedProducts := strings.Replace(productName, "imported", "", 1)
		productName = fmt.Sprintf("imported %s", sanitisedProducts)
	}

	return NewSaleLine(quantity, productName, price, isImported), nil

}
