# Build and Deploy 

## Pre-requisites 
- Java 1.8+  
- MVN 3.3+ 
- Mongo DB installed and running 
- Internet Access

This is a standalone app 

## Build Instructions 
mvn package 

## Run instructions 

Ensure that MongoDB is running on localhost on port 27017 ; Then 

mvn spring-boot:run

## Loading Mongo DB 

Ensure that the spring boot app is running ; then 

curl http://localhost:8080/startprocessor/{repoName}/{repoOwner}/{since}

or in a browser load the above URL 
repoName: name of the gitbut repository 
repoOwner : owner of the gitbut repository 
since : Loads commit data since a given date. Format YYYY-MM-DD

ex: http://localhost:8080/startprocessor/capitalone/hygieia/since=2017-01-01

## The Dashboard 

open http://localhsot:8080 in a web browser ( preferably Chrome ) 
Select 'Bar' for Month by Month data 
Select 'Pie' for total activity 


