package com.prapps.springfirestore;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseDatabase {

    @Value("${firebase.account.file}")
    private String adminAccount;

    private Firestore database;

    @PostConstruct
    public void init() {
        try {
            System.out.println("/home/pratik/.firebase/"+adminAccount);
            FileInputStream serviceAccount = new FileInputStream("/home/pratik/.firebase/"+adminAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://testfirestoredb-c5acf.firebaseio.com")
                    .setProjectId("testfirestoredb-c5acf")
                    .build();

            FirebaseApp app = FirebaseApp.initializeApp(options, "testfirestoredb-c5acf");
            System.out.println("appName: "+app.getName());
            database = FirestoreClient.getFirestore(app);
            System.out.println("database: "+database.getOptions().getDatabaseId());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(getBooks());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getBooks() {
        StringBuilder sb = new StringBuilder();
        ApiFuture<QuerySnapshot> query = database.collection("root").get();
        // ...
        // query.get() blocks on response
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println("User: " + document.getId());
            System.out.println("First: " + document.getString("first"));
            if (document.contains("middle")) {
                System.out.println("Middle: " + document.getString("middle"));
            }
            System.out.println("Last: " + document.getString("last"));
            System.out.println("Born: " + document.getLong("born"));
        }

        return sb.toString();
    }
}
