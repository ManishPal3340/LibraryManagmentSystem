# LibraryManagmentSystem

# Library & Student Management System in Java

This is a **Java console application** for managing a library and student records. The project uses **JDBC** to connect with a **MySQL database**, allowing CRUD (Create, Read, Update, Delete) operations on books and students.

---

## Features

### Library Management
- Create the `books` table automatically.
- Add new books.
- View all books.
- Update book details.
- Delete books by ID.
- Fetch book details by ID.

### Student Management
- Create the `mystud111` table automatically.
- Add new students.
- View all students.
- Update student details.
- Delete students by ID.
- Fetch student details by ID.

---

## Database Structure

### Books Table (`books`)
| Column Name | Data Type       | Key         |
|-------------|----------------|------------|
| bookId      | VARCHAR(30)    | PRIMARY KEY |
| title       | VARCHAR(100)   |             |
| author      | VARCHAR(50)    |             |
| publisher   | VARCHAR(50)    |             |
| year        | INT            |             |

### Students Table (`mystud111`)
| Column Name | Data Type       | Key         |
|-------------|----------------|------------|
| stdId       | VARCHAR(30)    | PRIMARY KEY |
| stdname     | VARCHAR(50)    |             |
| stdmail     | VARCHAR(50)    |             |
| stddob      | VARCHAR(30)    |             |

---

## How to Run

1. Clone the repository:
```bash
git clone https://github.com/yourusername/Library-Student-Management-System-Java.git
