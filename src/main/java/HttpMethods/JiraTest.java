package HttpMethods;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraTest {

    public static void main(String[] args) {

        SessionFilter sessionFilter = new SessionFilter();

        RestAssured.baseURI = "http://localhost:8080";

        //Login Scenario
     //  given().relaxedHTTPSValidation() = For Https if any certification
        String response =      given().header("Content-Type", "application/json").log().all().
                filter(sessionFilter).when().post("/rest").
                then().log().all().extract().response().asString();
        String  expectedMessage = "Hi! How are you?";

        // Add Comment
      String   addCommentResponse=  given().pathParam("key", "").log().all().header("Content-Type", "application/json")
                .body("").filter(sessionFilter).log().all().when().post("").then().assertThat().statusCode(201).extract().response().asString();
        JsonPath js = new JsonPath(addCommentResponse);
       String commentId= js.getString("id").toString();

        //Add Attachment
        given().header("X-Atlassian-Token", "no-check").filter(sessionFilter).pathParam("key", "")
                .header("Content-Type", "multipart/form-data").multiPart("file", new File("File Path"))
                .when().post("url").then().log().all().assertThat().statusCode(200).extract().response().asString();

        // Get Issue
        String getDetails = given().filter(sessionFilter).pathParam("key", "").queryParam("fields", "comment").log().all()
                .when().get("uri").then().log().all().extract().asString();
        System.out.println(getDetails);

        JsonPath js1 = new JsonPath(getDetails);
       int commentsSize= js1.get("fields.comment.comments.size()");
       for(int i=0;i<commentsSize;i++){

           String commentIdIssue = js1.get("fields.comment.comments["+i+"].id").toString();
           if(commentIdIssue.equalsIgnoreCase(commentId)){
             String message=  js1.get("fields.comment.comments["+i+"].body").toString();
               System.out.println(message);
               Assert.assertEquals(message, expectedMessage);
           }
        }



    }
}
