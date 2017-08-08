Java: JDK 8 Maven: v.3.3.9 

Application installation:

To start application you have to launch bin\ps-importer.bat  or
ps-file-importer-1.0-SNAPSHOT.jar as
java -jar ps-file-importer-1.0-SNAPSHOT.jar

You have to create two folders Inbox and Outbox to store your files
You can change paths to these folders in application.properties and rebuild application

Examples:
client.input.folder=src/main/resources/inbox
client.output.folder=src/main/resources/outbox

When service is up then you can put your files into Inbox folder and service will process them
All processed files will be saved into Outbox folder

Tests are placed src/test/java
Run with general JUnit way. Some autotests require the running application.

