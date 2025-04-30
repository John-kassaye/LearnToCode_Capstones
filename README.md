# LearnToCode_Capstones
# 💰 Ledger Console Application

This is a simple command-line based ledger system for managing deposits, payments, and generating financial reports. All data is stored in a CSV file.

## 📌 Features
<Details>
<Summary> Click here to seee Login System </Summary>
🔐 **Login System (New!)**
- **Sign In** – Existing users can log in using their credentials.
- **Sign Up** – New users can create an account.
[Login image](https://github.com/John-kassaye/LearnToCode_Capstones/blob/main/Accounting_Ledger_Application/src/Images/Login%20Image.png)

</details>

<Details>
<Summary> CLick to see Home Screen </Summary>
<br>
 🏠 Home Screen

The home screen provides the user with the following options. The application will continue running until the user chooses to exit.

- `D) Add Deposit` – Prompt the user for deposit information and save it to the CSV file.
- `P) Make Payment (Debit)` – Prompt the user for payment information and save it to the CSV file.
- `L) Ledger` – Display the ledger screen.
- `X) Exit` – Exit the application.

</Details>

<Details>
<Summary> Click here to see Ledger Screen </Summary>
<br>
 📒 Ledger Screen
 
The ledger displays entries with the most recent shown first.<br>

- `A) All` – Display all entries.
- `D) Deposits` – Show only deposits.
- `P) Payments` – Show only payments (negative values).
- `R) Reports` – Open the reports screen.
- `H) Home` – Return to the home screen.

</Details>

<Details><Summary> Click here to see Reports</Summary>
<br>
 📊 Reports

Users can run predefined or custom reports.

- `1) Month To Date`
- `2) Previous Month`
- `3) Year To Date`
- `4) Previous Year`
- `5) Search by Vendor` – Prompt for a vendor name and show matching entries.
- `6) Custom Search` *(Bonus Feature)* – Filter by:
  - Start Date
  - End Date
  - Description
  - Vendor
  - Amount
- `7) Summary Balance` – Displays the total Deposit, Payment, and Net balance of the account.
- `0) Back` – Return to the Ledger menu.
</Details>

<Details> 
<Summary> Click here to see the Data Storage </Summary>
<br>
 💾 Data Storage

- All transactions are saved in a CSV file.
- Each entry includes:
  - Date
  - Description
  - Vendor
  - Amount (positive for deposits, negative for payments)
</Details>

## 🛠 How to Run

1. Clone the repository:
    https://github.com/John-kassaye/LearnToCode_Capstones.git

