package util

class CustomObjectMarshallers {

	List marshallers = []

	def register() {
		marshallers.each{ it.register() }
	}
}
