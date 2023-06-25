package site.woulduduo.enumeration;

public enum Tier {
    UNR, IRO, BRO, SIL, GOL, PLA, DIA, MAS, GRA, CHA;

    public String toString() {
        String tier = name();
        switch (tier) {
            case "CHA":
                return "Challenger";
            case "IRO":
                return "Iron";
            case "BRO":
                return "Bronze";
            case "SIL":
                return "Silver";
            case "GOL":
                return "Gold";
            case "PLA":
                return "Platinum";
            case "DIA":
                return "Diamond";
            case "MAS":
                return "Master";
            case "GRA":
                return "Grandmaster";
            default:
                return "Unranked";
        }
    }
}
