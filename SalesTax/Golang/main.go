package main

import (
	"bufio"
	"fmt"
	"os"
)

func getInput() string {
	fmt.Print("Sale : ")
	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()
	text := scanner.Text()
	return text
}

func main() {
	fmt.Println("Enter sales in the format <qty> <description> at <unit price>\nFor example: 2 books at 13.25\nEntering a blank line completes the sale\n")

	var s Sale
	input := getInput()
	for input != "" {
		if !s.Add(input) {
			fmt.Println("Sales should be in the format of <qty> <description> at <unit price>\nFor example: 2 books at 13.25")
		}
		input = getInput()
	}

	fmt.Println(s.ToString())
	fmt.Println("--- Press Enter ---")
	bufio.NewReader(os.Stdin).ReadLine()

}
