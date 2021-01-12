# BoxOrganizationBe

Backend for a simple, convoluted, box organization project.

## Running the software

The application runs by default on port 80, but you can change that with the `APPLICATION_PORT` env var.

To run it locally, you can opt to do so:

### Dockerized

Run the following command:

```shell
$ make run-dockerized
```

If you need to add or change any environment variables, look at the [application.yml](src/main/resources/application.yml) file to see which you wanna change by adding a `.env` file to your root folder.

### In IDE

To run in the IDE, set the mandatory environment variables, and run the following command to fire up the development DB with some pre-filled data: 
 
```shell
$ make run-db
```

## API

Check out the DTOs and Paths at [http://localhost/swagger-ui.html](http://localhost/swagger-ui.html)

All endpoints are set to use basic auth. By default, in the dev environment, the user / password is `user:user`
