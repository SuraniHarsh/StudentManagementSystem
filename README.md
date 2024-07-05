# Student Management System

### Prerequisites

- Java Development Kit (JDK)
- Apache Maven
- Eclipse IDE (or any IDE with Maven support)
- MySQL (or any relational database)

### Installation Steps

1. **Clone the repository:**

   ```bash
   git clone <repository-url>
   cd StudentManagementSystem
   ```

2. **Import project into Eclipse:**

   - Open Eclipse IDE.
   - Select `File` > `Import` > `Existing Maven Projects`.
   - Navigate to the cloned repository and select the `pom.xml` file.

3. **Configure Database:**

   - Create a MySQL database named `StudentManagementSystem`.
   - Update the database connection details in `DatabaseConnector.java` if necessary:
     - URL: `jdbc:mysql://localhost:3306/StudentManagementSystem`
     - USER: `root`
     - PASSWORD: `root`

   ```java
   // DatabaseConnector.java
   private static final String URL = "jdbc:mysql://localhost:3306/StudentManagementSystem";
   private static final String USER = "root";
   private static final String PASSWORD = "root";
   ```



4. **Import Data from JSON File:**

   - Update the `filePath` variable in `App.java` with the path to your JSON file containing student data.

   ```java
   // App.java
   String filePath = "<path-to-your-json-file>";
   ```
   
5. **Run the Application:**
   - Locate and run `App.java` as a Java Application.

6. **Usage**

   - Upon running the application, you will be presented with a menu.
   - Choose options to interact with the student data.
