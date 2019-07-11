# uc4-AncillaryPricingDataGenerator

This project is responsible for generating booking data and then saving generated csv files into hdfs.

##Configuration

There are three parameters that can be pass as environment variables (option -e, example `-e HDFS_URL='hadoop'`) to docker:
* `HDFS_URL`  - the url of hdfs file system or ip of docker image with hadoop, if it is not passed, the default value hdfs://hdfs:8020 is used
* `INFINITE_GENERATE` - if this flag is set to true value, generator runs indefinitely, otherwise it will generate data only once
* `SAVE_TO_DATABASE` - if this flag is set to true value, generator saves the data into database defined in database.properties


##Building

1. Clone git projects and put in into this project structure


    uc4
        ├── uc4-ancillarypricingcalculator
        └── uc4-AncillaryPricingDataGenerator
    
2. Go into uc4-AncillaryPricingDataGenerator/scripts (cd uc4-AncillaryPricingDataGenerator/scripts)
3. Change build script permissions (chmod +x build.sh)
4. Then run script (./build.sh)

###`build.sh` script steps
* Generator code is compiled and docker image with output jar file is created by maven plugin.
* Docker image with calculator code is build


##Running

1. Execute building steps
2. Go into uc4-AncillaryPricingDataGenerator/scripts (cd uc4-AncillaryPricingDataGenerator/scripts)
3. Change build script permissions (chmod +x run.sh)
4. Then run script (./run.sh)

###`run.sh` script steps
* Running images are stopped.
* Docker network is created, if it is needed.
* Docker image with hadoop is started.
* The generator image is started and it connects to hadoop container using docker network to save generated data.
* Calculator docker image starts after termination of docker with generator. It reads data from the same hadoop docker, 
then saves output results in newly created directory ‘response’

All dockers are connected together within network created in step 2. 

