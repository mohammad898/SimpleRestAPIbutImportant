import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetApI {

    @Test
    public void GetWeatherDetails()
    {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";


        RequestSpecification httpRequest = RestAssured.given();


        Response response = httpRequest.request(Method.GET, "/Plano");


        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);


        int statusCode = response.getStatusCode();

        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode,200 );

        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine , "HTTP/1.1 200 OK" );
    }

    @Test
    public void GetWeatherDetailsInvalidCity()
    {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("/78789798798");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
    }
    @Test
    public void GetWeatherStatusLine()
    {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // Get the status line from the Response and store it in a variable called statusLine
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine /*actual value*/, "HTTP/1.1 200 OK" /*expected value*/, "Correct status code returned");
    }

    @Test
    public void GetWeatherHeaders()
    {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // Reader header of a give name. In this line we will get
        // Header named Content-Type
        String contentType = response.header("Content-Type");
        System.out.println("Content-Type value: " + contentType);

        // Reader header of a give name. In this line we will get
        // Header named Server
        String serverType =  response.header("Server");
        System.out.println("Server value: " + serverType);

        // Reader header of a give name. In this line we will get
        // Header named Content-Encoding
        String acceptLanguage = response.header("Content-Encoding");
        System.out.println("Content-Encoding: " + acceptLanguage);
    }

    //@Test
    public void GetWeatherHeaders2()
    {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // Reader header of a give name. In this line we will get
        // Header named Content-Type
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType /* actual value */, "application/json" /* expected value */);

        // Reader header of a give name. In this line we will get
        // Header named Server
        String serverType =  response.header("Server");
        Assert.assertEquals(serverType /* actual value */, "nginx/1.12.1" /* expected value */);

        // Reader header of a give name. In this line we will get
        // Header named Content-Encoding
        String contentEncoding = response.header("Content-Encoding");
        Assert.assertEquals(contentEncoding /* actual value */, "gzip" /* expected value */);
    }

    @Test
    public void WeatherMessageBody()
    {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();

        // To check for sub string presence get the Response body as a String.
        // Do a String.contains
        String bodyAsString = body.asString();
        Assert.assertEquals(bodyAsString.contains("Hyderabad") /*Expected value*/, true /*Actual Value*/, "Response body contains Hyderabad");
    }
    @Test
    public void WeatherMessageBody2()
    {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();

        // To check for sub string presence get the Response body as a String.
        // Do a String.contains
        String bodyAsString = body.asString();

        // convert the body into lower case and then do a comparison to ignore casing.
        Assert.assertEquals(bodyAsString.toLowerCase().contains("hyderabad") /*Expected value*/, true /*Actual Value*/, "Response body contains Hyderabad");
    }
}
