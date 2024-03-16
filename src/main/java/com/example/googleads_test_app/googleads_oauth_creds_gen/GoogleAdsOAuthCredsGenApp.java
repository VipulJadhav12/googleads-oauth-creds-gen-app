package com.example.googleads_test_app.googleads_oauth_creds_gen;

import com.example.googleads_test_app.googleads_oauth_creds_gen.service.OAuthCredsGenerator;
import com.google.auth.oauth2.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GoogleAdsOAuthCredsGenApp implements CommandLineRunner {

	private final ApplicationContext applicationContext;

	@Autowired
	public GoogleAdsOAuthCredsGenApp(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
    }

	public static void main(String[] args) { SpringApplication.run(GoogleAdsOAuthCredsGenApp.class, args); }

	@Override
	public void run(String... args) throws Exception {

		// Get the OAuthCredsGenerator bean from the Spring Application context.
		OAuthCredsGenerator oAuthCredsGenerator = applicationContext.getBean(OAuthCredsGenerator.class);

		// Generate the authorization code.
		oAuthCredsGenerator.generateAuthorizationCode();

		Thread.sleep(60000);

		// Generate the OAuth credentials.
		UserCredentials userCredentials = oAuthCredsGenerator.generateOAuthCredentials();

		// Print the OAuth credentials to the console.
		oAuthCredsGenerator.printOAuthCreds(userCredentials);

		// Generate an exit code using the Spring Application context and an ExitCodeGenerator.
		int exitCode = SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
			@Override
			public int getExitCode() {
				return 0;
			}
		});

		System.exit(exitCode);
	}
}
