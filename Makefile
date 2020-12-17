SERVICE_IMAGE=bot
SERVICE_TAG=1.0.0

DB_NAME=bot-db
DB_HOST=localhost
DB_USER=postgres
DB_PASSWORD=postgres

export DB_NAME
export DB_HOST
export DB_USER
export DB_PASSWORD
export SERVICE_IMAGE
export SERVICE_TAG

.PHONY: run-db
run-db:
	docker-compose up db

.PHONY: run-dockerized
run-dockerized: build
	docker-compose up

.PHONY: build
build:
	./gradlew jibDockerBuild --image ${SERVICE_IMAGE}:${SERVICE_TAG}
