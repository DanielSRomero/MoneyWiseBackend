package domain.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.jwt.*

object JwtConfig {
    private const val secret = "super_secret_key"  // 🔑 Cambia esto por algo más seguro
    private const val issuer = "domain.com"
    private const val audience = "ktor_audience"
    private const val realm = "ktor_realm"
    private val algorithm = Algorithm.HMAC256(secret)


    fun generateToken(userName: String): String {
        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withSubject("Authentication")
            .withClaim("userName", userName) //son específicas del usuario.
            .withClaim("time", System.currentTimeMillis()) //específicas del usuario.
            // .withExpiresAt(Date(System.currentTimeMillis() + 600000))  // Expira en 10 min
            .sign(algorithm)
    }

    fun configureAuthentication(config: JWTAuthenticationProvider.Config) {
        config.realm = realm
        config.verifier(
            JWT.require(algorithm)
                .withIssuer(issuer)
                .withAudience(audience)
                .build()
        )
        config.validate { credential ->
            if (credential.payload.getClaim("userName").asString() != null) {
                JWTPrincipal(credential.payload)
            } else null
        }
    }
}