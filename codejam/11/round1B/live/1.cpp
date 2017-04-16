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
		int n;
		cin >> n;
		char table[n][n];
		int sums[n];
		int counts[n];
		
		double wp[n];
		double owp[n][n];
		double myOwp[n];
		double oowp[n];
		double result[n];
		
		char c;
		FI(n)
		{
			FJ(n)
				cin >> table[i][j];
		}
		
		// FI(n)
		// {
			// FJ(n)
				// cout << table[i][j];
			// cout << endl;
		// }
		
		FI(n)
		{
			double sum = 0;
			double count = 0;
			FJ(n)
			{	
				if(table[i][j] == '1') ++sum;
				if(table[i][j] != '.') ++count;
			}
			
			wp[i] = sum/count;
			// cout << "team " << i << " sum " << sum << " count " << count << " wp " << wp[i] << endl;
			FJ(n)
			{
				owp[i][j] = (table[i][j] == '1' ? sum-1 : sum)/(table[i][j] == '.' ? count : count-1 );
			}
		}
		
		FI(n)
		{
			double sum=0;
			double count = 0;
			FJ(n)
			{
				if(table[i][j] != '.')
				{
					sum += owp[j][i];
					++count;
				}
			}
			myOwp[i] = sum / count;
		}
			
		FI(n)
		{
			double sum=0;
			double count = 0;
			FJ(n)
			{
				if(table[i][j] != '.')
				{
					sum += myOwp[j];
					++count;
				}
			}
			oowp[i] = sum / count;
			
			// cout << "team " << i << " wp " << wp[i] << " owp " << myOwp[i] << " oowp " << oowp[i] << endl;
			result[i] = 0.25 * wp[i] + 0.5*myOwp[i] + 0.25 * oowp[i];
		}
		
		
		// long n,k;
		// cin >> n >> k;
		// long t = 1 << n;
		
		cout << "Case #" << case_num+1 << ": " << endl;
		FI(n)
		{
			cout << result[i] << endl;
		}
		//cout << result << endl;
	}
	return 0;
}

