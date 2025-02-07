public class MediatorPattern {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        User user1 = new ChatUser(chatRoom, "Alice");
        User user2 = new ChatUser(chatRoom, "Bob");
        User user3 = new ChatUser(chatRoom, "Charlie");

        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        // Users send messages through the mediator
        user1.send("Hello everyone!");
        user2.send("Hey Alice!");
        user3.send("Hi all!");
    }
}

// Output:
