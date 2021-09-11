This project contains 2 apis.
1. GET method to get user details for the given id, id should be in param
2. POST method to create a user record

Technologies:

Java 15
Spring boot 2.3.7
Mysql
Jpa
redis

end points:
1. To get user details. Get from the cache if the user detail present in the cache, otherwise check in db
method: GET
url: /api/v1/user?id=
response:
    {
    "timestamp": "09-09-2021 15:18:28",
    "status": 200,
    "message": "User data received successfully ",
    "data": {
        "id": 1,
        "name": "Test",
        "email": "test@gmail.com",
        "password": "test"
    }
}
   
2. Create user details in db, id is auto generated. 
   method: POST
   url: /api/v1/user
   request:
   {
       "Name": "Test2",
       "Email": "test@gmail.com",
       "Password": "test"
   }
   response: 
   {
       "timestamp": "09-09-2021 15:15:04",
       "status": 200,
       "message": "Registration successful ",
       "data": {
           "id": 3,
           "Name": "Test2",
           "Email": "test@gmail.com",
           "Password": "test",
           "password": "test",
           "name": "Test2",
           "email": "test@gmail.com"
       }
   }

3.To display the registration form
Simple html and js form to create a user.
Method: GET
url: /api/v1/register
response: signup_form.html

