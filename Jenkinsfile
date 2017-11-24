pipeline {
  agent {
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
    stage('Set Version') {
      steps {

          def originalV = version();
          def major = originalV[1];
          def minor = originalV[2];
          def patch  = Integer.parseInt(originalV[3]) + 1;
          def v = "${major}.${minor}.${patch}"

          if (v) {
            echo "Building version ${v}"
          }

          sh 'mvn -B versions:set -DgenerateBackupPoms=false -DnewVersion=${v}'
          sh "mvn -B versions:set -DgenerateBackupPoms=false -DnewVersion=${v}"
          sh 'git add .'
          sh "git commit -m 'Raise version'"
          sh "git tag v${v}"
      }
    }
  }
}