# City Route [Spring Boot](http://projects.spring.io/spring-boot/) App

A simple app that determines if two cities are connected.
Two cities are considered connected if there’s a series of roads that can be traveled from one city to another.

This app is written in programmin language java, and also use [Swagger Api](www,swagger.io)

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org) or above

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.example.takehomechallenge` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
* **URL**

  /connected?origin=city1&destination=city2

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**

* **Data Params**
    
    source=[string]`
    
    destination=[string]

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ string : yes|no : "yes"  or "no}`
 
* **Error Response:**

    * **NONE**
 

######This app exposes  *one* **endpoint** while running on localost and on default port:
```shell
http://localhost:8080/connected?origin=city1&destination=city2
```
######To access Swagger UI use the following link when your app is running on localhost on default 
port: 8080, otherwise change your hostname and port number accordingly:
```aidl
http://localhost:8080/swagger-ui.html

```

###Usage

    - See *application.properties* for configuration
    
    - Populate data.txt file as necessary

```aidl
    ### Run the app
    $ ./mvnw spring-boot:run
```


###Client Sample:

```aidl
   Input data file city.txt content is:

     Boston, New York

     Philadelphia, Newark

     Newark, Boston

     Trenton, Albany
```

```aidl

    #invoke rest api end point:
    - Curl: 
    curl -X GET "http://localhost:8080/connected?destination=Newark&origin=Boston" -H "accept: */*"
    Request URL:
    http://localhost:8080/connected?destination=Newark&origin=Boston
    Server Response: yes
    Code:200


    - Curl: 
    curl -X GET "http://localhost:8080/connected?destination=Philadelphia&origin=Boston" -H "accept: */*"
    Request URL:
    http://localhost:8080/connected?destination=Philadelphia&origin=Boston
    Server Response: yes
    Code:200

    - Curl: 
    curl -X GET "http://localhost:8080/connected?destination=Albany&origin=Philadelphia" -H "accept: */*"
    Request URL:
    http://localhost:8080/connected?destination=Albany&origin=Philadelphia
    Server Response: no
    Code:200

```

## Roadmap
Varous endpoints that would be invoked using methods such as **POST**, **PUT**, **DELETE** will the added. Also Spring Security will be in place
to secure various endpoints that would allow to *add new route, *update* an existin route or *delete* existing one. **Input data** file would be accessed from *Sprin Boot 
Config Server* that would use *Github* as *backend*.  


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate and follow these steps below:

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D


## Copyright

Released under the Apache License 2.0



