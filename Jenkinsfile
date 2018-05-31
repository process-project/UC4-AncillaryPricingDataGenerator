def jdkVersion = 'jdk1.8.0_141'
def job = 'module/data-generator-ancillary'
def mavenStatement = 'mvn clean deploy'
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
                sh "${mavenStatement} -Dmaven.test.skip=true"
            }
        }
    }
}
