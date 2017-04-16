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
		long R,n,k;
		cin >> R >> k >> n;
		long* g = (long*)malloc(n*sizeof(long));
		for(int o=0;o<n;++o) cin >> g[o];
		long* jump = (long*)malloc(n*sizeof(long));
		long* sum = (long*)malloc(n*sizeof(long));
		for(int o=0;o<n;++o) sum[o] = 0;
		for(int o=0;o<n;++o) jump[o] = 0;

		long money = 0;
		int front = 0;
		for(int j = 0; j < R; ++j)
		{
			if(sum[front])
			{
				money+=sum[front];
				front=jump[front];
			}else{
				long tmpsum=0;
				int tmpindex = front;
				while(tmpsum+g[tmpindex] < k+1)
				{
					tmpsum += g[tmpindex];
					tmpindex++;
					if(tmpindex>=n) tmpindex=0;
					if(tmpindex==front) break;
				}
				sum[front]=tmpsum;
				money+=tmpsum;
				jump[front]=tmpindex;
				front=tmpindex;

			}
		}
		cout << "Case #" << i+1 << ": " << money << endl;
		delete g;
		delete sum;
		delete jump;
	}
	return 0;
}

