# nw-client
A Web-Client for NewtonWars written in Java and vanilla JavaScript

# building
You need a JDK to compile the java sources.
then you can simply run following command

On Linux or Mac

    ./mvnw clean install

On Windows

    mvnw.bat clean install
  
This will create a 'target' directory with the compiled code and most importantly a .jar file.

# running
After you've compiled the project you can run it with following command

    java -jar target/nw-client-0.0.1-SNAPSHOT.jar

This will leave you with a running server and a basic UI on http://localhost:8080/

# API endpoints

The endpoints expect and return JSON.

http://localhost:8080/api/connect - expects a username, returns a token for the following requests
http://localhost:8080/api/shoot - expects the token and an angle, optionally a velocity, returns your current energy
http://localhost:8080/api/disconnect - expects your token
