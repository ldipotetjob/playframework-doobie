<img width="928" alt="captura de pantalla 2017-10-07 a las 15 09 49" src="https://user-images.githubusercontent.com/8100363/31313078-665da9a6-abcf-11e7-9266-932880ea6ed2.png">

# Play framework Tutorial for Scala to connect to Postgres via Doobie

To run properly this project, you will need the correct version of Java and sbt. The template requires:

* Java Software Developer's Kit (SE) 1.8 or higher
* sbt 1.3.4 or higher.

If you do not have the required versions, follow these links to obtain them:

* [sbt](http://www.scala-sbt.org/download.html)

## Build and run the project

This example includes all Play components and an Akka HTTP server. 
The project is also configured with filters for Cross-Site Request Forgery (CSRF) protection and security headers.

To build and run the project:

1. Build the project. Enter: `sbt run`. The project builds and starts the embedded HTTP server. Since this downloads libraries and dependencies, the amount of time required depends partly on your connection's speed.

2. After the message `Server started, ...` displays, enter the following URL in a browser: <http://localhost:9000>

3. Preparing your postgres db
   
   - ``` sql
        CREATE DATABASE football OWNER postgres;
        CREATE TABLE footballgame (leagueid text PRIMARY KEY, season text NOT NULL, audience int);
      ```
   - ``` sql
        INSERT INTO cities VALUES ('PRML', '2018/2019');
        INSERT INTO cities VALUES ('CHAMPION', '2018/2019');
        INSERT INTO cities VALUES ('LALIGA', '2018/2019');
        INSERT INTO cities VALUES ('BUNDES', '2018/2019');
        INSERT INTO cities VALUES ('LIGUE1', '2018/2019');
        INSERT INTO cities VALUES ('SERIEA', '2018/2019');
     ```
   - ``` sql
        CREATE DATABASE genxxx OWNER scadip;
        CREATE TABLE genhubrequest (userId text, 
        request_time timestamp, 
        requestid text, 
        requested_samples int, 
        available_samples int, 
        payable boolean, 
         launched boolean)
      ```
   - ``` sql
         INSERT INTO genhubrequest values ('ldipotet@yello.com','2022-10-19 10:23:54','ldipotet@yello.com1666171434',50,60,true,false);
         INSERT INTO genhubrequest values ('jfdz@gmaila.com','2022-11-19 10:23:54','jfdz@gmaila.com1668853434',50,60,true,false);
     ```     
   - ``` sql
        ## connecting to db by psql terminal when server is started
        psql -h localhost -U scadip genxxx 
     ```

4. To reach our endpoint After the message `Server started, ...` displays:
   
   accept = Media types which accepted for the response(**text/csv or application/json**)
   
   Accept default value: **application/json**  

   - ```
      curl -H "Accept: {accept}" -H "Content-Type: text/plain" http://localhost:9000/games/{leagueid}
     ```
   4.1  Examples: 
   
      - ```
         curl -H "Accept: text/csv" -H "Content-Type: text/plain" http://localhost:9000/games/PRML ; echo
        ```
      - ``` 
         curl -H "Accept: application/json" -H "Content-Type: text/plain" http://localhost:9000/games/PRML ; echo
        ```

**Pending Endpoint2 Insert in postgres db => TODO HTTP POST**
**Pending TODO Test module**

ref https://tpolecat.github.io/doobie-0.2.1/09-Arrays.html
https://gitter.im/tpolecat/doobie?at=57851af359cfbd4c5e8ad0c4