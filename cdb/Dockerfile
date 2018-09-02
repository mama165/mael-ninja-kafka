FROM maven:3.5-jdk-8

# Prepare by downloading dependencies
ADD pom.xml pom.xml
# Adding source, compile and package into a fat jar
ADD src src
ADD wait-for-it.sh wait-for-it.sh

RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]


#CMD ["mvn", "install", "-DskipTests"]
#CMD ["mvn", "install"]

#Lorsque le container est lancé, la commande suivante sera utilisée
#CMD ["java", "-jar", "target/api.jar"]