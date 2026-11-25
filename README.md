<div align="center">

# 🎓 College Student Management System

### *A Menu-Driven Java Console Application Powered by Core Data Structures & Plain-Text File Persistence*

![Java](https://img.shields.io/badge/Java-JDK%208%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Data Structures](https://img.shields.io/badge/Data%20Structures-ArrayList%20%7C%20HashMap-1E88E5?style=for-the-badge)
![Algorithms](https://img.shields.io/badge/Algorithms-Search%20%7C%20Comparator%20Sort-8E24AA?style=for-the-badge)
![Storage](https://img.shields.io/badge/Persistence-Text%20File%20I%2FO-2E7D32?style=for-the-badge)
![Interface](https://img.shields.io/badge/Interface-CLI%20Console-455A64?style=for-the-badge)
![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20Linux-0078D6?style=for-the-badge)

---

**A lightweight, zero-dependency Java console utility engineered for managing, querying, ranking, and persisting college student academic records.**  
Built as a practical Data Structures and Algorithms (DSA) implementation, this project bridges in-memory collection efficiency (`ArrayList` + `HashMap`) with durable local disk storage (`students.txt`).

</div>

---

## 📑 Table of Contents

1. [🌟 Project Overview](#-project-overview)
2. [✨ Complete Feature Suite](#-complete-feature-suite)
3. [🛠️ System Requirements & Environment](#️-system-requirements--environment)
4. [📂 Repository & Directory Architecture](#-repository--directory-architecture)
5. [🚀 Compilation & Execution Guide](#-compilation--execution-guide)
6. [🖥️ Interactive Menu & End-to-End Walkthrough](#️-interactive-menu--end-to-end-walkthrough)
7. [💾 File Persistence & Storage Specification](#-file-persistence--storage-specification)
8. [🛡️ Input Validation & Fault-Tolerance Rules](#️-input-validation--fault-tolerance-rules)
9. [🧠 Data Structures & Algorithmic Design](#-data-structures--algorithmic-design)
10. [⏱️ Time & Space Complexity Matrix](#️-time--space-complexity-matrix)
11. [⚠️ Current Scope & Design Boundaries](#️-current-scope--design-boundaries)
12. [🔮 Roadmap & Future Enhancements](#-roadmap--future-enhancements)

---

## 🌟 Project Overview

The **College Student Management System** is a standalone, terminal-based Java application designed to handle routine student record operations cleanly and reliably without requiring external databases, frameworks, or network connectivity.

### 🎯 Core Engineering Objectives
* **Dual-Structure In-Memory Synchronization:** Combines an `ArrayList<Student>` for ordered sequential operations with a `HashMap<String, Student>` for constant-time key lookups.
* **Non-Destructive Ranking:** Uses a custom `Comparator` on a cloned list so students can be ranked by CGPA without corrupting their original registration sequence.
* **Resilient Plain-Text Persistence:** Reads and writes human-readable pipe-delimited (`|`) records in `students.txt` with built-in parser guards against malformed or corrupted lines.
* **Modular Console Architecture:** Organizes domain modeling (`Student` class), file I/O handlers, validation checks, and menu workflows into clean, cohesive Java methods inside `Main.java`.

---

## ✨ Complete Feature Suite

| Badge | Feature Module | Functional Capabilities & Behavior |
| :---: | :--- | :--- |
| ➕ | **Student Registration** | Captures **Student Name**, **Course**, and **CGPA** interactively via console prompts. Automatically assigns a sequential, collision-free Student ID starting at `S101`. |
| 📋 | **Complete Roster View** | Displays every student record currently loaded in active memory, preserving the exact chronological order in which records were loaded or added. |
| 🎯 | **Exact ID Lookup** | Retrieves a specific student record instantaneously using their unique Student ID (e.g., `S101`) backed by `HashMap` indexing. |
| 🔎 | **Flexible Name Search** | Searches across all student names using **partial, case-insensitive substring matching** (e.g., searching `"aman"` matches `"Aman Verma"`). |
| 🏆 | **CGPA Merit Sorting** | Dynamically sorts and displays all students in **descending order of CGPA** (highest to lowest) while keeping the underlying insertion order untouched. |
| 🗑️ | **Record Deletion** | Permanently removes a targeted student record from both active in-memory collections using their Student ID. |
| 💾 | **Session Save & Exit** | Serializes all active in-memory records back to `students.txt` upon selecting **Save and Exit**, guaranteeing data durability across sessions. |
| 🛡️ | **Input & Data Guardrails** | Enforces non-blank text inputs for Name and Course, validates numeric formatting for CGPA, and restricts CGPA strictly within `0` to `10`. |

---

## 🛠️ System Requirements & Environment

This application relies exclusively on the **Java Standard Edition (Java SE)** runtime library—no third-party `.jar` files, build tools, or database servers are needed.

| Requirement | Specification | Verification Command |
| :--- | :--- | :--- |
| ☕ **Java Development Kit** | **JDK 8 or later** (`javac` compiler & `java` runtime) | `java -version` & `javac -version` |
| 💻 **Operating System** | **Windows** (Command Prompt / PowerShell) or **Linux** (Bash / Terminal) | Any standard CLI shell |
| 🌐 **Network / Services** | **None** — 100% offline local execution | Zero external dependencies |

---

## 📂 Repository & Directory Architecture

```text
College-Student-Management-System/
│
├── 📄 Main.java                                      # Complete Java source code (Student entity, DSA collections, File I/O & CLI menu)
├── 🗄️ students.txt                                   # Local plain-text datastore containing sample & persisted student records
├── 📑 Harshal_RU-25-10559_Project-for-DSA-PS-01.pdf  # Formal academic interim project report & documentation
└── 📘 README.md                                      # Detailed technical & user documentation
