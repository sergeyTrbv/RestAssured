package in.reqres;

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

public class APIReqresTest {

    /**
     * Используя сервис https://reqres.in/ получаем список пользователей со второй страницы.
     * Проверяем, что имена файлов аватаров пользователей уникальны
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
                "Тест не пройдён. Все имена файлов аватаров должны быть уникальными.");
    }

    /**
     * Используя сервис https://reqres.in/ тестируем авторизацию пользователя в системе.
     * Тест на успешную авторизацию пользователя
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

        Assert.assertEquals(response.getStatusCode(), 200, "Неверный статус ответа");
        Token authToken = response.as(Token.class);
        Assert.assertNotNull(authToken.getToken());
        deleteSpec();
    }

    /**
     * Используя сервис https://reqres.in/ тестируем авторизацию пользователя в системе.
     * Тест с ошибкой авторизации из-за не введённого пароля
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

        Assert.assertEquals(response.getStatusCode(), 400);
        System.out.println("Код ошибки: " + response.getStatusCode());
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        Assert.assertEquals(errorResponse.getError(), "Missing password");
        deleteSpec();
    }


    /**
     * Используя сервис https://reqres.in/ тестируем что операция LIST <RESOURCE>
     * возвращает данные отсортированные по годам
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
        Assert.assertEquals(years, sortedYears, "Годы не отсортированы");
    }

    /**
     * Используя сервис https://gateway.autodns.com/ производим проверку, что количество тегов равно 15.
     */
    @Test(description = "Проверка XML-тела ответа на количество тегов")
    public void testTagCount() {
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("https://gateway.autodns.com/")
                .then()
                .extract().response();

        response.print();
        //getList(*) - *Groovy-подобный синтаксис для поиска всех элементов, у которых имя не равно null.
        // Это позволяет найти все теги в XML-документе.
        int tagCount = response.htmlPath().getList("**.findAll { it.name() != null }").size();
        Assert.assertEquals(tagCount, 15, "Количество тегов не равно 15");
    }
}
