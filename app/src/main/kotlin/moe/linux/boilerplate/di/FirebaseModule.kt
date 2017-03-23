package moe.linux.boilerplate.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {
    @Provides @Singleton
    fun provideFirebaseDatabase() = FirebaseDatabase.getInstance()

    @Provides @Singleton
    fun provideDatabaseReference(db: FirebaseDatabase) = db.getReference("board")
}
