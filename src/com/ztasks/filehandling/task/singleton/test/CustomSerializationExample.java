package com.ztasks.filehandling.task.singleton.test;

import java.io.*;

class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private transient String password;  // Marking password as transient (won't be serialized)

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Custom serialization logic
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();  // Serialize all non-transient fields (username)
        out.writeObject(encryptPassword(password)); // Custom logic for password
    }

    // Custom deserialization logic
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();  // Deserialize non-transient fields (username)
        this.password = decryptPassword((String) in.readObject());  // Custom logic for password
    }

    private String encryptPassword(String password) {
        return password == null ? null : "ENC-" + password;  // Simple encryption logic
    }

    private String decryptPassword(String encryptedPassword) {
        return encryptedPassword == null ? null : encryptedPassword.replace("ENC-", "");  // Simple decryption
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', password='" + password + "'}";
    }
}

public class CustomSerializationExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User("Alice", "secure123");

        // Serialize the object
        FileOutputStream fileOut = new FileOutputStream("user.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(user);
        out.close();
        fileOut.close();
        System.out.println("Serialized User: " + user);

        // Deserialize the object
        FileInputStream fileIn = new FileInputStream("user.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        User deserializedUser = (User) in.readObject();
        in.close();
        fileIn.close();
        System.out.println("Deserialized User: " + deserializedUser);
    }
}
