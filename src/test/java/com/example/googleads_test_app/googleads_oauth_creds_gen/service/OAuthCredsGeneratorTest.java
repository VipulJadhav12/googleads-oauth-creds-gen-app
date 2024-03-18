package com.example.googleads_test_app.googleads_oauth_creds_gen.service;

import com.example.googleads_test_app.googleads_oauth_creds_gen.utils.OAuthConfig;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.UserAuthorizer;
import com.google.auth.oauth2.UserCredentials;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OAuthCredsGeneratorTest {

    @Mock
    private OAuthConfig oAuthConfig;

    @Mock
    private UserAuthorizer userAuthorizer;

    @Mock
    private UserAuthorizer.Builder userAuthorizerBuilder;

    @InjectMocks
    private OAuthCredsGenerator oAuthCredsGenerator;

    private static final ImmutableList<String> SCOPES =
            ImmutableList.<String>builder().add("https://www.googleapis.com/auth/adwords").build();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void generateAuthorizationCodeTest() throws MalformedURLException {
        // Creates an anti-forgery state token as described here:
        // https://developers.google.com/identity/protocols/OpenIDConnect#createxsrftoken
        String state = new BigInteger(130, new SecureRandom()).toString(32);

        // Define the behavior of the mock OAuthConfig
        String clientId = "testClientId";
        String clientSecret = "testClientSecret";
        String redirectUri = "http://localhost:8080/oauth2callback";
        String loginEmailAddressHint = "testLoginEmailAddressHint@mydomain.com";

        when(oAuthConfig.getClientId()).thenReturn(clientId);
        when(oAuthConfig.getClientSecret()).thenReturn(clientSecret);
        when(oAuthConfig.getRedirectUri()).thenReturn(redirectUri);
        when(oAuthConfig.getLoginEmailAddressHint()).thenReturn(loginEmailAddressHint);

        // Mock the static method
        try (MockedStatic<UserAuthorizer> userAuthorizerMockedStatic = Mockito.mockStatic(UserAuthorizer.class)) {
            userAuthorizerMockedStatic.when(UserAuthorizer::newBuilder).thenReturn(userAuthorizerBuilder);
            when(userAuthorizerBuilder.setClientId(any())).thenReturn(userAuthorizerBuilder);
            when(userAuthorizerBuilder.setScopes(SCOPES)).thenReturn(userAuthorizerBuilder);
            when(userAuthorizerBuilder.build()).thenReturn(userAuthorizer);
            when(userAuthorizer.getAuthorizationUrl(anyString(), anyString(), any(URI.class)))
                    .thenReturn(URI.create("https://accounts.google.com/o/oauth2/auth?response_type=code&client_id=" +
                            clientId +
                            "&redirect_uri=" +
                            redirectUri +
                            "&scope=https://www.googleapis.com/auth/adwords&state=" +
                            state +
                            "&access_type=offline&approval_prompt=force&login_hint=" +
                            loginEmailAddressHint +
                            "&include_granted_scopes=true").toURL());

            // Call the method to test
            oAuthCredsGenerator.generateAuthorizationCode();

            // Verify that the methods on the mock UserAuthorizer and UserAuthorizer.Builder were called
            verify(userAuthorizerBuilder, times(1)).setClientId(any());
            verify(userAuthorizerBuilder, times(1)).setScopes(any());
            verify(userAuthorizerBuilder, times(1)).build();
            verify(userAuthorizer, times(1)).getAuthorizationUrl(anyString(), anyString(), any(URI.class));
        }
    }

    @Test
    public void generateOAuthCredentialsTest() throws IOException {
        // Define the behavior of the mock OAuthConfig
        String clientId = "testClientId";
        String clientSecret = "testClientSecret";
        String authorizationCode = "testAuthorizationCode";
        String redirectUri = "http://localhost:8080/oauth2callback";
        AccessToken testAccessToken = new AccessToken("testAccessToken", null);
        String refreshToken = "testRefreshToken";

        when(oAuthConfig.getClientId()).thenReturn(clientId);
        when(oAuthConfig.getClientSecret()).thenReturn(clientSecret);
        when(oAuthConfig.getAuthorizationCode()).thenReturn(authorizationCode);
        when(oAuthConfig.getRedirectUri()).thenReturn(redirectUri);

        // Mock the static method
        try (MockedStatic<UserAuthorizer> userAuthorizerMockedStatic = Mockito.mockStatic(UserAuthorizer.class)) {
            userAuthorizerMockedStatic.when(UserAuthorizer::newBuilder).thenReturn(userAuthorizerBuilder);
            when(userAuthorizerBuilder.setClientId(any())).thenReturn(userAuthorizerBuilder);
            when(userAuthorizerBuilder.setScopes(SCOPES)).thenReturn(userAuthorizerBuilder);
            when(userAuthorizerBuilder.build()).thenReturn(userAuthorizer);
            when(userAuthorizer.getCredentialsFromCode(anyString(), any(URI.class)))
                    .thenReturn(UserCredentials.newBuilder()
                            .setClientId(clientId)
                            .setClientSecret(clientSecret)
                            .setAccessToken(testAccessToken)
                            .setRefreshToken(refreshToken)
                            .build());

            // Call the method to test
            UserCredentials userCredentials = oAuthCredsGenerator.generateOAuthCredentials();

            // Verify that the methods on the mock UserAuthorizer and UserAuthorizer.Builder were called
            verify(userAuthorizerBuilder, times(1)).setClientId(any());
            verify(userAuthorizerBuilder, times(1)).setScopes(any());
            verify(userAuthorizerBuilder, times(1)).build();
            verify(userAuthorizer, times(1)).getCredentialsFromCode(anyString(), any(URI.class));

            // Assert that the returned UserCredentials has the expected client ID and client secret
            assertEquals(clientId, userCredentials.getClientId());
            assertEquals(clientSecret, userCredentials.getClientSecret());
        }
    }

}