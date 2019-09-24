node('apk') {

    //以下3个选项为用户自定义选项
    CLEAN_COMMAND = './gradlew clean' //删除编译缓存指令
    BUILD_COMMAND = './gradlew assembleRelease'  //编译命令,可用户自定义修改
    INSTALL_COMMAND = './gradlew :library:install' //上传aar至maven库指令
    EMAIL_NOTIFICATION_RECIPIENTS='chenchengyin@cvte.com'  //如果编译失败，邮件接收人

    env.ARTIFACT_ID="clog"
    env.AAR_PATH='library/build/outputs/aar/library-release.aar' //生成的aar完整路劲
    env.SOURCE_PATH='library/build/libs/library-sources.jar'
    env.POM_PATH='library/build/poms/pom-default.xml' //pom完整路劲

    //版本定义生成，即是R，D还是F版本的判断，如果有其他分支为R，请在此添加定义
    if (env.BRANCH_NAME.startsWith('master') || env.BRANCH_NAME.startsWith('hotfix')) {
        env.BUILD_TYPE = 'R'
    } else if (env.BRANCH_NAME.startsWith('release')) {
        env.BUILD_TYPE = 'RC'
    } else if (env.BRANCH_NAME.startsWith('develop')) {
        env.BUILD_TYPE = 'D'
    } else {
        env.BUILD_TYPE = 'F'
    }

    //一下配置为通用配置，一般无需修改
    try {
        stage('Checkout code') {
            sh 'git remote prune origin || true'
            checkout scm
            initProps()
        }

        stage('Clean project') {
            sh "${CLEAN_COMMAND}"
        }

        stage('Install sdk') {
            sh "${INSTALL_COMMAND}"
        }

        stage('Deploy sdk') {
            upload_sdk_to_maven("${ARTIFACT_ID}","${env.VERSION_NAME}","${env.AAR_PATH}","${env.SOURCE_PATH}","${env.POM_PATH}")
        }

        currentBuild.result = 'SUCCESS'
   }catch(err){
       currentBuild.result = 'FAILURE'
       throw err
    }finally{
       if ( currentBuild.result == 'FAILURE') {
           step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients:EMAIL_NOTIFICATION_RECIPIENTS , sendToIndividuals: true])
       }
       if (currentBuild.result == 'SUCCESS') {
           /**
           *如果有pre_build.sh之类的脚本，则需要在第二个参数中指定运行，如果没有填写空字符串即可
           *example1,需要执行pre_build.sh脚本：
           *    star_coverity_analysis(env.BRANCH_NAME,'./pre_build.sh',BUILD_COMMAND)
           *example2,不需要执行脚本：
           *    star_coverity_analysis(env.BRANCH_NAME,'',BUILD_COMMAND)
           **/
           star_coverity_analysis(env.BRANCH_NAME,'',BUILD_COMMAND)
       }
    }
}

/**
 ***********************************以下配置为通用配置，请勿修改********************************************************************
**/

def initProps(){
    //读取prop中的主板本和副版本
    props = readProperties  file:'gradle.properties'
    env.MAJOR_VERSION=props.MAJOR_VERSION
    env.MINOR_VERSION=props.MINOR_VERSION

    //apk配置
    env.MH_VERSION_NAME="${BUILD_TYPE}.${props.MAJOR_VERSION}.${props.MINOR_VERSION}.${env.BUILD_NUMBER}"
    env.MH_VERSION_CODE="${env.BUILD_NUMBER}"
    env.PROJECT_NAME="${env.JOB_NAME}".split("/")["${env.JOB_NAME}".split("/").size()-2]
    //sdk配置
    env.VERSION_NAME="${BUILD_TYPE}.${MAJOR_VERSION}.${MINOR_VERSION}.${BUILD_NUMBER}-SNAPSHOT"
    if ("${env.BUILD_TYPE}"=="R") {
        env.VERSION_NAME="${BUILD_TYPE}.${MAJOR_VERSION}.${MINOR_VERSION}.${BUILD_NUMBER}"
    }
    env.LIB_NAME="${JOB_BASE_NAME}"
    env.GROUP_ID="${MAXHUB_MAVEN_GROUP}"
    env.CLASSIFIER="release"
}
/**
**修改apk名称
**/
def rename_apk(){
    sh "rm -rf ruapk.sh && curl -uadmin:AP3jkosXZxsRypuj -O \"http://mhrepo.gz.cvte.cn/artifactory/tools/scripts/apk/ruapk.sh\""
    sh '''bash ruapk.sh ${APK_PATH} ${PROJECT_NAME}_${JOB_BASE_NAME} ${BUILD_TYPE} ${MAJOR_VERSION} ${MINOR_VERSION} ${BUILD_NUMBER}'''
}
/**
**上传apk到Artifactory
**/
def archive_apk(){
    sh "rm -rf buapk.sh && curl -uadmin:AP3jkosXZxsRypuj -O \"http://mhrepo.gz.cvte.cn/artifactory/tools/scripts/apk/buapk.sh\""
    sh '''bash buapk.sh ${APK_PATH} ${PROJECT_NAME}_${JOB_BASE_NAME} ${BUILD_TYPE} ${MAJOR_VERSION} ${MINOR_VERSION} ${BUILD_NUMBER}'''
}
/**
**上传aar到maven
**@param ARTIFACT_ID sdk的名称
**@param VERSION sdk的版本号
**@param DFILE aar文件路劲
**@param DPOM_FILE pom文件路劲
**/
def upload_sdk_to_maven(ARTIFACT_ID,VERSION,DFILE,DSOURCEFILE,DPOM_FILE){
    MVN_HOME = tool(name: 'mvn_3_5_2', type: 'maven')
    DURL='http://mvn.gz.cvte.cn/nexus/content/repositories/snapshots/'
    DREPOSITORYID='snapshots'
    if ("${env.BUILD_TYPE}"=="R") {
        DURL='http://mvn.gz.cvte.cn/nexus/content/repositories/releases/'
        DREPOSITORYID='releases'
    }
    sh "${MVN_HOME}/bin/mvn -Durl=${DURL} -Dpackaging=aar -Dfile=${DFILE} -DgroupId=com.cvte.maxhub -DrepositoryId=${DREPOSITORYID} -Dversion=${VERSION} -Dtype=aar -Dtypes=jar -DpomFile=${DPOM_FILE} -Dfiles=${DSOURCEFILE} -Dclassifiers=sources -DartifactId=${ARTIFACT_ID} deploy:deploy-file -e -X"
}

/**
**开始Coverity静态分析
**/
def star_coverity_analysis(branch,preBuildCmd,buildCmd){
    def gitUrl = sh(returnStdout: true, script: 'git config remote.origin.url').trim()
    build job: 'CoverityAnalysis/AndroidProjectAnalysis', parameters: [string(name: 'GIT_URL', value: gitUrl), string(name: 'BRANCH', value: branch), string(name: 'PRE_BUILD_COMMAND', value: preBuildCmd), string(name: 'BUILD_COMMAND', value: buildCmd)], wait: false
}