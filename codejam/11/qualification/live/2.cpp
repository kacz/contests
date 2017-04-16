// codejam1.cpp : Defines the entry point for the console application.
//

//#include "stdafx.h"
#include <assert.h>
#include <iostream>

#define indQ 0
#define indW 1
#define indE 2
#define indR 3
#define indA 4
#define indS 5
#define indD 6
#define indF 7
#define _IND(c) (c=='Q' ? indQ : \
					(c=='W' ? indW : \
					(c=='E' ? indE : \
					(c=='R' ? indR : \
					(c=='A' ? indA : \
					(c=='S' ? indS : \
					(c=='D' ? indD : \
					(c=='F' ? indF : 8))))))))
				
#define IND(c) (assert(0<=_IND(c) && 7>=_IND(c)),_IND(c))				
#define IND2(c) (assert(0<=_IND(c) && 8>=_IND(c)),_IND(c))				
#define BIT(c) (1 << IND(c))
using namespace std;

int count_bitmap(char* q, int len)
{
	int res = 0;
	for(int i = 0; i < len; ++i)
	{
		if(IND2(q[i]) != 8)
			res |= BIT(q[i]);
	}
	return res;
}


int main(int argc, char* argv[])
{
	int cases;
	cin >> cases;
	for(int i = 0; i<cases;++i)
	{
		// cout << "case #" << i+1 << endl;
		
		char combtable[8][8];
		for(int j = 0;j<8;++j)
			for(int k = 0;k<8;++k)
			{
				combtable[j][k]='-';
				combtable[k][j]='-';
			}
		
		int opptable[8];
		for(int j = 0;j<8;++j)
			opptable[j] = 0;
		
		char queue[100];
		for(int j = 0;j<100;++j)
			queue[j] = 0;
		
		int comb, opp;
		cin >> comb;
		for(int j = 0; j < comb;++j)
		{
			char c1,c2,c3;
			cin >> c1 >> c2 >> c3;
			combtable[IND(c1)][IND(c2)] = c3;
			combtable[IND(c2)][IND(c1)] = c3;
			// cout << c1 << "+" << c2 << "=" << c3 << endl;
		}
		
		cin >> opp;
		for(int j = 0; j < opp; ++j)
		{
			char c1,c2;
			cin >> c1 >> c2;
			opptable[IND(c1)] |= BIT(c2);
			opptable[IND(c2)] |= BIT(c1);
			// cout << c1 << " <> " << c2 << endl;
		}
		
		int len;
		
		cin >> len;
		int currlen = 0;
		int bitmap = 0;
		
		for(int j = 0; j < len; ++j)
		{
			char c1;
			cin >> c1;
			// cout << c1 << " ";
			
			if((currlen > 0) && (IND2(queue[currlen-1]) != 8) && (combtable[IND(queue[currlen-1])][IND(c1)] != '-')) // can we combine?
			{
				// cout << " comb " << endl;
				//bitmap ^= BIT(queue[currlen-1]);
				queue[currlen-1] = combtable[IND(queue[currlen-1])][IND(c1)];
				bitmap = count_bitmap(queue,currlen);
				// cout << "queue [";
				// for(int d = 0; d < currlen; ++d)
				// {
					// cout << queue[d] << " ";
				// }
				
				// cout << "]" << endl;
				continue;
			}
			
			if(bitmap & opptable[IND(c1)])		//is there opposite?
			{
				// cout << " opp " << endl;
				bitmap = 0;
				currlen = 0;
				// cout << "queue [";
				// for(int d = 0; d < currlen; ++d)
				// {
					// cout << queue[d] << " ";
				// }
				// cout << "]" << endl;
				continue;
			}
			
			
			
			// cout << " add" << endl;
			
			//lets just add to queue
			bitmap |= BIT(c1);
			queue[currlen] = c1;
			currlen++;
			// cout << "queue [";
			// for(int d = 0; d < currlen; ++d)
			// {
				// cout << queue[d] << " ";
			// }
			// cout << "]" << endl;
			
		}
		//
		
		// long n,k;
		// cin >> n >> k;
		// long t = 1 << n;
		
		bool space = false;
		cout << "Case #" << i+1 << ": ";
		cout << "[";
		for(int j = 0; j < currlen; ++j)
		{
			if(space)
				cout << ", ";
			cout << queue[j];
			space = true;
		}
		cout << "]" << endl;
		// cout << endl;
	}
	return 0;
}

