# Message-board-api

- Basic CRUD functionality to manage messages on public board. ✨ ✨

## Features
- View all the messages list.
- Add a new message with details such as message, and username.
- Modify existing message with access to specific username content.
  (We can add spring boot authentication to make to better from access point of view)
- And similarly delete existing message with access to content created by username.

This application holds backend part only: written using Java and Spring boot framework mainly.

## Tech
- [Spring boot] - Backend service.
- [H2] - to persist data into in memory db
- [Swagger ui] - A super cool UI for backend endpoints testing or interaction.

## Installation

Backend requires Java SDK 11, Maven build tool version 3.6.2 to build and run.

Install the dependencies and devDependencies and start the server.

```sh
cd message-board-api
mvn clean install
mvn sping-boot:run
```

Once we start both the applications locally, backend is available on [http://localhost:8080/swagger-ui.html]

There is lot of scope to add authetication, build interactive UI, however this is a good start. ✨ ✨


## License

MIT
**Free Software, Yeah!!**
