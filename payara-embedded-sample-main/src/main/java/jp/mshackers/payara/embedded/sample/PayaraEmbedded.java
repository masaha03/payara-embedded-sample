package jp.mshackers.payara.embedded.sample;

import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;

import java.io.InputStream;
import java.net.URISyntaxException;

public class PayaraEmbedded {

    public static void main(String[] args) throws URISyntaxException, GlassFishException {
        GlassFishRuntime runtime = GlassFishRuntime.bootstrap();

        GlassFishProperties glassFishProperties = new GlassFishProperties();
//        glassFishProperties.setPort("http-listener", 9080);
        String path = PayaraEmbedded.class.getResource("/domain").getPath();
        System.out.println(path);
        glassFishProperties.setInstanceRoot(path);

        GlassFish glassFish = runtime.newGlassFish(glassFishProperties);
        glassFish.start();

        InputStream inputStream = PayaraEmbedded.class.getClassLoader().getResourceAsStream("app.war");

        glassFish.getDeployer().deploy(inputStream, "--contextroot", "/");
    }
}
