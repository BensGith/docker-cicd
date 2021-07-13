job('NodeJS example') { // Job NAME
    scm { // Configure Source control management 
        git('git://github.com/bensgith/docker-cicd.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('bensgith@github.com')
        }
    }
    triggers { // Configure when to check for changes 
        scm('H/5 * * * *')
    }
    steps { // what steps to take 
        dockerBuildAndPublish {
            repositoryName('benmali94/ex2')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
            buildContext('./app')


    }
}
}

