sudo sh ${TOMCAT_HOME}/bin/shutdown.sh;

cd camel-spring-restlet-app;

mvn -e clean package;

sudo cp target/csra.war ${TOMCAT_HOME}/webapps/

sudo sh ${TOMCAT_HOME}/bin/startup.sh;

curl -XGET 'http://localhost:8080/csra/v1/config'