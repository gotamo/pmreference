package it.javaboss.pmknowledge.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import it.javaboss.pmknowledge.model.Document;
import it.javaboss.pmknowledge.model.Identifiable;
import it.javaboss.pmknowledge.model.Process;
import it.javaboss.pmknowledge.model.ProcessInput;
import it.javaboss.pmknowledge.model.ProcessOutput;
import it.javaboss.pmknowledge.service.ProcessService;
import it.javaboss.pmknowledge.utils.ArrayUtils;
import it.javaboss.pmknowledge.utils.ListUtils;

@SuppressWarnings("serial")
@Component
@ManagedBean
@SessionScoped
public class ProcessGraphManagedBean implements Serializable {
	
	@Autowired 
	MenuManagedBean menuManagedBean;
	
	@Autowired
	ProcessService processService;
	
	private List<Process> processes;
	
	private DefaultDiagramModel model;
	
	//-----------------------------------------------------------------------------------------	
	
	public DefaultDiagramModel getModel() {
		return model;
	}
	public void setModel(DefaultDiagramModel model) {
		this.model = model;
	}
	
	//-----------------------------------------------------------------------------------------	
	
	public void clear() {
		this.menuManagedBean.setSelectedProcesses(null);
		refresh();
	}
	
	//-----------------------------------------------------------------------------------------	
	
	public void refresh() {
		this.processes = null;
		this.model = null;	
		getFilteredProcesses();
		buildGraph();
	}
	
	//-----------------------------------------------------------------------------------------	
	
	public List<Process> getFilteredProcesses() {
		if ( this.processes == null ) {
			if ( ListUtils.isNotEmpty( menuManagedBean.getSelectedProcesses() ) ) {		
				try {
					this.processes = processService.search( 
								Arrays.asList( ArrayUtils.asListOfId( menuManagedBean.getSelectedProcesses() ) ), 
								Arrays.asList(), 
								Arrays.asList() );
				} catch (Exception e) {
					e.printStackTrace();
					this.processes = null;
				}
			}
		}
		return this.processes;
	}

	//-----------------------------------------------------------------------------------------	
	
	private void buildGraph() {
		if ( this.processes == null ) {
			return;
		}
		
		model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        
        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
        model.setDefaultConnector(connector);
		
		Process process1 = null;
		Process process2 = null;
		
		Element elementProcess1 = null;
		Element elementProcess2 = null;
		
		Connection connection = null;
		
		List<Document> documents = null;
		
		for ( int i = 0; i < processes.size() - 1; i++ ) {
			process1 = processes.get( i );
			
			elementProcess1 = createElement( model, process1 );
			
			for ( int j = i + 1; j < processes.size(); j++ ) {
				process2 = processes.get( j );
				
				elementProcess2 = createElement( model, process2 );
				
				documents = searchInputOutputDocuments( process1, process2 );
				if ( !documents.isEmpty() ) {
					connection = createConnection( elementProcess1, elementProcess2, documents );
					model.connect( connection );					
				}
 				
				documents = searchInputOutputDocuments( process2, process1 );
				if ( !documents.isEmpty() ) {
					connection = createConnection( elementProcess2, elementProcess1, documents );
					model.connect( connection );
				}
			}
		}
	}
	
	private Element createElement( DefaultDiagramModel model, Process process ) {
		Element element = searchElement( model, process );
		
		if ( element == null ) {
			element = new Element( process, posX( process ), posY( process ) );
			element.setTitle( process.getName() );
			model.addElement( element );
		}
		
		return element;
	}
	
	private Connection createConnection( Element elementStart, Element elementEnd, List<Document> documents ) {
		if ( !documents.isEmpty() ) {

			String label = "";
			String separator = "";
			for ( Document document : documents ) {
				if ( !StringUtils.isEmpty( document.getShortName() ) ) {
					label += separator + document.getShortName();
				} else {
					label += separator + document.getFullName();
				}
				separator = "<br/>";
			}
			
			EndPoint endpointStart = new BlankEndPoint( EndPointAnchor.CONTINUOUS_BOTTOM );
			EndPoint endpointEnd = new BlankEndPoint( EndPointAnchor.CONTINUOUS_TOP );
			
			elementStart.addEndPoint( endpointStart );
			elementEnd.addEndPoint( endpointEnd );
			
			return createConnection( endpointStart, endpointEnd, label );
		}
		
		return null;
	}
	
	private List<Document> searchInputOutputDocuments( Process start, Process end ) {
		List<Document> documents = new ArrayList<Document>();
		for ( ProcessOutput pout : start.getProcessOutputs() ) {
			if ( !pout.isUpdate() ) {
				for ( ProcessInput pin : end.getProcessInputs() ) {
					if ( pout.getDocument().getId().equals( pin.getDocument().getId() ) ) {
						documents.add( pout.getDocument() );
					}
				}
			}
		}
		return documents;
	}
	
	private Element searchElement( DefaultDiagramModel model, Identifiable identifiable ) {
		for ( Element element : model.getElements() ) {
			if ( ((Identifiable) element.getData()).getId().equals( identifiable.getId() ) ) {
				return element;
			}
		}
		return null;
	}
	
	private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(15, 15, 1, 1));
         
        if(label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
         
        return conn;
    }
	
	private String posX( Process process ) {
		Integer pos = 20 * ( process.getKnowledgeArea().getOrder() - 1 ) + 5 * ( process.getOrder() % 2 );
		return pos.toString() + "em";
	}
	
	private String posY( Process process ) {
		Integer pos = 20 * ( process.getOrder()  - 1 ) + 3;
		return pos.toString() + "em";
	}
}
