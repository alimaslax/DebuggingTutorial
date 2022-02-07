import grails.rest.example.Order


class BootStrap {

    def init = { servletContext ->
        new Order(stock:"AAPL", side:"S", price:200, size:1000).save()
        new Order(stock:"IBM", side:"B", price:300, size:2000).save()
		new Order(stock:"JNJ", side:"T", price:150, size:3000).save()
    }
    def destroy = {
    }
}
