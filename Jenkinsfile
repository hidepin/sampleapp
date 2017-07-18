pipeline {
    agent any
    tools {
        maven "Maven3.5.0"
        jdk "OracleJDK8u131"
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
        }
        stage('成果物の保存') {
            steps {
                archiveArtifacts artifacts: '*/*.war', fingerprint: true, onlyIfSuccessful: true
            }
        }
        stage('JUnitテスト結果の集計') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
        stage('コード解析結果の集計') {
            steps {
                // StepCounter結果の集計
                stepcounter outputFile: '', outputFormat: 'excel', settings: [[encoding: 'UTF-8', filePattern: 'src/main/java/**/*.java', filePatternExclude: '', key: 'java']]
            }
        }
    }
}
