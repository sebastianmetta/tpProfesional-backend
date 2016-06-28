package marshaller

import java.util.List;

import grails.converters.JSON;
import ar.fiuba.tpProfesional.infraestructura.Habitacion;
import ar.fiuba.tpProfesional.infraestructura.Sector;

/**
 * Encargado de proveer una representacion adecuada de un sector en formato JSON.
 * @author sebastian
 *
 */
class SectorMarshaller {
	void register() {
		JSON.registerObjectMarshaller(Sector) { Sector sector ->
			return [
					sector: sector.toString()
			]
		}
	}
}
