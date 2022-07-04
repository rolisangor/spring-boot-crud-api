def groovy

pipeline {
    agent any
    environment {
        NEW_VERSION = '1.3.0'
    }
    tools {
        maven 'Maven'
    }

    stages {

        stage("build") {
            when {
                expression {
                    BRANCH_NAME  == 'main'
                }
            }
            steps {
                script {
                    groovy.buildApp()
                }
            }
        }

        stage("test") {
            when {
                expression {
                    BRANCH_NAME  == 'main'
                }
            }
            steps {
                echo 'testing the application'
            }
        }

        stage("deploy") {

             steps {
                 echo 'deploy the application'
             }
        }
    }
}
