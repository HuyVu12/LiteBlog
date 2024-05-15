import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.liteblog.utils.Model.UserInfor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStorage(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences>
                by preferencesDataStore("UserStorage")
        val USER_ID_KEY = stringPreferencesKey("username")
    }
    val getUsername: Flow<String?> = context.dataStore.data
        .map {preferences ->
            preferences[USER_ID_KEY] ?: ""
        }
    suspend fun saveUsername(username: String) = coroutineScope  {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = username
        }
    }
}

object UserData {
    var username = ""
    var userinfor = UserInfor()
}
