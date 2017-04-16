// codejam1.cpp : Defines the entry point for the console application.
//

//#include "stdafx.h"
#include <iostream>

using namespace std;


int main(int argc, char* argv[])
{
	int cases;
	cin >> cases;
	//cout << "5+4=" << (5^4) << endl;
	//cout << "7+9=" << (7^9) << endl;
	//cout << "50+10=" << (50^10) << endl;
	
	for(int i = 0; i<cases;++i)
	{
		int n;
		cin >> n;
		
		int min = 0;
		long sum = 0;
		int x = 0;
		
		for(int j = 0; j < n; ++j)
		{
			int num;
			cin >> num;
			x ^= num;
			sum += num;
			min = (min==0 ? num : ( num<min ? num : min ));
		}
		// long n,k;
		// cin >> n >> k;
		// long t = 1 << n;
		
		
		
		cout << "Case #" << i+1 << ": ";
		if(x)
		{
			cout << "NO" << endl;
		}
		else
		{
			cout << sum - min << endl;
		}
	}
	return 0;
}

