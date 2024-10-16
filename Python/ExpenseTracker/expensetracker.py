import sys

expenses = []

def show_expenses():
    if len(expenses) == 0:
        print("No expenses recorded!")
    else:
        total = 0
        print("Here are your expenses:")
        for i, expense in enumerate(expenses):
            print(f"{i+1}. {expense['item']} - ${expense['amount']}")
            total += expense['amount']
        print(f"\nTotal Expenses: ${total}")  # Issue 1: Format the output for better clarity

def add_expense(item, amount):
    # Issue 2: No input validation for negative amounts
    expenses.append({"item": item, "amount": amount})
    print(f"Expense '{item}' of ${amount} added successfully!")

# Function to remove an expense by index
def remove_expense(expense_number):
    if expense_number > len(expenses) or expense_number <= 0:
        print(f"Invalid expense number: {expense_number}")
    else:
        removed_expense = expenses.pop(expense_number-1)
        print(f"Expense '{removed_expense['item']}' removed successfully!")

# Function to clear all expenses
def clear_expenses():
    global expenses
    expenses = []  # Issue 3: Same issue as in the to-do list; refactor for efficiency
    print("All expenses have been cleared!")

# Main function to interact with the user
def main():
    while True:
        print("\nExpense Tracker Application")
        print("1. Show Expenses")
        print("2. Add Expense")
        print("3. Remove Expense")
        print("4. Clear Expenses")
        print("5. Exit")

        choice = input("\nEnter your choice (1-5): ")

        if choice == '1':
            show_expenses()
        elif choice == '2':
            item = input("Enter the expense item: ")
            try:
                amount = float(input("Enter the amount: "))
                add_expense(item, amount)
            except ValueError:
                print("Invalid amount! Please enter a numeric value.")  # Issue 4: Add more robust input validation
        elif choice == '3':
            try:
                expense_number = int(input("Enter the expense number to remove: "))
                remove_expense(expense_number)
            except ValueError:
                print("Invalid input! Please enter a valid expense number.")
        elif choice == '4':
            clear_expenses()
        elif choice == '5':
            print("Exiting the application.")
            sys.exit()
        else:
            print("Invalid choice! Please choose a valid option.")

if __name__ == "__main__":
    main()
