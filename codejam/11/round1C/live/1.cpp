// codejam1.cpp : Defines the entry point for the console application.
//

//#include "stdafx.h"
#include <iostream>

#define FOR(i,a,b) for(int i=(a);i<(b);++i)

#define FI(n) FOR(i,0,n)
#define FJ(n) FOR(j,0,n)
#define FK(n) FOR(k,0,n)



using namespace std;


int main(int argc, char* argv[])
{
	int cases;
	cin >> cases;
	for(int case_num = 0; case_num<cases;++case_num)
	{
		cout << "Case #" << case_num+1 << ": " << endl;
		
		int r,c;
		
		cin >> r >> c;
		
		char table[r][c];
		
		FI(r)
		{
			FJ(c)
			{
				cin >> table[i][j];
			}
		}
		
		bool possible = true;
		FI(r)
		{
			FJ(c)
			{
				if(table[i][j] == '#')
				{
					if((i == r-1) || (j == c-1))
					{
						possible = false;
					}else if( (table[i][j+1] != '#') || (table[i+1][j+1] != '#') || (table[i+1][j] != '#') )
					{
						possible = false;
					}else
					{
					table[i][j] = '/';
					table[i][j+1] = '\\';
					table[i+1][j+1] = '/';
					table[i+1][j] = '\\';
					}
					if(!possible) break;
				}
			}
			if(!possible) break;
		}
		
		if(!possible)
		{
			cout << "Impossible" << endl;
		} else
		{
			FI(r)
			{
				FJ(c)
				{
					cout << table[i][j];
				}
				cout << endl;
			}
		}
		//solve here..
		
		// long n,k;
		// cin >> n >> k;
		// long t = 1 << n;
		
		// cout << result << endl;
	}
	return 0;
}

