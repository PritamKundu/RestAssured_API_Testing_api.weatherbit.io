package AirQualityForecast;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Air_Quality_Forecast {

    @Test
    public void latitudeLongitude(){

        //base URI with Rest Assured class
        RestAssured.baseURI= "https://api.weatherbit.io/v2.0";

        //obtain Response from GET request
        Response res
                = given().
                        header("Content-Type", "application/json")
                        .accept(ContentType.JSON)

                  .when()
                        .get("/forecast/airquality?city=dhaka&country=bangladesh&key=097b8d8826bb439bb0896fd225bbfd9f&include=minutely");

                 //System.out.println(res.prettyPrint());

                //Assertion - Header Data
                Assert.assertEquals(res.statusCode(),200);
                Assert.assertEquals(res.contentType(),"application/json; charset=utf-8");


                //Assertion - Body Data
                JsonPath j = new JsonPath(res.asString());

                //Assertion - TimeZone
                String timezone = j.getString("timezone");
                System.out.println("name for timezone: " + timezone);
                Assert.assertEquals(timezone, "Asia/Dhaka");

                //Assertion - CityName
                String cityName = j.getString("city_name");
                System.out.println("name for city name: " + cityName);
                Assert.assertEquals(cityName, "Dhaka");

                //Assertion - Country Code
                String countryCode = j.getString("country_code");
                System.out.println("name for country code: " + countryCode);
                Assert.assertEquals(countryCode, "BD");

                //Assertion - State Code
                String stateCode = j.getString("state_code");
                System.out.println("name for state code: " + stateCode);
                Assert.assertEquals(stateCode, "81");
        }

    }

