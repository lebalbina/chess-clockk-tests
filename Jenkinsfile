pipeline {
    agent any

    stages {
        stage('Checkout repo') {
            steps {
                checkout scm
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
                bat 'gradlew clean test'
            }
        }

        stage('Publish TestNG reports') {
            testNG(reportFilenamePattern: '**/testng-results.xml')
        }
    }
}
