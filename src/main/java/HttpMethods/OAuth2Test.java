package HttpMethods;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pojo.API;
import pojo.GetCourseResponse;
import pojo.WebAutomation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuth2Test {

    public static void main(String[] args) throws InterruptedException {

/*        System.setProperty("webdriver.chome.driver", "C:\\Users\\Raghuveer.MH\\Desktop\\Project\\chromedriver_win32 (2)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
        driver.findElement(By.xpath("//input[@type = 'email']")).sendKeys("raghuveermh30@gmail.com");
        driver.findElement(By.xpath("//input[@type = 'email']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("R@ghumh30179632487175");
        driver.findElement(By.xpath("//input[@type = 'email']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);*/
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWgs1zfM5YDKOta4VMeMgbpx81fGDY9gwUO-4isHqDBO6xc0by4vFqEfitDUeCD44Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
       String partialUrl =  url.split("code=")[1];
       String code =partialUrl.split("&scope")[0];
        System.out.println("Authorization Code is : " +code);

        String accessTokenResponse = given()

                .urlEncodingEnabled(false)

                .queryParams("code",code)


                .queryParams("access_type", "offline")
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

                .queryParams("grant_type", "authorization_code")

                .queryParams("state", "verifyfjdss")

                .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

                // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")



                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")

                .when().post("https://www.googleapis.com/oauth2/v4/token").then().log().all().extract().response().asString();

        JsonPath jsonPath = new JsonPath(accessTokenResponse);
        String accessToken = jsonPath.getString("access_token");

    /*    String response = given().queryParam("access_token", accessToken).log().all().when()
                .get("https://rahulshettyacademy.com/getCourse.php").then().log().all().extract().response().asString();
*/
        GetCourseResponse getCourseResponse= given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON).when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourseResponse.class);

        System.out.println(getCourseResponse.getLinkedIn());
        System.out.println(getCourseResponse.getInstructor());

        List<API> apiCourses = getCourseResponse.getCourses().getApi();

        for(int i = 0; i<apiCourses.size();i++){

            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
              String price=  apiCourses.get(i).getPrice().toString();
                System.out.println(price);
            }
        }

        // Get the courses of WebAutomation
        String courseTitles[] = {"Selenium Webdriver Java", "Cypress", "Protractor"};
        Arrays.asList(courseTitles);

        ArrayList<String> al = new ArrayList<>();
        List<WebAutomation> webAutomationList=  getCourseResponse.getCourses().getWebAutomation();

        for(int i =0 ; i<webAutomationList.size();i++){
          String webAutoCourseTitle=  webAutomationList.get(i).getCourseTitle();
            al.add(webAutomationList.get(i).getCourseTitle());
            System.out.println(webAutoCourseTitle);
        }

        List<String> expectedList=	Arrays.asList(courseTitles);
        Assert.assertTrue(al.equals(expectedList));
    }
}
