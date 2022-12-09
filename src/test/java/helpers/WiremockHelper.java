package helpers;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;

public class WiremockHelper {

    public static WireMockServer wireMockServer = new WireMockServer();

    public void setupStub1() {

        wireMockServer.stubFor(post(urlEqualTo("/askJochem"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "text/plain")
                .withBody("You're the best!")));
    }

    public void setupStub2() {

        wireMockServer.stubFor(get(urlEqualTo("/askJochem/weather?search_term=today"))
                .withQueryParam("search_term", equalTo("today"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("It's raining")));

        wireMockServer.stubFor(get(urlEqualTo("/askJochem/weather?search_term=yesterday"))
                .withQueryParam("search_term", equalTo("yesterday"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("The sun will shine!")));

    }

    public void startWireMockServer() {

        wireMockServer.start();
    }

    public void stopWireMockServer() {

        wireMockServer.stop();
    }
}