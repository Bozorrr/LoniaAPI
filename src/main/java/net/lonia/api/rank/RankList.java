package net.lonia.api.rank;

public class RankList {

    public static final Rank FONDATEUR    = new Rank("Fondateur", "§4Fondateur ", "§4", 10);
    public static final Rank DEVELOPPEUR  = new Rank("Développeur", "§4Développeur ", "§4", 11);
    public static final Rank RESPONSSABLE = new Rank("Responsable", "§cResponsable ", "§c", 12);
    public static final Rank MODERATEUR   = new Rank("Modérateur", "§cModérateur ", "§c", 13);
    public static final Rank CONSTRUCTEUR = new Rank("Constructeur", "§cConstructeur ", "§c", 14);
    public static final Rank ANIMATEUR    = new Rank("Animateur", "§cAnimateur ", "§c", 15);
    public static final Rank YOUTUBEUR    = new Rank("Youtubeur", "§6Youtubeur ", "§6", 16);
    public static final Rank VIPPP        = new Rank("VIP++", "§5VIP++ ", "§5", 17);
    public static final Rank VIPP         = new Rank("VIP+", "§aVIP+ ", "§a", 18);
    public static final Rank VIP          = new Rank("VIP", "§eVIP ", "§e", 29);
    public static final Rank JOUEUR       = new Rank("Joueur", "§7Joueur ", "§7", 20);


    public void createRanks() {

        FONDATEUR.createRank();
        DEVELOPPEUR.createRank();
        RESPONSSABLE.createRank();
        MODERATEUR.createRank();
        CONSTRUCTEUR.createRank();
        ANIMATEUR.createRank();
        YOUTUBEUR.createRank();
        VIPPP.createRank();
        VIPP.createRank();
        VIP.createRank();
        JOUEUR.createRank();
    }


    public void removeRanks() {

        FONDATEUR.delete();
        DEVELOPPEUR.delete();
        RESPONSSABLE.delete();
        MODERATEUR.delete();
        CONSTRUCTEUR.delete();
        ANIMATEUR.delete();
        YOUTUBEUR.delete();
        VIPPP.delete();
        VIPP.delete();
        VIP.delete();
        JOUEUR.delete();
    }
}


