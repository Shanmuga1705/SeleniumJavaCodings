# Project Documentation for Selenium E2E Tests

This project is a Selenium end-to-end testing framework written in Java. It is designed to facilitate automated testing of web applications by providing a structured approach to managing page objects and WebDriver instances.

## Project Structure

- **src/main/java/com/example/pages/BasePage.java**: Contains the `BasePage` class, which serves as a base class for all page objects. It includes common methods for interacting with web elements.
  
- **src/main/java/com/example/utils/WebDriverUtils.java**: Contains the `WebDriverUtils` class, which provides utility methods for managing the WebDriver instance, such as initializing and quitting the driver.
  
- **src/test/java/com/example/tests/BaseTest.java**: Contains the `BaseTest` class, which serves as a base class for all test classes. It includes setup and teardown methods for initializing the WebDriver before tests and closing it afterward.

- **pom.xml**: The configuration file for Maven, specifying project dependencies, build settings, and plugins required for the project.

## Getting Started

1. **Prerequisites**: Ensure you have Java and Maven installed on your machine.
2. **Clone the Repository**: Clone this repository to your local machine.
3. **Install Dependencies**: Navigate to the project directory and run `mvn install` to install the required dependencies.
4. **Run Tests**: Use Maven to run the tests with the command `mvn test`.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.