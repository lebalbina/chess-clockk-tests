# Chess Clock App - Appium Test Suite

This is an automated UI test project for my custom **Chess Clock app** for Android, built using **Appium**, **Java**, and the **Page Object Model (POM)** pattern.

It verifies the complete user experience and functionality of the app through both **end-to-end** and **functional** tests covering all interactive components.

Currently, tests run locally on emulator or device. The next step is integrating with **local Jenkins** to enable triggered runs.

## 🗂️ Project Structure

```
ChessClockkTests/
├── app/
│   ├── src/test/
│   │   ├── java/   
│   │   └──resources/
│   │      └── testng.xml  
├── gradle/
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── .gitignore
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle
```

## Notes

Project under tests is available [here](https://github.com/lebalbina/chess-clockk)