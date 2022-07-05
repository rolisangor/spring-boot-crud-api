pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage("pre-build") {
            steps{
                script {
                    sh "sudo docker-compose -f docker-compose_arm64.yaml down"
                    sh "sudo docker image rm rolisangor/crud-app"
                }
            }
        }

        stage("build") {
            steps {
                script {
                    sh "mvn compile"
                    sh "mvn package -Dmaven.test.skip=true"
                    sh "sudo docker-compose -f docker-compose_arm64.yaml up -d"
                }
            }
        }

        stage("test") {
            steps {
                script {
                    sh "echo test app"
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
