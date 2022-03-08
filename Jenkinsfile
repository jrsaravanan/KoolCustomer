pipeline {
  agent {
    kubernetes {
      label 'customer-portal-app'
      yaml '''
  spec:
  containers:
  - name: jnlp
  - name: jdk
    image: openjdk:11-jdk
    command:
    - cat
    tty: true
      '''
    }
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  stages {
    stage('Build') {
      steps {
        container ('jdk') {
          // Note: consider using Pipeline Maven integration to
          // inject Maven settings
          // collect Maven build reports
          // trigger pipeline on snapshot dependency updates
          sh 'mvn clean install'
        }
      }
    }
    stage ('Deploy to Testing Environment') {
      steps {
        
      }
    }
  }
  
}
