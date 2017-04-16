// yapocet.cpp : Defines the entry point for the console application.
//

#include <fstream>
#include <utility>
#include <vector>
#include <iostream>
#include <iomanip>
#include <string>
#include <set>
#include <cmath>
#include <iterator>


using namespace std;

//#typedef ember vector<pair<int,int>>

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
//	double d;
//	d = 3 + sqrtl(5);
	for(int i=1;i<=cases;++i)
	{
//!!!here comes the code
//	long n;
//	input >> n;
//	long double xp = powl(d,n);
//	long long l = xp;
//	int tr = long long(xp) % 1000;
	int types;
	input >> types;
	vector<int> booltomb;
	for(int j=0;j<types;++j)
	{
		booltomb.push_back(1);
	}
	int cust;
	input >> cust;

	vector<vector<pair<int,int>>> people;

	for(int j=0;j<cust;++j)
	{
		vector<pair<int,int>> p;
		int num;
		input >> num;
		for(int o=0;o<num;++o)
		{
			int melyik, milyen;
			input >> melyik;
			input >> milyen;
			p.push_back(pair<int,int> (melyik,milyen));
		}
		people.push_back(p);
	}

	bool ready = false;
	while(!ready)
	{
		ready = true;
		for each(vector<pair<int,int>> elem in people)
		{
			if(elem.size() == 1 && elem.at(0).second == 1)
			{
				if(booltomb.at(elem.at(0).first-1) == 1) ready = false;
				booltomb.at(elem.at(0).first-1) = 0;
			}
		};
		vector<vector<pair<int,int>>>::iterator elem = people.begin();
		while(elem<people.end())
		{
			vector<pair<int,int>>::iterator it=(*elem).begin();
			while(it<(*elem).end())
			{
				if(booltomb.at((*it).first-1)==0 && (*it).second == 0)
				{
					(*elem).erase(it);
					it = (*elem).begin();
				}else{
					++it;
				};
					
			};
			++elem;
		};

	};
	bool imp = false;
	for each(vector<pair<int,int>> elem in people)
	{
		if(elem.size() == 0)
		{
			imp = true;
		}
	};
	if(imp)
	{
		output << "Case #" << i << ": IMPOSSIBLE" << endl;
	}else{
		output << "Case #" << i << ": ";
		for(int u=0;u<booltomb.size();++u)
		{
			output << 1 - booltomb.at(u) << " ";
		};
		output << endl;
	};

//!!!
		
	}
	return 0;
}

//<< setw(3) << setfill('0') << tr 