package abluvaToyPubSubProblem;

import java.util.ArrayList;
import java.util.List;

public class AtLeastNStrategy implements MessagesStrategy {
	private int n;

    public AtLeastNStrategy(int n) {
        this.n = n;
    }

    @Override
    public void sendMessages(List<String> messages, List<Subscriber> subscribers) {
        for (String message : messages) {
            List<Subscriber> choosedSubs = new ArrayList<>(subscribers);
            /*while (choosedSubs.size() > n) {
            	choosedSubs.remove(choosedSubs.size() - 1);
            }*/
            if(n<=subscribers.size()) {
	            for (Subscriber subscriber : choosedSubs) {
	            	
	                subscriber.receiveMessage(message);
	            }
            }else {
            	System.out.println("Less subscibers than "+n);
            }
        }
    }
	

}
