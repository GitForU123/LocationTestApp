
import groovy.json.JsonSlurper


//    pipeline {
//     agent any
     parameters {
        string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
    }
   

        // implicit checkout stage
        timestamps{
            
        
        node{
// 		 stages {
        stage('CheckOut'){

            killPreviousPRJob()

		try{

            gitClone()
		}catch (err){
		sh "echo 'retrying'"
		git branch : 'master', changelog: false, url: 'https://github.com/GitForU123/LocationTestApp.git'
		currentBuild.result = 'SUCCESS'
		}
 }
       stage('Example') {
            greeting()
        }

             stage('Test'){
            // runTest()
            echo "running test"
 }
        stage('SonarQube Analysis') {
            // sonarqubeAnalysis()
            echo "running analysis"



           
  }
   
	} // node close
    // post after stages, for entire pipeline, is also an implicit step albeit with explicit config here, unlike implicit checkout stage


        }
     

//     }
	   
	   
//    }
		
        




def gitClone(){
 git branch : 'master', changelog: false, url: 'https://github.com/GitForU123/LocationTestApps.git'
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


def killPreviousPRJob(){
def jobName = env.JOB_NAME
echo "jobName is : $jobName"

// get the last Job
def pullRequest = jobName.tokenize('/').last() 


//echo "pullRequest is : $pullRequest"

def CurrentPRjob = jenkins.model.Jenkins.instance.getItemByFullName(jobName)




// Get the latest build builds
def builds = CurrentPRjob.getBuilds()

//get the latest build 

def latestBuildNumber = 1
for(build in builds){
latestBuildNumber = build.getNumber() > latestBuildNumber ? build.getNumber() : latestBuildNumber
}


// Do something with the latest build
echo "The latest build for ${pullRequest} is #${latestBuildNumber}"

// Remove the ID of the latest build from the list
// buildIds.remove(latestBuildId)

// Abort previous builds
for (build in builds) {
    def buildNumber = build.getNumber()
    echo "build's listed : ${buildNumber}"
  if(buildNumber != latestBuildNumber && build.isBuilding()){
    echo "aborting build : ${buildNumber}"
    build.doStop()
   echo "status of build #${buildNumber} : ${build.getResult()}"
  }
}
}
