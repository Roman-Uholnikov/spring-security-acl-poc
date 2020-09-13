## Spring Security ACL

POC for https://wiki.conductor.com/display/CATS/Permissions+module+design+proposal#Permissionsmoduledesignproposal-Accesscontrollibrary


## Info
First assumption is that Authorization and authentication of the end user will happen in different ends:
 1. Monolith will guard Authentication of the incoming  HTTP requests
 2. Live Editor Service will receive HTTP requests that contains Identification of the user (JWT token)
 3. Live Editor wil perform authentication based on JWT token data
 4. Live Editor will perform Authorization for the user requests based on its ACL configuration.
 
 See [sequence Diagram](https://sequencediagram.org/index.html#initialData=title%20Live%20Editor%20Access%20Control%20list%0A%0Aactor%20User%0Aparticipant%20Monolith%0Aparticipant%20Live%20Editor%0Adatabase%20Live%20Editor%20DB%0A%0A%0AUser%20%3C-%3EMonolith%3A%20init%20sessions%20(authentication)%0AUser%20-%3E%20Monolith%3A%20request%20Live%20Editor%20action%0Aactivate%20Monolith%0AMonolith%20-%3EMonolith%3A%20extend%20request%20%5Cn%20with%20user%20identification%20%5Cn%20(JWT%20token)%0AMonolith%20-%3ELive%20Editor%3Arequest%0Adeactivate%20Monolith%0Aactivate%20Live%20Editor%0ALive%20Editor%20-%3ELive%20Editor%3A%20extract%20user%20identity%0ALive%20Editor%20%3C-%3ELive%20Editor%20DB%3A%20validate%20access%20based%20on%20provided%20identity%0ALive%20Editor%20-%3E%5D%3Ado%20live%20changes%20if%20allowed)
 
 
 todo move jwt token creation on Monolith side
 todo map domain object to our use case
 
 ## Security of the solution
 Only searchlight authenticated user will be able to make calls to Live Editor service
 Only configured list (in Live Editor) will be able to perform changes
 Live Editor will process only requests with valid signed JWT token
 Valid signed JWT token will be created only with valid JWT secret
 Only production system (monolith) should have access to valid JWT secret for prod env   