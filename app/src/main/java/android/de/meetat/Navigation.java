package android.de.meetat;

/**
 * Created by raina on 12.03.2016.
 */
public enum Navigation {

    Login, Register, Start, Profile, Friends, MyReminders; // Configuration;

    public static Navigation fromInteger(int x) {
        switch (x) {
            case 0:
                return Login;
            case 1:
                return Register;
            case 2:
                return Start;
            case 3:
                return Profile;
            case 4:
                return Friends;
            case 5:
                return MyReminders;
            //case 5:
            //    return Configuration;
        }
        return null;
    }

    public static int fromEnum(Navigation x) {
        switch (x) {
            case Login:
                return 0;
            case Register:
                return 1;
            case Start:
                return 2;
            case Profile:
                return 3;
            case Friends:
                return 4;
            case MyReminders:
                return 5;
            //case Configuration:
            //    return 5;
        }
        return -1;
    }

    public static Navigation fromId(int id) {
        if (id == R.id.login) {
            return Login;
        //} if (id == R.id.register) {
        //    return Register;
        } else if (id == R.id.start) {
            return Start;
        } else if (id == R.id.profile) {
            return Profile;
        } else if (id == R.id.friends) {
            return Friends;
        } else if (id == R.id.my_reminders) {
            return MyReminders;
        }// else if (id == R.id.configuration) {
        //    return Profile;
        //}
        return null;
    }

    }

