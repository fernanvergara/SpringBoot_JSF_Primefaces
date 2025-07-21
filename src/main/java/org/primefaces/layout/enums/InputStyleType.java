package org.primefaces.layout.enums;

public enum InputStyleType {

    OUTLINED("outlined"),
    FILLED("filled");

    private String toString;

    InputStyleType() {
    }

    InputStyleType(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return ((this.toString != null) ? this.toString : super.toString());
    }

    public static InputStyleType toType(String type) {
        switch(type.toLowerCase()) {
            case "outlined":
                return InputStyleType.OUTLINED;
            case "filled":
                return InputStyleType.FILLED;
        }

        return null;
    }
}
