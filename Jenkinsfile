#!/usr/bin/env groovy
// shebang tells most editors to treat as groovy (syntax highlights, formatting, etc)

pipeline {
    agent any
    // triggers { pollSCM('* * * * *') }
//     tools {tool 'SQInstance1'}
     parameters {
        string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
    }
    stages {
        // implicit checkout stage
        stage('CheckOut'){
            steps{

            
    git branch : 'master', changelog: false, url: 'https://github.com/GitForU123/LocationTestApp.git'
            }
 }

        // stage('Build') {
        //     steps {
        //         sh './mvnw clean package'
        //     }
        // }
       stage('Example') {
            steps {
                echo "${params.Greeting} World!"
            }
        }
        stage('SonarQube Analysis') {
            steps{
//     def scannerHome = tool 'SQInstance1';
    withSonarQubeEnv(installationName : 'SQInstance1') {
//       sh "${tool 'SQInstance1'}/bin/sonar-scanner"
         sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
    }
            }
  }
//         stage('Test'){
//             steps{

//     sh 'npm install' 
//    sh 'npm run test --watchAll'
//             }
//  }

    }
    // post after stages, for entire pipeline, is also an implicit step albeit with explicit config here, unlike implicit checkout stage
    // post {
    //     always {
    //         junit '**/target/surefire-reports/TEST-*.xml'
    //         archiveArtifacts 'target/*.jar'
    //     }
    // }
}
