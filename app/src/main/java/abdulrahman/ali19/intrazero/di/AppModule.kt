package abdulrahman.ali19.intrazero.di

import abdulrahman.ali19.intrazero.data.remote.PagePicsumApi
import abdulrahman.ali19.intrazero.data.repository.RepositoryImpl
import abdulrahman.ali19.intrazero.domain.repository.Repository
import abdulrahman.ali19.intrazero.domain.usercase.GetPagesUseCase
import abdulrahman.ali19.intrazero.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePagePicsumApi(): PagePicsumApi =
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PagePicsumApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: PagePicsumApi): Repository =
        RepositoryImpl(api)


}