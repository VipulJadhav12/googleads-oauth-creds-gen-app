# Google Ads API OAuth 2.0 Credentials Generator

This repository contains a Java - Spring Boot application which provide a simple and efficient way to generate OAuth 2.0 credentials for the Google Ads API. These credentials are necessary for authenticating and authorizing custom Desktop/Web based application(s) to interact with the Google Ads API.

## Features

- <b>OAuth 2.0 Credentials Generation</b>: The application generates OAuth 2.0 credentials for the Google Ads API. This is handled by the `OAuthCredsGenerator` service class, which uses the Google's Authorization server to generate an authorization code and then uses this code to generate the OAuth credentials.
- <b>Configurable OAuth Settings</b>: The application uses the `OAuthConfig` class to manage the necessary configurations for the OAuth 2.0 credentials generation. Configurations like: Client ID, Client Secret, Developer Token, Login Customer ID, Login Email Address Hint, and Redirect URI can easily be stored in and modified from the `application.properties` file.
- <b>Secure Random State Token Generation</b>: The application generates a secure random state token for anti-forgery during the OAuth 2.0 authorization process.
- <b>Detailed Console Outputs</b>: The application provides detailed console outputs during the OAuth 2.0 credentials generation process, including the authorization URL and the generated OAuth credentials.
- <b>Cross-Platform Compatibility</b>: The application can be built and run on both Linux and Windows operating systems using the provided Maven wrapper scripts (`mvnw` for Linux and `mvnw.cmd` for Windows).

## Requirements

- Java 8 or higher
- Maven 3.0+
- Google Ads account (Production and Test account).
- Google Cloud Project with the Google Ads API enabled.
- Google Cloud OAuth 2.0 `Client ID` and `Client Secret`.
- `Developer Token` associated with a Production Google Ads Manager account.
- `Login Customer ID` of a Production Google Ads Manager account.

## Tech Stack

- Java 8 or higher
- Maven 3.0+ (for building the application)
- Spring Boot 2.7.0+
- JUnit 5
- Mockito

## Run Locally

Clone the project

```bash
  git clone https://github.com/VipulJadhav12/googleads-oauth-creds-gen-app.git
```

Go to the project directory

```bash
  cd googleads-oauth-creds-gen-app
```

Open and edit the src/main/resources/application.properties file

```bash
  google.ads.clientId=INSERT_CLIENT_ID_HERE
  google.ads.clientSecret=INSERT_CLIENT_SECRET_HERE
  google.ads.developerToken=INSERT_DEVELOPER_TOKEN_HERE
  google.ads.loginCustomerId=INSERT_LOGIN_CUSTOMER_ID_HERE
  google.ads.loginEmailAddressHint=INSERT_LOGIN_EMAIL_ADDRESS_HINT_HERE
  google.ads.redirectUri=http://localhost:8080/oauth2callback
```

Run the above source code through command line using `mvnw`

```bash
  mvnw spring-boot:run
```

Follow the instructions printed on the console to generate your `OAuth 2.0` credentials.

Compile and Package the above source code as a JAR

```bash
  mvnw clean package
```
or
```bash
  mvnw clean package -Dmaven.test.skip=true
```

## Running Tests

To run tests, run the following commands

Go to the project directory

```bash
  cd googleads-oauth-creds-gen-app
```

Run the unit test-cases through command line using `mvnw`

```bash
  mvnw test
```
or
```bash
  mvnw.cmd test
```

## Authors

- [@VipulJadhav12](https://github.com/VipulJadhav12)