//CODE_CHANGES = getGitChanges()

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
                echo 'building the application'
                echo "building version ${NEW_VERSION}"
                sh "mvn --version"
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
                 withCredentials([
                         usernamePassword(credentials: 'test', usernameVariable: USER, passwordVariable: PWD)
                 ]) {
                     sh "some script ${USER} and ${PWD}"
                 }
             }
        }
    }

//    post {
//        always {
//            // this block always is executed don't meter success or failed
//        }
//        success {
//            // if stages executed successfully
//        }
//        failure {
//            // if same stage failed
//        }
//    }
}
