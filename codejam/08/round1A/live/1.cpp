// yapocet.cpp : Defines the entry point for the console application.
//

#include <fstream>
#include <utility>
#include <vector>
#include <iostream>
#include <iomanip>
#include <string>
#include <list>

using namespace std;



int main(int argc, char * argv[])
{
	int cases;
	ifstream input;
	input.open( "in" );
	if(!(input.is_open())){
		cerr << input.is_open();
		return 1;
	};
	ofstream output;
	output.open( "out", ios_base::out);
	if(!(output.is_open())){
		cerr << output.is_open();
		return 1;
	};
	input >> cases;
	input >> ws;
	for(int k=1;k<=cases;++k)
	{
//!!!here comes the code
		int n;
		input >> n;
		list<int> vect1, vect2;
		for(int i=0;i<n;++i)
		{
			int x;
			input >> x;
			vect1.push_back(x);
		}
		for(int i=0;i<n;++i)
		{
			int x;
			input >> x;
			vect2.push_back(x);
		}
		vect1.sort();
		vect2.sort();
		long product = 0;
		for(int i=0;i<n;++i)
		{
			list <int>::iterator vect1iter = vect1.begin();
			list <int>::iterator vect2iter = vect2.end();
			vect2iter--;

			product += *(vect1iter) * *(vect2iter);
			vect1.pop_front();
			vect2.pop_back();
		}
//!!!
		output << "Case #" << k << ": " << product << endl;
	}
	return 0;
}

