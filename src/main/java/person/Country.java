package person;

import exceptions.InvalidPersonFieldException;

import java.io.Serializable;
import java.time.ZoneId;

/**
 * Class that defines the country of residence for objects of the Person type
 * Also, its functionality allows you to return the available countries in the form of a text list,
 * select the time zone
 */
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

    public static String[] returnCountries() {
        String[] countries = {"china", "south_korea", "north_korea"};
        return countries;
    }

    public static Country switchCountry(String country) {
        Country p = null;
        switch (country) {
            case "china":
                p = CHINA;
            break;
            case "south_korea":
                p = SOUTH_KOREA;
            break;
            case "north_korea":
                p = NORTH_KOREA;
            break;
        }
        return p;
    }
}
