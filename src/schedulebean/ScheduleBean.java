package schedulebean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class ScheduleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6862823887099334937L;
	
	private ScheduleModel schedule;
	private ScheduleEvent event;
	private LazyScheduleModel lazySchedule;
	private Map<Date, ScheduleEvent> events;

	@PostConstruct
	public void init() {
		this.schedule = new DefaultScheduleModel();
		this.gerarEventosAleatorios();
		
		this.lazySchedule = new LazyScheduleModel() {
            @Override
            public void loadEvents(Date start, Date end) {
            
            	Calendar calendar = Calendar.getInstance();
        		
        		calendar.set(Calendar.YEAR, 2018);
        		calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        		calendar.set(Calendar.DATE, 05);
        		calendar.set(Calendar.HOUR, 0);
        		calendar.set(Calendar.MINUTE, 0);
        		calendar.set(Calendar.SECOND, 0);
        		calendar.set(Calendar.MILLISECOND, 0);
        		
	        for (Map.Entry<Date, ScheduleEvent> evento : events.entrySet()) {
	          if (evento.getValue().getStartDate().after(start) && evento.getValue().getStartDate().before(end)) {
	            			addEvent(evento.getValue());
	            		}
	            }
            }   
        };
	}

	private void gerarEventosAleatorios() {
		this.events = new HashMap<Date, ScheduleEvent>();
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.YEAR, 2018);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 05);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		this.events.put(calendar.getTime(), new DefaultScheduleEvent("Evento dia 05/01/2018", calendar.getTime(), calendar.getTime()));
		
		calendar.set(Calendar.YEAR, 2018);
		calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
		calendar.set(Calendar.DATE, 06);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		this.events.put(calendar.getTime(), new DefaultScheduleEvent("Evento dia 06/02/2018", calendar.getTime(), calendar.getTime()));
		
		calendar.set(Calendar.YEAR, 2018);
		calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
		calendar.set(Calendar.DATE, 07);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		this.events.put(calendar.getTime(), new DefaultScheduleEvent("Evento dia 07/02/2018", calendar.getTime(), calendar.getTime()));
	}
	
	public void selecionarEvento(SelectEvent selectEvent) {
		this.event = (ScheduleEvent) selectEvent.getObject();
	}

	public void selecionarData(SelectEvent selectEvent) {
		this.event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}

	public void adicionarEvento(ActionEvent actionEvent) {
		if (this.event.getId() == null)
			this.schedule.addEvent(event);
		else
			this.schedule.updateEvent(event);
		
		this.event = new DefaultScheduleEvent();
	}

	public String getLocale() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Locale locale = facesContext.getViewRoot().getLocale();
		return locale.getLanguage();
	}

	public ScheduleModel getSchedule() {
		return this.schedule;
	}

	public void setSchedule(ScheduleModel schedule) {
		this.schedule = schedule;
	}

	public ScheduleEvent getEvent() {
		return this.event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public LazyScheduleModel getLazySchedule() {
		return lazySchedule;
	}

	public void setLazySchedule(LazyScheduleModel lazySchedule) {
		this.lazySchedule = lazySchedule;
	}
	
}
