## reuirements
1. **docker-compose** version 1.27.4, build 40524192
2. **Docker version** 20.10.7, build f0df350
3. **git version** 2.25.1
4. **Apache Maven 3.6.3**
    Maven home: /usr/share/maven
    Java version: 11.0.11, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
    Default locale: en_US, platform encoding: UTF-8
    OS name: "linux", version: "5.4.0-80-generic", arch: "amd64", family: "unix"
5. ** java -version**
    openjdk version "11.0.11" 2021-04-20
    OpenJDK Runtime Environment (build 11.0.11+9-Ubuntu-0ubuntu2.20.04)
    OpenJDK 64-Bit Server VM (build 11.0.11+9-Ubuntu-0ubuntu2.20.04, mixed mode, sharing)



## Getting Started

#### 1. Get the code

  git clone https://github.com/YaredNegede/WorkMotionChallenge.git
  
#### 2. change dir  
  
  cd WorkMotionChallenge
  
#### 3. build the docker images  
  
  sudo docker-compose  build
  
#### 4. run the images  
  
  sudo docker-compose  up
  
#### 5. follow this line which will show you openapi spec

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
