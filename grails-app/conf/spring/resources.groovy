import marshaller.CamaMarshaller
import marshaller.HabitacionMarshaller
import marshaller.InternacionPacienteMarshaller
import marshaller.PacienteMarshaller
import marshaller.SectorMarshaller
import util.CustomObjectMarshallers

// Place your Spring DSL code here
beans = {
	customObjectMarshallers( CustomObjectMarshallers ) { bean ->
		bean.initMethod = 'register'
		//Agregar aca todos los marshallers necesarios.
		marshallers = [
			new PacienteMarshaller(),
			new CamaMarshaller(),
			new SectorMarshaller(),
			new HabitacionMarshaller(),
			new InternacionPacienteMarshaller()
			]
	}
}