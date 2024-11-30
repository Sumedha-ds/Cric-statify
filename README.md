# CricStatify

CricStatify is a web application designed to manage and analyze cricket player statistics. Users can input details of players, calculate important cricket metrics, store the data in a MySQL database, and export the results as a CSV file.

## Features

- **Dynamic Input Form**: Add details for multiple players dynamically using JavaScript.
- **Metrics Calculation**: Automatically calculate cricket statistics such as:
  - Strike Rate
  - Batting Average
  - Economy Rate
  - Bowling Average
- **Data Storage**: Store player details and statistics in a MySQL database.
- **Export to CSV**: Generate a CSV file containing all the stored player data.
- **Interactive Frontend**: A user-friendly form to input player details.

## Technologies Used

- **Backend**: Spring Boot (Java), Thymeleaf
- **Frontend**: HTML, CSS, JavaScript
- **Database**: MySQL
- **File Handling**: CSV

## How to Run the Project

1. **Clone the Repository**:
    ```bash
    git clone <repository-url>
    cd Cricket-Score-Analyzer
    ```

2. **Set Up the Database**:
   - Create a MySQL database named `cricket_stats`.
   - Update the `application.properties` file in the `src/main/resources` directory with your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/cricket_stats
     spring.datasource.username=<your-username>
     spring.datasource.password=<your-password>
     ```

3. **Build and Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

4. **Access the Application**:
   - Open a browser and navigate to `http://localhost:8080`.

5. **Input Player Details**:
   - Enter the number of players and provide their statistics using the dynamically generated form.

6. **View Results**:
   - Download the CSV file with player statistics from the application.



