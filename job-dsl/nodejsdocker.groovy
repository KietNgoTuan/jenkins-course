job('NodeJS Docker example') {
    scm {
        git('git://github.com/KietNgoTuan/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('KietNgoTuan')
            node / gitConfigEmail('kiet.insa.lyon98@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('kiettheo98/simple-jenkins-nodeapp')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
