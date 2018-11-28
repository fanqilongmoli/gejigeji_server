git pull

# mvn clean install -X -DskipTests
# ./startup.sh

mvn clean package -DskipTests dockerfile:build
