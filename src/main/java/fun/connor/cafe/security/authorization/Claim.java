package fun.connor.cafe.security.authorization;

public class Claim {
    private final ResourceIdentifier appliesTo;

    public Claim(final ResourceIdentifier appliesTo) {
        this.appliesTo = appliesTo;
    }

    public ResourceIdentifier getAppliesTo() {
        return appliesTo;
    }

}
