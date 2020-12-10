package Models;

public class TradeOffer {

    Player tradingPlayer;
    int monetaryContribution;
    Deed[] propertyContribution;

    public TradeOffer(Player tradingPlayer, int monetaryContribution, Deed[] propertyContribution) {
        this.tradingPlayer = tradingPlayer;
        this.monetaryContribution = monetaryContribution;
        this.propertyContribution = propertyContribution;
    }
}
