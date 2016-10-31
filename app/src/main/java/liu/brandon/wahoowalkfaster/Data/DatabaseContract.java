package liu.brandon.wahoowalkfaster.Data;

import android.provider.BaseColumns;

/**
 * Created by Brandon on 8/17/16.
 */

public class DatabaseContract {

    // Prevent somebody from accidentally instantiating this class.
    public DatabaseContract() {}

    public static abstract class RouteEntry implements BaseColumns {
        public static final String TABLE_NAME = "routes";
        public static final String COLUMN_NAME_ROUTE_ID = "id";
        public static final String COLUMN_NAME_ROUTE_NAME = "name";
    }

}
