# Sourcery Academy 2023 special task
## Launch instructions:
Use provided steps to launch project. 
### 1. Back-end:
__make sure 8080 port is available!__

commands to execute in terminal (starting in root directory):\
$ cd back-end \
$ java -jar backend.jar

_Side note: Back-end is based on Spring boot, H2, JPA. Compiled and build with maven using Java 16 version_ 

### 2. Front-end:
__make sure 3000 port is available!__

commands to execute in terminal (starting in root directory):\
$ cd front-end \
$ cd website \
$ npm install 

__choose between production and development builds:__ \
(these will launch production build) \
$ npm run build \
$ serve build 

(this will launch development build) \
$ npm start 

_Side note: Front-end is based on React, Axios, serve(for production deployment)_ 

##Notes on project:\
There remains TODOs, which make project faulty and not ready for deployment. \
Example 1: if there is one faulty data field (let's say invalid email format) in .csv file, API doesn't communicate to front-end, so user only knows that there was "Server error"
Example 2: end user should know, which user is faulty, for easier data fixing
Example 3: API doesn't communicate, that employee exists with identical email or phone number, so front-end only receives "server error"

