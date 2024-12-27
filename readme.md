## Database Project
 by sajjad jalili

# github:
https://github.com/SJH14o3/vehicle-rental-system

## How To Run:
 Have IntelliJ preferably latest version
javafx SDK 21.0.2 saved at "C:\permanently\javafx"
mongo-java-driver-3.11.2 added as global library to IntelliJ

in project's github, download folder "src/main/resources/images/vehicles" since due to size was not added to zip and put it back to the extracted project file.
in mongodb, create database named vrs and add three collections into it: reservations, users and vehicles. import data with provided json files to the corresponding collection base on json file name.

now open folder containing project with IntelliJ. use Maven as build system.
main class is Main.java 

## Implementation
a user logs-in or sign-up.
then they will see a list of vehicles, some they might not be able to rent but they can usea little bit of filtering
user clicks a vehicle, they can see full specification
they insert how many days they want to rent then they will be guided to an (imaginary) payment page
after that they have successfully reserved a vehicle and they can see details from main menu if they click reservations.
also users can cancel they're reservations only if the reservation is still in waiting mode (today is not start day of reservation)
a thread will run at start of program to update state of reservation. for example a vehicle will be free when a reservation of
it expires.

Database class holds every function for communication between program and database.