package demo.automation.api.restassured.teste;

import demo.automation.api.restassured.dominio.Usuario;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class TesteRegistro extends TesteBase {

    private static final String REGISTRA_USUARIO_ENDPOINT = "/register";
    private static final String LOGIN_ENDPOINT = "/login";

    @BeforeClass
    public static void setupRegistro() {
        RestAssured.responseSpecification = new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_BAD_REQUEST).build();
    }

    @Test
    public void testeNaoEfeatuaRegistroQuandoSenhaEstaFaltando() {
        Usuario usuario = new Usuario();
        usuario.setEmail("sydney@fife");

        given().
                body(usuario).
                when().
                post(LOGIN_ENDPOINT).
                then().
                body("error", is("Missing password"));
    }

    @Test
    public void testeLoginNaoEfeatuadoQuandoSenhaEstaFaltando() {
        Usuario usuario = new Usuario();
        usuario.setEmail("sydney@fife");

        given().
                body(usuario).
                when().
                post(REGISTRA_USUARIO_ENDPOINT).
                then().
                body("error", is("Missing password"));
    }

}
