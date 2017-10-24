package delivery.models.common;



import delivery.interfaces.item.common.IQueryParameter;
import delivery.utils.StringHelper;

import java.util.List;


/**
 * Represents URL filter used for querying items
 */
public class Filters {

    public static class EqualsFilter implements IQueryParameter {

        private String field;
        private String value;

        public EqualsFilter(
                 String field,
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
                 String field,
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
                 String field,
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
                 String field,
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
                 String field,
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
                 String field,
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
                 String field,
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
                 String field,
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
                 String field,
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
                 String field,
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
