# 📚 DULC Library Rental Management System

A desktop **Library Rental Management System** built with **Java, JavaFX, FXML, CSS, and Maven**.

The application provides a graphical interface for library members to log in using their library credentials, browse available books, view book information, select a rental period, calculate rental costs, add books to a cart, and confirm their order.

---

## ✨ Features

### 🔐 Member Login

Users authenticate using four pieces of information:

* Username
* Seat Number
* Library ID
* Password

Credentials are read from the application's local `userData.txt` file. When authentication succeeds, the application creates a session containing the user's username, seat number, library ID, and profile picture. Invalid credentials display an error message.

### 📖 Book Catalogue

The main library screen provides six books:

| Book                     | Author              | Base Price / Day |
| ------------------------ | ------------------- | ---------------: |
| Atomic Habits            | James Clear         |            $0.50 |
| Dog Man                  | Dav Pilkey          |            $0.10 |
| Mein Kampf               | Adolf Hitler        |            $2.00 |
| Simpson V                | Sharky Finny        |            $0.30 |
| A Court Of Mist And Fury | Sarah J. Maas       |            $0.50 |
| OOP With Java            | Mukesh Kumar Rathee |            $1.20 |

Book descriptions are stored separately in `bookDescriptions.txt` and loaded when a book is selected.

### 📘 Book Overview

Selecting a book opens a dedicated **BOOK OVERVIEW** screen containing:

* Book cover
* Title
* Author
* Description
* Base rental price
* Rental-date selector
* Calculated rental price
* Add to Cart button
* Checkout button

### 📅 Rental Price Calculation

The user selects the date until which the book will be borrowed.

The rental price is calculated as:

```text
Rental Price = Number of Rental Days × Base Price
```

The selected date must be later than the current date. Invalid dates disable the cart action and display an error message.

### 🛒 Shopping Cart

Books can be added to an in-memory cart.

The cart maintains:

* A list of selected book titles
* The accumulated rental cost

The cart can be accessed from the shopping-cart icon in the main menu.

### 💳 Checkout

The checkout screen displays:

* Username
* Library ID
* Seat Number
* Selected books
* Total rental cost

The user can return to the previous screen or confirm the order.

If the cart is empty, the application prevents checkout and displays an error message.

### 🚪 Logout

Users can log out from the main menu.

Logging out:

* Returns to the login screen
* Clears the current cart
* Resets the cart's total price
* Clears the selected book list

---

## 🖥️ Application Flow

```text
┌───────────────┐
│  Login Screen │
└───────┬───────┘
        │
        │ Valid credentials
        ▼
┌──────────────────┐
│   Library Menu   │
│   Book Catalogue │
└────────┬─────────┘
         │
         │ Select book
         ▼
┌────────────────────┐
│   Book Overview    │
│                    │
│ • Book information │
│ • Rental date      │
│ • Price calculation│
└─────────┬──────────┘
          │
          │ Add to Cart
          ▼
┌──────────────────┐
│   Cart / Billing │
│                  │
│ • Books          │
│ • User details   │
│ • Total cost     │
└────────┬─────────┘
         │
         │ Confirm Order
         ▼
┌──────────────────────┐
│   Thank You Screen   │
│   + Logout button    │
└──────────────────────┘
```

---

## 🛠️ Technologies Used

* **Java 21**
* **JavaFX**

  * JavaFX Controls
  * JavaFX FXML
* **FXML** for UI layout
* **CSS** for application styling
* **Maven** for dependency and build management
* **JUnit 5** dependencies are included in the Maven configuration for testing

The project uses the Java module system through `module-info.java` and requires:

```text
javafx.controls
javafx.fxml
```

The `libraryrental.libraryrental` package is opened to JavaFX FXML and exported by the module.

---

## 📁 Project Structure

