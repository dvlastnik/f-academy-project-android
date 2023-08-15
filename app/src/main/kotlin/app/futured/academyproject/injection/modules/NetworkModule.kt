package app.futured.academyproject.injection.modules

import app.futured.academyproject.BuildConfig
import app.futured.academyproject.data.remote.ApiService
import app.futured.academyproject.tools.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    // TODO Krok 3:
    //  Implementuj HttpLoggingInterceptor, ktory bude logovat requesty a response pre jednoduchšie debugovanie
    //  Nezabudni nastavit level na HttpLoggingInterceptor.Level.BODY a na logovanie využi Timber
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor(logger = HttpLoggingInterceptor.Logger { Timber.d(it) })
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    // TODO Krok 4:
    //  Vytvor OkHttp vlienta HttpLoggingInterceptor a pridaj k nemu logging interceptor
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: Interceptor,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(loggingInterceptor)
        return okHttpClient.build()
    }

    // TODO Krok 5:
    //  Za pomoci Retrofitu vytvor ApiService, ktorý automaticky implementuje definíciu z ApiService na reálnu implementáciu.
    //  Nezabudni pridať OkHttpClient a converterFactory `json.asConverterFactory("application/json".toMediaType()`
    //  URL nájdeš v konštatnátch ako BASE_PROD_URL
    //  Odporúča sa nastaviť aj validateEagerly(BuildConfig.DEBUG)
    @Provides
    @Singleton
    fun provideRetrofitService(
        okHttpClient: OkHttpClient,
        json: Json,
    ): ApiService {
        val convertFactory = json.asConverterFactory("application/json".toMediaType())
        return Retrofit.Builder()
            .baseUrl(Constants.Api.BASE_PROD_URL)
            .addConverterFactory(convertFactory)
            .client(okHttpClient)
            .validateEagerly(BuildConfig.DEBUG)
            .build()
            .create(ApiService::class.java)
    }
}
