# 📚 Digital Library Management System

A full-stack, web-based Digital Library Management System built with **Spring Boot** and modern frontend web technologies. The platform automates everyday library administration—eliminating manual ledger maintenance, minimizing inventory discrepancies, and providing interactive visual insights into circulation trends.

---

## 📌 Project Overview

Traditional paper-based or disconnected spreadsheet methods often struggle with lost books, overdue follow-ups, and slow lookups. This project delivers a centralized operational dashboard where administrators can manage physical book assets, track borrowers in real time, and audit daily transactions seamlessly.

---

## 🚀 Key Features

* **Interactive Analytics Dashboard:** Real-time metrics tracking total catalog volume, currently issued copies, overdue returns, and category-wise circulation charts.
* **Complete Book Catalog Management:** Add, edit, remove, and filter books by title, author, category, or ISBN with immediate availability updates.
* **Borrower & Member Records:** Maintain student/member profiles, verify eligibility, and manage individual borrowing histories.
* **Transaction & Circulation Tracking:** Date-stamped checkout and check-in workflows with automatic return status tracking and due-date monitoring.
* **Responsive Modern UI:** Clean, intuitive user interface styled with Bootstrap 5 components and dynamic visualizations powered by Chart.js.

---

## 🛠️ Tech Stack & Tools

* **Backend Framework:** Spring Boot (Java 17+)
* **Build & Dependency Tool:** Apache Maven
* **Frontend:** HTML5, CSS3, JavaScript (ES6)
* **Styling & Components:** Bootstrap 5
* **Data Visualization:** Chart.js
* **Development & Version Control:** VS Code, Git, GitHub Desktop

---

## 🏗️ System Workflow

1. **Cataloging:** New books are registered into the database with specific copies and category tags.
2. **Issue Operation:** A book is mapped to a verified student record with an automatic timestamp and calculated due date.
3. **Return & Restock:** Returned books update the transaction history and instantly restock the available inventory count.
4. **Data Aggregation:** The analytics engine compiles circulation records to render live dashboard metrics and distribution charts.

---

## ⚙️ How to Run Locally

### Prerequisites
* **Java Development Kit (JDK):** Version 17 or higher installed on your system.
* **Browser:** Google Chrome, Microsoft Edge, or Firefox.

### Step-by-Step Setup

1. **Clone or Download the Project:**
   * Download the repository ZIP file and extract it, or clone directly using Git:
     ```bash
     git clone https://github.com/paripelliganesh073-beep/library.git
     ```

2. **Open in VS Code:**
   * Launch VS Code.
   * Go to **File** $\rightarrow$ **Open Folder...** and select the root `digitallibrary` folder.

3. **Run the Application:**
   * Open the integrated terminal in VS Code (**Terminal $\rightarrow$ New Terminal**) and execute:
     * **Windows:**
       ```cmd
       ./mvnw.cmd spring-boot:run
       ```
     * **macOS / Linux:**
       ```bash
       ./mvnw spring-boot:run
       ```
   * *(Alternatively, click the **Run / Debug** button directly inside VS Code).*

4. **Launch the Web Interface:**
   * Once the console shows that Tomcat has started on port 8080, open your web browser and visit:
     ```text
     http://localhost:8080
     ```

---

## 🔮 Future Enhancements

* Role-based access control (Admin, Librarian, Student portals).
* Automated email or SMS notifications for approaching and overdue return dates.
* Barcode / QR-code scanning support for quick physical checkouts.
