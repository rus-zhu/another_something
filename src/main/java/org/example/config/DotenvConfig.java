package org.example.config;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvConfig {
    public static Dotenv Dotenv() {
        return Dotenv.configure()
                     .directory("../assets/")
                     .ignoreIfMalformed()
                     .ignoreIfMissing()
                     .load();
    }
}
