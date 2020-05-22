package com.myapp.firebaase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Map;

public class App {
    private static boolean isBlank(String s) {
        return s == null || s.length() <= 0;
    }

    public static void main(String[] args) {
        System.out.println("==== Starting with Args: " + Arrays.deepToString(args) + " ====");
        try {
            // Also can use env var: export GOOGLE_APPLICATION_CREDENTIALS="/home/user/Downloads/service-account-file.json"
            final FileInputStream serviceAccount =
                    new FileInputStream("./my-app-adminsdk.json");

            final FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            // If using env var... options do not need to be provided here.
            FirebaseApp.initializeApp(options);

            final String email = (args.length > 0) ? args[0] : "johndoe@gmail.com";
            getUserCredentials(email);

        } catch (Throwable t) {
            System.err.println(t.toString());
            t.printStackTrace();
        }
        System.out.println("==== Ending ====");
    }

    private static void getUserCredentials(String email) throws Exception {
        if (isBlank(email)) {
            // bad call!
            return;
        }

        final UserRecord user = FirebaseAuth.getInstance().getUserByEmail(email);
        if (user == null) {
            throw new Exception("No user found with email: " + email);
        }
        if (user.isDisabled()) {
            throw new Exception("User found email: " + email + " is currently disabled!");
        }

        Map<String, Object> customClaims = user.getCustomClaims();
        String s = "Email: " + email
                + ", displayName: " + user.getDisplayName()
                + ", claims: " + (customClaims == null ? "None" : customClaims.toString());
        System.out.println(s);
    }
}

