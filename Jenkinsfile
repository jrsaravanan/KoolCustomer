podTemplate(label: 'mypod', containers: [
    containerTemplate(name: 'maven', image: 'maven:3.3.9-jdk-8-alpine', ttyEnabled: true, command: 'cat'),
    containerTemplate(name: 'jnlp', image: 'jenkinsci/jnlp-slave', args: '${computer.jnlpmac} ${computer.name}'),
]) {

    node ('mypod') {
        stage 'Get a Maven project'
        git 'https://github.com/jrsaravanan/KoolCustomer.git'
        container('maven') {
            stage 'Build a Maven project'
            sh 'mvn clean install'
        }
    }
}
