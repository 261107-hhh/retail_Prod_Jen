pipeline {
    agent any
	tools {
        // Install the Maven version configured as "M3" and add it to the path.
         maven 'mvn'
    }
    stages {
        stage('Build') {
            steps {
              bat 'mvn clean compile'
              echo 'mvn clean and compiled'
              
              //bat "mvn -Dmaven.test.failure.ignore=true clean package"
              //echo 'mvn packed'
            }
        }
         stage('Test') {
            steps {
                //bat './mvnw test'
                //bat 'mvn test'
                bat 'mvn clean test'
                echo 'mvn tested'
            }
        }
         stage('Deploy') {
            steps {

                //bat 'mvn deploy'
                bat 'java -jar target/'
                echo 'mvn deployed'
            }
        }
       
       
    }
}
