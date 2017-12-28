
# Build Maven
# clean install
mvn clean install

#To Build docker images
mvn dockerfile:build -pl customer-admin

mvn dockerfile:build -pl customer-discovery

mvn dockerfile:build -pl customer-service

