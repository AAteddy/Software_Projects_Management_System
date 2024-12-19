
package com.spms.config;

import io.github.cdimascio.dotenv.Dotenv;

public class JwtConstants {

    // Load the environment variables
    private static final Dotenv dotenv = Dotenv.load();

    public static final String SECRET_KEY = dotenv.get("JWT_SECRET_KEY");
    public static final String JWT_HEADER = "Authorization";

}
