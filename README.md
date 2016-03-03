# vertx-pruebas
playing with vertx+springboot

## how to play with it...

1. run as java ar.gfritz.pruebas.vertx.Application class
2. open browser and go to http://localhost:8080/add
3. each time u hit that address will deploy a new verticle (ar.gfritz.pruebas.vertx.verticle.TestVerticle) instance
3.1 each verticle instance will subscribe to an unique EB address
4. a periodic task will send a messate to some EB address (one of the existing addresses)
5. you can use AB for bulk testing
6. connect with VisualVM to see some results... enjoy!
