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
                    sh "mvn compile"
                    sh "mvn package -Dmaven.test.skip=true"
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
                     sh "sudo docker ps -a"
                 }
             }
        }
    }
}
