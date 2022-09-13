package person;

import java.io.Serializable;
import java.time.ZoneId;

public enum Country implements Serializable {
    CHINA,
    SOUTH_KOREA,
    NORTH_KOREA;


    public static String selectZoneId(Country country) {
        String zoneId = "";
        switch (country) {
            case CHINA:
                zoneId = "Asia/Shanghai";
                break;
            case SOUTH_KOREA:
                zoneId = "Asia/Pyongyang";
                break;
            case NORTH_KOREA:
                zoneId = "Asia/Seoul";
                break;
            default:
                zoneId = "Australia/Sydney";
        }
        return zoneId;
    }
}
