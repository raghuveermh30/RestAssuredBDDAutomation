package Fils;

public class Payload {

    public static String addPlace(){

        return "{\n" +
                "    \"location\": {\n" +
                "        \"lat\": -38.383494,\n" +
                "        \"lng\": 33.427362\n" +
                "    },\n" +
                "    \"accuracy\": 50,\n" +
                "    \"name\": \"Raghuveer TEst 123\",\n" +
                "    \"phone_number\": \"+919480739651\",\n" +
                "    \"address\": \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\n" +
                "        \"shoe park\",\n" +
                "        \"shop\"\n" +
                "    ],\n" +
                "    \"website\": \"http://google.com\",\n" +
                "    \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String getCourses(){

       return "{\n" +
               "\n" +
               "\"dashboard\": {\n" +
               "\n" +
               "\"purchaseAmount\": 1162,\n" +
               "\n" +
               "\"website\": \"rahulshettyacademy.com\"\n" +
               "\n" +
               "},\n" +
               "\n" +
               "\"courses\": [\n" +
               "\n" +
               "{\n" +
               "\n" +
               "\"title\": \"Selenium Python\",\n" +
               "\n" +
               "\"price\": 50,\n" +
               "\n" +
               "\"copies\": 6\n" +
               "\n" +
               "},\n" +
               "\n" +
               "{\n" +
               "\n" +
               "\"title\": \"Cypress\",\n" +
               "\n" +
               "\"price\": 40,\n" +
               "\n" +
               "\"copies\": 4\n" +
               "\n" +
               "},\n" +
               "\n" +
               "{\n" +
               "\n" +
               "\"title\": \"RPA\",\n" +
               "\n" +
               "\"price\": 45,\n" +
               "\n" +
               "\"copies\": 10\n" +
               "\n" +
               "},\n" +
               "{\n" +
               "\n" +
               "\"title\": \"Appium\",\n" +
               "\n" +
               "\"price\": 36,\n" +
               "\n" +
               "\"copies\": 7\n" +
               "\n" +
               "}\n" +
               "\n" +
               "]\n" +
               "\n" +
               "}";
    }

    public static String addBook(String isbn, String id){
        String payload = "{\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+id+"\",\n" +
                "\"author\":\"John foer\"\n" +
                "}\n";

        return payload;
    }
}
