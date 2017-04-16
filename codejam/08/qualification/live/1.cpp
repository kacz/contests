// yapocet.cpp : Defines the entry point for the console application.
//

#include <fstream>
#include <utility>
#include <vector>
#include <iostream>
#include <iomanip>
#include <string>
#include <set>

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
	for(int i=1;i<=cases;++i)
	{
		int switches = 0;
		int s;
		vector<string> engines;
		input >> s;
		input >> ws;
		for(int j=1;j<=s;++j)			//load the engines
		{
			char c;
			string eng;
			input >> noskipws >> c;
			while(c!='\n')
			{
				eng+=c;
				input >> noskipws >> c;
			}
			engines.push_back(eng);
		}
		set<string> avaible;
		for(int m=0;m<engines.size();++m)
		{
			avaible.insert(engines.at(m));
		}
		int q;
		input >> q;
		input >> ws;
		for(int k=1;k<=q;++k)
		{
			char c;
			string query;
			input >> noskipws >> c;
			while(c!='\n' && !input.fail())
			{
				query+=c;
				input >> noskipws >> c;
			};
			if(avaible.find(query)!=avaible.end())
			{
				avaible.erase(avaible.find(query));
			};
			if(avaible.size() == 0)
			{
				++switches;
				for(int m=0;m<engines.size();++m)
				{
					avaible.insert(engines.at(m));
				}
			}
			if(avaible.find(query)!=avaible.end())
			{
				avaible.erase(avaible.find(query));
			};
		}
		output << "Case #" << i << ": " << switches << endl;
	}
	return 0;
}

