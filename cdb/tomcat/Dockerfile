FROM tomcat:9-jre8-slim

# Copy war file
# Attention :  La commande suivante recupere le dossier fourni par le build docker
# Il s'agit de "cdb/" on appelle ça le "CONTEXT"
# Voir dans le Jenkinsfile
# C'est donc le "cdb/target/DB.war" qu'on copie vers "/usr/local/tomcat/webapps/CDB.war"
# Oui le dossier target existe car on a effectué un build dans
COPY ["./target/CDB.war", "/usr/local/tomcat/webapps/CDB.war"]

EXPOSE 8080