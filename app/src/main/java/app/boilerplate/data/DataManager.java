package app.boilerplate.data;

import app.boilerplate.data.local.DbHelper;
import app.boilerplate.data.local.prefs.PreferencesHelper;
import app.boilerplate.data.remote.ApiSource;


public interface DataManager extends DbHelper, PreferencesHelper, ApiSource {

}
