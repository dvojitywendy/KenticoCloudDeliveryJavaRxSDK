package com.kenticocloud.delivery.models.common;



import com.kenticocloud.delivery.interfaces.item.common.IQueryParameter;
import com.kenticocloud.delivery.utils.StringHelper;

import java.util.List;

/**
 * URL parameter
 */

public class Parameters {

    public static class ElementsParameter implements IQueryParameter {

        private List<String> elements;

        public ElementsParameter(
                 List<String> elements
        ) {
            this.elements = elements;
        }

        public String getParam(){

            return "elements";
        }

        public String getParamValue(){
            return StringHelper.join(this.elements, ",");
        }
    }

    public static class LimitParameter implements IQueryParameter {

        private int limit;

        public LimitParameter(
                int limit
        ) {
            this.limit = limit;
        }

        public String getParam(){

            return "limit";
        }

        public String getParamValue(){
            return Integer.toString(this.limit);
        }
    }

    public static class SkipParameter implements IQueryParameter {

        private int skip;

        public SkipParameter(
                int skip
        ) {
            this.skip = skip;
        }

        public String getParam(){

            return "skip";
        }

        public String getParamValue(){
            return Integer.toString(this.skip);
        }
    }

    public static class OrderParameter implements IQueryParameter {

        private String field;
        private OrderType orderType;

        public OrderParameter(
                 String field,
                 OrderType type
        ) {
            orderType = type;
            this.field = field;
        }

        public String getParam(){
            return "order";
        }

        public String getParamValue(){
            return field + "[" + orderType.toString() + "]";
        }
    }

    public static class DepthParameter implements IQueryParameter{

        private int depth;

        public DepthParameter(
                int depth
        ) {
            this.depth = depth;
        }

        public String getParam(){

            return "depth";
        }

        public String getParamValue(){
            return Integer.toString(this.depth);
        }
    }

    public static class LanguageParameter implements IQueryParameter {

        private String language;

        public LanguageParameter(
                 String language
        ) {
            this.language = language;
        }

        public String getParam(){

            return "language";
        }

        public String getParamValue() {
            return this.language;
        }
    }
}
