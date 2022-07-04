pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage("build") {
            steps {
                script {
                    sh "mvn --version"
                }
            }
        }

        stage("test") {
            steps {
                script {
                    sh "ls -a"
                }
            }
        }

        stage("deploy") {
             steps {
                 script {
                     sh "echo deploy"
                 }
             }
        }
    }
}
