## Spring Security ACL

POC for https://wiki.conductor.com/display/CATS/Permissions+module+design+proposal#Permissionsmoduledesignproposal-Accesscontrollibrary


## Info
First assumption is that Authorization and authentication of the end user will happen in different ends:
 1. Monolith will guard Authentication of the incoming  HTTP requests
 2. Live Editor Service will receive HTTP requests that contains Identification of the user (JWT token)
 3. Live Editor wil perform authentication based on JWT token data
 4. Live Editor will perform Authorization for the user requests based on its ACL configuration.
 5. Searchlight will own permission of so called "Administrator" - user that will grand access to other users 
 
 See [sequence Diagram](https://sequencediagram.org/index.html#initialData=title%20Live%20Editor%20Access%20Control%20list%0A%0Aactor%20User%0Aparticipant%20Monolith%0Aparticipant%20Live%20Editor%0Adatabase%20Live%20Editor%20DB%0A%0A%0AUser%20%3C-%3EMonolith%3A%20init%20sessions%20(authentication)%0AUser%20-%3E%20Monolith%3A%20request%20Live%20Editor%20action%0Aactivate%20Monolith%0AMonolith%20-%3EMonolith%3A%20extend%20request%20%5Cn%20with%20user%20identification%20%5Cn%20(JWT%20token)%0AMonolith%20-%3ELive%20Editor%3Arequest%0Adeactivate%20Monolith%0Aactivate%20Live%20Editor%0ALive%20Editor%20-%3ELive%20Editor%3A%20extract%20user%20identity%0ALive%20Editor%20%3C-%3ELive%20Editor%20DB%3A%20validate%20access%20based%20on%20provided%20identity%0ALive%20Editor%20-%3E%5D%3Ado%20live%20changes%20if%20allowed)
 
 
 ## Security of the solution
 Only searchlight authenticated user will be able to make calls to Live Editor service
 Only configured list (in Live Editor) will be able to perform changes
 Live Editor will process only requests with valid signed JWT token
 Valid signed JWT token will be created only with valid JWT secret
 Only production system (monolith) should have access to valid JWT secret for prod env
 This solution support role separation + domain controlling: user may be PUBLISHER but he/she may publish only to its own domains
 
 ## Demo role configs
  POC configured with 3 users:   
  `1` - has ROLE_PUBLISHER for domains `conductor.com` and `apple.com`
  `2` - has ROLE_PUBLISHER for `apple.com` 
  `3` - has ROLE_ADMIN for `conductor.com` and `apple.com` and `example.com`
  
  Application has configured domains(web property). Roles are assigned to domains. Domains has Changes. 
  Each Change inherit permissions from the domain.
 
 ## How to DEMO
 - make sure you have mysql is running on your `localhost:3306`
 - start an application `mvn spring-boot:run`
 - after application is started get authentication token (ideally Monolith will generate it based on authenticated user)
 authenticate 3 users (generate 3 JWT tokens:
 ```bash
USER1=$(curl -X POST "http://localhost:8081/authenticate" -d '{"user":1}' -H "Content-Type: application/json")
USER2=$(curl -X POST "http://localhost:8081/authenticate" -d '{"user":2}' -H "Content-Type: application/json")
USER3=$(curl -X POST "http://localhost:8081/authenticate" -d '{"user":3}' -H "Content-Type: application/json")
```
 - using previously generated JWT token, check thet each user has different visibility (according to ACL configuration) 
 for the Domain Changes
 
 User `3` can see changes for domains `conductor.com` and `apple.com` and `example.com`
  ```bash
 curl -X GET "http://localhost:8081/changes" -H "Authorization:$USER3"
 ```

 User `2` can see changes only for domains `conductor.com`
 ```bash
curl -X GET "http://localhost:8081/changes" -H "Authorization:$USER2"
```

 User `1` can see changes for domains `conductor.com` and `apple.com`
 ```bash
curl -X GET "http://localhost:8081/changes" -H "Authorization:$USER1"
```
 and can publish new changes to one of those domains
 ```bash
curl -X POST "http://localhost:8081/changes" -d '{"id":33,"changeContent": "new change","webProperty": {"id": 3,"name": "conductor.com"}}' -H "Content-Type: application/json" -H "Authorization:$USER1"
```
 however can not publish to `example.com`
```bash
curl -X POST "http://localhost:8081/changes" -d '{"id": 34,"changeContent": "new change","webProperty": {"id": 1,"name": "example.com"}}' -H "Content-Type: application/json" -H "Authorization:$USER1"

```