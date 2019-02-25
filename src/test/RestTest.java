import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class RestTest {

    @Rule
    public WireMockRule wiremockRule = new WireMockRule(8888);
    @Test
    public void testSend() {
        WireMock wiremock = new WireMock(8888);
        wiremock.register(post(urlEqualTo("/person/1"))
                .withRequestBody(containing("me"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("0")));

        wiremock.verifyThat(WireMock.postRequestedFor(urlEqualTo("/person/1")));
    }
}
