DB_NAME=bot-db
DB_HOST=localhost
DB_USER=postgres
DB_PASSWORD=postgres

export DB_NAME
export DB_HOST
export DB_USER
export DB_PASSWORD

.PHONY: run-db
run-db:
	docker-compose up db
