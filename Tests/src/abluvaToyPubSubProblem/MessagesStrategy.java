package abluvaToyPubSubProblem;

import java.util.List;

public interface MessagesStrategy {
	 void sendMessages(List<String> messages, List<Subscriber> subscribers);
}
