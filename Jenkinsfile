def jdkVersion = 'jdk1.8.0_141'
def job = 'module/datageneratorancillary'
def mavenStatement = 'mvn -B clean deploy'
def mavenVersion = 'maven-3.5.0'

node {
    echo 'Build ${job}'
}

pipeline {
    agent none
    tools {
        maven "${mavenVersion}"
        jdk "${jdkVersion}"
    }

    stages {
        stage('module/data-generator-ancillary') {
            agent {
                label 'master'
            }

            steps {
                echo '*************************************************************************************'
                echo "*********************  ${job}  ******************************************************"
                echo '*************************************************************************************'
                sh "${mavenStatement}"
            }
        }
    post {
        failure {
            mail to: 'joerg.pancake-steeg@lhsystems.com',
                subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                body: "Something is wrong with ${env.BUILD_URL}"
            }
        }		
    }
}
