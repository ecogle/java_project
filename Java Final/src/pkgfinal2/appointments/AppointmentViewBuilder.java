package pkgfinal2.appointments;
public class AppointmentViewBuilder {
    
    private AppointmentView apptView = new AppointmentView();
    
    public AppointmentViewBuilder setName (String s){
        this.apptView.setCustName(s);
        return this;
    }
    
    public AppointmentViewBuilder setDate(String s){
        this.apptView.setDate(s);
        return this;
    }
    
    public AppointmentViewBuilder setStartTime(String s){
        this.apptView.setStartTime(s);
        return this;
    }
    
    public AppointmentViewBuilder setEndTime(String s){
        this.apptView.setEndTime(s);
        return this;
    }
    
    public AppointmentViewBuilder setDescription(String s){
        this.apptView.setDescription(s);
        return this;
    }
    
    public AppointmentViewBuilder setLocation(String s){
        this.apptView.setLocation(s);
        return this;
    }
    
    public AppointmentViewBuilder setTitle(String s){
        this.apptView.setTitle(s);
        return this;
    }
    
    public AppointmentViewBuilder setContact(String s){
        this.apptView.setContact(s);
        return this;
    }

}
