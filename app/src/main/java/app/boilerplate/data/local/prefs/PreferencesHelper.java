package app.boilerplate.data.local.prefs;

import app.boilerplate.data.LoggedInMode;

public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(LoggedInMode mode);

}
