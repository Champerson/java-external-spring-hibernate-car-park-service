<h2>java-external-spring-hibernate-car-park-service</h2>

<h3>Car Park Service description</h3>
The purpose of this service to store and control information about bus routes, 
assign buses to routes, assign users to buses. Any bus can be assigned to one route only.
Any driver can be assigned to one bus only. Any route can contain multiple buses assigned.

<h5>As a <b><i>GUEST</i></b> you can:</h5>
<ul>
<li>register</li>
<li>log in to the system</li>
<li>switch language</li>
</ul>

<h5>As a <b><i>DRIVER</i></b> you can:</h5>
<ul>
<li>view your personal data</li>
<li>edit your personal data</li>
<li>view assignment details</li>
<li>accept upcoming assignment</li>
<li>decline upcoming assignment</li>
<li>decline existing assignment</li>
<li>switch language</li>
<li>log out</li>
</ul>

<h5>As an <b><i>ADMIN</i></b> you can:</h5>
<ul>
<li>view all users</li>
<li>view all buses</li>
<li>view all routes</li>
<li>view bus details</li>
<li>view route details with all assignments</li>
<li>view user details with assignment, without password</li>
<li>edit bus details</li>
<li>edit route details</li>
<li>edit user role</li>
<li>create new bus</li>
<li>create new route</li>
<li>create assignment - bus to route</li>
<li>create assignment - user to bus</li>
<li>delete bus</li>
<li>delete route</li>
<li>delete user</li>
<li>delete assignment</li>
<li>decline user assignment</li>
<li>switch language</li>
<li>log out</li>
</ul>

<h3>Project local set up</h3>
<h4>Prerequisites</h4>
<ul>
<li><a href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html">java 8</a></li>
<li><a href="https://dev.mysql.com/downloads/installer/">MySQL 8</a></li>
<li><a href="https://maven.apache.org/install.html">Maven</a></li>
</ul>

<h4>Configuration:</h4>
<ul>
<li>MySQL: execute SQL script <b><i>car-park-service-scheme.sql</i></b> to create tables with fields</li>
<li>MySQL: execute SQL script <b><i>car-park-service-data.sql</i></b> to populate db with test data</li>
<li>MySQL: create user [ <b><i>root</i></b> / <b><i>root</i></b> ] and grant access for <b><i>car_park_service_db</i></b></li>
<li>Maven: download dependencies from <b><i>pom.xml</i></b></li>
</ul>

<h4>Run:</h4>
<ul>
<li>from command line navigate to project folder</li>
<li>from command line execute <b><i>mvn tomcat7:run</i></b></li>
<li>from browser open <a href="http://localhost:8088/carpark/">http://localhost:8088/carpark/</a></li>
<li>to access the service as a <b><i>DRIVER</i></b> use credentials: [ <b><i>user</i></b> / <b><i>user</i></b> ]</li>
<li>to access the service as an <b><i>ADMIN</i></b> use credentials: [ <b><i>admin</i></b> / <b><i>admin</i></b> ]</li>
<li>for all other test users use their name as login and password</li>
</ul>