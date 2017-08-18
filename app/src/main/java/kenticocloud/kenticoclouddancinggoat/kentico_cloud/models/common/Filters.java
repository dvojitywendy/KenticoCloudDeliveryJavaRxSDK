package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common;

import android.support.annotation.NonNull;

import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IQueryParameter;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils.StringHelper;

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

    public static class AllFilter implements IQueryParameter {

        private String _field;
        private List<String> _values;

        public AllFilter(
                @NonNull String field,
                @NonNull List<String> values
        ) {
            _field = field;
            _values = values;
        }

        public String getParam(){

            return this._field.trim() + "[all]";
        }

        public String getParamValue(){
            return StringHelper.Join(_values, ",");
        }
    }

    public static class AnyFilter implements IQueryParameter {

        private String _field;
        private List<String> _values;

        public AnyFilter(
                @NonNull String field,
                @NonNull List<String> values
        ) {
            _field = field;
            _values = values;
        }

        public String getParam(){

            return this._field.trim() + "[any]";
        }

        public String getParamValue(){
            return StringHelper.Join(_values, ",");
        }
    }

    public static class ContainsFilter implements IQueryParameter {

        private String _field;
        private List<String> _values;

        public ContainsFilter(
                @NonNull String field,
                @NonNull List<String> values
        ) {
            _field = field;
            _values = values;
        }

        public String getParam(){

            return this._field.trim() + "[contains]";
        }

        public String getParamValue(){
            return StringHelper.Join(_values, ",");
        }
    }

    public static class Infilter implements IQueryParameter {

        private String _field;
        private List<String> _values;

        public Infilter(
                @NonNull String field,
                @NonNull List<String> values
        ) {
            _field = field;
            _values = values;
        }

        public String getParam(){

            return this._field.trim() + "[in]";
        }

        public String getParamValue(){
            return StringHelper.Join(_values, ",");
        }
    }


    public static class GreaterThanFilter implements IQueryParameter {

        private String _field;
        private String _value;

        public GreaterThanFilter(
                @NonNull String field,
                String value
        ) {
            _field = field;
            _value = value;
        }

        public String getParam(){

            return this._field.trim() + "[gt]";
        }

        public String getParamValue(){
            return this._value.trim();
        }
    }

    public static class LessThanFilter implements IQueryParameter {

        private String _field;
        private String _value;

        public LessThanFilter(
                @NonNull String field,
                String value
        ) {
            _field = field;
            _value = value;
        }

        public String getParam(){

            return this._field.trim() + "[lt]";
        }

        public String getParamValue(){
            return this._value.trim();
        }
    }

    public static class LessThanOrEqualFilter implements IQueryParameter {

        private String _field;
        private String _value;

        public LessThanOrEqualFilter(
                @NonNull String field,
                String value
        ) {
            _field = field;
            _value = value;
        }

        public String getParam(){

            return this._field.trim() + "[lte]";
        }

        public String getParamValue(){
            return this._value.trim();
        }
    }
    public static class GreaterThanOrEqualFilter implements IQueryParameter {

        private String _field;
        private String _value;

        public GreaterThanOrEqualFilter(
                @NonNull String field,
                String value
        ) {
            _field = field;
            _value = value;
        }

        public String getParam(){

            return this._field.trim() + "[gte]";
        }

        public String getParamValue(){
            return this._value.trim();
        }
    }
    public static class RangeFilter implements IQueryParameter {

        private String _field;
        private int _lowerValue;
        private int _higherValue;

        public RangeFilter(
                @NonNull String field,
                int lowerValue,
                int higherValue
        ) {
            _field = field;
            _lowerValue = lowerValue;
            _higherValue = higherValue;
        }

        public String getParam(){

            return this._field.trim() + "[range]";
        }

        public String getParamValue(){
            return _lowerValue + "," + _higherValue;
        }
    }

}
