package org.primefaces.layout.enums;

public enum LayoutType {

    LIGHT("light"),
    DARK("dark");

    private String toString;

    LayoutType() {
    }

    LayoutType(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return ((this.toString != null) ? this.toString : super.toString());
    }

    public static LayoutType toType(String type) {
        switch(type.toLowerCase()) {
            case "light":
                return LayoutType.LIGHT;
            case "dark":
                return LayoutType.DARK;
        }

        return null;
    }
}
