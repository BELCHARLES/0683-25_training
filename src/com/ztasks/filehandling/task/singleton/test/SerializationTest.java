package com.ztasks.filehandling.task.singleton.test;

import java.io.*;

class NonSerializableSuper {
    int superData;

    // ✅ Added a no-arg constructor
    public NonSerializableSuper() {
        System.out.println("NonSerializableSuper no-arg constructor called");
    }

    public NonSerializableSuper(int superData) {
        this.superData = superData;
    }
}

class SerializableSub extends NonSerializableSuper implements Serializable {
    int subData;

    public SerializableSub(int superData, int subData) {
        super(superData);
        this.subData = subData;
    }

    // ✅ Manually handle serialization of superclass fields
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();  // Serialize subclass fields
        oos.writeInt(superData);   // Manually serialize superclass field
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();  // Deserialize subclass fields
        superData = ois.readInt(); // Manually deserialize superclass field
    }
}

public class SerializationTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableSub obj = new SerializableSub(100, 200);

        // Serialize
        FileOutputStream fos = new FileOutputStream("data.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.close();

        // Deserialize
        FileInputStream fis = new FileInputStream("data.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SerializableSub deserializedObj = (SerializableSub) ois.readObject();
        ois.close();

        // ✅ Both values are properly restored!
        System.out.println("superData: " + deserializedObj.superData); // ✅ 100
        System.out.println("subData: " + deserializedObj.subData);     // ✅ 200
    }
}

     