package me.geosmart.pssms.client.front.test;

import com.alibaba.fastjson.JSONObject;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import me.geosmart.pssms.Application;

import static io.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestTest {

    @Value("${local.server.port}")
    int port;

    @Before
    public void doBefore() {
        RestAssured.port = port;
    }

    @Test
    public void getTest() {
        given().queryParam("product_code", "6021+").get("/saleOrder/query").prettyPrint();
    }

    @Test
    public void postTest() {
        JSONObject parm = new JSONObject();
        parm.put("product_code", "6021");
        ValidatableResponse response = (ValidatableResponse) given().contentType("application/json")
                .request().body(parm.toJSONString()).when().post("/rpcs/tbSaleOrder").then()
                //断言，类似Assert
                .body("product_code", new Equals("6021"))
                //可以接多个断言
                .body("name", new ResponseAwareMatcher<Response>() {
                    @Override
                    public Matcher<?> matcher(Response response) throws Exception {
                        return new Equals("三毛");
                    }
                });
        JSONObject json = JSONObject.parseObject(response.extract().asString());//获取返回的json数据(2)
        System.out.println(json);
    }
}


