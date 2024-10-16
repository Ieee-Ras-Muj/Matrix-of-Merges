import sys

todo_list = []

def show_tasks():
    if len(todo_list) == 0:
        print("No tasks in your to-do list!")
    else:
        print("Here are your tasks:")
        for i, task in enumerate(todo_list):
            print(f"{i+1}. {task}")

def add_task(task):
    todo_list.append(task)
    print(f"Task '{task}' added successfully!")

def remove_task(task_number):
    if task_number > len(todo_list) or task_number <= 0:
        print(f"Invalid task number: {task_number}")
    else:
        removed_task = todo_list.pop(task_number-1)
        print(f"Task '{removed_task}' removed successfully!")

def clear_tasks():
    global todo_list
    todo_list = []  # Issue 1: Consider a more efficient way to clear the list
    print("All tasks have been cleared!")

def main():
    while True:
        print("\nTo-Do List Application")
        print("1. Show Tasks")
        print("2. Add Task")
        print("3. Remove Task")
        print("4. Clear Tasks")
        print("5. Exit")

        choice = input("\nEnter your choice (1-5): ")

        if choice == '1':
            show_tasks()
        elif choice == '2':
            task = input("Enter the task you want to add: ")
            add_task(task)
        elif choice == '3':
            task_number = int(input("Enter the task number to remove: "))
            remove_task(task_number)
        elif choice == '4':
            clear_tasks()
        elif choice == '5':
            print("Exiting the application.")
            sys.exit()
        else:
            print("Invalid choice! Please choose a valid option.")

if __name__ == "__main__":
    main()
