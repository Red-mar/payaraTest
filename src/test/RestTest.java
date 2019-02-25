import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import model.facade.HelloJava;
import model.facade.MyFacade;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Arquillian.class)
public class RestTest {

    @Rule
    public WireMockRule wiremockRule = new WireMockRule(8888);

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(HelloJava.class);
    }

    @Test
    public void shouldSayHello() {
        HelloJava hj = new HelloJava();
        Assert.assertThat(hj.sayHello(), is("Hello"));
    }

    @Test
    public void testSend() {
        WireMock wiremock = new WireMock(8888);
        wiremock.register(post(urlEqualTo("/person/1"))
                .withRequestBody(containing("me"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("0")));
        // do thing
        wiremock.verifyThat(WireMock.postRequestedFor(urlEqualTo("/person/1")));
    }
}
