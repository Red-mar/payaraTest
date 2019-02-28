import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import model.facade.HelloJava;
import model.facade.MyFacade;
import model.facade.StreamFacade;
import model.logic.SongRest;
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
                .addClass(HelloJava.class)
                .addClass(StreamFacade.class)
                .addClass(SongRest.class);
    }

    @Test
    public void shouldSayHello() {
        HelloJava hj = new HelloJava();
        Assert.assertThat(hj.sayHello(), is("Hello"));
    }

    @Test
    public void TestOneSong() {
        WireMock wiremock = new WireMock(8888);
        wiremock.register(post(urlEqualTo("/song/1"))
                .withRequestBody(containing("test"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("0")));
        // Add song to the database
        StreamFacade sf = new StreamFacade();
        sf.AddSongToDatabase("test", "testurl");
        // Then check the rest api
        wiremock.verifyThat(WireMock.postRequestedFor(urlEqualTo("/song/1")));
    }

    @Test
    public void TestAllSongs() {
        WireMock wiremock = new WireMock(8888);
        wiremock.register(post(urlEqualTo("/song/"))
                .withRequestBody(containing("test"))
                .withRequestBody(containing("test2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("0")));
        // Add song to the database
        StreamFacade sf = new StreamFacade();
        sf.AddSongToDatabase("test", "testurl");
        sf.AddSongToDatabase("test2", "test2url");
        // Then check the rest api
        wiremock.verifyThat(WireMock.postRequestedFor(urlEqualTo("/song/")));
    }
}
