pipeline {
  agent any 
    /*{
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m2'
    }
    
  }*/
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

    stage('Integeration Test') {
      steps {
        sh 'docker run -d --name mysql-server -e MYSQL_DATABASE="customers" -e MYSQL_USER="appuser" -e MYSQL_PASSWORD="appuser"  -e MYSQL_ROOT_PASSWORD="appuser" -e MYSQL_ROOT_HOST="192.168.10.168" -p 3306:3306 mysql:latest '
        sh 'java -jar -DCUSTOMER_APP_USER=root -DCUSTOMER_APP_PASSWORD=appuser  -Dspring.port=8090 customer-service/target/customer-service-0.0.1-SNAPSHOT.jar &'

      }
    }

    stage('Report') {
      steps {
        sh 'docker stop mysql-server'
        sh 'docker rm mysql-server'
      }
    }
  }
}