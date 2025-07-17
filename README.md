# Selenium Test Framework

This project contains a comprehensive test automation framework built with:
- Selenium WebDriver for UI testing
- Rest Assured for API testing
- TestNG for test execution and assertions
- ExtentReports for test reporting

## Project Structure

```
src/
├── main/java/
│   ├── pages/       # Page Object Model classes
│   ├── model/       # Data models
│   └── utils/       # Utilities and helpers
└── test/java/
    ├── base/        # Test base classes
    └── tests/       # Test classes
```

## Features

- Page Object Model design pattern
- Extensible test base classes
- Separate base classes for UI and API testing
- Automated test reporting with screenshots
- API testing capabilities using Rest Assured
- Modular and maintainable test structure

## Prerequisites

- Java 11 or higher
- Maven
- Chrome browser

## Running Tests

To run UI tests:
```bash
mvn test -Dtest=LoginTest
```

To run API tests:
```bash
mvn test -Dtest=APITest
```
