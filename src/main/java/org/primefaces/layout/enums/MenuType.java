package org.primefaces.layout.enums;

public enum MenuType {

    STATIC("static"),
    OVERLAY("overlay"),
    HORIZONTAL("horizontal"),
    SLIM("slim");

    private String toString;

    MenuType() {
    }

    MenuType(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return ((this.toString != null) ? this.toString : super.toString());
    }

    public static MenuType toType(String type) {
        switch(type.toLowerCase()) {
            case "static":
                return MenuType.STATIC;
            case "overlay":
                return MenuType.OVERLAY;
            case "horizontal":
                return MenuType.HORIZONTAL;
            case "slim":
                return MenuType.SLIM;
        }

        return null;
    }
}