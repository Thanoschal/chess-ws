# Kafka Chess Web Server
<p align="justify">
The server side of the <a href="https://github.com/patschris/ChessOverKafka">Chess game over Kafka</a> project. Contains <a href="https://javaee.github.io/tutorial/jaxrs001.html">RESTful web services</a>. After building the project, a <a href="https://www.oreilly.com/library/view/learning-java-4th/9781449372477/ch15s03.html">war file</a> is produced. You have to deploy this war file to <a href="http://tomcat.apache.org/">Apache Tomcat</a> and install the database 
<a href="https://github.com/Thanoschal/chess-ws/blob/master/m111.sql">m111</a> and the Kafka server at the same machine Tomcat runs. Then, the services are available under the path http://x.x.x.x:8080/chess-ws/rest, where x.x.x.x is the IP of the hosting machine.
<br/> <br/> <img src="https://github.com/Thanoschal/chess-ws/blob/master/ApacheTomcatManager.PNG">
</p>
