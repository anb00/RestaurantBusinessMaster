# RestaurantBusinessMaster
Top Restaurants in the World
## *Overview*
This is a project in development...

## *Prerequisites*
* Java (jdk10)
* Maven 3
* JPA/WEB/HIBERNATE
* MySQL (DB)
* wildfly as a Application Server
* Spring Boot 5/6
* Angular
* HTML , CSS & Javascript (AngularJS/Primeng)


## *Build Project*
1. Create Database ```CREATE DATABASE project;```
2. Set Username and Password in the ```application.properties``` file, these are root as username and Root1234 as Password.
3. Clone the project.
4. Navigate to root path (kingproject)
5. ```mvn clean install``` - This Compile My project in a *.war file for Wildfly App server
5. Invoke ```bower install```
6. Invoke ```mvn clean install -DskipTests```
7. Navigate to target folder

## *Manage it with deploy in Wildfly as a project* and Fly!
navigate to ```http://localhost:8080/```
or
navigate to ```http://localhost:4200``` if you run angular server from angular folder with ```npm start```inside /angular2.0/ folder.
