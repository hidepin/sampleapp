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
                    mattermostSend color: 'good', message: "${env.JOB_NAME} - ${env.BUILD_NUMBER} :white_check_mark: Success after ${currentBuild.durationString} (<${env.BUILD_URL}|Open>)"
                 }
                 failure {
                    mattermostSend color: 'danger', message: "error Build Started: ${env.JOB_NAME} ${env.BUILD_NUMBER} :no_entry_sign:  Failed after (<${env.BUILD_URL}|Open)"
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
