pipeline {
  agent any 
  {
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m2'
    }
    
  }

  stages {
   
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean install'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn install -fae'
      }
    }
  
  }
  
}