FROM mysql:5.7.22

# ENV 
ENV MYSQL_ROOT_PASSWORD my-secret-pw
# These variables are optional, used in conjunction to create a new user and to set that user's password. This user will be granted superuser permissions (see above) for the database specified by the MYSQL_DATABASE variable. Both variables are required for a user to be created.
ENV MYSQL_USER admincdb
ENV MYSQL_PASSWORD qwerty1234
# This variable is optional and allows you to specify the name of a database to be created on image startup. If a user/password was supplied (see below) then that user will be granted superuser access (corresponding to GRANT ALL) to this database.
ENV MYSQL_DATABASE computer-database-db

# Copy Scripts
# Initializing a fresh instance When a container is started for the first time, a new database with the specified name will be created and initialized with the provided configuration variables. Furthermore, it will execute files with extensions .sh, .sql and .sql.gz that are found in /docker-entrypoint-initdb.d. Files will be executed in alphabetical order. You can easily populate your mysql services by mounting a SQL dump into that directory and provide custom images with contributed data. SQL files will be imported by default to the database specified by the MYSQL_DATABASE variable.

# Attention: le dossier courant est le celui donné par le Jenkinsfile !!
COPY ["./1-schema-r2.sql", "/docker-entrypoint-initdb.d"]
COPY ["./2-entries-r2.sql", "/docker-entrypoint-initdb.d"]

EXPOSE 3306