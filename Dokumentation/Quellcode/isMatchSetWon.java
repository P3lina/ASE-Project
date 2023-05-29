public IsWon isMatchSetWon(List<T> games, Function<T, Player> getWinner, int gameCount, int playerCount) {
        IsWon isWon = new IsWon();
        Map<Player, Integer> playerAndPlayerWinsWithMostGamesWon = getPlayerAndWinsOfPlayerWithMostGamesWon(games, getWinner);
        Player currentWinner = (Player) playerAndPlayerWinsWithMostGamesWon.keySet().toArray()[0];
        int currentWinnerGamesWon = playerAndPlayerWinsWithMostGamesWon.get(currentWinner);
        int gamesNeedToWin = gameCount / playerCount + 1;
        if (currentWinnerGamesWon < gamesNeedToWin) {
            return isWon;
        }
        isWon.setPlayer(currentWinner);
        return isWon;
    }