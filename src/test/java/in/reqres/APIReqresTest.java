package in.reqres;

import data.*;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static specification.Specification.*;

public class APIReqresTest {


    @Test()
    public void UniqueAvatarFileNamesTest() {

        ResourceDto resource = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().body()
                .extract().body().as(ResourceDto.class);

        List<DataUserDto> users = resource.getData();
        Set<String> avatarFileNames = new HashSet<>();

        for (DataUserDto user : users) {
            String userAvatarUrl = user.getAvatar();
            String avatarFileName = userAvatarUrl.substring(userAvatarUrl.lastIndexOf('/') + 1);
            if (!avatarFileNames.add(avatarFileName)) {
                Assert.fail("Обнаружено повторяющееся имя файла аватара: " + avatarFileName);
            }
        }
        Assert.assertEquals(avatarFileNames.size(), users.size(),
                "Тест не пройдён. Все имена файлов аватаров должны быть уникальными.");
    }


    @Test()
    public void successfulAuthorizationTest() {
        installSpec(requestSpec());
        installSpec(responseSpec200());

        LoginRequestDTO request = new LoginRequestDTO("eve.holt@reqres.in", "cityslicka");
        Response response = given()
                .body(request)
                .when()
                .post("/api/login")
                .then()
                .extract().response();

        response.print();

        Assert.assertEquals(response.getStatusCode(), 200, "Неверный статус ответа");
        LoginResponseDTO loginResponse = response.as(LoginResponseDTO.class);
        Assert.assertNotNull(loginResponse.getToken());
        deleteSpec();
    }

    @Test()
    public void notSuccessfulAuthorizationTest() {
        installSpec(requestSpec());
        installSpec(responseSpec400());
        LoginRequestDTO request = new LoginRequestDTO("eve.holt@reqres.in", "");

        Response response = given()
                .body(request)
                .when()
                .post("/api/login")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 400);
        ErrorResponseDTO errorResponse = response.as(ErrorResponseDTO.class);
        Assert.assertEquals(errorResponse.getError(), "Missing password");
        deleteSpec();
    }


    @Test()
    public void dataSortedByYearTest() {
        installSpec(requestSpec());
        installSpec(responseSpec200());

        ResourceDto2 resourse = given()
                .when()
                .get("/api/unknown")
                .then()
                .log().body()
                .extract().body().as(ResourceDto2.class);


    }

}
