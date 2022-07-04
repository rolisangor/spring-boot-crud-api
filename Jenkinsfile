def gv

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
                    gv.buildApp()
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
                script {
                    gv.testApp()
                }
            }
        }

        stage("deploy") {
             steps {
                 script {
                     gv.deployApp()
                 }
             }
        }
    }
}
