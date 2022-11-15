package com.rnlocation
def start(){
//    pipeline {
//     agent any
//      parameters {
//         string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
//     }
//     stages {
        // implicit checkout stage
        node{
        stage('CheckOut'){
            gitClone()
 }
       stage('Example') {
            greeting()
        }
//              stage('Test'){
//             runTest()
//  }
//         stage('SonarQube Analysis') {
//             sonarqubeAnalysis()
           
//   }
   
	}
    // post after stages, for entire pipeline, is also an implicit step albeit with explicit config here, unlike implicit checkout stage
		
    post {
		       always{
	       echo "current working space : ${WORKSPACE}"
	echo "current build status : ${currentBuild.currentResult}"
        sh 'rm -rf "${WORKSPACE}"/* '
		       }
	       }
		
        
}

def gitClone(){
 git branch : 'master', changelog: false, url: 'https://github.com/GitForU123/LocationTestApp.git'
}

def greeting(){
      echo "${params.Greeting} World!"
}

def runTest(){
     nodejs(nodeJSInstallationName: 'NodeJS1'){
    echo 'nodejs tool is running'
    sh 'npm install' 
   sh 'npm test -- --coverage'
                }
}

def sonarqubeAnalysis(){
    script {
                    scannerHome = tool 'SQ1'
                        }

                
    withSonarQubeEnv(installationName : 'SQInstance1') {
         println "${env.SONAR_HOST_URL}" 
        println "${env.SONAR_AUTH_TOKEN}"
        echo 'sonarqube is running'
        sh "${scannerHome}/bin/sonar-scanner"
    }
}
