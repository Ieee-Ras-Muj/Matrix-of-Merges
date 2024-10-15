#include <iostream>
#include <unordered_map>
#include <iomanip>  

using namespace std;


void displayCurrencies(const unordered_map<string, double>& rates) {
    cout << "Available Currencies: ";
    for (const auto& currency : rates) {
        cout << currency.first << " ";
    }
    cout << endl;
}


double convertCurrency(double amount, double rateFrom, double rateTo) {
    return amount * (rateTo / rateFrom);
}

int main() {
    
    unordered_map<string, double> exchangeRates = {
        {"USD", 1.0},   // US Dollar
        {"EUR", 0.85},  // Euro
        {"GBP", 0.75},  // British Pound
        {"INR", 74.0},  // Indian Rupee
        {"JPY", 110.0}, // Japanese Yen
        {"CAD", 1.25}   // Canadian Dollar
    };

    string fromCurrency, toCurrency;
    double amount;

    cout << "Welcome to the Currency Converter!" << endl;

    displayCurrencies(exchangeRates);

    cout << "Enter the currency you want to convert from (e.g., USD): ";
    cin >> fromCurrency;
    
    if (exchangeRates.find(fromCurrency) == exchangeRates.end()) {
        cout << "Error: Invalid currency entered!" << endl;
        return 1;  
    }

    cout << "Enter the currency you want to convert to (e.g., EUR): ";
    cin >> toCurrency;

    if (exchangeRates.find(toCurrency) == exchangeRates.end()) {
        cout << "Error: Invalid currency entered!" << endl;
        return 1; 
    }

    cout << "Enter the amount to convert: ";
    cin >> amount;

    if (amount < 0) {
        cout << "Error: Amount cannot be negative!" << endl;
        return 1;  
    }

    
    double convertedAmount = convertCurrency(amount, exchangeRates[fromCurrency], exchangeRates[toCurrency]);

    
    cout << fixed << setprecision(2);
    cout << amount << " " << fromCurrency << " = " << convertedAmount << " " << toCurrency << endl;

    return 0;
}
