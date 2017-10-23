package com.kenticocloud.delivery.models.common;

import android.support.annotation.NonNull;

import java.util.List;

import com.kenticocloud.delivery.interfaces.item.common.IQueryParameter;
import com.kenticocloud.delivery.utils.StringHelper;


/**
 * Represents URL filter used for querying items
 */
public class Filters {

    public static class EqualsFilter implements IQueryParameter {

        private String field;
        private String value;

        public EqualsFilter(
                @NonNull String field,
                String value
        ) {
            this.field = field;
            this.value = value;
        }

        public String getParam(){

            return this.field.trim();
        }

        public String getParamValue(){
            if (this.value == null) {
                return null;
            }

            return this.value.trim();
        }
    }

    public static class AllFilter implements IQueryParameter {

        private String field;
        private List<String> values;

        public AllFilter(
                @NonNull String field,
                List<String> values
        ) {
            this.field = field;
            this.values = values;
        }

        public String getParam(){

            return this.field.trim() + "[all]";
        }

        public String getParamValue(){
            return StringHelper.join(values, ",");
        }
    }

    public static class AnyFilter implements IQueryParameter {

        private String field;
        private List<String> values;

        public AnyFilter(
                @NonNull String field,
                List<String> values
        ) {
            this.field = field;
            this.values = values;
        }

        public String getParam(){

            return this.field.trim() + "[any]";
        }

        public String getParamValue(){
            return StringHelper.join(values, ",");
        }
    }

    public static class ContainsFilter implements IQueryParameter {

        private String field;
        private List<String> values;

        public ContainsFilter(
                @NonNull String field,
                List<String> values
        ) {
            this.field = field;
            this.values = values;
        }

        public String getParam(){

            return this.field.trim() + "[contains]";
        }

        public String getParamValue(){
            return StringHelper.join(values, ",");
        }
    }

    public static class Infilter implements IQueryParameter {

        private String field;
        private List<String> values;

        public Infilter(
                @NonNull String field,
                List<String> values
        ) {
            this.field = field;
            this.values = values;
        }

        public String getParam(){

            return this.field.trim() + "[in]";
        }

        public String getParamValue(){
            return StringHelper.join(values, ",");
        }
    }


    public static class GreaterThanFilter implements IQueryParameter {

        private String field;
        private String value;

        public GreaterThanFilter(
                @NonNull String field,
                String value
        ) {
            this.field = field;
            this.value = value;
        }

        public String getParam(){

            return this.field.trim() + "[gt]";
        }

        public String getParamValue(){
            return this.value.trim();
        }
    }

    public static class LessThanFilter implements IQueryParameter {

        private String field;
        private String value;

        public LessThanFilter(
                @NonNull String field,
                String value
        ) {
            this.field = field;
            this.value = value;
        }

        public String getParam(){

            return this.field.trim() + "[lt]";
        }

        public String getParamValue(){
            return this.value.trim();
        }
    }

    public static class LessThanOrEqualFilter implements IQueryParameter {

        private String field;
        private String value;

        public LessThanOrEqualFilter(
                @NonNull String field,
                String value
        ) {
            this.field = field;
            this.value = value;
        }

        public String getParam(){

            return this.field.trim() + "[lte]";
        }

        public String getParamValue(){
            return this.value.trim();
        }
    }
    public static class GreaterThanOrEqualFilter implements IQueryParameter {

        private String field;
        private String value;

        public GreaterThanOrEqualFilter(
                @NonNull String field,
                String value
        ) {
            this.field = field;
            this.value = value;
        }

        public String getParam(){

            return this.field.trim() + "[gte]";
        }

        public String getParamValue(){
            return this.value.trim();
        }
    }
    public static class RangeFilter implements IQueryParameter {

        private String field;
        private int lowerValue;
        private int higherValue;

        public RangeFilter(
                @NonNull String field,
                int lowerValue,
                int higherValue
        ) {
            this.field = field;
            this.lowerValue = lowerValue;
            this.higherValue = higherValue;
        }

        public String getParam(){

            return this.field.trim() + "[range]";
        }

        public String getParamValue(){
            return lowerValue + "," + higherValue;
        }
    }
}
