// codejam1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>

using namespace std;


int _tmain(int argc, _TCHAR* argv[])
{
	int cases;
	cin >> cases;
	for(int i = 0; i<cases;++i)
	{
		long n,k;
		cin >> n >> k;
		long t = 1 << n;
		cout << "Case #" << i+1 << ": ";
		cout << ( (k+1)%t ? "OFF" : "ON") << endl;
	}
	return 0;
}

