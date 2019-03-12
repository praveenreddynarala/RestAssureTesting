pipeline{

    agent any

    triggers {
        cron('H */4 * * 1-5')
    }
    stages{

        stage('Compile Stage'){
            steps{
                withMaven(maven : 'maven_3_6_0'){
                    bat 'mvn clean compile'
                }
            }
        }

        stage('test stage'){
            steps{
                withMaven(maven : 'maven_3_6_0'){
                    bat 'mvn test'
                }
            }
        }

        stage('Deployment Stage'){
            steps{
                withMaven(maven : 'maven_3_6_0'){
                    bat 'mvn deploy'
                }
            }
        }

    }

}