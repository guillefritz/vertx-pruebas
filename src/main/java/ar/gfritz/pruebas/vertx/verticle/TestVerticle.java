package ar.gfritz.pruebas.vertx.verticle;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestVerticle extends AbstractVerticle {

	long id;

	@Override
	public void start() throws Exception {
		id = config().getLong("id");
		System.out.println("starting "+id);
		//vertx.setPeriodic(1000 * RandomUtils.nextLong(1, 10), t -> System.out.println("tick de " + id));
		vertx.eventBus().consumer("test-"+id, h -> {
			System.out.println("-> test-"+id);
			h.reply("ok");
		});
	}
	@Override
	public void stop() throws Exception {
		super.stop();
		System.out.println("stopping "+id);
	}
}
