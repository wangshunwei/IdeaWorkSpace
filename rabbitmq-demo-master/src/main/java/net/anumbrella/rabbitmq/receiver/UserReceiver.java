
package net.anumbrella.rabbitmq.receiver;

import net.anumbrella.rabbitmq.entity.User;

@Component
public class UserReceiver {

	@RabbitListener(queues = "user")
	public void process(User user) {
		System.out.println("user receive  : " + user.getName() + " / " + user.getAddress());
	}

}
