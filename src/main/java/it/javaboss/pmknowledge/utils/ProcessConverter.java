package it.javaboss.pmknowledge.utils;

import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import it.javaboss.pmknowledge.model.Process;
import it.javaboss.pmknowledge.service.ProcessService;

@FacesConverter( value="processConverter")
public class ProcessConverter implements Converter {
	
	private ProcessService processService = ApplicationContextUtils.getBean( ProcessService.class );

	@Override
	public Object getAsObject(FacesContext faceContext, UIComponent component, String value) {
		if ( value != null && value.trim().length() > 0 ) {
            try {
            	Optional<Process> process = processService.findProcessById( Long.parseLong(value) );
                return process.get();
            } catch(Exception e) {
            	e.printStackTrace();
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid process."));
            }
        }
        else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext faceContext, UIComponent component, Object object) {
		if ( object != null ) {
            return String.valueOf(((Process) object).getId());
        }
        else {
            return null;
        }
	}

}
