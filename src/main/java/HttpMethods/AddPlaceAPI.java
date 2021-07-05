package HttpMethods;

import Fils.Payload;
import Fils.ReusableClass;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import javax.activation.FileDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceAPI {
    public static void main(String[] args) throws IOException {

        //Validate Add Place API is working

        //given - all inputs detials
        //When - Submit the API - resource and http Method
        //Then - Validate the Response

        //Add place and update place with the New Address and Get place to validate if the new address is present in the response


        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payload.addPlace())
              //  .body(GenerateStringFromResource(""))
                .when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)")
                .extract().response().asString();

        System.out.println(response);

        JsonPath jsonPath = ReusableClass.rawToJson(response);
        String placeId = jsonPath.get("place_id").toString();

        System.out.println("Place Id " + placeId);

        //Update the Place

        String newAddress = "S R Road, 2nd Cross, Gandhinagara, Challakere";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        //Validate the updated address using Get Place API

        String getResponse = given().log().all().queryParam("place_id", placeId).queryParam("key", "qaclick123")
                .when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response().asString();

        System.out.println("Get Response \n" + getResponse);

        JsonPath jsonPath1 = ReusableClass.rawToJson(getResponse);
        String actualAddress = jsonPath1.get("address").toString();

        Assert.assertEquals(actualAddress, newAddress, "Address not matched");

    }

    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }


}
