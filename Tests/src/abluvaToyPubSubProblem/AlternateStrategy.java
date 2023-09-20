package abluvaToyPubSubProblem;

import java.util.List;

public class AlternateStrategy implements MessagesStrategy {
	int currentIndex=0;

    @Override
    public void sendMessages(List<String> messages, List<Subscriber> subscribers) {
    	
    	for (String message : messages) {
            subscribers.get(currentIndex).receiveMessage(message);
            currentIndex = (currentIndex + 1) % subscribers.size();
        }
    }
	

}
