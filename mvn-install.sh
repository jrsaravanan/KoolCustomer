
# Build Maven
# clean install
mvn clean install

#To Build docker images
mvn dockerfile:build -pl customer-admin

mvn dockerfile:build -pl customer-discovery

mvn dockerfile:build -pl customer-service

mvn dockerfile:build -pl customer-auth

mvn dockerfile:build -pl customer-edge-service

