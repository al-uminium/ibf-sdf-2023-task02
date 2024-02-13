# To run the file: 

Follow the steps below to run the file in your respective OSes. 

For both the commands, run the following command ***after*** you have moved into the root directory ***and*** ran `mvn package`. Make sure to run without the apostrophes!

## For Linux/Mac users:

``` bash
java -cp target/<"JAR FILE NAME"> main.App <"DIR">

# JAR FILE NAME should be 2023-task02-1.0-SNAPSHOT.jar if no changes were made in the .pom file. 
# DIR can be for any directory with text files. For testing purposes I have created 4 text files in the data directory, feel free to use that. 
```

## For Windows users:
```powershell

java -cp target\<"JAR FILE NAME"> main.App <"DIR">

# JAR FILE NAME should be 2023-task02-1.0-SNAPSHOT.jar if no changes were made in the .pom file
# DIR can be for any directory with text files. For testing purposes I have created 4 text files in the data directory, feel free to use that. 
```