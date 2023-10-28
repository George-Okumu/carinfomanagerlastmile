# Car information manager
- an application that can store and retrieve car details

## LiveLink
[carinfomanager.app](https://lucid-blow-production.up.railway.app/)

## Main Endpoints to visit
- ```/``` for user login
- ```/admin``` for admin login
- ``/vehicles/all`` see all vehicles and their details
## Workflow For data entry user
- A user logs in using the username data and password 1234. 
- He is presented with a form with 3 input fields : car make , model and year of
  manufacture. 
- He fills the form and submits the information. 
- After submitting , a table showing the data he has entered is displayed. 
- A new row should be added for every new entry. 
- The data entry is able to view only the records he created. 
- The User logs out .
## Workflow For Admin
- The admin logs in using the username admin and password 1234. 
- He is presented with a form with 3 input fields : car make , car model
  and year of manufacture. 
- He fills the form and submits the information. 
- After submitting , a table showing the data he has entered is displayed. 
- A new row should be added for every new entry 
- The admin user is able to view the records he created as well as the ones
  created by the data entry user. 
- The Admin logs out.

## ```GET Endpoint : /vehicles/all```
- Returns all vehicles and their details in json format