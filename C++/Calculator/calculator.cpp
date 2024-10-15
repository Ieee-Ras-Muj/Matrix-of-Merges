#include <iostream>
#include <cmath> // For advanced mathematical operations

using namespace std;

void displayMenu() {
    cout << "Welcome to the C++ Calculator!" << endl;
    cout << "Select operation:" << endl;
    cout << "1. Addition (+)" << endl;
    cout << "2. Subtraction (-)" << endl;
    cout << "3. Multiplication (*)" << endl;
    cout << "4. Division (/)" << endl;
    cout << "5. Power (^)" << endl;
    cout << "6. Square Root (√)" << endl;
    cout << "7. Modulus (%)" << endl;
}

int main() {
    int choice;
    double num1, num2, result;

    displayMenu();
    
    cout << "Enter your choice (1-7): ";
    cin >> choice;

    if (choice < 1 || choice > 7) {
        cout << "Error: Invalid operation choice!" << endl;
        return 1;
    }

    // For square root operation, we only need one input
    if (choice == 6) {
        cout << "Enter a number: ";
        cin >> num1;

        if (num1 < 0) {
            cout << "Error: Cannot calculate square root of a negative number!" << endl;
            return 1;
        }

        result = sqrt(num1); // Square root calculation
        cout << "Result: √" << num1 << " = " << result << endl;
        return 0;
    }

    // Input for other operations
    cout << "Enter first number: ";
    cin >> num1;
    cout << "Enter second number: ";
    cin >> num2;

    switch (choice) {
        case 1:
            result = num1 + num2;
            cout << "Result: " << num1 << " + " << num2 << " = " << result << endl;
            break;
        case 2:
            result = num1 - num2;
            cout << "Result: " << num1 << " - " << num2 << " = " << result << endl;
            break;
        case 3:
            result = num1 * num2;
            cout << "Result: " << num1 << " * " << num2 << " = " << result << endl;
            break;
        case 4:
            if (num2 == 0) {
                cout << "Error: Division by zero is not allowed!" << endl;
                return 1;
            }
            result = num1 / num2;
            cout << "Result: " << num1 << " / " << num2 << " = " << result << endl;
            break;
        case 5:
            result = pow(num1, num2); // Power calculation
            cout << "Result: " << num1 << "^" << num2 << " = " << result << endl;
            break;
        case 7:
            if (static_cast<int>(num2) == 0) {
                cout << "Error: Modulus by zero is not allowed!" << endl;
                return 1;
            }
            result = static_cast<int>(num1) % static_cast<int>(num2); // Modulus operation
            cout << "Result: " << static_cast<int>(num1) << " % " << static_cast<int>(num2) << " = " << result << endl;
            break;
        default:
            cout << "Error: Invalid operation!" << endl;
            return 1;
    }

    return 0;
}
