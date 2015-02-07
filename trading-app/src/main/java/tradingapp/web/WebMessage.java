package tradingapp.web;

public class WebMessage {

    private String name;
    
    public WebMessage() {
    	
    }
    
    public WebMessage(String name) {
    	this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

}
