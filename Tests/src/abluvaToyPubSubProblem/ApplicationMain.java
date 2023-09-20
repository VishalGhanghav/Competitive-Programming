package abluvaToyPubSubProblem;

import java.util.List;

public class ApplicationMain {
	public static void main(String[] args) {
        ToyPubSubSystem pubSubSystemALN = new ToyPubSubSystem(new AtLeastNStrategy(2)); 
        ToyPubSubSystem pubSubSystemAMN = new ToyPubSubSystem(new AtMostNStrategy(2));
        ToyPubSubSystem pubSubSystemAM = new ToyPubSubSystem(new AlternateStrategy());
        Subscriber subscriberA = new Subscriber("A");
        Subscriber subscriberB = new Subscriber("B");
        Subscriber subscriberC = new Subscriber("C");
        Subscriber subscriberD = new Subscriber("D");
        Subscriber subscriberE = new Subscriber("E");
        
        pubSubSystemALN.addSubscriber(subscriberA);
        
        pubSubSystemAMN.addSubscriber(subscriberA);
        pubSubSystemAMN.addSubscriber(subscriberB);
        pubSubSystemAMN.addSubscriber(subscriberC);
        pubSubSystemAMN.addSubscriber(subscriberD);
        pubSubSystemAMN.addSubscriber(subscriberE);
        
        pubSubSystemAM.addSubscriber(subscriberA);
        pubSubSystemAM.addSubscriber(subscriberB);
        pubSubSystemAM.addSubscriber(subscriberC);
        pubSubSystemAM.addSubscriber(subscriberD);
        pubSubSystemAM.addSubscriber(subscriberE);

        List<String> messages = List.of("Message 1", "Message 2", "Message 3");
        System.out.println("----------At Least N-----");
        pubSubSystemALN.publishMessages(messages);
        System.out.println("----------At Most N-----");
        pubSubSystemAMN.publishMessages(messages);
        System.out.println("----------Alternate-----");
        List<String> messagesAlternate = List.of("Message 1", "Message 2", "Message 3",
        		"Message 4","Message 5","Message 6","Message 7");
        pubSubSystemAM.publishMessages(messagesAlternate);
    }
}
