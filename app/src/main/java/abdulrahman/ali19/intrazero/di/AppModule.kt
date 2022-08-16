package abdulrahman.ali19.intrazero.di

import abdulrahman.ali19.intrazero.data.local.IntrazeroDatabase
import abdulrahman.ali19.intrazero.data.paging.PicsumPigination
import abdulrahman.ali19.intrazero.data.paging.PicsumRemoteMediator
import abdulrahman.ali19.intrazero.data.remote.PagePicsumApi
import abdulrahman.ali19.intrazero.data.repository.RepositoryImpl
import abdulrahman.ali19.intrazero.domain.repository.Repository
import abdulrahman.ali19.intrazero.utils.Constants
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideInrazeroDatabase(@ApplicationContext context: Context): IntrazeroDatabase =
        IntrazeroDatabase.init(context)

    @Provides
    @Singleton
    fun provideRepository(
        db: IntrazeroDatabase,
        mediator: PicsumRemoteMediator,
        paging: PicsumPigination
    ): Repository =
        RepositoryImpl(db, mediator, paging)

    @Provides
    @Singleton
    fun providePicsumRemoteMediator(
        api: PagePicsumApi,
        db: IntrazeroDatabase
    ): PicsumRemoteMediator = PicsumRemoteMediator(api, db)

    @Provides
    @Singleton
    fun providePicsumPaging(api: PagePicsumApi): PicsumPigination = PicsumPigination(api)

}