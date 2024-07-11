package in.reqres.api;

import data.*;
import helpers.DataProviderTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static specification.Specification.*;

/**
 * Класс {@code InReqresAPITest} для тестирования API сервиса https://reqres.in/.
 *
 * @author SergeyTrbv
 */
public class InReqresAPITest {

    /**
     * Метод {@code UniqueAvatarFileNamesTest} для проверки уникальности имен файлов аватаров пользователей.
     *
     * @param page Номер страницы для запроса
     */
    @Test(description = "Проверка уникальности имен файлов аватаров пользователей",
            dataProvider = "pageNumber", dataProviderClass = DataProviderTest.class)
    public void UniqueAvatarFileNamesTest(Integer page) {

        ResourceDto resource = given()
                .when()
                .get("https://reqres.in/api/users?page=" + page)
                .then()
                .log().body()
                .extract().body().as(ResourceDto.class);

        List<DataUserDto> users = resource.getData();
        Set<String> uniqueNameAvatarFiles = new HashSet<>();

        for (DataUserDto user : users) {
            String userAvatarUrl = user.getAvatar();
            String avatarFileName = userAvatarUrl.substring(userAvatarUrl.lastIndexOf('/') + 1);

            if (!uniqueNameAvatarFiles.add(avatarFileName)) {
                Assert.fail("Обнаружено повторяющееся имя файла аватара: " + avatarFileName);
            }
        }

        Assert.assertEquals(uniqueNameAvatarFiles.size(), users.size(),
                "Количество уникальных имен файлов аватаров (" + uniqueNameAvatarFiles.size() +
                        ") не совпадает с количеством пользователей (" + users.size() + ").");
    }

    /**
     * Метод {@code successfulAuthorizationTest} для тестирования успешной авторизации пользователя.
     *
     * @param authorization Объект авторизации
     */
    @Test(description = "Тест на авторизацию пользователя (успешная)",
            dataProvider = "successfulAuthorization", dataProviderClass = DataProviderTest.class)
    public void successfulAuthorizationTest(Authorization authorization) {
        installSpec(requestSpec());
        installSpec(responseSpec200());

        Response response = given()
                .body(authorization)
                .when()
                .post("/api/login")
                .then()
                .extract().response();

        response.print();

        Assert.assertEquals(response.getStatusCode(), 200, "Для данного теста ожидаемый код состояния: 200, но получен: " + response.getStatusCode());
        Token authToken = response.as(Token.class);
        Assert.assertNotNull(authToken.getToken(), "Токен не должен быть null");

        deleteSpec();
    }

    /**
     * Метод {@code unSuccessfulAuthorizationTest} для тестирования ошибки авторизации из-за не введённого пароля.
     *
     * @param authorization Объект авторизации
     */
    @Test(description = "Тест на авторизацию пользователя (с ошибкой из-за не введённого пароля)",
            dataProvider = "unSuccessfulAuthorization", dataProviderClass = DataProviderTest.class)
    public void unSuccessfulAuthorizationTest(Authorization authorization) {
        installSpec(requestSpec());
        installSpec(responseSpec400());

        Response response = given()
                .body(authorization)
                .when()
                .post("/api/login")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 400, "Для данного теста ожидаемый код состояния: 400, но получен: " + response.getStatusCode());
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        Assert.assertEquals(errorResponse.getError(), "Missing password", "Ожидаемое сообщение об ошибке «Отсутствует пароль»");

        deleteSpec();
    }


    /**
     * Метод {@code dataSortedByYearTest} для тестирования отсортированных данных по годам.
     */
    @Test(description = "Тест на отсортированные данные по годам")
    public void dataSortedByYearTest() {
        installSpec(requestSpec());
        installSpec(responseSpec200());

        ResourceColorDto resource = given()
                .when()
                .get("/api/unknown")
                .then()
                .log().body()
                .extract().body().as(ResourceColorDto.class);

        List<Integer> years = resource.getData().stream()
                .map(ColorDto::getYear)
                .collect(Collectors.toList());
        List<Integer> sortedYears = years.stream()
                .sorted()
                .collect(Collectors.toList());

        Assert.assertEquals(years, sortedYears, "Данные не отсортированы по годам. Необходимо исправить сортировку.");

        deleteSpec();
    }
}



























