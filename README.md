# chess-ws
The server side of the [Chess game over Kafka](https://github.com/patschris/ChessOverKafka) project. Contains 
[RESTful web services](https://javaee.github.io/tutorial/jaxrs001.html). After building the project, a 
[war file](https://www.oreilly.com/library/view/learning-java-4th/9781449372477/ch15s03.html) is produced. You have to deploy this
war file to [Apache Tomcat](http://tomcat.apache.org/) and install the database 
[m111](https://github.com/Thanoschal/chess-ws/blob/master/m111.sql) and the Kafka server at the same machine. Then, the services are
available under the path http://x.x.x.x:8080/chess-ws/rest, where x.x.x.x is the IP of the hosting machine.
<br/>
**Important Note**: The Kafka server, the Apache Tomcat and the MySQL database must be installed in the same machine.
