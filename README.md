# algafood-api

Food delivery API made in Java (JDK 17) with Spring (using Spring Boot 3.2.3)

## Prerequisites
- [Java (JDK 17)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Git](https://git-scm.com/downloads)
- [MySQL 8.0.35 or higher (with an open connection on port 3306)](https://dev.mysql.com/downloads/windows/installer/)

or 

- [Docker](https://www.docker.com/)

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

The project will start on the port ```80```

## Calling the project endpoints
With the project started, you can access the local url ```http://localhost:8080/swagger-ui/index.html``` (or ```http://localhost:80/swagger-ui/index.html``` in case of Docker) in order to see the endpoints documentation and also test them.

To complete the authorization process and login in the application, follow the steps below:

### 1. After accessing the documentation endpoint above, click in the "Authorize" button

![Authorize button](https://i.stack.imgur.com/ULVnQ.png)

### 2. Mark the checkboxes and fill the "client_id" and "client_secret" fields with _algafood-web_ and _web123_, respectively

![OAuth2 Credentials screen](https://snipboard.io/M1gJcp.jpg)

### 3. Fill the "Username" and "Password" fields with _joao.man@algafood.com_ and _123_, respectively

![OAuth2 user login credentials screen](https://snipboard.io/JOkgai.jpg) 


### 4. Click the button "Close" to close the OAuth2 authorization success screen 

![OAuth2 authorization success screen](https://snipboard.io/B5P0Sg.jpg)

You can also test the endpoints with the Postman collection available in the project root (see the important notes)

## Production environment
If you want to implement the project in production environment, you will need an AWS Account to use the following services:
- **Amazon ECS with Fargate:** To deploy the application container in the cloud environment
- **Amazon ECR:** To store the application Docker image
- **Amazon RDS**: To create the MySQL production instance
- **Amazon S3**: To store the saved product photos

In addition, it's recommended to use **AWS Parameter Store** to keep the environment variables values in a cleaner and safer way.

To generate the image to deploy in Amazon ECR, run the following command in the project root directory:
```
./mvnw clean package -Pdocker
```

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

Then, verify if the transitional e-mails were sent to the customer e-mail inbox.

### Test API endpoints in Postman
**Before start:** Be sure if your application is up on ```localhost:8080``` and the environment variables ```environmentUrl``` and ```redirectUri``` are both configured with the value ```127.0.0.1:8080``` on Postman (see the reference links to find out how to complete this step). 


**1. In Postman inicial page, import the API collection located in project root by clicking on "Import" button, then dragging the collection file into the Import field**

![Postman Import collection button](https://snipboard.io/mwaneT.jpg)
![Postman Import drag n drop](https://snipboard.io/PjAo2y.jpg)


**2. Select "OAuth2 Request Authorization Code" request into "OAuth2" folder, then copy the request URL and paste it in your browser, replacing {{environmentUrl}} and {{redirectUri}} by your localhost URL**

![Postman OAuth2 Get Authorization Code Request](https://snipboard.io/CFD3Zx.jpg)
![OAuth2 Authentication URL in browser](https://snipboard.io/twaAzR.jpg)


**3. Fill the "Username" and "Password" login fields with _joao.man@algafood.com_ and _123_, respectively**

![OAuth2 Login Credentials Page](https://snipboard.io/s5I6lS.jpg)


**4. After OAuth2 login, copy the code generated in the URL**

![OAuth2 Authorization Code in URL](https://snipboard.io/sRX4Je.jpg)


**5. Going back to Postman, select "Authorization Code - Request Token" request inside "OAuth2" folder, then paste the copied code into "code" body request variable and click "Send" button**

![OAuth2 Generate access token](https://snipboard.io/7kgPe6.jpg)


After following these steps, the authorization code for an admin user will be generated and you will be able to test the application endpoints on Postman.


## License

MIT

## Reference links
- [Create SendGrid API keys](https://docs.sendgrid.com/api-reference/api-keys/create-api-keys)
- [Install MySQL on Mac](https://dev.mysql.com/doc/mysql-installation-excerpt/8.3/en/macos-installation-pkg.html)
- [Install MySQL on Linux](https://dev.mysql.com/doc/refman/8.0/en/linux-installation.html)
- [Install Docker Desktop on Windows](https://docs.docker.com/desktop/install/windows-install/)
- [Install Docker Desktop on Mac](https://docs.docker.com/desktop/install/mac-install/)
- [Install Docker Desktop on Linux](https://docs.docker.com/desktop/install/linux-install/)
- [Configure and edit environment variables on Postman](https://learning.postman.com/docs/sending-requests/variables/environment-variables/)
