# Alphv Admin Portal interview Assignment
## This project uses Java SpringBoot, Mysql and React. 
## Instructions to run:

### Prerequisites
- Install Docker. (https://www.docker.com/get-started/)
- Use command line arguments such as "curl" to test the REST API
- [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en) to test the REST API.


### Run the project using Docker
- Simply clone the repository. In your console:
  ```
  git clone https://github.com/wenjielee11/adminportal
  ```
- ```cd fetchBEinterview```
- Make sure you have docker installed first!
- Paste the following code in order:
```
docker compose build
```
```
docker compose up
```
- And that's it! You may use Postman or cURL to test the service routes.

### REST Web Service Routes


Request to add:
```
curl -H "Content-Type: application/json" -d '{"id":null, "firstname": "John", "lastName":"Doe", "color":"Red","shape":"Circle"}' http://localhost:8080/admin/add
```

EndPoints : 
```
http://localhost:8080/admin/add
```
```
http://localhost:8080/admin/edit
```
```
http://localhost:8080/admin/delete
```
To get all active users:
```
http://localhost:8080/admin/
```
