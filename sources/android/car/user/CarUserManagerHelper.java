package android.car.user;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import com.android.internal.util.UserIcons;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class CarUserManagerHelper {
    private static final String HEADLESS_SYSTEM_USER = "android.car.systemuser.headless";
    private static final String TAG = "CarUserManagerHelper";
    private final ActivityManager mActivityManager;
    private final Context mContext;
    private Bitmap mDefaultGuestUserIcon;
    private OnUsersUpdateListener mUpdateListener;
    private final BroadcastReceiver mUserChangeReceiver = new BroadcastReceiver() { // from class: android.car.user.CarUserManagerHelper.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            CarUserManagerHelper.this.mUpdateListener.onUsersUpdate();
        }
    };
    private final UserManager mUserManager;

    /* loaded from: classes.dex */
    public interface OnUsersUpdateListener {
        void onUsersUpdate();
    }

    public CarUserManagerHelper(Context context) {
        this.mContext = context.getApplicationContext();
        this.mUserManager = (UserManager) this.mContext.getSystemService("user");
        this.mActivityManager = (ActivityManager) this.mContext.getSystemService("activity");
    }

    public void registerOnUsersUpdateListener(OnUsersUpdateListener listener) {
        if (this.mUpdateListener != null) {
            unregisterOnUsersUpdateListener();
        }
        this.mUpdateListener = listener;
        registerReceiver();
    }

    public void unregisterOnUsersUpdateListener() {
        unregisterReceiver();
    }

    public boolean isHeadlessSystemUser() {
        return SystemProperties.getBoolean(HEADLESS_SYSTEM_USER, false);
    }

    public UserInfo getSystemUserInfo() {
        return this.mUserManager.getUserInfo(0);
    }

    public UserInfo getCurrentForegroundUserInfo() {
        return this.mUserManager.getUserInfo(getCurrentForegroundUserId());
    }

    public int getCurrentForegroundUserId() {
        ActivityManager activityManager = this.mActivityManager;
        return ActivityManager.getCurrentUser();
    }

    public UserInfo getCurrentProcessUserInfo() {
        return this.mUserManager.getUserInfo(getCurrentProcessUserId());
    }

    public int getCurrentProcessUserId() {
        return UserHandle.myUserId();
    }

    public List<UserInfo> getAllSwitchableUsers() {
        if (isHeadlessSystemUser()) {
            return getAllUsersExceptSystemUserAndSpecifiedUser(getCurrentForegroundUserId());
        }
        return getAllUsersExceptSpecifiedUser(getCurrentForegroundUserId());
    }

    public List<UserInfo> getAllUsers() {
        if (isHeadlessSystemUser()) {
            return getAllUsersExceptSystemUserAndSpecifiedUser(0);
        }
        return this.mUserManager.getUsers(true);
    }

    private List<UserInfo> getAllUsersExceptSpecifiedUser(int userId) {
        List<UserInfo> users = this.mUserManager.getUsers(true);
        Iterator<UserInfo> iterator = users.iterator();
        while (iterator.hasNext()) {
            UserInfo userInfo = iterator.next();
            if (userInfo.id == userId) {
                iterator.remove();
            }
        }
        return users;
    }

    private List<UserInfo> getAllUsersExceptSystemUserAndSpecifiedUser(int userId) {
        List<UserInfo> users = this.mUserManager.getUsers(true);
        Iterator<UserInfo> iterator = users.iterator();
        while (iterator.hasNext()) {
            UserInfo userInfo = iterator.next();
            if (userInfo.id == userId || userInfo.id == 0) {
                iterator.remove();
            }
        }
        return users;
    }

    public boolean isSystemUser(UserInfo userInfo) {
        return userInfo.id == 0;
    }

    public boolean isDefaultUser(UserInfo userInfo) {
        return userInfo.id == 10;
    }

    public boolean isForegroundUser(UserInfo userInfo) {
        return getCurrentForegroundUserId() == userInfo.id;
    }

    public boolean isCurrentProcessUser(UserInfo userInfo) {
        return getCurrentProcessUserId() == userInfo.id;
    }

    public boolean isForegroundUserGuest() {
        return getCurrentForegroundUserInfo().isGuest();
    }

    public boolean canUserBeRemoved(UserInfo userInfo) {
        return !isSystemUser(userInfo);
    }

    public boolean foregroundUserHasUserRestriction(String restriction) {
        return this.mUserManager.hasUserRestriction(restriction, getCurrentForegroundUserInfo().getUserHandle());
    }

    public boolean canForegroundUserAddUsers() {
        return !foregroundUserHasUserRestriction("no_add_user");
    }

    public boolean isCurrentProcessSystemUser() {
        return this.mUserManager.isSystemUser();
    }

    public boolean isCurrentProcessDemoUser() {
        return this.mUserManager.isDemoUser();
    }

    public boolean isCurrentProcessGuestUser() {
        return this.mUserManager.isGuestUser();
    }

    public boolean isCurrentProcessRestrictedProfileUser() {
        return this.mUserManager.isRestrictedProfile();
    }

    public boolean isCurrentProcessUserHasRestriction(String restriction) {
        return this.mUserManager.hasUserRestriction(restriction);
    }

    public boolean canCurrentProcessModifyAccounts() {
        return (isCurrentProcessUserHasRestriction("no_modify_accounts") || isCurrentProcessDemoUser() || isCurrentProcessGuestUser()) ? false : true;
    }

    public boolean canCurrentProcessAddUsers() {
        return !isCurrentProcessUserHasRestriction("no_add_user");
    }

    public boolean canCurrentProcessRemoveUsers() {
        return !isCurrentProcessUserHasRestriction("no_remove_user");
    }

    public boolean canCurrentProcessSwitchUsers() {
        return !isCurrentProcessUserHasRestriction("no_user_switch");
    }

    public UserInfo createNewAdminUser(String userName) {
        UserInfo user = this.mUserManager.createUser(userName, 2);
        if (user == null) {
            Log.w(TAG, "can't create admin user.");
            return null;
        }
        assignDefaultIcon(user);
        return user;
    }

    public UserInfo createNewNonAdminUser(String userName) {
        UserInfo user = this.mUserManager.createUser(userName, 0);
        if (user == null) {
            Log.w(TAG, "can't create non-admin user.");
            return null;
        }
        assignDefaultIcon(user);
        return user;
    }

    public boolean removeUser(UserInfo userInfo, String guestUserName) {
        if (isSystemUser(userInfo)) {
            Log.w(TAG, "User " + userInfo.id + " is system user, could not be removed.");
            return false;
        } else if (isHeadlessSystemUser() && isDefaultUser(userInfo)) {
            Log.w(TAG, "User " + userInfo.id + " is the default user, could not be removed.");
            return false;
        } else {
            if (userInfo.id == getCurrentForegroundUserId()) {
                startNewGuestSession(guestUserName);
            }
            return this.mUserManager.removeUser(userInfo.id);
        }
    }

    public boolean switchToUserId(int id) {
        if (id == 0 && isHeadlessSystemUser()) {
            return false;
        }
        return this.mActivityManager.switchUser(id);
    }

    public boolean switchToUser(UserInfo userInfo) {
        if (userInfo.id == getCurrentForegroundUserId()) {
            return false;
        }
        return switchToUserId(userInfo.id);
    }

    public boolean startNewGuestSession(String guestName) {
        UserInfo guest = this.mUserManager.createGuest(this.mContext, guestName);
        if (guest == null) {
            Log.w(TAG, "can't create user.");
            return false;
        }
        assignDefaultIcon(guest);
        return switchToUserId(guest.id);
    }

    public Bitmap getUserDefaultIcon(UserInfo userInfo) {
        return UserIcons.convertToBitmap(UserIcons.getDefaultUserIcon(this.mContext.getResources(), userInfo.id, false));
    }

    public Bitmap getGuestDefaultIcon() {
        if (this.mDefaultGuestUserIcon == null) {
            this.mDefaultGuestUserIcon = UserIcons.convertToBitmap(UserIcons.getDefaultUserIcon(this.mContext.getResources(), -10000, false));
        }
        return this.mDefaultGuestUserIcon;
    }

    public Bitmap getUserIcon(UserInfo userInfo) {
        Bitmap picture = this.mUserManager.getUserIcon(userInfo.id);
        if (picture == null) {
            return assignDefaultIcon(userInfo);
        }
        return picture;
    }

    public Drawable scaleUserIcon(Bitmap icon, int desiredSize) {
        Bitmap scaledIcon = Bitmap.createScaledBitmap(icon, desiredSize, desiredSize, true);
        return new BitmapDrawable(this.mContext.getResources(), scaledIcon);
    }

    public void setUserName(UserInfo user, String name) {
        this.mUserManager.setUserName(user.id, name);
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.USER_REMOVED");
        filter.addAction("android.intent.action.USER_ADDED");
        filter.addAction("android.intent.action.USER_INFO_CHANGED");
        filter.addAction("android.intent.action.USER_SWITCHED");
        filter.addAction("android.intent.action.USER_STOPPED");
        filter.addAction("android.intent.action.USER_UNLOCKED");
        this.mContext.registerReceiverAsUser(this.mUserChangeReceiver, UserHandle.ALL, filter, null, null);
    }

    private Bitmap assignDefaultIcon(UserInfo userInfo) {
        Bitmap bitmap = userInfo.isGuest() ? getGuestDefaultIcon() : getUserDefaultIcon(userInfo);
        this.mUserManager.setUserIcon(userInfo.id, bitmap);
        return bitmap;
    }

    private void unregisterReceiver() {
        this.mContext.unregisterReceiver(this.mUserChangeReceiver);
    }
}
