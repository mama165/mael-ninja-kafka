pipeline {
    agent any
    stages {
       // On build l'image docker contenant la base (script sql), le tomcat ainsi que les script maven pour builder l'application
       // On a donc une image de notre projet à déployer sur la prod
       stage('Docker compose ') {
            steps {
                echo 'A new commit ==> Building...'
                script {
                    echo '<------------------------------------------->'
                    echo 'We are now building our database image...'
                    echo '<------------------------------------------->'
                    sh "docker-compose up --build -d db"
                    //sh "docker-compose up --build -d web"
                    echo '<------------------------------------------->'
                    echo 'We are now building our WebApp image with maven dependencies...'
                    echo '<------------------------------------------->'
                    sh "docker-compose up --build builddocker" // -d devant buildocker enleve la stacktrace !
                }
            }
        }

        // On a d'abord installé docker-compose sur le serveur de build
        // A l'aide d'une image docker pour SonarQube...
        // Sur Jenkins, on installe le plugin SonarScanner
        // Dans les config Jenkins on configure le SonarQubeServers ⇒	Nom, URL, token...
        // On coche : Enable injection of SonarQube server configuration as build environment variables
        //Aller dans Configuration ⇒ SonarQube servers
            //Nom : SonarNinja
            //Url : http://mnana-build.takima.io:9000
            //Token : <token from Sonar>
        //On créé le sonar-project.properties à la racine du projet (à côté du Jenkinsfile)
        stage('SonarQube analysis') {
           steps {
            echo '<------------------------------------------->'
               echo 'We are now running a SonarQube analysis...'
               echo '<------------------------------------------->'
               script {
                   scannerHome = tool 'SonarQubeScanner';
               }
               withSonarQubeEnv('SonarNinja') {
                   sh "${scannerHome}/bin/sonar-scanner -Dproject.settings=./sonar-project.properties"
              }
           }
       }

        /*stage('Build and test') {
            steps {
                echo 'Build...'
                script {
                    sh "docker build -f cdb/Dockerfile.build -t ninja_build ./cdb"
                    sh "docker run -v \$(pwd)/cdb/target:/target ninja_build"
                }
            }
        }*/

        // Jenkins se connecte au registry docker (sur le Gitlab) via les credentials Gitlab (Docker-registry-Gitlab)
        // Jenkins lance sur le serveur des scripts de build d'images de la DB & WebApp
        // Jenkins déploie (push) les images sur le registry
        // https://git.e-biz.fr/mnana/mael-ninja/container_registry
        // Voir explications  : https://git.e-biz.fr/mnana/mael-ninja/container_registry
        stage('Deploy image') {
            steps {
                echo '<------------------------------------------->'
                echo 'We are now deploying our database and webApp...'
                echo '<------------------------------------------->'
                script {
                    def registry = 'registry.e-biz.fr'
                    def idCredentialDockerRegistry = 'Docker-registry-Gitlab'
                    docker.withRegistry("https://${registry}", "${idCredentialDockerRegistry}") {
                                               
                        // au lieu de latest on devrait mettre : ${env.BUILD_NUMBER}
                        def customImageWebApp = docker.build("${registry}/mnana/mael-ninja/image-prod-webapp:latest", "-f cdb/tomcat/Dockerfile cdb/")
                        customImageWebApp.push()
                        
                        // We need to build the DB docker image (cdb/db/Dockerfile)
                        // Important : le context de build cdb/cdb indique le chemin depuis
                        // lequel le Dockerfile va chercher les scripts sql 
                        def customImageDb = docker.build("${registry}/mnana/mael-ninja/image-prod-db:latest", "-f cdb/db/Dockerfile cdb/db")
                        customImageDb.push()
                    }
                }
            }
        }

        // Jenkins peut appeler Ansible car lors de la création du serveur de build
        // on a installé le role ansible ainsi que docker sur le serveur
        // Jenkins lance le script ansible-playbook avec un vault (cryptage des credentials docker)
        // Ansible a besoin de se connecter à la prod donc on fournit la clé privée du serveur de prod
        // Dans les config Jenkins on a "ssh-build-private-key" de créé
        // Par la suite, Ansible prend la main et va chercher l'image docker dans le registry via les credentials
        // Puis Ansible la déploie sur le serveur de prod
        // Voir le rôle "registry" ==> resources/ansible_provide_prod/roles/registry/tasks/main.yml
        
        stage('Call Ansible') {
            steps {
                echo '<------------------------------------------->'
                echo 'We are now calling ansible in order to provide our prod server...'
                echo '<------------------------------------------->'
                //Equivalent : sh "ansible-playbook -i resources/ansible_provide_prod/inventories/production.yml --ask-vault-pass -v --private-key=/home/centos/.ssh/id_rsa resources/ansible_provide_prod/playbook.yml "
                ansiblePlaybook(
                    playbook: './resources/ansible_provide_prod/playbook.yml',
                    inventory: './resources/ansible_provide_prod/inventories/production.yml',
                    credentialsId: 'ssh-build-private-key',
                    vaultCredentialsId: 'docker-registry-vault-credentials',
                    extras: '-vvv'
                    //become(true)
                )
            }
        }

        
    }
}