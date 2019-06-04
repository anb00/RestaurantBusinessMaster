# RestaurantBusinessMaster
Top Restaurants in the World
## *Overview*
This is a project in development...

## *Prerequisites*
* Java (jdk10)
* Maven 3
* JPA/HIBERNATE
* PostGres (DB)
* wildfly as a Application Server

* JSF (Added in Wildfly by default)
* HTML , CSS & Javascript (Added in wildfly)


## *Build Project Fast Version, the Complete guide is in the Final Document attached*
1. Create Database ```CREATE DATABASE wildfly;```
2. Set Username and Password in the ```wildfly app server``` file, these are wildfly as username and friends as Password.
3. Clone the project.
4. Navigate to root path (webrestaurants)
5. ```mvn clean install``` - This Compile My project in a *.war file for Wildfly App server
7. Deploy that warfile into deploy folder .


## *Manage it with deploy in Wildfly as a project* and Fly!
navigate to ```http://localhost:8080/```

