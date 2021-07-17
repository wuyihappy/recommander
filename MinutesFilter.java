package com.company;

public class MinutesFilter implements Filter {
	private int minMin;
	private int maxMin;

	public MinutesFilter(int min, int max) {
		minMin = min;
		maxMin = max;
	}
	
	@Override
	public boolean satisfies(String id)
	{
		int movieMin = MovieDatabase.getMinutes(id);

		return movieMin >= minMin && movieMin <= maxMin;
	}

}
