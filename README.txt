Here are steps on how to get this app running - 

Open a terminal/commandPromp to run CLI commands-

1. Go to CraftExercise folder 
2. Go to exercise folder
3. run mvn clean package command
	exercise/>mvn clean package
4. run another command to deploy it.
	exercise/>java -jar target/exercise-0.0.1-SNAPSHOT.jar

5. In a few second you should be able to see something like below-
	Tomcat started on port(s): 8080 (http)
	Started Application in 5.154 seconds

Now to run API. Please use below URL - 
	http:<hostname>:8080/api/orders

For more details on usage please see Documents/Interface_Specification.doc
Also Documents/DemoApplication.json.postman_collection is collection of sample requests for each API call.
