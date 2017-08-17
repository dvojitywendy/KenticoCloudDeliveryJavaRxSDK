package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IField;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class Field implements IField{

    private String _codename;
    private String _type;
    private String _name;
    private Object _value;

    public Field(@NonNull String codename, @NonNull String type, @NonNull String name, @NonNull String value){
        _codename = codename;
        _type = type;
        _name = name;
        _value = value;
    }

    public String getType() {
        return _type;
    }

    public String getName() {
        return _name;
    }

    public Object getValue() {
        return _value;
    }

    public String getCodename() {
        return _codename;
    }
}
