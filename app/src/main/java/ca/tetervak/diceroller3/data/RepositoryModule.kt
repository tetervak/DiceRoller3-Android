package ca.tetervak.diceroller3.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHistoryDataRepository(): HistoryDataRepository{
        return HistoryDataRepository.getRepository()
    }

    @Provides
    @Singleton
    fun provideRollDataRepository(): RollDataRepository{
        return RollDataRepository.getRepository()
    }

}