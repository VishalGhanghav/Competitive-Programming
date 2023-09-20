package abluvaToyPubSubProblem;

import java.util.ArrayList;
import java.util.List;

public class ToyPubSubSystem {
	private List<Subscriber> subscribers;
	private MessagesStrategy messageStrategy;

	public ToyPubSubSystem(MessagesStrategy messageStrategy) {
		this.subscribers = new ArrayList<>();
		this.messageStrategy = messageStrategy;
	}

	public void addSubscriber(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	public void publishMessages(List<String> messages) {
		messageStrategy.sendMessages(messages, subscribers);
	}
}

