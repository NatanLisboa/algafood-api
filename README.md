# algafood-api

Food delivery API made in Java (JDK 17) with Spring (using Spring Boot 3.2.3)

## Prerequisites
- Java (JDK 17)
- Git
- MySQL 8.0.35 (with an open connection on port 3306)

or 

- Docker

## Build and run the project
### 1. Fork the project to your remote repository
In the project's root directory on github, select the fork option found in the top right corner of the screen to copy the project to your remote repository

![GitHub fork button](https://docs.github.com/assets/cb-40742/mw-1440/images/help/repository/fork-button.webp)

This project does not accept new pull requests. The fork must be done just for personal use.

### 2. Clone the project in your local environment
With an open terminal, in the folder where you want to place the project, type the command below to clone the project to your local environment

```
git clone https://github.com/<your username>/algafood-api.git
```

### 3. Setup the environment variables 
The project has some environment variables that need to be configured before running the project in local environment, these are:

- **DB_HOST:** Database host (localhost by default)
- **SPRING_DATASOURCE_USERNAME:** Database connection username
- **SPRING_DATASOURCE_PASSWORD:** Database connection password
- **PHOTO_CATALOG_LOCAL_URL:** Restaurant product photos directory
- **ALGAFOOD_AUTOMATIC_EMAIL_SENDER:** Sender e-mail address for the automatic transactional e-mails sent by the application (must be a valid e-mail address)
- **OAUTH2_CLIENT_ID:** OAuth2 client id in Authorization Code flow (The value for this environment variable must be **algafood-web**)
- **OAUTH2_CLIENT_SECRET:** OAuth2 client secret in Authorization Code flow (The value for this environment variable must be **web123**)

### 4.a. Running the project (without Docker)
In the project root directory, run the following command:

```
./mvnw clean install -DskipTests
```

This command will install all the Maven dependencies and build a new package of the project.
Then, run the following command to run the project in your local environment

```
java -jar ./target/algafood-api-0.0.1-SNAPSHOT.jar --spring.profiles.active=development
```

The project will start on the port ```8080```

### 4.b. Running the project (with Docker)
In the project root directory, run the following command:

```
docker compose up
```

After this, the project will start on the port ```80```

## Calling the project endpoints
With the project started, you can access the local url ```http://localhost:8080/swagger-ui/index.html``` (or ```http://localhost:80/swagger-ui/index.html``` in case of Docker) in order to see the endpoints documentation and also test them.

To complete the authorization process and login in the application, follow the steps below:

1. After accessing the documentation endpoint above, click in the "Authorize" button
![Authorize button](https://i.stack.imgur.com/ULVnQ.png)

## Important notes
### Test SMTP e-mail sending in dev environment
1. Go to "application.properties" file of the project
2. Change 'algafood.email.impl' value from "mock" to "smtp"
3. Uncomment the "E-mail" related properties in "application-development.properties" file
4. Define an e-mail value to the environment variable ALGAFOOD_AUTOMATIC_EMAIL_SENDER (must be a valid e-mail address)
5. Create a SendGrid account using the same e-mail defined to ALGAFOOD_AUTOMATIC_EMAIL_SENDER
6. Create a new SendGrid API key to the new account (see the first reference link to know how to complete this step)
7. Fill the field 'spring.mail.password' with the API key generated in SendGrid (Using environment variable to hide its value is strongly recommended)
8. Run the project in development profile
9. Issue an order with a customer with a valid email
10. Confirm and deliver or cancel the order issued

Then, verify if the transitional e-mails was sent to the customer e-mail inbox.

## License

MIT

## Reference links
- [Create SendGrid API keys](https://docs.sendgrid.com/api-reference/api-keys/create-api-keys)
