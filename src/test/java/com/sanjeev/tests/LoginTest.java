package com.sanjeev.tests;

import static io.restassured.RestAssured.given;
import static com.google.common.truth.Truth.assertThat;
import com.sanjeev.dataModel.Success;
import com.sanjeev.dataModel.UserCredentials;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class LoginTest extends TestBase {

    @Test
    public void userShouldGetOkStatusCodeWithValidCredentials() {
        // Create request data
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail("eve.holt@reqres.in");
        userCredentials.setPassword("cityslicka");
        Response response = given()
                .contentType("application/json").body(userCredentials).log().body()
                .when()
                .post("https://reqres.in/api/login");
        response.prettyPrint();
        assertThat(response.statusCode()).isEqualTo(200);
        Success success = response.body().as(Success.class);
        System.out.println(success.getToken());
        assertThat(success.getToken()).isNotNull();
    }

}