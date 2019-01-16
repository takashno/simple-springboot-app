#!/user/bin/env groovy

// Mattermost Notification
def MATTERMOST_WEBHOOK='http://mattermost.cicd.handson/hooks/wzgj3restffjpqkd979pswtygc'
def FAILURE_COLOR='#FF0000'
def SUCCESS_COLOR='#00BBFF'
def UNSTABLE_COLOR='#FFEE00'

// Pipeline
pipeline {
    
    agent any
    
    options {
      gitLabConnection('GitLab')
      gitlabBuilds(builds: ['build'])
    }
    
    triggers {
        gitlab(triggerOnPush: true, triggerOnMergeRequest: true, branchFilterType: 'All')
    }
    
    stages {
        stage('clone source and setup env') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'release') {
                        deleteDir()
                    }
                    checkout scm
                    env.SPRING_PROFILES_ACTIVE='test'
                    env.ORG_GRADLE_PROJECT_databaseJdbcSchema='sampledb'
                    env.TEST_DB_SCHEMA='sampledb'
                    env.PROJECT_RC_VERSION = new Date().format('yyyyMMddHHmm')
                }
            }
        }
        stage('Build, Test, Sonar') {
            steps {
                script {
                    withSonarQubeEnv('SonarQube') {
                        timeout(time: 1,unit: 'HOURS') {
                            if (env.BRANCH_NAME.startsWith('master') || env.BRANCH_NAME.startsWith('develop')) {
                                sh './gradlew build sonarqube -x test -PinhouseRepositoryBaseUrl=${INHOUSE_REPOSITORY_BASE_URL} -PinhouseRepositoryUser=${INHOUSE_REPOSITORY_USER} -PinhouseRepositoryPass=${INHOUSE_REPOSITORY_PASS}'
                            } else {
                                sh './gradlew build -x test -PinhouseRepositoryBaseUrl=${INHOUSE_REPOSITORY_BASE_URL} -PinhouseRepositoryUser=${INHOUSE_REPOSITORY_USER} -PinhouseRepositoryPass=${INHOUSE_REPOSITORY_PASS}'
                            }
                        }
                    }
                }
            }
        }
        stage('Deploy to Nexus') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master' || env.BRANCH_NAME.startsWith('release')) {
                        echo "##################################################"
                        echo "              Start Deploy to Nexus               "
                        echo "##################################################"
                        sh './gradlew publish -PinhouseRepositoryBaseUrl=${INHOUSE_REPOSITORY_BASE_URL} -PinhouseRepositoryUser=${INHOUSE_REPOSITORY_USER} -PinhouseRepositoryPass=${INHOUSE_REPOSITORY_PASS}'
                    }
                }
            }
        }
    }
    
    // Post
    post {
        failure {
            updateGitlabCommitStatus name: 'build', state: 'failed'
            mattermostSend endpoint: MATTERMOST_WEBHOOK, channel: 'CICD', color: FAILURE_COLOR, message: "FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}: (<${env.BUILD_URL}|Open>)"
        }
        success {
            updateGitlabCommitStatus name: 'build', state: 'success'
            mattermostSend endpoint: MATTERMOST_WEBHOOK, channel: 'CICD', color: SUCCESS_COLOR, message: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}: (<${env.BUILD_URL}|Open>)"
        }
        unstable {
            updateGitlabCommitStatus name: 'build', state: 'pending'
            mattermostSend endpoint: MATTERMOST_WEBHOOK, channel: 'CICD', color: UNSTABLE_COLOR, message: "UNSTABLE: ${env.JOB_NAME} #${env.BUILD_NUMBER}: (<${env.BUILD_URL}|Open>)"
        }
    }
}
