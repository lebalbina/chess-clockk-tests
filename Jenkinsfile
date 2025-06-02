pipeline {
    agent any

    stages {
        stage('Checkout repo') {
            steps {
                git branch: 'main', url: 'https://github.com/lebalbina/chess-clockk-tests'
            }
        }

        stage('Start emulator') {
            script {
                bat 'emulator -avd Medium_Phone_API_36.0 0 -no-snapshot-load -no-audio -no-window'
                bat 'adb wait-for-device'
                bat 'adb shell getprop sys.boot_completed | grep -m 1 "1"'
            }
        }

        stage('Execute tests') {
            script {
                bat 'gradlew test'
            }
        }

        stage('Publish TestNG reports') {
            testNG(reportFilenamePattern: '**/testng-results.xml')
        }
    }
}
