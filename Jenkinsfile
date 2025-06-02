pipeline {
    agent any

    environment {
        PATH = "${env.ANDROID_HOME}\\emulator;${env.ANDROID_HOME}\\platform-tools;${env.PATH}"
    }

    stages {
        stage('Checkout repo') {
            steps {
                checkout scm
            }
        }

        stage('Start emulator') {
            steps {
                script {
                    bat 'start /B emulator -avd Medium_Phone_API_36.0 -port 5554 -no-snapshot-load -no-audio -no-window'
                    bat 'C:\\jenkins\\wait_for_emulator_ready.bat'
                }
            }
        }
        
        stage('Prepare config') {
            steps {
                configFileProvider([configFile(fileId: 'clockk-config', targetLocation:'app/config.properties')]) {}
            }
        }

        stage('Execute tests') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'UNSTABLE') {
                    script {
                        bat 'gradlew test'
                    }
                }
            }
        }

        stage('Publish TestNG reports') {
            steps {
                testNG(reportFilenamePattern: '**/app/build/reports/tests/test/testng-results.xml')
            }
        }
    }
}
