def jdkVersion = 'jdk-openJDK-1.8-latest'
def job = 'module/datageneratorancillary'
def mavenStatement = 'mvn -B clean deploy'
def mavenVersion = 'maven-3.6.3'

node {
    echo 'Build ${job}'
}

pipeline {
    agent {
        label 'master'
    }

    tools {
        maven "${mavenVersion}"
        jdk "${jdkVersion}"
    }

    stages {
        stage('module/data-generator-ancillary') {
            steps {
                echo '*************************************************************************************'
                echo "*********************  ${job}  ******************************************************"
                echo '*************************************************************************************'
                sh "${mavenStatement}"
            }
        }
    }
    post {
        always {
			checkstyle canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '**/target/checkstyle-result.xml', unHealthy: ''
			jacoco execPattern: '**/target/jacoco.exec'
            junit '**/target/surefire-reports/*.xml'
        }
        failure {
            mail    to: 'janek.reichardt@lhsystems.com,balazs.somoskoi@lhsystems.com',
                    cc: 'joerg.pancake-steeg@lhsystems.com',
                    subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                    body: "Something is wrong with ${env.BUILD_URL}"
        }
    }
}
