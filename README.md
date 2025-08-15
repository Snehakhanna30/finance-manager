# 💰 Finance Manager - Full Stack Project

A personal finance management web application built using **Java Spring Boot (Backend)**, **React.js + Tailwind CSS (Frontend)**, and **MySQL Database**.  
It allows users to track expenses, manage income, set budgets, and view financial analytics in an easy-to-use dashboard.

---

## 🚀 Features
- **Expense Management** – Add, edit, delete, and search transactions.
- **Income Tracking** – Record all sources of income.
- **Budget Planning** – Set monthly/weekly budgets and track spending.
- **Analytics Dashboard** – View charts and summaries of your financial data.
- **Search & Filter** – Quickly find transactions by category, date, or description.
- **Responsive Design** – Works seamlessly on desktop and mobile.

---

## 🛠️ Tech Stack

### **Frontend**
- React.js
- Tailwind CSS
- Axios (API calls)

### **Backend**
- Java Spring Boot
- REST APIs
- JDBC / JPA

### **Database**
- MySQL

---
#Backend Setup (Spring Boot)
cd finance manager
# Update application.properties with your MySQL credentials
mvn clean install
mvn spring-boot:run


#Frontend Setup (React + Tailwind)
cd finance-track
npm install
npm start
Backend will run on http://localhost:8080
Frontend will run on http://localhost:3000


#MySQL Database Setup

MySQL install & run karo.

MySQL CLI or phpMyAdmin open karo.

New database create karo:

CREATE DATABASE finance_manager;


Database credentials backend/src/main/resources/application.properties me set karo:

spring.datasource.url=jdbc:mysql://localhost:3306/finance_manager
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

🤝 Contributing


Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.



#Author

Sneha Khanna