```text
Library-Rental/
│
├── .mvn/
│   └── wrapper/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── module-info.java
│       │   │
│       │   └── libraryrental/
│       │       └── libraryrental/
│       │           ├── Main.java
│       │           ├── LoginController.java
│       │           ├── LoginModel.java
│       │           ├── MenuController.java
│       │           ├── SummaryController.java
│       │           ├── CheckoutController.java
│       │           └── EndController.java
│       │
│       └── resources/
│           └── libraryrental/
│               └── libraryrental/
│                   ├── login-view.fxml
│                   ├── menu-view.fxml
│                   ├── summary-view.fxml
│                   ├── checkout-view.fxml
│                   ├── end-view.fxml
│                   ├── application.css
│                   ├── userData.txt
│                   ├── bookDescriptions.txt
│                   └── image assets
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

## 🧩 Main Components

### `Main.java`

The JavaFX application entry point.

It:

* Extends `Application`
* Loads `login-view.fxml`
* Creates the initial scene
* Applies `application.css`
* Sets the window title to `DULC Library`
* Provides a reusable FXML scene loader

### `LoginController.java`

Handles the login interface and calls `LoginModel.validate()` to authenticate the entered credentials.

Successful authentication loads the main library menu.

### `LoginModel.java`

Responsible for validating login credentials against the records stored in `userData.txt`.

The expected record structure is:

```text
username;seat number;library ID;password;profile picture
```

### `MenuController.java`

Controls the main library catalogue.

It:

* Maintains the current user's session information
* Displays the user's profile information
* Handles book selection
* Loads book descriptions
* Opens book overview screens
* Opens the cart
* Handles logout

### `SummaryController.java`

Controls the book overview and rental selection process.

It:

* Displays the selected book
* Displays its description and author
* Displays the base price
* Validates the rental date
* Calculates the rental price
* Adds books to the cart

### `CheckoutController.java`

Displays the cart contents and billing information.

It also validates that the cart is not empty before allowing the user to confirm the order.

### `EndController.java`

Controls the final screen and returns the user to the login screen when **LOGOUT** is selected.

---

## 📦 Data Storage

This project does not use a database.

Instead, it uses local text files:

### `userData.txt`

Stores local user authentication information and profile-picture references.

### `bookDescriptions.txt`

Stores book descriptions using the format:

```text
Book Name;Book Description
```

Book descriptions are searched by title when the user opens a book overview.

---

## 🎨 User Interface

The UI is implemented using **JavaFX FXML** and styled through `application.css`.

The application contains five main views:

1. `login-view.fxml` — Login
2. `menu-view.fxml` — Library catalogue
3. `summary-view.fxml` — Book overview and rental selection
4. `checkout-view.fxml` — Cart and billing
5. `end-view.fxml` — Order completion / logout

The application also includes image assets for the library branding, book covers, user profiles, and interface elements.

---

## ⚙️ Requirements

Before running the project, make sure you have:

* **JDK 21**
* **Maven** (or use the included Maven wrapper)
* A JavaFX-compatible environment

The project's Maven configuration targets Java 21 and uses JavaFX `21-ea+5`.

---

## 🚀 Running the Project

Clone the repository:

```bash
git clone https://github.com/khizeryz14/Library-Rental.git
cd Library-Rental
```

### Using Maven

The project includes Maven wrapper scripts:

**Windows:**

```bash
mvnw.cmd clean package
```

**Linux / macOS:**

```bash
./mvnw clean package
```

### Running from an IDE

The safest way with the current repository configuration is to open the project as a **Maven project** in an IDE such as IntelliJ IDEA and run:

```text
Main.java
```

as the JavaFX application.

---

## ⚠️ Current Configuration Notes

The current source code contains several **absolute Windows filesystem paths** pointing to the original development directory, for example paths under:

```text
C:\Khizer Projects (CPP)\Java OOP\LibraryRental\
```

These occur in parts of the application responsible for loading user pictures, book images, book descriptions, and the login data.

Therefore, when cloning the project onto another machine, these paths may need to be updated before the application can load all resources correctly.

The project already stores the resources under `src/main/resources`, so these paths can be replaced with classpath/resource-based loading for a more portable setup.

Additionally, the Maven JavaFX plugin configuration currently references:

```text
libraryrental.libraryrental.HelloApplication
```

while the repository's actual JavaFX application entry point is `Main.java`. Running `Main.java` directly from the IDE avoids relying on that mismatched plugin entry-point configuration.

---

## 🎓 Project Context

This application was developed as a **Java Object-Oriented Programming project** and demonstrates the use of:

* Java classes and objects
* Encapsulation
* JavaFX GUI development
* FXML-based UI design
* Event-driven programming
* File I/O
* Scene switching
* Session state
* Collections
* Basic modular Java application structure
* Maven dependency management

The login screen identifies the project with **UBIT Literary Club**, and the application branding uses **DULC Library Rental**.

---

## 👥 Project Contributors

The login interface credits:

* Khizer
* Shafin
* Junaid
* Mohsin

Student IDs are also displayed within the application's login screen.

---

## 📄 License

No license file is currently included in the repository.
