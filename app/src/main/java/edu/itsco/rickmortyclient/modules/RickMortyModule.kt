package edu.itsco.rickmortyclient.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.itsco.rickmortyclient.data.api.ApiConstants
import edu.itsco.rickmortyclient.data.api.RickMortyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickMortyModule {

    @Provides
    @Singleton
    fun getRickMortyApi(): RickMortyApi{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickMortyApi::class.java)
    }
}