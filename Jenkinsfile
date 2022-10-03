 #!/usr/bin/env groovy
 stage('CheckOut'){
    git branch : 'master', changelog: false, url: 'https://github.com/GitForU123/LocationTestApp.git'
 }

 stage('Test'){
   bat 'npm run test --watchAll'
 }