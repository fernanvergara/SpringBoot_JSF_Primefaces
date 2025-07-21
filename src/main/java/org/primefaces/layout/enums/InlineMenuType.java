package org.primefaces.layout.enums;

public enum InlineMenuType {

    TOP("top"),
    BOTTOM("bottom"),
    BOTH("both");

    private String toString;

    InlineMenuType() {
    }

    InlineMenuType(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return ((this.toString != null) ? this.toString : super.toString());
    }

    public static InlineMenuType toType(String type) {
        switch(type.toLowerCase()) {
            case "top":
                return InlineMenuType.TOP;
            case "bottom":
                return InlineMenuType.BOTTOM;
            case "both":
                return InlineMenuType.BOTH;
        }

        return null;
    }
}
