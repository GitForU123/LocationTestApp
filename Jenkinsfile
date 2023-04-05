#!/usr/bin/env groovy
// shebang tells most editors to treat as groovy (syntax highlights, formatting, etc)

pipeline {
    agent any
    // triggers { pollSCM('* * * * *') }
//     tools {tool 'SQInstance1'}
     parameters {
        string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
         string(name: 'test', defaultValue: 'true', description:'whether to run the test case')
         string(name: 'sonarAnalysis', defaultValue: 'false', description:'whether to run sonar Analysis')
    }
    stages {
        // implicit checkout stage
//         stage('CheckOut'){
//             steps{

            
//     git branch : 'master', changelog: false, url: 'https://github.com/GitForU123/LocationTestApp.git'
//             }
//  }

        // stage('Build') {
        //     steps {
        //         sh './mvnw clean package'
        //     }
        // }
       stage('Example') {
            steps {
                echo "${params.Greeting} World!"

                echo "this is running due to PR request from PR1-Test Jenkins file"
                 echo "current jenkins_home : ${env.JENKINS_HOME}"
                echo "current working space : ${WORKSPACE}"

            }
        }
     
             stage('Test'){
//                  when {
//                 // Only say hello if a "greeting" is requested
//                 expression { params.test == 'true' }
//             }
            steps{
                nodejs(nodeJSInstallationName: 'NodeJS1'){
    echo 'nodejs tool is running'
    sh 'npm install' 
   sh 'npm test -- --coverage'
                }
            }
        }
       
//         stage('SonarQube Analysis') {
//             when {
//                 // Only say hello if a "greeting" is requested
//                 expression { params.sonarAnalysis == 'true' }
//             }
//             steps{
//                 //     def scannerHome = tool 'SQ1';
//                 script {
//                     scannerHome = tool 'SQ1'
//                         }

                
//     withSonarQubeEnv(installationName : 'SQInstance1') {
//          println "${env.SONAR_HOST_URL}" 
//         println "${env.SONAR_AUTH_TOKEN}"
//         echo 'sonarqube is running'
//         sh "${scannerHome}/bin/sonar-scanner"
//     }
//             }
  
//         }
   
}   
    // post after stages, for entire pipeline, is also an implicit step albeit with explicit config here, unlike implicit checkout stage
    post {
//         always {
//             junit '**/target/surefire-reports/TEST-*.xml'
//             archiveArtifacts 'target/*.jar'
//         }
    //     success {
    //     sh 'echo "Pipeline succeeded"'
    //     sh 'rm -rf "${WORKSPACE}"/* '
         
    // }
    // failure {
    //     script {
    //         sh '''
    //         |echo "This job failed"
    //         |echo "And I am not sure why"
    //         '''.stripMargin().stripIndent()
    //     }
    // }

    always{
        echo "current build status : ${currentBuild.currentResult}"
        sh 'rm -rf "${WORKSPACE}"/* '
    }
    }
}
