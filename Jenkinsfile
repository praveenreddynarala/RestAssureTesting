pipeline{

    agent any

    /*environment {
        IMAGE = readMavenPom().getArtifactId()    //Use Pipeline Utility Steps
        VERSION = readMavenPom().getVersion()
    }*/

    triggers {
        pollSCM('H */4 * * 1-5')
    }

    parameters{
        string(name: 'USER', defaultValue: 'Praveen Reddy', description: 'Example parameter initialization')
    }

    stages { // Stages Start

        stage('Compile Stage') {
            steps {
                echo "Compile Stage. It is been run by user - ${USER}."
                withMaven(maven: 'maven_3_6_0') {
                    bat 'mvn clean compile'
                }
            }
        }

        stage('test stage') {
            steps {
                withMaven(maven: 'maven_3_6_0') {
                    bat 'mvn test'
                }
            }
        }

        stage('Deployment Stage') {
            steps {
                withMaven(maven: 'maven_3_6_0') {
                    bat 'mvn deploy'
                }
            }
        }
    } // Stages End

    post{ // Post starts
        failure{// notify users when the Pipeline fails
            mail(to: 'praveenreddy.narala@gmail.com', subject: 'Failed Pipeline', body: 'Build is failed. Kindly look at failure log.')
        }
        unstable{
            mail(to: 'praveenreddy.narala@gmail.com', subject: 'Unstable Pipeline', body: 'Build is not stable for deployment. Kindly look at failure log.')
        }
    } // Post End

}