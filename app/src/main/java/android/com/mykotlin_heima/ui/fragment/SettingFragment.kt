package android.com.mykotlin_heima.ui.fragment

import android.com.mykotlin_heima.R
import android.com.mykotlin_heima.ui.activity.AboutActivity
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SettingFragment : PreferenceFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addPreferencesFromResource(R.xml.setting)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        val key = preference?.key
        when(key){
            "about" ->{
                startActivity<AboutActivity>()
            }
            "clear_cache" ->{
                toast("clear_cache")
            }
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference)
    }
}