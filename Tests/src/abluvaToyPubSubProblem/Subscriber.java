package abluvaToyPubSubProblem;

public class Subscriber {
	    public String name;

	    public Subscriber(String name) {
	        this.name = name;
	    }

	    public void receiveMessage(String message) {
	        System.out.println(name + " received message: " + message);
	    }
	
}
