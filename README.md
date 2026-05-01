# 🏦 Matrix Bank: Account Ledger CLI

A high-performance, command-line financial tracking application built in Java. This application allows users to record, filter, and analyze business or personal financial transactions through a custom-built, animated terminal interface.

## 🚀 Overview

The Account Ledger App is designed to provide a robust, terminal-based user experience for tracking deposits and debits. It features a custom "Matrix" aesthetic, complete with screen-clearing rendering, frame-by-frame animations, and strict input-validation architecture to prevent data corruption.

All financial data is persistently stored and dynamically retrieved from a local `.csv` database, ensuring transaction history is maintained between sessions.

## ✨ Features

* **Custom UI Engine:** Built with ANSI escape codes and custom spatial teleportation logic (`\033[H\033[2J`) for a modern, flicker-free "Single Page Application" feel in the terminal.
* **Transaction Management:** Seamlessly add Deposits and Payments with defensive input trapping (preventing crashes from invalid string/double mismatches).
* **Dynamic Ledger:** View all transactions sorted automatically by Date and Time (newest first).
* **Advanced Reporting:** Query the CSV database using complex date-time logic to generate instant reports for:
    * Month-to-Date
    * Previous Month
    * Year-to-Date
    * Previous Year
    * Custom Search by Vendor Name
* **File I/O Persistence:** Writes seamlessly to `transactions.csv` using `BufferedWriter` and `String.format` for perfect pipe-delimited data storage.

## 🛠️ Tech Stack

* **Language:** Java (JDK 17)
* **Libraries:** `java.io`, `java.time`, `java.util`
* **Architecture:** Object-Oriented Programming (OOP), Custom I/O Streams, Command-Line Interface (CLI)

## 📸 Screenshots

*(Add your Git Bash screenshots to an `images` folder in your repo, then replace these links!)*

![Home Screen UI](images/home-screen.png)
![Ledger Reports](images/ledger-reports.png)

## 💻 Installation & Usage

To run this application locally, it is highly recommended to use **Git Bash**, **MinTTY**, or an equivalent terminal emulator that fully supports ANSI escape codes and cursor manipulation.

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/YOUR-USERNAME/account-ledger-app.git](https://github.com/YOUR-USERNAME/account-ledger-app.git)