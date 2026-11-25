# College Student Management System

A menu-driven Java console application for maintaining basic college student records. This Data Structures and Algorithms project demonstrates how to use `ArrayList`, `HashMap`, searching, sorting with a `Comparator`, methods, input validation, and text-file persistence together.

## Features

- Add a student using their name, course, and CGPA. IDs are generated automatically, starting at `S101`.
- View all students currently loaded in the system.
- Search by student ID, or search names using a partial, case-insensitive match.
- Sort and display students by CGPA in descending order.
- Delete a student by ID.
- Load saved records from `students.txt` at startup and save records when **Save and Exit** is selected.
- Validate that names and courses are not blank and that a CGPA is a number from `0` to `10`.

## Requirements

- Java Development Kit (JDK) 8 or later.
- A terminal or command prompt.
- Windows or Linux. No database server, web server, or internet connection is required.

## Compile and run

Open a terminal in the project directory (the directory containing `Main.java` and `students.txt`), then run:

```text
javac Main.java
java Main
```

The program displays a menu:

```text
1. Add a student
2. View all students
3. Search student by ID
4. Sort students by CGPA
5. Delete a student
6. Search student by name
7. Save and Exit
```

Enter the number for the operation you want. Choose **7** to save the current records and exit. Changes are written to disk on this option; exiting the process another way does not trigger the save operation.

## Data storage

Student records are stored one per line in the plain-text file `students.txt`, using this pipe-separated format:

```text
ID|Name|Course|CGPA
```

For example:

```text
S101|Aman Verma|B.Tech AIML|8.5
```

The application reads this file when it starts and writes the current records when you save and exit. The file path is relative to the program's working directory, so run the commands from the project directory to use the included data file. If the file does not exist, the application starts with no records and creates the file when it saves. Malformed lines, duplicate IDs, blank fields, invalid CGPAs, and CGPAs outside the supported range are skipped while loading.

The included `students.txt` contains sample records. Back up this file before manually editing or replacing the data.

## Data structures and algorithms

Each student is represented by a `Student` object containing an ID, name, course, and CGPA. `Main.java` contains both the `Student` class and the public `Main` class.

- An `ArrayList<Student>` keeps records in insertion order for display and iteration.
- A `HashMap<String, Student>` provides average constant-time lookup by ID. Additions and deletions update both collections.
- ID search uses the `HashMap`; name search scans the list and supports partial, case-insensitive matches.
- CGPA sorting uses a `Comparator` to order a copy of the list from highest to lowest, leaving the original record order unchanged.
- File handling loads records at startup and saves them on exit.

Let **N** be the number of student records:

| Operation | Time complexity |
| --- | --- |
| Add a student (including ID generation) | O(N) |
| Search by ID (average) | O(1) |
| Search by name | O(N) |
| Sort by CGPA | O(N log N) |
| Delete a student | O(N) |
| View all students | O(N) |
| Load or save records | O(N) |

Generated IDs start at `S101` and are based on the largest ID number currently present in the records. Because the application does not persist a separate ID counter, deleting the highest-numbered student can allow that ID to be generated again later.

## Project structure

```text
.
├── Main.java                                      # Student model and console application
├── students.txt                                   # Sample and persisted student records
├── Harshal_RU-25-10559_Project-for-DSA-PS-01.pdf  # Project interim report
└── README.md                                      # Project documentation
```

## Current scope and limitations

This version is a lightweight, single-user console program. It does not include a database, graphical interface, authentication, editing existing records, marks management, or attendance tracking. Data is stored locally in a plain-text file.

Possible future enhancements include student record updates, database integration, a Swing or JavaFX interface, marks and attendance management, user authentication, advanced filters, and PDF or CSV report export.
