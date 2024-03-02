# Workshop Application Documentation

## 1. Introduction

The Workshop Application is a REST-based system designed to streamline workshop operations in customer service. It provides users with the ability to check the repair status of vehicles and receive email notifications upon repair completion. This documentation aims to provide an overview of the application's features, usage, and technical details.
## 2. Getting Started
### Installation

    Clone the project repository from Git.
    Import the project into your preferred Java IDE (designed in InteliJ 2023.3).
    Ensure you have the Java version 17 installed.

### Configuration

    Ensure database configurations are correctly set up in application.properties. H2 is configured by default.
    Modify email configurations in email.properties for access your service.

## 3. Features

    User Authentication and Authorization: Users can log in and add new user securely with new credentials. 
    Admin can assign user with car in workshop. Basic authorization is implemented to ensure proper access 
    control.
    Repair status: The administrator adds a car to the database and automatically creates the first task 
    basic inspection. Subsequently, additional repairs are added according to the owner's suggestions 
    or faults detected during the inspection
    Repair Status Check: Users can query the repair status of vehicles by providing his credentials.
    Email Notifications: Email notifications are sent to users upon repair completion.

## 4. Technologies Used

    Spring Boot Framework: For developing RESTful APIs with basic authorization features.
    Hibernate: For object-relational mapping and data storage.
    Lombok: For reducing boilerplate code.
    Field Validation: For validating input data like VIN number or password.
    Email Service: For sending email notifications.
    JUnit: For unit testing.
    RestAssured: For integration testing.
    Postman: For API testing and data input/output.
    Git: For version control.

## 5. Application Structure

    src/main/java: Contains the application source code.
    src/test/java: Contains unit and integration tests.
    src/main/resources: Contains configuration files.

## 6. Usage

    Running the Application:
        Start the application locally using your IDE or by running mvn spring-boot:run in the terminal.

    Interacting with the API:
        Use Postman to send POST requests to the /user/add endpoint.
        Authenticate using the Postman GET request to endpoint /user/email/{userEmail} with valid credentials.

## 7. Authentication and Authorization

    Users can register in using their email adress and password via the /user/add endpoint.
    Authentication successful after proper credentials included in all requests.

## 8. Database Schema

### Users Table
username	password	role
example_user	hashed_password	ROLE_USER

### Repairs Table
repair_id	customer_name	vehicle_make	vehicle_model	status
RP123456	John Doe	Toyota	Camry	In Progress

## 9. Testing

### Unit Tests (JUnit)

    Unit tests are located in the src/test/java directory.
    Run tests using your IDE or mvn test command.

### Integration Tests (RestAssured)

    Integration tests are located in the src/test/java directory.
    Run tests using your IDE or mvn verify command.

## 10. Deployment

    Deploy the application to your preferred environment (local, cloud, etc.).
    Ensure proper configuration for the deployment environment.

## 11. Some problems during project

    Vin crc verification. There is no only one crc algorithm for Vin number. 
    Most comapany has them own crc algorithm. The most popular is Luna10 
    (inculde weight multiply system and modulo divide by 11 method).

## 12. Troubleshooting

  ### 1. Application Fails to Start

Issue: The application fails to start, and an error message is displayed.

Solution:

    Check if all required dependencies are installed and configured correctly.
    Verify that the database connection settings in application.properties are accurate.
    Review the application logs for detailed error messages to pinpoint the issue.

  ### 2. Unable to Authenticate

Issue: Users are unable to authenticate and receive errors when attempting to log in.

Solution:

    Ensure that the user is registered.
    Ensure that the provided credentials are correct.
    Check the user roles and permissions to ensure proper access control.

  ### 3. Email Notifications Not Sending

Issue: Email notifications are not being sent to users upon repair completion.

Solution:

    Verify that the email service configuration in email.properties is correct, including the SMTP server settings.
    Check if the email address provided for notifications is valid and accessible.
    Review the application logs for any errors related to the email service.

### 4. Database Connection Issues

Issue: The application encounters errors related to database connectivity.

Solution:
<div class="wrap-text">
    Check if the database server is running and accessible. H2 server is running all the time with application.
    Verify that the database credentials and connection settings in application.properties are accurate.
    Test the connection manually using a database client or command-line tool.
</div>
    
## 13. Future Improvements

    Roadmap for future development includes adding additional features such as user management page.
    Feature requests and suggestions are encouraged.

## 14. License

    This project is licensed under the MIT License.

## 15. Contact

    For support or inquiries, contact the project maintainers at nes211nes211nes@gmail.com.
    Project repository: https://github.com/nes211/Workshop
