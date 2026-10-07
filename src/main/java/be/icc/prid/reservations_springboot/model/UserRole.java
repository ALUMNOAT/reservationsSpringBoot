package be.icc.prid.reservations_springboot.model;

public enum UserRole {
    ADMIN("Administrateur"),
    MEMBER("Membre"),
    AFFILIATE("Affilie"),
    PRESS("Critique de presse"),
    PRODUCER("Producteur");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
