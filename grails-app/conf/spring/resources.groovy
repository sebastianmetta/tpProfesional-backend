import marshaller.AdministrativoMarshaller
import marshaller.AguardandoAltaMarshaller
import marshaller.AltaPacienteMarshaller;
import marshaller.CamaMarshaller
import marshaller.DiagnosticoMarshaller
import marshaller.EnfermeroMarshaller
import marshaller.EstudioPendienteDeRetiroMarshaller
import marshaller.EstudioRetiradoMarshaller
import marshaller.EstudioSolicitadoMarshaller
import marshaller.HabitacionMarshaller
import marshaller.InterconsultaMarshaller
import marshaller.InternacionPacienteMarshaller
import marshaller.InternadoMarshaller
import marshaller.MedicoMarshaller
import marshaller.PacienteMarshaller
import marshaller.PostQuirurjicoMarshaller
import marshaller.SectorMarshaller
import marshaller.UserMarshaller
import util.CustomObjectMarshallers

// Place your Spring DSL code here
beans = {
	customObjectMarshallers( CustomObjectMarshallers ) { bean ->
		bean.initMethod = 'register'
		//Agregar aca todos los marshallers necesarios.
		marshallers = [
			new AdministrativoMarshaller(),
			new AguardandoAltaMarshaller(),
			new AltaPacienteMarshaller(),
			new CamaMarshaller(),
			new DiagnosticoMarshaller(),
			new EnfermeroMarshaller(),
			new EstudioPendienteDeRetiroMarshaller(),
			new EstudioRetiradoMarshaller(),
			new EstudioSolicitadoMarshaller(),
			new HabitacionMarshaller(),
			new InterconsultaMarshaller(),
			new InternacionPacienteMarshaller(),
			new InternadoMarshaller(),
			new MedicoMarshaller(),
			new PacienteMarshaller(),
			new PostQuirurjicoMarshaller(),
			new SectorMarshaller(),
			new UserMarshaller()
			]
	}
}