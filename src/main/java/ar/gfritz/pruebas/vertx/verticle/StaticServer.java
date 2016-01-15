package ar.gfritz.pruebas.vertx.verticle;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

@Component
public class StaticServer extends AbstractVerticle {

	@Autowired
	ApplicationContext applicationContext;

	long count = 0;

	@Override
	public void start() throws Exception {
		Router router = Router.router(vertx);

		// Serve the static pages
		// router.route().handler(StaticHandler.create());
		router.get("/add").handler(h -> {
			count++;
			JsonObject config = new JsonObject();
			config.put("id", count);
			DeploymentOptions dop = new DeploymentOptions();
			dop.setConfig(config);
			TestVerticle bean = applicationContext.getBean(TestVerticle.class);
			vertx.deployVerticle(bean, dop, h2 -> {
				System.out.println("deploy count " + count);
			});
			h.response().end("ok");
		});
		vertx.createHttpServer().requestHandler(router::accept).listen(8080, h -> System.out.println("listen 8080"));

		vertx.setPeriodic(1000 * RandomUtils.nextLong(1, 5), t -> {
			if (count > 0)
				vertx.eventBus().send("test-" + RandomUtils.nextLong(1, count), "ping");
		});
	}
}
