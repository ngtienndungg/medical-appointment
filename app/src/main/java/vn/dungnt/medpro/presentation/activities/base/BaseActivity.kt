package vn.dungnt.medpro.presentation.activities.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import vn.dungnt.medpro.data.models.EventType
import vn.dungnt.medpro.data.models.MessageEvent
import vn.dungnt.medpro.domain.entities.LanguageEntity
import vn.dungnt.medpro.utils.Constants
import vn.dungnt.medpro.utils.SharedPrefs
import vn.dungnt.medpro.utils.Utils
import vn.dungnt.medpro.utils.convertFromJson


open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)

        val prefs = SharedPrefs.getString(Constants.PREFS_LANGUAGE_MODEL, "")
        val languageModel = if (prefs.isEmpty()) {
            LanguageEntity.getLanguageList()[0]
        } else {
            prefs.convertFromJson()

        }
        newBase?.let {
            Utils.updateLocale(it, languageModel.getLanguageCode())
        }
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(messageEvent: MessageEvent) {
        if (messageEvent.eventType == EventType.CLEAR_DATA_GO_TO_LOGIN) {
//            val intent = Intent(this, LoginActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
            finish()
        }
    }
}