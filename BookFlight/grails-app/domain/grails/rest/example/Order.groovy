package grails.rest.example

import grails.rest.*
@Resource(uri='/api/orders', formats=['json', 'xml'])
class Order {
	
	Long id
	String stock
	String side
	Double price
	Long size
	
	static mapping = {
		version false
		table 'orders'
		id column: 'id', generator:'native', params:[sequence:'order_seq']
	  }
	
    static constraints = {
			stock blank:false
			side blank:false
			price blank:false
			size blank:false
	}
}
