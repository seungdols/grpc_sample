package com.seungdols.dev.grpc.configuration

import feign.Client
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.io.IOException
import java.io.InputStream
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import javax.net.ssl.KeyManager
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory

@Component
class FeignClientConfiguration(
    @Value("\${server.ssl.key-store}") val keyStore: Resource,
    @Value("\${server.ssl.key-store-password}") val keyStorePassword: String
) {
    @Bean
    @Throws(Exception::class)
    fun feignClient(): Client {
        return try {
            Client.Default(
                sslSocketFactory(keyStore.inputStream, keyStorePassword),
                null
            )
        } catch (e: Exception) {
            throw Exception("Error in initializing feign client", e)
        }
    }

    @Throws(NoSuchAlgorithmException::class, KeyStoreException::class, CertificateException::class, IOException::class, KeyManagementException::class)
    private fun sslSocketFactory(trustStoreStream: InputStream, trustStorePassword: String): SSLSocketFactory? {
        val sslContext: SSLContext = SSLContext.getInstance("TLSv1.2")
        val tmf = createTrustManager(trustStoreStream, trustStorePassword)
        sslContext.init(arrayOf<KeyManager>(), tmf.trustManagers, null)
        return sslContext.socketFactory
    }

    @Throws(KeyStoreException::class, IOException::class, NoSuchAlgorithmException::class, CertificateException::class)
    private fun createTrustManager(trustStoreStream: InputStream, trustStorePassword: String): TrustManagerFactory {
        val trustStore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        trustStore.load(trustStoreStream, trustStorePassword.toCharArray())
        val tmf = TrustManagerFactory
            .getInstance(TrustManagerFactory.getDefaultAlgorithm())
        tmf.init(trustStore)
        return tmf
    }
}
