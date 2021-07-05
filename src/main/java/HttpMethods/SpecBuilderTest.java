package HttpMethods;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlaceAPIRequest;
import pojo.LocationRequest;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {

    public static void main(String[] args) {

        AddPlaceAPIRequest addPlaceAPIRequest = new AddPlaceAPIRequest();
        addPlaceAPIRequest.setAccuracy(50);
        addPlaceAPIRequest.setAddress("S R Road, 2md Cross, Gandhinagara, Challakere");
        addPlaceAPIRequest.setLanguage("Kannada");
        addPlaceAPIRequest.setPhone_number("345678");
        addPlaceAPIRequest.setWebsite("https://raghuveer.com");
        addPlaceAPIRequest.setName("Raghuveer");
        List<String> list = new ArrayList<String>();
        list.add("shoe park");
        list.add("shop");
        addPlaceAPIRequest.setTypes(list);

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setLat(-38.383494);
        locationRequest.setLng(33.427362);
        addPlaceAPIRequest.setLocation(locationRequest);

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().queryParams("key", "qaclick123").body(addPlaceAPIRequest).log().all().when().
                post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().response().asString();

        System.out.println(response);

        System.out.println("******************");

        RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();
        RequestSpecification res=given().spec(req)
                .body(addPlaceAPIRequest);

        ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        Response response1 =res.when().post("/maps/api/place/add/json").
                then().spec(resspec).extract().response();

        String responseString=response1.asString();
        System.out.println(responseString);

    }
}
