import marshaller.CamaMarshaller
import marshaller.PacienteMarshaller
import org.springframework.web.servlet.LocaleResolver;

import util.CustomObjectMarshallers;

// Place your Spring DSL code here
beans = {
	customObjectMarshallers( CustomObjectMarshallers ) { bean ->
		bean.initMethod = 'register'
		//Agregar aca todos los marshallers necesarios.
		marshallers = [
			new PacienteMarshaller(),
			new CamaMarshaller()
			]
	}
}