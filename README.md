# SimpleApplication for CICD Hands-on 

## What's this
This application is simple Spring Boot application.   
It made this for CICD hands-on.  
This application has no meaning.

## How To Build

#### build 
```bash
gradle build
```

#### build and don't execute test
```bash
gradle build -x test
```

## How To Execute at Local

#### Premise

- have to installed docker.
- have to installed java. required 8 or later.
- using the included Gradle wrapper.

#### Startup MySQL

Start using Docker

```bash
$ cd {projectRoot}/environment/docker/develop
$ docker-compose up -d
```

#### Launch Application

```bash
gradle bootRun
```

It is completed when the following display is made.

```bash
2018-MM-DD 17:34:40.880  INFO 32234 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2018-MM-DD 17:34:40.971  INFO 32234 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2018-MM-DD 17:34:40.974  INFO 32234 --- [  restartedMain] c.e.s.SimpleSpringbootAppApplication     : Started SimpleSpringbootAppApplication in 4.719 seconds (JVM running for 5.165)
2018-MM-DD 17:36:03.151  INFO 32234 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2018-MM-DD 17:36:03.151  INFO 32234 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2018-MM-DD 17:36:03.164  INFO 32234 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 13 ms

```
visit to this url.

```
http://localhost:8080
```
