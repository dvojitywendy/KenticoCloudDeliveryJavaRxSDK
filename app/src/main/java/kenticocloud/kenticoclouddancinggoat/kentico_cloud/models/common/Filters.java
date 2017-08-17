package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IQueryParameter;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class Filters {
    public static class EqualsFilter implements IQueryParameter {

        private String _field;
        private String _value;

        public EqualsFilter(
                @NonNull String field,
                String value
        ) {
             _field = field;
             _value = value;
        }

        public String getParam(){

            return this._field.trim();
        }

        public String getParamValue(){
            if (this._value == null) {
                return null;
            }

            return this._value.trim();
        }
    }
}
