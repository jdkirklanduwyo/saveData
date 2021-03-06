package edu.cs4730.preferencedemo;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PrefupdateFrag extends PreferenceFragment implements OnSharedPreferenceChangeListener {

    private EditTextPreference mEditTextPreference;
    private ListPreference mListPreference;

	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefupdate);
		
		//NOTE the rest of this code in not necessary, used so you can display current value on the summary line.
		
        // Get a reference to the preferences, so we can dynamically update the preference screen summary info.
		mEditTextPreference = (EditTextPreference)getPreferenceScreen().findPreference("textPref");
        mListPreference = (ListPreference)getPreferenceScreen().findPreference("list_preference");

	}
    @Override
	public void onResume() {
        super.onResume();

        // Setup the initial values
        mEditTextPreference.setSummary( "Text is " + mEditTextPreference.getSharedPreferences().getString("textPref", "Default"));
        mListPreference.setSummary("Current value is " + mListPreference.getSharedPreferences().getString("list_preference", "Default"));

        // Set up a listener whenever a key changes            
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
	public void onPause() {
        super.onPause();

        // Unregister the listener whenever a key changes            
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);    
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("textPref")) {  //where textPref is the key used in the xml.
        	mEditTextPreference.setSummary( "Text is " + sharedPreferences.getString("textPref", "Default"));
        }  else if (key.equals("list_preference")) {
        	
          mListPreference.setSummary("Current value is " + sharedPreferences.getString(key, "Default")); 
        }
	}



}
