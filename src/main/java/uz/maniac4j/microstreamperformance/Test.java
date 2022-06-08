package uz.maniac4j.microstreamperformance;

import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        // Initialize a storage manager ("the database") with purely defaults.
        final EmbeddedStorageManager storageManager = EmbeddedStorage.start();

// print the last loaded root instance,
// replace it with a current version and store it
        System.out.println(storageManager.root());
        storageManager.setRoot("Hello World! @ " + new Date());
        storageManager.storeRoot();

// shutdown storage
        storageManager.shutdown();
    }
}
