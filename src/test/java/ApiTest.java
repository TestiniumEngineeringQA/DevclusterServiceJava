import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {

    @Test
    public void testGetRequest() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(400, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }

    @Test
    public void testGetAllPosts() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts")
                .asJson();

        assertEquals(200, response.getStatus());
        assertTrue(response.getBody().getArray().length() > 0);
    }

    @Test
    public void testCreatePost() {
        HttpResponse<JsonNode> response = Unirest.post("https://jsonplaceholder.typicode.com/posts")
                .header("Content-Type", "application/json")
                .body("{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}")
                .asJson();

        assertEquals(201, response.getStatus());
        assertEquals("foo", response.getBody().getObject().getString("title"));
    }

    @Test
    public void testUpdatePost() {
        HttpResponse<JsonNode> response = Unirest.put("https://jsonplaceholder.typicode.com/posts/1")
                .header("Content-Type", "application/json")
                .body("{\"id\":1, \"title\":\"updated title\", \"body\":\"updated body\", \"userId\":1}")
                .asJson();

        assertEquals(200, response.getStatus());
        assertEquals("updated title", response.getBody().getObject().getString("title"));
    }

    @Test
    public void testDeletePost() {
        HttpResponse<String> response = Unirest.delete("https://jsonplaceholder.typicode.com/posts/1")
                .asString();

        assertEquals(200, response.getStatus()); // Bazı sistemler 204 dönebilir
    }

    @Test
    public void testGetNonExistingPost_ShouldFail() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/99999")
                .asJson();

        assertEquals(200, response.getStatus());

        JsonNode body = response.getBody();
        System.out.println("Response: " + body.toString());

        assertEquals(1, body.getObject().getInt("id")); // bu satır testin patlamasına sebep olur
    }

    @Test
    public void testGetPostWithQueryParam() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts")
                .queryString("title", "qui est esse")
                .asJson();

        assertEquals(200, response.getStatus());
        assertTrue(response.getBody().getArray().length() >= 0); // eşleşmeyebilir ama istek başarılı olmalı
    }

    @Test
    public void testGetWithCustomHeader() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .header("Accept", "application/json")
                .asJson();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testCreatePostAndCheckBody() {
        HttpResponse<JsonNode> response = Unirest.post("https://jsonplaceholder.typicode.com/posts")
                .header("Content-Type", "application/json")
                .body("{\"title\":\"test title\",\"body\":\"test body\",\"userId\":5}")
                .asJson();

        assertEquals(201, response.getStatus());
        assertEquals("test body", response.getBody().getObject().getString("body"));
    }

    @Test
    public void testGetPostsByUserId() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts")
                .queryString("userId", 1)
                .asJson();

        assertEquals(200, response.getStatus());
        assertTrue(response.getBody().getArray().length() > 0);
    }

    @Test
    public void testCreatePostWithInvalidBody() {
        HttpResponse<JsonNode> response = Unirest.post("https://jsonplaceholder.typicode.com/posts")
                .header("Content-Type", "application/json")
                .body("{}")
                .asJson();

        assertEquals(201, response.getStatus()); // jsonplaceholder yine de bir şey dönebilir
    }

    @Test
    public void testInvalidUrl() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/wrongpath")
                .asJson();

        assertEquals(404, response.getStatus());
    }

    @Test
    public void testGetUserDetails() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/users/1")
                .asJson();

        assertEquals(200, response.getStatus());
        assertEquals("Leanne Graham", response.getBody().getObject().getString("name"));
    }

    @Test
    public void testGetComments() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/comments")
                .asJson();

        assertEquals(200, response.getStatus());
        assertTrue(response.getBody().getArray().length() > 0);
    }

    @Test
    public void testDeleteAndTryToGetPost() {
        // Silme
        HttpResponse<String> deleteResponse = Unirest.delete("https://jsonplaceholder.typicode.com/posts/2")
                .asString();
        assertEquals(200, deleteResponse.getStatus());

        // Tekrar GET ile kontrol (JSONPlaceholder sahte API olduğu için hala dönebilir)
        HttpResponse<JsonNode> getResponse = Unirest.get("https://jsonplaceholder.typicode.com/posts/2")
                .asJson();

        assertEquals(200, getResponse.getStatus());
    }

    @Test
    public void testGetRequest1() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest2() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest3() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest4() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest5() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest6() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest7() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest8() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest9() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest10() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest11() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest12() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest13() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest14() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest15() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest16() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest17() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest18() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest19() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest20() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest21() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest22() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest23() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest24() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest25() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest26() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest27() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest28() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest29() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest30() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest31() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest32() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest33() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest34() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest35() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest36() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest37() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest38() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest39() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest40() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest41() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest42() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest43() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest44() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest45() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest46() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest47() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest48() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest49() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest50() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest51() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest52() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest53() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest54() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest55() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest56() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest57() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest58() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest59() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest60() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest61() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest62() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest63() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest64() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest65() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest66() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest67() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest68() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest69() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest70() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest71() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest72() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest73() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest74() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest75() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest76() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest77() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest78() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest79() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest80() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest81() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest82() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest83() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest84() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest85() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest86() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest87() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest88() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest89() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest90() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest91() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest92() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest93() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest94() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest95() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest96() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest97() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest98() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest99() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


    @Test
    public void testGetRequest100() {
        HttpResponse<JsonNode> response = Unirest.get("https://jsonplaceholder.typicode.com/posts/1")
                .asJson();

        assertEquals(200, response.getStatus());
        JsonNode body = response.getBody();
        System.out.println(body.toString());

        assertEquals(1, body.getObject().getInt("id"));
    }


}
