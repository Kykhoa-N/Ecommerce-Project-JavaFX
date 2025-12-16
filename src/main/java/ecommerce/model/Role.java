package ecommerce.model;

public enum Role {
    ADMIN("Administrator"),
    CLIENT("Customer");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
