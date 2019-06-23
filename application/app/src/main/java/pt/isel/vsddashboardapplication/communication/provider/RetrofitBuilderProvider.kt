package pt.isel.vsddashboardapplication.communication.provider

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import pt.isel.vsddashboardapplication.communication.NullOnEmptyConverterFactory
import retrofit2.Retrofit

object RetrofitBuilderProvider {


    /**
     * Returns a predefined Retrofit builder class
     */
    fun getBuilder() : Retrofit.Builder =
            Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(NullOnEmptyConverterFactory())
                .addConverterFactory(MoshiProvider.getFactory())


}