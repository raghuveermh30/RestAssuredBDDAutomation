package Fils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class DynamicJson {


    @Test(dataProvider = "BookDetails")
    public void addBook(String isbn, String isbnId) {
        String response = RestAssured.baseURI = "https://rahulshettyacademy.com";
        given().log().all().header("Content-Type", "application/json").body(Payload.addBook(isbn, isbnId))
                .when().log().all()
                .post("Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();

        JsonPath js= ReusableClass.rawToJson(response);

        String id=js.get("ID");

        System.out.println(id);

        //Delete Book
    }

    @DataProvider(name = "BookDetails")
    public Object[][] getBookData(){
        return new Object[][] {{"dsadasd", "32321"},{"fddaasdasd", "654323"},{"cczxcsd", "8765"}};
    }

}
