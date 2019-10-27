# react-backend
## Initial Repository

### Initial backend repository for testing etc.

Options:
* POST
    * /create - Creating new user, requires body type:  
        {  
            &nbsp;&nbsp;&nbsp;&nbsp;"login" : "Your_Login",  
            &nbsp;&nbsp;&nbsp;&nbsp;"firstname" : "Your_First_Name",  
            &nbsp;&nbsp;&nbsp;&nbsp;"lastname" : "Your_Last_Name",  
            &nbsp;&nbsp;&nbsp;&nbsp;"dateofbirth" : "YYYY.MM.DD",  
            &nbsp;&nbsp;&nbsp;&nbsp;"active" : "true/false"  
         }
     * /update/{login} - Updating existing user, requires body as above
* GET
    * /findbylogin/{login} - Searching for user by login, returns string with info about user, or failure message
    * /ifcreated/{login} - Checking if user with this login have been already created, returns message
    * /retrive/{login} - Retriving user with this login, returns user or string message when failed
    * /delete/{login} - Deleting user with this login, returns message