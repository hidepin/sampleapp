pipeline {
    agent any
    tools {
        maven "Maven3.5.0"
    }
    stages {
        stage('チェックアウト') {
            steps {
                // Gitリポジトリの指定
                git url: 'https://github.com/hidepin/sampleapp.git'
            }
        }
        stage('Maven build') {
            steps {
                ansiColor('xterm') {
                    sh "mvn clean package"
                }
            }
            post {
                 success {
                    mattermostSend color: 'good', message: "BUILD_ID=${env.BUILD_ID} BUILD_DISPLAY_NAME=${env.BUILD_DISPLAY_NAME} BUILD_TAG=${BUILD_TAG} JOB_URL=${JOB_URL} JOB_NAME=${env.JOB_NAME} - BUILD_NUMBER=${env.BUILD_NUMBER} :green_heart: Success after ${currentBuild.durationString} (<${env.BUILD_URL}|Open>) \n BRANCH_NAME=${env.BRANCH_NAME} <br> CHANGE_ID=${env.CHANGE_ID}"
                    mattermostSend color: 'good', message: "CHANGE_URL=${env.CHANGE_URL}"
                    mattermostSend color: 'good', message: "CHANGE_TITLE=${env.CHANGE_TITLE}"
                    mattermostSend color: 'good', message: "CHANGE_AUTHOR=${env.CHANGE_AUTHOR}"
                    mattermostSend color: 'good', message: "CHANGE_AUTHOR_DISPLAY_NAME=${env.CHANGE_AUTHOR_DISPLAY_NAME}"
                    mattermostSend color: 'good', message: "CHANGE_CHANGE_AUTHOR_EMAIL=${env.CHANGE_AUTHOR_EMAIL}"
                    mattermostSend color: 'good', message: "CHANGE_TARGET=${env.CHANGE_TARGET}"
                    mattermostSend color: 'good', message: "changeSets=${currentBuild.changeSets}"
                 }
                 failure {
                    mattermostSend color: 'danger', message: "error Build Started: ${env.JOB_NAME} ${env.BUILD_NUMBER} :broken_heart:  Failed after (<${env.BUILD_URL}|Open)"
                 }
            }
        }
        stage('成果物の保存') {
            steps {
                archiveArtifacts artifacts: '*/*.war', fingerprint: true, onlyIfSuccessful: true
            }
        }
//        stage('JUnitテスト結果の集計') {
//            steps {
//                junit 'target/surefire-reports/*.xml'
//            }
//        }
        stage('カバレッジ') {
            steps {
                jacoco()
            }
        }
        stage('コード解析結果の集計') {
            steps {
                // CheckStyle
                checkstyle canComputeNew: false, defaultEncoding: 'UTF-8'
                // FindBugs
                findbugs canComputeNew: false, defaultEncoding: 'UTF-8', pattern: '**/findbugsXml.xml'
                // StepCounter結果の集計
                stepcounter settings: [[encoding: 'UTF-8', filePattern: 'src/main/java/**/*.java', filePatternExclude: '', key: 'java'], [encoding: 'UTF-8', filePattern: 'src/main/webapp/**/*.jsp', filePatternExclude: '', key: 'jsp'], [encoding: 'UTF-8', filePattern: 'src/main/webapp/**/*.xml', filePatternExclude: '', key: 'xml']]
            }
        }
    }
}
