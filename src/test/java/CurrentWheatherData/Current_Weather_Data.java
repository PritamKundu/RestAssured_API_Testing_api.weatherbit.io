package CurrentWheatherData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Current_Weather_Data {

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
                        .get("/current?lat=23.8103&lon=90.4125&key=097b8d8826bb439bb0896fd225bbfd9f");

                 //System.out.println(res.prettyPrint());

                //Assertion - Header Data
                Assert.assertEquals(res.statusCode(),200);
                Assert.assertEquals(res.contentType(),"application/json; charset=utf-8");


                //Assertion - Body Data
                JsonPath j = new JsonPath(res.asString());

                //Assertion - TimeZone
                String timezone = j.getString("data.timezone");
                System.out.println("name for timezone: " + timezone);
                Assert.assertEquals(timezone, "[Asia/Dhaka]");

                //Assertion - CityName
                String cityName = j.getString("data.city_name");
                System.out.println("name for city name: " + cityName);
                Assert.assertEquals(cityName, "[Paltan]");

                //Assertion - Sunrise
                String sunrise = j.getString("data.sunrise");
                System.out.println("name for sunrise: " + sunrise);
                Assert.assertEquals(sunrise, "[23:40]");

                //Assertion - Temp
                String temp = j.getString("data.temp");
                System.out.println("name for temp: " + temp);
                //Assert.assertEquals(temp, "[33]");

        }

    }

