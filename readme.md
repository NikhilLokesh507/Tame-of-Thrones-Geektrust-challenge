##Tame of Thrones
####Overview
_Enum_ `Kingdom` used to instantiate the kingdoms.<br>
Interface `PoliticalEntity` implemented by the `Kingdom`.<br>
 `Kingdom` contains an instance of `Encryption` to help communicate with instances of `PoliticalEntity`. 
---
####Structure
 - `PoliciticalEntity` - Takes on the roles Decision making by interacting with other instances of `PolicitalEntity` along with attempting to make alliances.
 - `Decider` - Implemented by `PoliticalEntity`, to abstract out the decision-making responsibility.
 - `Reciever` - Implemented by `PoliticalEntity` to help an instance interact with other instances.
---
####Running the project
`Maven` is used to build the project. Run the following commands with appropriate place holders. 
```
mvn clean install -DskipTests -q assembly:single
java -jar <path_to>/geektrust.jar <absolute_path_to_input_file>
```
####Running tests
```
mvn test
```
---