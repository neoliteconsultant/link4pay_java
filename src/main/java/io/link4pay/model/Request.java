package io.link4pay.model;

import java.util.Map;

/**
 * Abstract class for fluent interface request builders.
 */
public abstract class Request {
    public String toJSON() {
        throw new UnsupportedOperationException();
    }

    /*
    public String toQueryString(String parent) {
        throw new UnsupportedOperationException();
    }

    public String toQueryString() {
        throw new UnsupportedOperationException();
    }

    public Map<String, Object> toGraphQLVariables() {
        throw new UnsupportedOperationException();
    }

    public String getKind() {
        return null;
    }

    protected String buildXMLElement(Object element) {
        return RequestBuilder.buildXMLElement(element);
    }

    protected String buildXMLElement(String name, Object element) {
        return RequestBuilder.buildXMLElement(name, element);
    }*/
}
