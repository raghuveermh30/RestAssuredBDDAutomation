package Fils;

import io.restassured.path.json.JsonPath;

public class ReusableClass {

    public static  JsonPath rawToJson(String resposne){
        JsonPath jsonPath = new JsonPath(resposne);
        return jsonPath;
    }
}
