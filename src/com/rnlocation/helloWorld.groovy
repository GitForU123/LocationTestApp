package com.rnlocation
def start(){
//    pipeline {
//     agent any
//      parameters {
//         string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
//     }
//     stages {
        // implicit checkout stage
        timestamps{
            
        
        node{
        stage('CheckOut'){
            killPreviousPRJob()
            gitClone()
 }
       stage('Example') {
            greeting()
        }
             stage('Test'){
            runTest()
 }
        stage('SonarQube Analysis') {
            sonarqubeAnalysis()
           
  }
   
// }
    // post after stages, for entire pipeline, is also an implicit step albeit with explicit config here, unlike implicit checkout stage
//     post {
//         always {
//             // junit '**/target/surefire-reports/TEST-*.xml'
//             archiveArtifacts '**/*.html'
//         }
//     }
// }
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


def killPreviousPRJob{
def jobName = env.JOB_NAME
// get the latest the latest build
def pullRequestNumber = jobName.tokenize('/')[1]
def CurrentPRjob= jenkins.model.Jenkins.instance.getItemByFullName(jobName)
def latestBuild = currentPRjob.getBuilds().findAll{ it.description.contains("PR-${pullRequestNumber}") }.last()
// Do something with the latest build
echo "The latest build for PR-${pullRequestNumber} is #${latestBuild.getNumber()}"
// Get the build IDs of previous builds
def buildIds = currentPRjob.builds.collect { it.id }
// Remove the ID of the latest build from the list
buildIds.remove(latestBuild.id)
// Abort previous builds
for (buildId in buildIds) {
CurrentJob.getBuildByNumber(buildId).doStop()
}
}