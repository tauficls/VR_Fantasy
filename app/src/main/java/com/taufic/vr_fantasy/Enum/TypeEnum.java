package com.taufic.vr_fantasy.Enum;

import java.io.Serializable;

/**
 * Created by taufic on 11/9/2017.
 */

public enum TypeEnum implements Serializable {
    Apartment {
        @Override
        public String toString() {
            return "Apartemen";
        }
    }, TouristAttraction {
        @Override
        public String toString() {
            return "Tempat Wisata";
        }
    }
}
