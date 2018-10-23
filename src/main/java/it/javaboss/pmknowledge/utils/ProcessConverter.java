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

//@FacesConverter("processConverter")
//public class ProcessConverter implements Converter {
//
//	@Override
//	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
//		if(value != null && value.trim().length() > 0) {
//            try {
//            	ProcessService service = (ProcessService) fc.getExternalContext().getApplicationMap().get("processService");
//            	Optional<Process> process = service.findById( Long.parseLong(value) );
//                return process.orElseThrow( () -> new ConverterException() );
//            } catch(Exception e) {
//            	e.printStackTrace();
//                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid process."));
//            }
//        }
//        else {
//            return null;
//        }
//	}
//
//	@Override
//	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
//		if(object != null) {
//            return String.valueOf(((Process) object).getId());
//        }
//        else {
//            return null;
//        }
//	}
//
//}
