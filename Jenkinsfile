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
             stage('Test'){
            steps{
                nodejs(nodeJSInstallationName: 'NodeJS1'){
    echo 'nodejs tool is running'
    sh 'npm install' 
   sh 'npm run test --watchAll'
                }
            }
 }
        stage('SonarQube Analysis') {
            steps{
                //     def scannerHome = tool 'SQ1';
                script {
                    scannerHome = tool 'SQ1'
                        }

                
    withSonarQubeEnv(installationName : 'SQInstance1') {

//          sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
//          sh "SQInstance1/bin/sonar-scanner"
//              sh "${scannerHome}/bin/sonar-scanner"
        echo 'sonarqube is running'
//               sh "${tool 'SQInstance1'}/bin/sonar-scanner"
//         withMaven(maven : 'maven 3.8.6'){
//         sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
        
             sh 'sonar-scanner'
    }
            }
  }
   
}
    // post after stages, for entire pipeline, is also an implicit step albeit with explicit config here, unlike implicit checkout stage
    // post {
    //     always {
    //         junit '**/target/surefire-reports/TEST-*.xml'
    //         archiveArtifacts 'target/*.jar'
    //     }
    // }
}
