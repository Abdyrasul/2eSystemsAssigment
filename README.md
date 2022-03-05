# 2eSystemsAssigment

URL for published documentation
https://documenter.getpostman.com/view/11222410/UVktoY1M

###Reuirements
JDK: 1.8.0_192\
MYSQL: 8.0.28

###Automated Task
Parameters:\
scheduling.fixedDelay(default=PT5S)\
scheduling.enabled(default=false)\
PT5S = every 5 seconds



####Example:
java -jar metar-1.0.jar scheduling.enabled=true scheduling.fixedDelay=PT5S

You can find jar file inside the project folder