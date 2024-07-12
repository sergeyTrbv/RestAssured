package com.autodns.gateway.api;

import helpers.DataProviderTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Класс {@code GatewayAPITest} для тестирования API сервиса https://gateway.autodns.com/.
 *
 * @author SergeyTrbv
 */
public class GatewayAPITest {

    /**
     * Метод {@code testTagCount} для проверки количества тегов в XML-теле ответа.
     * <p>
     * getList(*) - *Groovy-подобный синтаксис для поиска всех элементов, у которых имя не равно null.
     * Это позволяет найти все теги в XML-документе.
     *
     * @param url              URL для запроса
     * @param expectedTagCount Ожидаемое количество тегов в ответе
     */
    @Test(description = "Проверка XML-тела ответа на количество тегов",
            dataProvider = "tagCountProvider", dataProviderClass = DataProviderTest.class)
    public void testTagCount(String url, int expectedTagCount) {
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(url)
                .then()
                .extract().response();

        response.print();
        int tagCount = response.htmlPath().getList("**.findAll { it.name() != null }").size();

        Assert.assertEquals(tagCount, expectedTagCount, "Ожидалось " + expectedTagCount +
                " тегов, но найдено " + tagCount + " тегов.");
    }
}




