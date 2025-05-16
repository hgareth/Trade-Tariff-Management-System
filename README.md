# Trade Tariff Management System

This is a Java-based project that simulates how tariffs impact international trade. It handles everything from reading product and tariff data from files to processing trade requests and updating trade values based on specific rules.

## What It Does

The system focuses on two main areas:

1. Updating Product Prices Based on Tariffs  
   It loads trade data from a text file, applies tariff adjustments depending on the country and category, sorts the list by product name, and writes the results to a new file.

2. Evaluating Trade Requests 
   It reads trade request records and evaluates them by comparing the proposed tariff to a minimum requirement. Based on how close they are, requests can be accepted, conditionally accepted (with a surcharge), or rejected.

## How It Works

- Trade data is handled using `ArrayList` for easy sorting and processing.
- Trade request evaluations are managed using a custom-built linked list structure.
- The program includes full file input/output operations and prints results to the console in a clear format.
- A console interface lets users look up tariffs, test linked list operations, and interact with the data.

## Technologies Used

- Java  
- `ArrayList`, custom linked list (not Java's built-in one)  
- File handling using `Scanner` and `PrintWriter`  
- Basic OOP concepts like interfaces, deep copying, and encapsulation

## How to Use

1. Make sure your input files (`TradeData.txt`, `Tariffs.txt`, `TradeRequests.txt`) are in the project folder.
2. Compile the Java files.
3. Run the `TradeManager` class.
4. Follow the prompts shown in the terminal to run different features.

## Notes

This project was built from scratch without relying on advanced libraries. All linked list behavior is implemented manually. Input files should follow the expected format to work correctly.

## License

Personal project. Sharing, copying, or publishing the code elsewhere is not allowed.
