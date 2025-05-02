# LearnToCode_Capstones
# 💰 Ledger Console Application

This is a simple command-line based ledger system for managing deposits, payments, and generating financial reports. All data is stored in a CSV file.

## 📌 Features
<Details>
<Summary> 🔐 Login System (New!) </Summary>
<br>

- **Sign In** – Existing users can log in using their credentials.
- **Sign Up** – New users can create an account.

[Login_Image](https://github.com/John-kassaye/LearnToCode_Capstones/blob/main/Accounting_Ledger_Application/src/Images/Login_Screen.png)
</details>

<Details>
<Summary> 🏠 Home Screen </Summary>
<br>

The home screen provides the user with the following options. The application will continue running until the user chooses to exit.

- `D) Add Deposit` – Prompt the user for deposit information and save it to the CSV file.
- `P) Make Payment (Debit)` – Prompt the user for payment information and save it to the CSV file.
- `L) Ledger` – Display the ledger screen.
- `X) Exit` – Exit the application.

[Home_Screen_Image](https://github.com/John-kassaye/LearnToCode_Capstones/blob/main/Accounting_Ledger_Application/src/Images/Home_Screen.png)

</Details>

<Details>
<Summary> 📒 Ledger Screen </Summary>
<br>
 
The ledger displays entries with the most recent shown first.<br>

- `A) All` – Display all entries.
- `D) Deposits` – Show only deposits.
- `P) Payments` – Show only payments (negative values).
- `R) Reports` – Open the reports screen.
- `H) Home` – Return to the home screen.

[Ledger_Screen_Image](https://github.com/John-kassaye/LearnToCode_Capstones/blob/main/Accounting_Ledger_Application/src/Images/Ledger%20Menu.png)
</Details>

<Details><Summary> 📊 Reports</Summary>
<br>

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
- `9) Exit`

[Reports_Screen_Image](https://github.com/John-kassaye/LearnToCode_Capstones/blob/main/Accounting_Ledger_Application/src/Images/Reports%20Menu.png)
</Details>

<Details> 
<Summary>  💾 Data Storage </Summary>
<br>


- All transactions are saved in a CSV file.
- Each entry includes:
  - Date
  - Description
  - Vendor
  - Amount (positive for deposits, negative for payments)
</Details>

<Details>
<Summary> Interesting code </Summary>

</Details>

## 🛠 How to Run

1. Clone the repository:
    https://github.com/John-kassaye/LearnToCode_Capstones.git

