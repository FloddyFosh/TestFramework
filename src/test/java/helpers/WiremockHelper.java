package helpers;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

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

        wireMockServer.stubFor(post(urlEqualTo("/askJochem/Weather"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "text/plain")
                .withBody("It's raining")));
    }

    public void startWireMockServer() {

        wireMockServer.start();
    }

    public void stopWireMockServer() {

        wireMockServer.stop();
    }
}