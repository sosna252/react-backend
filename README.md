# react-backend
## Initial Repository

### Initial backend repository for testing etc.

####WEBSITE: http://sosna252.us-east-1.elasticbeanstalk.com/

Options:
* POST
    * /users/ - Creating new user, requires body type:  
        {  
            &nbsp;&nbsp;&nbsp;&nbsp;"login" : "Your_Login",  
            &nbsp;&nbsp;&nbsp;&nbsp;"firstname" : "Your_First_Name",  
            &nbsp;&nbsp;&nbsp;&nbsp;"lastname" : "Your_Last_Name",  
            &nbsp;&nbsp;&nbsp;&nbsp;"dateofbirth" : "YYYY.MM.DD",  
            &nbsp;&nbsp;&nbsp;&nbsp;"active" : "true/false"  
         }
* GET
    * /login/{login} - Searching for user by login, returns user, or failure message
    * /{login} - Checking if user with this login have been already created, returns message
    * /user/{login} - Retriving user with this login, returns user or string message when failed   
* PUT
    * /{login} - Updating existing user, requires body as above
* DELETE
    * /{login} - Deleting user with this login, returns message