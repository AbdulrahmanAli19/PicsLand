package abdulrahman.ali19.intrazero.di

import abdulrahman.ali19.intrazero.data.local.IntrazeroDatabase
import abdulrahman.ali19.intrazero.data.paging.PicsPagination
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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePagePicsumApi(httpClient: OkHttpClient): PagePicsumApi =
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(PagePicsumApi::class.java)

    @Provides
    @Singleton
    fun provideInrazeroDatabase(@ApplicationContext context: Context): IntrazeroDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            IntrazeroDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideRepository(
        db: IntrazeroDatabase,
        mediator: PicsumRemoteMediator,
        paging: PicsPagination
    ): Repository =
        RepositoryImpl(db, mediator, paging)


    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

}