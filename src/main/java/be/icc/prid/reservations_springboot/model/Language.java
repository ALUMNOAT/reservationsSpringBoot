package be.icc.prid.reservations_springboot.model;

public enum Language {
    EN("Anglais"),
    FR("Francais"),
    NL("Neerlandais");

    private String description;

    private Language(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
