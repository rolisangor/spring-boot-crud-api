def gv

pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage("build") {
            steps {
                script {
                    gv.buildApp()
                }
            }
        }

        stage("test") {
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
