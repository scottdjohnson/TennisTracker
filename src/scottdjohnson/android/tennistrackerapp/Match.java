package scottdjohnson.android.tennistrackerapp;

import java.util.*;

public class Match {

	private List sets;
	private Set currentSet;
	private Player a, b;
	private TennisTrackerActivity app;
	
	private int maxSets;
	
	public Match(Player a, Player b, TennisTrackerActivity app)
	{
		sets = new ArrayList();
		currentSet = new Set(a,b,app,0);
		sets.add(currentSet);
		
		this.a = a;
		this.b = b;
		this.app = app;
		
		this.maxSets = 3;
	}
	
	public void newSet(Player wonSet)
	{
		if (sets.size() == maxSets)
			app.matchOver(wonSet);
		else
		{
			currentSet = new Set(a,b,app,sets.size());
			sets.add(currentSet);
		}
	}
	
	public void point(Player wonPoint, Player lostPoint, Point p)
	{
		currentSet.point(wonPoint,lostPoint, p);

		if ( currentSet.isSetOver() )
		{
			currentSet.wonSet(wonPoint, lostPoint);
			newSet(wonPoint);
		}
	}

	public void getStats(StatisticsCollection sc)
	{
		for (int i = 0; i < sets.size(); i++)
		{
			((Set)sets.get(i)).getStats(sc);
		}		
	}
}
