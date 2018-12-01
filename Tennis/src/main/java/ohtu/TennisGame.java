package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;
    private final static String[] SCORES = {"Love", "Fifteen", "Thirty", "Forty"};
    private final static int WINMARGIN = 2;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score += 1;
        else
            player2Score += 1;
    }

    private boolean player1Wins() {
        return (player1Score >= SCORES.length && player1Score - player2Score >= WINMARGIN);
    }
    
    private boolean player1Advantage() {
        return (player1Score >= SCORES.length && player1Score - player2Score == 1);
    }
    
    private boolean player2Wins() {
        return (player2Score >= SCORES.length && player2Score - player1Score >= WINMARGIN);
    }
    
    private boolean player2Advantage() {
        return (player2Score >= SCORES.length && player2Score - player1Score == 1);
    }
    
    private boolean deuce() {
        return (player1Score >= SCORES.length && player1Score == player2Score);
    }
    
    public String getScore() {
        if (player1Wins() || player2Wins()) {
            return "Win for " + (player1Score > player2Score ? player1Name : player2Name);
        }
        if (player1Advantage() || player2Advantage()) {
            return "Advantage " + (player1Score > player2Score ? player1Name : player2Name);
        }
        if (deuce()) {
            return "Deuce";
        }
        if (player1Score == player2Score) {
            return SCORES[player1Score] + "-All";
        }
        return SCORES[player1Score] + "-" + SCORES[player2Score];
    }
    
}