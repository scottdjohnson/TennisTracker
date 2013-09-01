package scottdjohnson.android.tennistrackerapp;

public class Player {
	String name;
	int points;
	int games;
	int sets;
	
	/*
	 * 	This should all go away and be replaced by a linked list of points, where each point gives
	 *  server, how point finished, aces & double faults, etc.
	 */
	
	int winners;
	int errors;
	
	int netPoints;
	int netPointsWon;
	int breakPointOpportunities;
	int breakPointsWon;
	
	int aces;
	int doubleFaults;
	int firstServePoints;
	int fristServePointsWon;
	int secondServePoints;
	int secondServePointsWon;
	
	public Player(String name)
	{
		this.name = name;
		points = 0;
		games = 0;
		sets = 0;
		winners = 0;
		errors = 0;
	}

	public void addPoint()
	{
		points++;
	}

	public void subtractPoint()
	{
		points--;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public void addGame()
	{
		games++;
	}
	
	public int getGames()
	{
		return games;
	}
	
	public String getGameScore()
	{
		String score;
		
		switch(points)
		{
			case 1:
				score = "15";
				break;
			case 2:
				score = "30";
				break;
			case 3:
				score = "40";
				break;
			case 4:
				score = "AD";
				break;
			default:
				score = "0";
				break;
		}
		
		return score;
	}
	
	public void addSet()
	{
		sets++;
	}

	public int getSets()
	{
		return sets;
	}
	
	public void newGame()
	{
		points = 0;
	}
	
	public void newSet()
	{
		games = 0;
	}
	
	public void addWinner()
	{
		winners++;
	}
	
	public int getWinners()
	{
		return winners;
	}
	
	public void addError()
	{
		errors++;
	}
	
	public int getErrors()
	{
		return errors;
	}
	
	public String getName()
	{
		return name;
	}
}
