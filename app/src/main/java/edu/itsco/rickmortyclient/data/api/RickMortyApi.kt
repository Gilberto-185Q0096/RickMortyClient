package edu.itsco.rickmortyclient.data.api

import edu.itsco.rickmortyclient.data.api.model.Characters
import retrofit2.http.GET

interface RickMortyApi {

    @GET("character")
    suspend fun getCharacters(): Characters
}