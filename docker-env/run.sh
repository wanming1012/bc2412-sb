cd bc-coin
mvn clean install
cd ..
cd demo-coin-web
mvn clean install
cd ..
docker compose build
docker compose up -d
