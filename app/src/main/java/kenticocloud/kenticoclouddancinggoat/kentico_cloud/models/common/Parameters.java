package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common;

import android.support.annotation.NonNull;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IQueryParameter;

/**
 * Created by RichardS on 17. 8. 2017.
 */

public class Parameters {

    public static class LimitParameter implements IQueryParameter {

        private int _limit;

        public LimitParameter(
                int limit
        ) {
            _limit = limit;
        }

        public String getParam(){

            return "limit";
        }

        public String getParamValue(){
            return Integer.toString(this._limit);
        }
    }

    public static class SkipParameter implements IQueryParameter {

        private int _skip;

        public SkipParameter(
                int skip
        ) {
            _skip = skip;
        }

        public String getParam(){

            return "skip";
        }

        public String getParamValue(){
            return Integer.toString(this._skip);
        }
    }

    public static class OrderParameter implements IQueryParameter {

        private String _field;
        private OrderType _orderType;

        public OrderParameter(
                @NonNull String field,
                @NonNull OrderType type
        ) {
            _orderType = type;
            _field = field;
        }

        public String getParam(){
            return "order";
        }

        public String getParamValue(){
            return _field + "[" + _orderType.toString() + "]";
        }
    }

    public static class DepthParameter implements IQueryParameter{

        private int _depth;

        public DepthParameter(
                int depth
        ) {
            _depth = depth;
        }

        public String getParam(){

            return "depth";
        }

        public String getParamValue(){
            return Integer.toString(this._depth);
        }
    }

    public static class LanguageParameter implements IQueryParameter {

        private String _language;

        public LanguageParameter(
                @NonNull String language
        ) {
            _language = language;
        }

        public String getParam(){

            return "language";
        }

        public String getParamValue() {
            return _language;
        }
    }

    public enum OrderType {
        Asc("asc"),
        Desc("desc");
        private final String text;

        /**
         * @param text text of enum
         */
        private OrderType(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return text;
        }
    }
}
