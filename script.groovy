def buildApp() {
    echo 'building the application'
    echo "building version ${NEW_VERSION}"
    sh "mvn --version"
}

return this