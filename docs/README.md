# Squid User Guide

## **Introduction**
Squid is a simple and intuitive chatbot task manager designed to help users organise their tasks efficiently. 
Using a conversational interface, Squid allows users to add, delete, mark, and list tasks in a user-friendly format.

## **Table of Contents**
- [Getting Started](#getting-started)
- [Features](#features)
    - Adding Tasks: [`todo`](#adding-todo-task)
    - Adding Deadlines: [`deadline`](#adding-deadline-task)
    - Adding Events: [`event`](#adding-event-task)
    - Listing Tasks: [`list`](#listing-tasks)
    - Deleting Tasks: [`delete`](#deleting-tasks)
    - Marking Completed Tasks: [`mark`](#marking-tasks)
    - Unmarking Incomplete Tasks: [`unmark`](#unmarking-tasks)
    - Finding Tasks with Keyword: [`find`](#finding-tasks)
    - Showing Tasks on Date: [`show`](#showing-tasks-on-date)
    - Clearing Task List: [`clear`](#clearing-task-list)



---

## **Getting Started**

To launch Squid, run the following command in the terminal:

```sh
java -jar squid.jar
```

---

## **Features**

---
### Adding Todo Task
To add a todo task, use: `todo <task description>`

**Example:**
```sh
todo cook krabby patty
```
**Expected output:**
```
Ok! I have added:
[T][] cook krabby patty
Now you have 6 tasks!
```
(*The number of tasks is based on your current list of tasks*)

---

### Adding Deadline Task
To add a deadline task, use: `deadline <task description> /by YYYY-MM-DD HHmm`

**Example:**
```sh
deadline return book /by 2025-03-03 1300
```
**Expected output:**
```
Ok! I have added:
[D][] return book (by: Mar 3 2025 13:00)
Now you have 7 tasks!
```
(*The number of tasks is based on your current list of tasks*)

---

### Adding Event Task
To add a event task, use: `event <task description> /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm`

**Example:**
```sh
event meet patrick /from 2025-03-05 1300 /to 2025-03-05 1500
```
**Expected output:**
```
Ok! I have added:
[E][] meet patrick (from: Mar 5 2025 13:00 to: Mar 5 15:00)
Now you have 8 tasks!
```
(*The number of tasks is based on your current list of tasks*)

---

### Listing tasks
To list all tasks, use: `list`

**Example:**
```sh
list
```
**Expected output:**
```
Here are your tasks:
1. [T][] eat
2. [T][] find the spatula
3. [D][] submit new menu (by: Feb 21 2025 23:59)
4. [T][] wash the kitchen
5. [T][] clean the tables
6. [T][] cook krabby patty
7. [D][] return book (by: Mar 3 2025 13:00)
8. [E][] meet patrick (from: 2025-03-05 1300 to: 2025-03-05 1500)
```

---

### Deleting Tasks
To delete a Task, use: `delete <task index>`

**Example:**
```sh
delete 2
```

**Expected output:**
```
Task removed:
[T][] find the spatula
Remaining tasks: 7
```
(*The number of tasks is based on your current list of tasks*)

---

### Marking Tasks
To mark a task as completed, use: `mark <index>`

**Example:**
```sh
mark 2
```

**Expected output:**
```
Marked as done!
```

---

### Unmarking Tasks
To mark a task as completed, use: `unmark <index>`

**Example:**
```sh
mark 2
```

**Expected output:**
```
Marked as undone!
```

---

### Finding Tasks
To find specific tasks, use: `find <keyword>`

**Example:**
```sh
find krabby patty
```

**Expected output:**
```
Here are the matching tasks in your list:
1. [T][] cook krabby patty
```

---

### Showing Tasks on Date
To find specific tasks on date, use: `show <YYYY-MM-DD>`

**Example:**
```sh
show 2025-03-03
```

**Expected output:**
```
Tasks on 2025-03-03:
1. [D][] return book (by: Mar 3 2025 13:00)
```

---

### Clearing Task List
To clear the Task List, use: `clear`

**Example:**
```sh
clear
```

**Expected output:**
```
Task list is cleared!