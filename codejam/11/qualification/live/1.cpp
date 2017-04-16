// codejam1.cpp : Defines the entry point for the console application.
//

//#include "stdafx.h"
#include <cmath>
#include <iostream>

#define ABS(x) ((x<0) ? (-(x)) : (x))
using namespace std;


int main(int argc, char* argv[])
{
	int cases;
	cin >> cases;
	for(int i = 0; i<cases;++i)
	{
		int n;
		int btime = 0;
		int bpos = 1;
		int otime = 0;
		int opos = 1;
		cin >> n;
		char robot;
		int button;
		for(int j = 0; j < n; ++j)
		{
			cin >> robot >> button;
			// cout << robot << ":" << button << endl;
			switch(robot)
			{
			case 'O':	otime = max( max(otime,btime)+1, otime+ABS(button-opos)+1);
						opos = button;
						break;
			case 'B':	btime = max( max(otime,btime)+1, btime+ABS(button-bpos)+1);
						bpos = button;
						break;
			}
			// cout << "opos:" << opos << "otime:" << otime << "bpos:" << bpos << "btime:" << btime << endl;
		}
		
		// cout << endl;
		// long t = 1 << n;
		
		
		cout << "Case #" << i+1 << ": ";
		cout << max(otime,btime) << endl;
	}
	return 0;
}

