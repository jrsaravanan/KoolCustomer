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

    stage('Integeration Init') {
       
      when { branch 'master' }
      steps {
        sh 'sudo docker run -d --name mysql-server -e MYSQL_DATABASE="customers" -e MYSQL_USER="appuser" -e MYSQL_PASSWORD="appuser"  -e MYSQL_ROOT_PASSWORD="appuser" -e MYSQL_ROOT_HOST="192.168.10.168" -p 3306:3306 mysql:latest '
        sh 'export HOST_NAME=$(hostname  -I | cut -f1 -d " ")'
        sh 'export CUSTOMERS_URI=http://$HOST_NAME:8090/v1.0/customer'
        sh 'echo $HOST_NAME'
        sh 'echo $CUSTOMERS_URI'
        sh 'java -DCUSTOMER_APP_USER=appuser -DCUSTOMER_APP_PASSWORD=appuser  -DCUSTOMER_DB_URI=$HOST_NAME -Dserver.port=8090 -jar customer-service/target/customer-service-0.0.1-SNAPSHOT.jar &'
        sleep(50)
      }
    }

    stage('Integeration Test') {

      when { branch 'master' }

      try {
           steps { 
             sh 'cd customer-bdd; mvn clean install'       
       }
      } catch (Exception e) {

        throw e;
      } finally {

      }
     
    }

    stage ('Clean') {

      when { branch 'master' }
      steps {
        sh 'docker stop mysql-server'
        sh 'docker rm mysql-server'
      }

    }
    
  }

    
}