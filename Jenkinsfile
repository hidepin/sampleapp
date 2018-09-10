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
                    sh "./tools/chkfile.py > ./result-check.txt"
                }
            }
        }
        stage('成果物の保存') {
            steps {
                archiveArtifacts artifacts: '*/*.war', fingerprint: true, onlyIfSuccessful: true
                archiveArtifacts artifacts: 'result-check.txt', fingerprint: true, onlyIfSuccessful: true
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
                // PMD
                pmd canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '**/target/pmd.xml', unHealthy: ''
                // CPD
                dry canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '**/target/cpd.xml', unHealthy: ''
                // CheckStyle
                checkstyle canComputeNew: false, defaultEncoding: 'UTF-8'
                // FindBugs
                findbugs canComputeNew: false, defaultEncoding: 'UTF-8', pattern: '**/findbugsXml.xml'
                // StepCounter結果の集計
                stepcounter settings: [[encoding: 'UTF-8', filePattern: 'src/main/java/**/*.java', filePatternExclude: '', key: 'java'], [encoding: 'UTF-8', filePattern: 'src/main/webapp/**/*.jsp', filePatternExclude: '', key: 'jsp'], [encoding: 'UTF-8', filePattern: 'src/main/webapp/**/*.xml', filePatternExclude: '', key: 'xml']]
            }
            environment {
                        PMD_RESULT = """${sh(
                           script: 'find . -name "pmd.xml" -exec cat {} \\; | grep "<violation" | wc -l',
                           returnStdout: true
                           )}"""
            }
            post {
                 success {
                    mattermostSend text: "@admin @jenkins hogehoge\n|チェック方法|結果|\n|:---|:---|\n|PMD|aa|", color: 'good', message: ":green_heart: Success after ${currentBuild.durationString} (<${env.BUILD_URL}|Open>) \n"
                 }
                 failure {
                    mattermostSend color: 'danger', message: "error Build Started: ${env.JOB_NAME} ${env.BUILD_NUMBER} :broken_heart:  Failed after (<${env.BUILD_URL}|Open)"
                 }
             }
            }
    }
}
