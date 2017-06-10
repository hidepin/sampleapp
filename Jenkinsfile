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
                git url: 'https://github.com/hidepin/Jenkins_Practical_Guide_3rd_Edition.git'
            }
        }
        stage('Mavenリポジトリ') {
            steps {
                ansiColor('xterm') {
                    sh "mvn clean package"
                }
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
